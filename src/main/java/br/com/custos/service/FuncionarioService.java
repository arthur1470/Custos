package br.com.custos.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.custos.dao.FuncionarioDAO;
import br.com.custos.model.Funcionario;
import br.com.custos.util.jpa.Transactional;

@SessionScoped
public class FuncionarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	@Transactional
	public void guardar(Funcionario funcionario) {
		
		funcionarioDAO.guardar(funcionario);
	}
	
}
