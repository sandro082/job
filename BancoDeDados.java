package com.projeto.trabalho;



import com.projeto.trabalho.rh.dominio.PessoaRepository;
import com.projeto.trabalho.cidade.dominio.Cidade;
import com.projeto.trabalho.cidade.dominio.CidadeRepository;
import com.projeto.trabalho.rh.dominio.DepartamentoRepository;
import com.projeto.trabalho.rh.dominio.Departamento;
import com.projeto.trabalho.rh.dominio.Pessoa;

import jakarta.transaction.Transactional;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@Transactional
public class BancoDeDados implements CommandLineRunner {

	
	@Autowired
	private PessoaRepository pessoaRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private DepartamentoRepository departamentoRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Cidade cidade1 = new Cidade("Maceio", "AL");
		Cidade cidade2 = new Cidade("Rio", "RJ");
		Cidade cidade3 = new Cidade("São Paulo", "SP");
		Cidade cidade4 = new Cidade("Arapiraca", "AL");
		Cidade cidade5 = new Cidade("Messias", "AL");
		Cidade cidade6 = new Cidade("Aracaju", "SE");
		
		cidadeRepo.save(cidade1);
		cidadeRepo.save(cidade2);
		cidadeRepo.flush();
		
		Departamento departamento1 = new Departamento("Tecnologia da Informação", "TI");
		Departamento departamento2 = new Departamento("Recursos Humanos", "RH");
		Departamento departamento3 = new Departamento("Produção", "PROD");
		Departamento departamento4 = new Departamento("Comunicação", "ASCOM");
		Departamento departamento5 = new Departamento("Administração", "ADM");
		
		departamentoRepo.save(departamento1);
		departamentoRepo.save(departamento2);
		departamentoRepo.save(departamento3);
		
		departamentoRepo.flush();
		
		
		
		Pessoa p1 = new Pessoa("Joao");
		p1.setDataNascimento(LocalDate.of(1990, 4, 1));
		p1.setEmail("joao@gmail.com");
		p1.setCpf("10518516962");
		p1.setCidade(cidade1);
		p1.setDepartamento(departamento3);
		
		
		Pessoa p2 = new Pessoa("Maria");
		p2.setDataNascimento(LocalDate.of(1900, 1, 1));
		p2.setEmail("maria@gmail.com");
		p2.setCpf("10518516962");
		p2.setCidade(cidade1);
		p2.setDepartamento(departamento3);
		
		pessoaRepo.save(p1);
		pessoaRepo.save(p2);
	}
	
	
	
	
	
}
