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
	private Tarefa tarefa;
	private Tarefa tarefaSelecionada;
	private Integer auxiliar;

	public CadastroBean () {
		this.tarefa = new Tarefa();
		this.tarefas = new ArrayList<Tarefa>();
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
	
	public void verificarInclusao(ActionEvent event) {
		if ("".equals(this.tarefa.getResponsavel())) {
			this.tarefa.setResponsavel("Não informado");
		}
	}
	
	public void incluir() {
		this.tarefa.setSituacao("Pendente");
		this.tarefas.add(this.tarefa);
		this.tarefa = new Tarefa();
	}
	
	public void concluir() {
		this.auxiliar = this.tarefas.indexOf(this.tarefaSelecionada);
		this.tarefaSelecionada.setSituacao("Concluída");
		this.tarefas.set(this.auxiliar, this.tarefaSelecionada);
	}
	
	public void refazer() {
		this.auxiliar = this.tarefas.indexOf(this.tarefaSelecionada);
		this.tarefaSelecionada.setSituacao("Pendente");
		this.tarefas.set(this.auxiliar, this.tarefaSelecionada);
	}
	
	public void excluir() {
		this.tarefas.remove(this.tarefaSelecionada);
	}
	
	public String obterAjuda() {
		if (this.tarefas.isEmpty()) {
			return "ajuda?faces-redirect=true";
		} else {
			return "ajuda-avancada?faces-redirect=true";
		}
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
	
}
