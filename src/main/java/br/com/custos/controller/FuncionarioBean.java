package br.com.custos.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.custos.model.Funcionario;
import br.com.custos.model.Salario;
import br.com.custos.service.FuncionarioService;

@Named("funcionarioBean")
@SessionScoped
public class FuncionarioBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	private Date dataMaxima;
	
	@Inject
	private FuncionarioService funcionarioService;

	public void guardar() {
		funcionarioService.guardar(this.funcionario);
		this.funcionario = new Funcionario();
	}
	
	//Util
	
	public void preparar() {
		this.funcionario = new Funcionario();
		this.funcionario.setSalario(new Salario());
	}
	
	//Getters and Setters
	
	public Funcionario getFuncionario() {		
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getDataMaxima() {
		LocalDate localDataMaxima = LocalDate.now().minusYears(18);
		this.dataMaxima = Date.from(localDataMaxima.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return dataMaxima;
	}

	public void setDataMaxima(Date dataMaxima) {
		this.dataMaxima = dataMaxima;
	}

}
