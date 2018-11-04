package com.reservalab.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.reservalab.filter.CursoFilter;
import com.reservalab.model.Curso;
import com.reservalab.repository.CursoRepository;
import com.reservalab.service.CursoService;


import com.reservalab.model.PageWrapper;

@Controller
@RequestMapping("/curso/")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private CursoRepository cursoRepository;

	private String msgDeleteSucesso = "Curso deletado com sucesso!";

	private String msgDeleteError = "Erro ao deletar Curso!";

	private String msgSucessoCriacao = "Curso criado com sucesso!";
	
	

	@GetMapping("/create")
	public ModelAndView create(Curso curso) {
		String key = UUID.randomUUID().toString();
		curso.setHashDescricao(key);
		curso.setHashDescricao(BCrypt.hashpw(key, BCrypt.gensalt()));
		
		
		ModelAndView mv = new ModelAndView("curso/create");
		if (curso.getId() == null) {
			mv.addObject("title", "Criar Curso");
			mv.addObject("btn", "Criar");
		} else {
			mv.addObject("title", "Editar Curso");
			mv.addObject("btn", "Editar");
		}
		mv.addObject(curso);
	
		
		

		return mv;
	}

	@PostMapping("/create")
	public ModelAndView save(@Validated Curso curso, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return create(curso);
		}

		cursoService.save(curso);
		attributes.addFlashAttribute("message", msgSucessoCriacao);
		return new ModelAndView("redirect:/curso/create");

	}

	@GetMapping("/{code}")
	public ModelAndView edit(@PathVariable("code") Integer code) {
		Curso curso = new Curso();
		curso = cursoRepository.findOne(code);

		return create(curso);

	}

	@GetMapping("/list")
	public ModelAndView search(CursoFilter cursoFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		System.out.println(cursoService);
		ModelAndView mv = new ModelAndView("curso/list");
		mv.addObject("pagina", new PageWrapper<>(cursoService.filter(cursoFilter, pageable),httpServletRequest));
        
		
		return mv;
	}

	@DeleteMapping("/delete/{code}")
	public ModelAndView delete(@PathVariable("code") Integer code, RedirectAttributes attributes) {
		System.out.println(code);
		Curso curso = new Curso();
		curso.setId(code);
		try {
			cursoService.delete(curso);
			attributes.addFlashAttribute("message", msgDeleteSucesso);
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("messageErro", msgDeleteError);
		}

		return new ModelAndView("redirect:/curso/list");
	}

}
