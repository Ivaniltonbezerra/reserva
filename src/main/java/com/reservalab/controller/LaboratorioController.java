package com.reservalab.controller;

import javax.servlet.http.HttpServletRequest;

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

import com.reservalab.filter.LaboratorioFilter;
import com.reservalab.model.Laboratorio;
import com.reservalab.repository.LaboratorioRepository;
import com.reservalab.service.LaboratorioService;

import com.reservalab.repository.CursoRepository;
import com.reservalab.model.PageWrapper;

@Controller
@RequestMapping("/laboratorio/")
public class LaboratorioController {

	@Autowired
	private LaboratorioService laboratorioService;
	
	@Autowired
	private LaboratorioRepository laboratorioRepository;

	private String msgDeleteSucesso = "Laboratório deletado com sucesso!";

	private String msgDeleteError = "Erro ao deletar Laboratorio!";

	private String msgSucessoCriacao = "Laboratório criado com sucesso!";
	
	@Autowired private CursoRepository cursofkRepository;

	@GetMapping("/create")
	public ModelAndView create(Laboratorio laboratorio) {
		ModelAndView mv = new ModelAndView("laboratorio/create");
		if (laboratorio.getId() == null) {
			mv.addObject("title", "Criar laboratório");
			mv.addObject("btn", "Criar");
		} else {
			mv.addObject("title", "Editar laboratório");
			mv.addObject("btn", "Editar");
		}
		mv.addObject(laboratorio);
	
		
		mv.addObject("cursofkList",cursofkRepository.findAll());

		return mv;
	}

	@PostMapping("/create")
	public ModelAndView save(@Validated Laboratorio laboratorio, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return create(laboratorio);
		}

		laboratorioService.save(laboratorio);
		attributes.addFlashAttribute("message", msgSucessoCriacao);
		return new ModelAndView("redirect:/laboratorio/create");

	}

	@GetMapping("/{code}")
	public ModelAndView edit(@PathVariable("code") Integer code) {
		Laboratorio laboratorio = new Laboratorio();
		laboratorio = laboratorioRepository.findOne(code);

		return create(laboratorio);

	}

	@GetMapping("/list")
	public ModelAndView search(LaboratorioFilter laboratorioFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		System.out.println(laboratorioService);
		ModelAndView mv = new ModelAndView("laboratorio/list");
		mv.addObject("pagina", new PageWrapper<>(laboratorioService.filter(laboratorioFilter, pageable),httpServletRequest));
        
		mv.addObject("cursofkList",cursofkRepository.findAll());
		return mv;
	}

	@DeleteMapping("/delete/{code}")
	public ModelAndView delete(@PathVariable("code") Integer code, RedirectAttributes attributes) {
		System.out.println(code);
		Laboratorio laboratorio = new Laboratorio();
		laboratorio.setId(code);
		try {
			laboratorioService.delete(laboratorio);
			attributes.addFlashAttribute("message", msgDeleteSucesso);
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("messageErro", msgDeleteError);
		}

		return new ModelAndView("redirect:/laboratorio/list");
	}

}
