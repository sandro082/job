package com.projeto.trabalho.rh.controle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projeto.trabalho.cidade.dominio.CidadeRepository;
import com.projeto.trabalho.rh.dominio.DepartamentoRepository;
import com.projeto.trabalho.rh.dominio.Pessoa;
import com.projeto.trabalho.rh.dominio.PessoaRepository;

import jakarta.validation.Valid;

@Controller
public class PessoaControle {
	
	
	private PessoaRepository pessoaRepo;

	private CidadeRepository cidadeRepo;
	
	private DepartamentoRepository departamentoRepo;
	
	public PessoaControle(PessoaRepository pessoaRepo, CidadeRepository cidadeRepo, DepartamentoRepository departamentoRepo) {
		this.pessoaRepo = pessoaRepo;
		this.cidadeRepo = cidadeRepo;
		this.departamentoRepo = departamentoRepo;
	}
	
	
	

	@GetMapping("/rh/pessoas")
	public String pessoas(Model model) {
			model.addAttribute("listaPessoa", pessoaRepo.findAll());
	return "rh/pessoas/index";
	}
	
	@GetMapping("/rh/pessoas/nova")
	public String novaPessoa(Model model) {		
		model.addAttribute("pessoa", new Pessoa(""));
		model.addAttribute("cidades", cidadeRepo.findAll());
		model.addAttribute("departamentos", departamentoRepo.findAll());
		return "rh/pessoas/form";
		
	}
	
	@GetMapping("/rh/pessoas/{id}")
	public String alterarPessoa(@PathVariable("id") long id, Model model) {
		Optional<Pessoa> pessoaOpt = pessoaRepo.findById(id);
		if (pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida.");
		}
		
		model.addAttribute("pessoa", pessoaOpt.get());
		model.addAttribute("cidades", cidadeRepo.findAll());
		model.addAttribute("departamentos", departamentoRepo.findAll());
		return "rh/pessoas/form";
	}
	
	
	
	@PostMapping("/rh/pessoas/salvar")
	public String salvarPessoa(@Valid @ModelAttribute("pessoa") Pessoa pessoa, BindingResult bindingresult, Model model ) {
		if(bindingresult.hasErrors()) {
			model.addAttribute("cidades", cidadeRepo.findAll());
			model.addAttribute("departamentos", departamentoRepo.findAll());
			return "rh/pessoas/form";
		}
		
		pessoaRepo.save(pessoa);
		return "redirect:/rh/pessoas";
	}
	
	
	
	
	@GetMapping("/rh/pessoas/excluir/{id}")
	public String excluirPessoa(@PathVariable("id") long id) {
		Optional<Pessoa> pessoaOpt = pessoaRepo.findById(id);
		if (pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida.");
		}
		
		pessoaRepo.delete(pessoaOpt.get());
		return "redirect:/rh/pessoas";
	}
	
	
	
}





















