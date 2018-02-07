package br.com.custos.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.custos.model.Custo;

public class CustoDAO extends DAO{
	private static final long serialVersionUID = 1L;

	public List<Custo> carregarTodos(){
		Query query = criarQuery("SELECT c FROM Custo c");
		return query.getResultList();
	}
	
	public List<Custo> carregarTodosPendentes(){
		Query query = criarQuery("SELECT c FROM Custo c WHERE c.pago = false");
		return query.getResultList();
	}
	
	public List<Custo> carregarTodosPagos(){
		Query query = criarQuery("SELECT c FROM Custo c WHERE c.pago = true");
		return query.getResultList();
	}	
}
