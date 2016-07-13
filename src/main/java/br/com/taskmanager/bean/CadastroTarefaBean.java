
package br.com.taskmanager.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.taskmanager.entity.Tarefa;
import br.com.taskmanager.service.TarefaService;

@Named
@ViewScoped
public class CadastroTarefaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tarefa tarefa;

	private Long id;

	@Inject
	private FacesContext context;

	@Inject
	private TarefaService tarefaService;

	private String diaSemana;

	public void adicionarDia() {
		getTarefa().adicionarDia(diaSemana);
	}

	public void removerDia(String dia) {
		getTarefa().removerDia(dia);
	}

	public String salvarTarefa() {
		if (getTarefa().getDiasDaSemana().size() == 0) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dias", "É obrigatorio o cadastro de um dia"));
			return null;
		}

		context.getExternalContext().getFlash().setKeepMessages(true);
		if (id == null) {
			tarefaService.adiciona(getTarefa());
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "tarefa", "Tarefa adicionada com sucesso"));
		} else {
			tarefaService.editar(getTarefa());
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "tarefa", "Tarefa editada com sucesso"));
		}
		return "home.xhtml?faces-redirect=true";
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public void carregarTarefaPeloId() {
		tarefa = tarefaService.buscar(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
