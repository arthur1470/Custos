package br.com.custos.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import br.com.custos.dao.CustoDAO;
import br.com.custos.model.Custo;
import br.com.custos.util.jpa.Transactional;

@SessionScoped
public class CustoService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CustoDAO custoDAO;
	
	@Transactional
	public void guardar(Custo custo) {
		custoDAO.guardar(custo);
	}
	
	@Transactional
	public void remover(Custo custo) {
		custoDAO.remover(custo);
	}
	
	public List<Custo> carregarTodosPendentes() {
		return custoDAO.carregarTodosPendentes();
	}
	
	public List<Custo> carregarTodosPagos() {
		return custoDAO.carregarTodosPagos();
	}
	
	public Double carregarCustoTotalPendentes() {
		Double custoTotalPendentes = 0.0;
		
		if(carregarTodosPendentes() != null) {
			for(Custo custo : carregarTodosPendentes()) {
				custoTotalPendentes += custo.getValor();
			}
		}
		
		return custoTotalPendentes;
	}
	
	public Double carregarCustoTotalPagos() {
		Double custoTotalPagos = 0.0;
		
		if(carregarTodosPagos() != null) {
			for(Custo custo : carregarTodosPagos()) {
				custoTotalPagos += custo.getValor();
			}
		}
		
		return custoTotalPagos;
	}
}
