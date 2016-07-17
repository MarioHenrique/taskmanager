package br.com.taskmanager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.taskmanager.entity.Tarefa;
import br.com.taskmanager.service.TarefaService;
import br.com.taskmanager.util.DiasDaSemanaUtil;
import br.com.taskmanager.vo.Dia;
import br.com.taskmanager.vo.DiaHora;

@Named
@ViewScoped
public class HomeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private TarefaService tarefaService;

	private List<Tarefa> listaDeTarefas;

	private List<DiaHora> diaHora;

	private Tarefa tarefaDetalhes = new Tarefa();

	private Integer horasTotais;

	private String filtro = "TODOS";

	private Integer indexDia;

	@Inject
	private FacesContext context;

	@PostConstruct
	public void iniciaDiaDaSemana() {
		int dia = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		indexDia = dia;
		filtro = DiasDaSemanaUtil.getDiasDaSemana().get(dia - 1).getValue();
	}

	public List<Tarefa> getListaTarefas() {
		if (listaDeTarefas == null) {
			listaDeTarefas = tarefaService.listaTarefas();
		}

		return listaDeTarefas.stream().filter(s -> s.getDiasDaSemana().contains(filtro) || filtro.equals("TODOS"))
				.collect(Collectors.toList());
	}

	public List<Dia> getDiasDaSemana() {
		return DiasDaSemanaUtil.getDiasDaSemana();
	}

	public String signOut() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login.xhtml?faces-redirect=true";
	}

	public void removerTarefa(Tarefa tarefa) {
		tarefaService.remover(tarefa);
		listaDeTarefas = tarefaService.listaTarefas();
		diaHora = null;
		horasTotais = null;
	}

	public Integer getHorasTotais() {
		if (horasTotais == null)
			horasTotais = listaDeTarefas.stream().map(s -> s.getDiasDaSemana().size() * s.getDuracao()).reduce(0,
					(s1, s2) -> s1 + s2);
		return horasTotais;
	}

	public String formEditar(Integer id) {
		return "cadastroTarefa.xhtml?faces-redirect=true&amp;tarefaId=" + id;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void setFiltro(String filtro, Integer indexDia) {
		this.filtro = filtro;
		this.indexDia = indexDia;
	}

	public void setarTarefa(Tarefa tarefa) {
		this.tarefaDetalhes = tarefa;
	}

	public Tarefa getTarefaDetalhes() {
		return tarefaDetalhes;
	}

	public void setTarefaDetalhes(Tarefa tarefaDetalhes) {
		this.tarefaDetalhes = tarefaDetalhes;
	}

	public List<DiaHora> getDiaHora() {

		if (diaHora == null) {
			diaHora = new ArrayList<>();
			for (Dia dia : DiasDaSemanaUtil.getDiasDaSemana()) {
				int horas = listaDeTarefas.stream().filter(s -> s.getDiasDaSemana().contains(dia.getValue()))
						.mapToInt(s1 -> s1.getDuracao()).reduce(0, (s1, s2) -> s1 + s2);
				diaHora.add(new DiaHora(dia.getLabel(), horas));
			}
		}

		return diaHora;
	}

	public Integer getIndexDia() {
		return indexDia;
	}

	public void setIndexDia(Integer indexDia) {
		this.indexDia = indexDia;
	}

}
