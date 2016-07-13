package br.com.taskmanager.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.taskmanager.entity.Tarefa;

@Stateless
public class TarefaDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "taskmanager")
	EntityManager manager;
	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void adiciona(Tarefa tarefa){
		manager.persist(tarefa);
	}

	public List<Tarefa> listaTarefas(Long id) {
		TypedQuery<Tarefa> query = manager.createQuery("select t from Tarefa t where t.usuario.id = :pId",Tarefa.class);
		query.setParameter("pId",id);
		return query.getResultList();
	}

	public void remover(Tarefa tarefa) {
		Tarefa tarefaManaged = manager.merge(tarefa);
		manager.remove(tarefaManaged);
	}

	public Tarefa buscar(Long id) {
		return manager.find(Tarefa.class,id);
	}

	public void editar(Tarefa tarefa) {
		manager.merge(tarefa);
	}
	
}
