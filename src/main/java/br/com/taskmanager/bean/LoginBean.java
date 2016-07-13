package br.com.taskmanager.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.taskmanager.entity.Usuario;
import br.com.taskmanager.service.UsuarioService;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;

	@Inject
	private UsuarioService usuarioSevice;

	@Inject
	private FacesContext context;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String login() {
		try {
			if (this.usuarioSevice.login(usuario)) {
				return "home.xhtml?faces-redirect=true";
			} else {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "usuario", "Email ou senha invalido"));
				return null;
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			context.addMessage(null, new FacesMessage("login", "Problema na autenticação"));
			return null;
		}
	}

}
