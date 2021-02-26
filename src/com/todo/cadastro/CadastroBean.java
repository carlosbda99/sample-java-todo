package com.todo.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import com.todo.tarefa.Tarefa;

@SuppressWarnings("deprecation")
@ManagedBean
@ApplicationScoped
public class CadastroBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Tarefa> tarefas;
	private List<Tarefa> tarefasPendentes;
	private List<Tarefa> tarefasFiltradas;
	private Tarefa tarefaSelecionada;
	private Tarefa tarefa;
	private Integer auxiliar;
	
	private Integer numeroFiltro;
	private String tituloDescrFiltro;
	private String responsavelFiltro;
	private String situacaoFiltro;
	
	public CadastroBean () {
		this.tarefa = new Tarefa();
		this.tarefas = new ArrayList<Tarefa>();
		this.tarefasPendentes = new ArrayList<Tarefa>();
		this.tarefasFiltradas = new ArrayList<Tarefa>();
	}

	public Integer getNumeroFiltro() {
		return numeroFiltro;
	}

	public void setNumeroFiltro(Integer numeroFiltro) {
		this.numeroFiltro = numeroFiltro;
	}

	public String getSituacaoFiltro() {
		return situacaoFiltro;
	}

	public void setSituacaoFiltro(String situacaoFiltro) {
		this.situacaoFiltro = situacaoFiltro;
	}

	public List<Tarefa> getTarefasPendentes() {
		return tarefasPendentes;
	}

	public List<Tarefa> getTarefasFiltradas() {
		return tarefasFiltradas;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}
	
	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}
	
	public Tarefa getTarefaSelecionada() {
		return tarefaSelecionada;
	}

	public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
		this.tarefaSelecionada = tarefaSelecionada;
	}

	public Integer getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(Integer auxiliar) {
		this.auxiliar = auxiliar;
	}
	
	public void setResponsavelFiltro(String responsavelFiltro) {
		this.responsavelFiltro = responsavelFiltro;
	}
	
	public String getResponsavelFiltro() {
		return responsavelFiltro;
	}
	
	public String getTituloDescrFiltro() {
		return tituloDescrFiltro;
	}

	public void setTituloDescrFiltro(String tituloDescrFiltro) {
		this.tituloDescrFiltro = tituloDescrFiltro;
	}
	
	public Integer getIndex(Tarefa item) {
		return (this.tarefas.indexOf(item)+1);
	}
	
	public void concluir() {
		this.auxiliar = this.tarefas.indexOf(this.tarefaSelecionada);
		this.tarefaSelecionada.setSituacao("Concluída");
		this.tarefas.set(this.auxiliar, this.tarefaSelecionada);
		this.tarefasPendentes.remove(this.tarefaSelecionada);
//		this.tarefasFiltradas.remove(this.tarefaSelecionada);
	}
	
	public void excluir() {
		this.auxiliar = this.tarefas.indexOf(this.tarefaSelecionada);
		this.tarefaSelecionada.setSituacao("Excluída");
		this.tarefas.set(this.auxiliar, this.tarefaSelecionada);
		this.tarefasPendentes.remove(this.tarefaSelecionada);
		this.tarefasFiltradas.remove(this.tarefaSelecionada);
	}

	public void incluir() {
		if ("".equals(this.tarefa.getTitulo())) {
			this.tarefa.setTitulo("Tarefa " + (this.tarefas.size()+1));
		}
		if ("".equals(this.tarefa.getResponsavel())) {
			this.tarefa.setResponsavel("Não informado");
		}
		if ("".equals(this.tarefa.getDescricao())) {
			this.tarefa.setDescricao("Não informado");
		}
		if ("".equals(this.tarefa.getPrioridade())) {
			this.tarefa.setPrioridade("Alta");
		}
		this.tarefa.setSituacao("Pendente");
		this.tarefas.add(this.tarefa);
		this.tarefasPendentes.add(this.tarefa);
		this.tarefa = new Tarefa();
		this.tarefasFiltradas.clear();
		this.responsavelFiltro = "";
	}
	
	public void filtrar() {
		this.tarefasFiltradas.clear();
		for (Tarefa item: this.tarefas) {
//			if (this.tarefas.indexOf(item) == this.numeroFiltro ||
			if (( item.getTitulo().startsWith(tituloDescrFiltro) || item.getDescricao().startsWith(tituloDescrFiltro ))
					&& item.getResponsavel().startsWith(this.responsavelFiltro)
					&& item.getSituacao().startsWith(this.situacaoFiltro))
				{
				this.tarefasFiltradas.add(item);
			}
			
		}
	}
	
}
