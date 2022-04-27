package br.edu.ifpe.cipa.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import br.edu.ifpe.cipa.dao.PessoaDao;
import br.edu.ifpe.cipa.model.Pessoa;
import br.edu.ifpe.cipa.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class ControlePessoa {
	
	PessoaService pessoaservice = new PessoaService();
	
//	PessoaDao pessoadao = new PessoaDao();
	
	@GetMapping("")
	public List<Pessoa> list(){
		
		
		return pessoaservice.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> consultarPessoaPorId(@PathVariable Integer id){
		try {
			return new ResponseEntity<Pessoa>(pessoaservice.listar().get(id), HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	public void add(@RequestBody Pessoa pessoa) {
		pessoaservice.inserir(pessoa);
	}
	
	@DeleteMapping("/r/{id}")
	public void remove(@PathVariable Integer id) {
		pessoaservice.remover(id);
	}
	
	@PutMapping("/u")
	public void update(@RequestBody Pessoa pessoa) {
		pessoaservice.alterar(pessoa);
	}


}
