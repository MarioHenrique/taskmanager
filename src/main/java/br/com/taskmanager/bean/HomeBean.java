package br.com.taskmanager.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.taskmanager.entity.Tarefa;
import br.com.taskmanager.service.TarefaService;

@Named
@ViewScoped
public class HomeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TarefaService tarefaService;

	private List<Tarefa> listaDeTarefas;
	
	private String filtro = "TODOS";

	@Inject
	private FacesContext context;

	public List<Tarefa> getListaTarefas() {
		if (listaDeTarefas == null) {
			listaDeTarefas = tarefaService.listaTarefas();
		}
		
		return listaDeTarefas.stream().filter(s-> s.getDiasDaSemana().contains(filtro) || filtro.equals("TODOS")).collect(Collectors.toList());
	}

	public String signOut() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login.xhtml?faces-redirect=true";
	}

	public void removerTarefa(Tarefa tarefa) {
		tarefaService.remover(tarefa);
		listaDeTarefas = tarefaService.listaTarefas();
	}

	public Integer getHorasDeEstudo() {
		return listaDeTarefas.stream().filter(s-> s.getDiasDaSemana().contains(filtro) || filtro.equals("TODOS")).map(s -> s.getDuracao()).reduce(0, (s1, s2) -> s1 + s2);
	}

	public String formEditar(Integer id){
		return "cadastroTarefa.xhtml?faces-redirect=true&amp;tarefaId="+id;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
}
