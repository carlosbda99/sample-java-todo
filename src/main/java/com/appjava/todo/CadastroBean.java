package com.appjava.todo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.PrimeFaces;

import com.appjava.tarefa.Tarefa;

@ManagedBean
@ApplicationScoped
public class CadastroBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Tarefa> tarefas;
	private List<Tarefa> tarefasFiltradas;
	private Tarefa tarefaSelecionada;
	private Tarefa tarefa;
	private String validado;

	private String tituloDescrFiltro;
	private String responsavelFiltro;
	private String situacaoFiltro;
	private Boolean filtrado;
	private Conexao conn;

	public CadastroBean() {
		this.tarefa = new Tarefa();
		this.tarefas = new ArrayList<Tarefa>();
		this.tarefasFiltradas = new ArrayList<Tarefa>();
		this.conn = new Conexao();
	}

	public void resetFiltro() {
		this.tituloDescrFiltro = "";
		this.responsavelFiltro = "";
		this.situacaoFiltro = "";
		this.filtrado = false;
	}

	public Boolean getFiltrado() {
		return filtrado;
	}

	public String getSituacaoFiltro() {
		return situacaoFiltro;
	}

	public void setSituacaoFiltro(String situacaoFiltro) {
		this.situacaoFiltro = situacaoFiltro;
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

	public String getValidado() {
		return validado;
	}

	public void setValidado(String validado) {
		this.validado = validado;
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
		return (this.tarefas.indexOf(item) + 1);
	}

	public void filtrar() {
		this.tarefasFiltradas.clear();
		this.filtrado = false;
		this.tarefas = conn.getTarefas();
		for (Tarefa item : this.tarefas) {
			if ((item.getTitulo().startsWith(tituloDescrFiltro) || item.getDescricao().startsWith(tituloDescrFiltro))
					&& item.getResponsavel().startsWith(this.responsavelFiltro)
					&& item.getSituacao().startsWith(this.situacaoFiltro)) {
				this.tarefasFiltradas.add(item);
			}

		}
		if (tarefasFiltradas.size() > 0) {
			this.filtrado = true;
		} else {
			this.filtrado = false;
		}
	}

	public void limpar() {
		this.tarefasFiltradas.clear();
		this.validado = "";
		this.tarefa = new Tarefa();
		this.tarefas = new ArrayList<Tarefa>();
		this.tarefasFiltradas = new ArrayList<Tarefa>();
		this.resetFiltro();
	}

	public void incluirBanco() throws InterruptedException {
		if ("".equals(this.tarefa.getTitulo())) {
			this.validado = "Vazio";
		} else {	
			if ("".equals(this.tarefa.getResponsavel())) {
				this.tarefa.setResponsavel("Não informado");
			}
			if ("".equals(this.tarefa.getDescricao())) {
				this.tarefa.setDescricao("Não informado");
			}
			if ("".equals(this.tarefa.getPrioridade())) {
				this.tarefa.setPrioridade("Alta");
			}
			if (null == this.tarefa.getDeadline()) {
				Date now = new Date();
				Date tomorrow = new Date(now.getTime() + (1000 * 60 * 60 * 48));
				this.tarefa.setDeadline(tomorrow);
			}
			this.tarefa.setSituacao("Pendente");
			this.conn.saveTarefa(this.tarefa);
			this.limpar();
			FacesMessage msg = new FacesMessage (FacesMessage.SEVERITY_INFO, "Cadastro de tarefas", "Cadastrado com sucesso"); 
			PrimeFaces.current().dialog().showMessageDynamic(msg);
		}
	}

	public void concluirBanco() {
		this.tarefaSelecionada.setSituacao("Concluída");
		this.conn.updateTarefa(this.tarefaSelecionada);
	}

	public void excluirBanco() {
		this.tarefaSelecionada.setSituacao("Excluída");
		this.conn.deleteTarefa(this.tarefaSelecionada);
		this.tarefasFiltradas.remove(this.tarefaSelecionada);
	}
}