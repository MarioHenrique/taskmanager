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

import br.com.taskmanager.entity.Usuario;

@Stateless
public class UsuarioDao {

	@PersistenceContext(unitName = "taskmanager")
	EntityManager manager;

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void criarUsuario(Usuario usuario) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		manager.persist(usuario);
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public Usuario procuraUsuarioPeloEmail(String email) {
		TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u where u.email = :pEmail",
				Usuario.class);
		query.setParameter("pEmail", email);
		return getResult(query);
	}

	public Usuario procuraUsuarioPorEmailESenha(Usuario usuario) {
		TypedQuery<Usuario> query = manager
				.createQuery("select u from Usuario u where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		return getResult(query);
	}

	public Usuario getResult(TypedQuery<Usuario> query) {
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
