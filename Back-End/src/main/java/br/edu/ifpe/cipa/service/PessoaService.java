package br.edu.ifpe.cipa.service;

import java.util.List;

import br.edu.ifpe.cipa.dao.PessoaDao;
import br.edu.ifpe.cipa.model.Pessoa;

public class PessoaService {
	
	PessoaDao pessoadao = new PessoaDao();
	public List<Pessoa> listar() {
		return pessoadao.listar();
	}
	
	public void alterar(Pessoa pessoa) {
		pessoadao.alterar(pessoa);
	}
	
	public void inserir(Pessoa pessoa) {
		pessoadao.inserir(pessoa);
	}
	
	public void remover(int id) {
		pessoadao.remover(id);
	}
	
//	public Object listAllPessoa(){
//		
//		PessoaDao pessoadao = new PessoaDao();
//		
//		return pessoadao.listar();
//		
//	}

//	public void savePessoa(Pessoa pessoa) {
//		lista.add(pessoa);
//	}

}
