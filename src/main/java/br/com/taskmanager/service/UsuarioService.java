package br.com.taskmanager.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.taskmanager.dao.UsuarioDao;
import br.com.taskmanager.dto.NovoUsuario;
import br.com.taskmanager.exception.UsuarioExistenteException;

@Stateless
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioDao usuarioDao;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void criarUsuario(NovoUsuario novoUsuario) throws NoSuchAlgorithmException, UnsupportedEncodingException, UsuarioExistenteException {
		boolean emailCadastrado = usuarioDao.procuraUsuarioPeloEmail(novoUsuario.getEmail()) == null ? false : true;
		if(emailCadastrado){
			throw new UsuarioExistenteException("Email já cadastrado no sistema");
		}
		usuarioDao.criarUsuario(novoUsuario);
	}

}
