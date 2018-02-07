package br.com.custos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.custos.model.Custo;
import br.com.custos.service.CustoService;
import br.com.custos.util.MensagensUtil;

@Named("custo")
@SessionScoped
public class CustoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Custo custo = new Custo();
	private List<Custo> custosPendentes = new ArrayList<>();
	private List<Custo> custosPagos = new ArrayList<>();
	
	@Inject
	private CustoService custoService;

	public void guardarCusto() {
		custoService.guardar(custo);
		custoNovo();
		atualizarLista();
		MensagensUtil.info("Dados guardados com sucesso!");
	}
	
	public void finalizarCusto(Custo custo) {
		custo.setPago(true);
		custoService.guardar(custo);
		atualizarLista();
	}
	
	public void removerCusto(Custo custo) {
		custoService.remover(custo);
		
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		atualizarLista();
	}
	
	public void prepararEditar(Custo custo) {
		this.custo = custo;
	}	
	
	// utils
	
	public void atualizarLista() {
		this.custosPendentes = custoService.carregarTodosPendentes();
		this.custosPagos = custoService.carregarTodosPagos();
	}
	
	public void custoNovo() {			
		this.custo = new Custo();
	}
	
	public void limparDialog() {		
		this.custo = new Custo();		
		MensagensUtil.info("Campos limpos com sucesso!");
	}
	
	//getters and setters
	
	public Custo getCusto() {
		return custo;
	}

	public void setCusto(Custo custo) {
		this.custo = custo;
	}

	public List<Custo> getCustosPendentes() {
		if(this.custosPendentes.isEmpty()) {
			atualizarLista();
		}
		return custosPendentes;
	}

	public Double getCustoTotalPendentes() {		
		return custoService.carregarCustoTotalPendentes();
	}

	public Double getCustoTotalPagos() {		
		return custoService.carregarCustoTotalPagos();
	}

	public List<Custo> getCustosPagos() {
		if(this.custosPagos.isEmpty()) {
			atualizarLista();
		}
		return custosPagos;
	}
}
