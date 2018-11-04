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

import com.reservalab.filter.ReservaFilter;
import com.reservalab.model.Reserva;
import com.reservalab.repository.ReservaRepository;
import com.reservalab.service.ReservaService;

import com.reservalab.repository.LaboratorioRepository;import com.reservalab.repository.UsuarioRepository;
import com.reservalab.model.PageWrapper;

@Controller
@RequestMapping("/reserva/")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private ReservaRepository reservaRepository;

	private String msgDeleteSucesso = "Reserva deletada com sucesso!";

	private String msgDeleteError = "Erro ao deletar Reserva!";

	private String msgSucessoCriacao = "Reserva criada com sucesso!";
	
	@Autowired private LaboratorioRepository laboratoriofkRepository;@Autowired private UsuarioRepository usuariofkRepository;

	@GetMapping("/create")
	public ModelAndView create(Reserva reserva) {
		ModelAndView mv = new ModelAndView("reserva/create");
		if (reserva.getId() == null) {
			mv.addObject("title", "Criar reserva");
			mv.addObject("btn", "Criar");
		} else {
			mv.addObject("title", "Editar reserva");
			mv.addObject("btn", "Editar");
		}
		mv.addObject(reserva);
	
		
		mv.addObject("laboratoriofkList",laboratoriofkRepository.findAll());mv.addObject("usuariofkList",usuariofkRepository.findAll());

		return mv;
	}

	@PostMapping("/create")
	public ModelAndView save(@Validated Reserva reserva, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return create(reserva);
		}

		reservaService.save(reserva);
		attributes.addFlashAttribute("message", msgSucessoCriacao);
		return new ModelAndView("redirect:/reserva/create");

	}

	@GetMapping("/{code}")
	public ModelAndView edit(@PathVariable("code") Integer code) {
		Reserva reserva = new Reserva();
		reserva = reservaRepository.findOne(code);

		return create(reserva);

	}

	@GetMapping("/list")
	public ModelAndView search(ReservaFilter reservaFilter, BindingResult result,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		System.out.println(reservaService);
		ModelAndView mv = new ModelAndView("reserva/list");
		mv.addObject("pagina", new PageWrapper<>(reservaService.filter(reservaFilter, pageable),httpServletRequest));
        
		mv.addObject("laboratoriofkList",laboratoriofkRepository.findAll());mv.addObject("usuariofkList",usuariofkRepository.findAll());
		return mv;
	}

	@DeleteMapping("/delete/{code}")
	public ModelAndView delete(@PathVariable("code") Integer code, RedirectAttributes attributes) {
		System.out.println(code);
		Reserva reserva = new Reserva();
		reserva.setId(code);
		try {
			reservaService.delete(reserva);
			attributes.addFlashAttribute("message", msgDeleteSucesso);
		} catch (Exception e) {
			e.printStackTrace();
			attributes.addFlashAttribute("messageErro", msgDeleteError);
		}

		return new ModelAndView("redirect:/reserva/list");
	}

}
