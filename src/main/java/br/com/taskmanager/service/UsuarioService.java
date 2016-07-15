package br.com.taskmanager.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.taskmanager.dao.UsuarioDao;
import br.com.taskmanager.entity.Usuario;
import br.com.taskmanager.exception.UsuarioExistenteException;
import br.com.taskmanager.util.PasswordEncodeUtil;

@Stateless
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private FacesContext context;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void criarUsuario(Usuario usuario)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, UsuarioExistenteException {
		boolean emailCadastrado = usuarioDao.procuraUsuarioPeloEmail(usuario.getEmail()) == null ? false : true;
		if (emailCadastrado) {
			throw new UsuarioExistenteException("Email já cadastrado no sistema");
		}
		usuario.setSenha(new PasswordEncodeUtil().encode(usuario.getSenha()));
		usuarioDao.criarUsuario(usuario);
	}

	public boolean login(Usuario usuario) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		usuario.setSenha(new PasswordEncodeUtil().encode(usuario.getSenha()));
		Usuario usuarioLogado = usuarioDao.procuraUsuarioPorEmailESenha(usuario);
		if (usuarioLogado == null) {
			return false;
		}

		context.getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogado);

		return true;
	}

}
