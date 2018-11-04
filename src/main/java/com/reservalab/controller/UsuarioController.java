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

import com.reservalab.filter.UsuarioFilter;
import com.reservalab.model.Usuario;
import com.reservalab.repository.UsuarioRepository;
import com.reservalab.service.UsuarioService;

import com.reservalab.repository.CursoRepository;
import com.reservalab.model.PageWrapper;

@Controller
@RequestMapping("/usuario/")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	private String msgDeleteSucesso = "Usuario deletado com successo!";

	private String msgDeleteError = "Erro ao deletar Usuario!";

	private String msgSucessoCriacao = "Usuario criado com successo!";
	
	@Autowired private CursoRepository cursofkRepository;

	@GetMapping("/create")
	public ModelAndView create(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/create");
		if (usuario.getId() == null) {
			mv.addObject("title", "Criar usuário");
			mv.addObject("btn", "Criar");
		} else {
			mv.addObject("title", "Editar usuário");
			mv.addObject("btn", "Editar");
		}
		mv.addObject(usuario);
	
		
		mv.addObject("cursofkList",cursofkRepository.findAll());

		return mv;
	}

	@PostMapping("/create")
	public ModelAndView save(@Validated Usuario usuario, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return create(usuario);
		}

		usuarioService.save(usuario);
		attributes.addFlashAttribute("message", msgSucessoCriacao);
		return new ModelAndView("redirect:/usuario/create");

	}

	@GetMapping("/{code}")
	public ModelAndView edit(@PathVariable("code") Integer code) {
		Usuario usuario = new Usuario();
		usuario = usuarioRepository.findOne(code);

		return create(usuario);

	}

	@GetMapping("/list")
	public ModelAndView search(UsuarioFilter usuarioFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		System.out.println(usuarioService);
		ModelAndView mv = new ModelAndView("usuario/list");
		mv.addObject("pagina", new PageWrapper<>(usuarioService.filter(usuarioFilter, pageable),httpServletRequest));
        
		mv.addObject("cursofkList",cursofkRepository.findAll());
		return mv;
	}

	@DeleteMapping("/delete/{code}")
	public ModelAndView delete(@PathVariable("code") Integer code, RedirectAttributes attributes) {
		System.out.println(code);
		Usuario usuario = new Usuario();
		usuario.setId(code);
		try {
			usuarioService.delete(usuario);
			attributes.addFlashAttribute("message", msgDeleteSucesso);
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("messageErro", msgDeleteError);
		}

		return new ModelAndView("redirect:/usuario/list");
	}

}
