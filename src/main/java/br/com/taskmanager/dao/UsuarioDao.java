package br.com.taskmanager.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.taskmanager.builders.UsuarioEntityBuilder;
import br.com.taskmanager.dto.NovoUsuario;
import br.com.taskmanager.dto.Usuario;
import br.com.taskmanager.entity.UsuarioEntity;

@Stateless
public class UsuarioDao {

	@PersistenceContext(unitName = "taskmanager")
	EntityManager manager;

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void criarUsuario(NovoUsuario novoUsuario) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		UsuarioEntity usuario = UsuarioEntityBuilder.build(novoUsuario);
		manager.persist(usuario);
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public UsuarioEntity procuraUsuarioPeloEmail(String email) {
		TypedQuery<UsuarioEntity> query = manager.createQuery("select u from UsuarioEntity u where u.email = :pEmail",
				UsuarioEntity.class);
		query.setParameter("pEmail", email);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
