package br.com.taskmanager.service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.taskmanager.dao.TarefaDao;
import br.com.taskmanager.entity.Tarefa;
import br.com.taskmanager.entity.Usuario;

@Stateless
public class TarefaService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private TarefaDao tarefaDao;
	
	@Inject 
	private FacesContext context;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void adiciona(Tarefa tarefa){
		Usuario usuario = (Usuario)context.getExternalContext().getSessionMap().get("usuarioLogado");
		tarefa.setUsuario(usuario);
		tarefaDao.adiciona(tarefa);
	}

	public List<Tarefa> listaTarefas() {
		Usuario usuario = (Usuario)context.getExternalContext().getSessionMap().get("usuarioLogado");
		return Collections.unmodifiableList(tarefaDao.listaTarefas(usuario.getId()));
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(Tarefa tarefa) {
		tarefaDao.remover(tarefa);
	}

	public Tarefa buscar(Long id) {
		return tarefaDao.buscar(id);
	}

	public void editar(Tarefa tarefa) {
		tarefaDao.editar(tarefa);
	}
	
	
}
