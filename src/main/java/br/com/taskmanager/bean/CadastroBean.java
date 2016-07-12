package br.com.taskmanager.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import br.com.taskmanager.dto.NovoUsuario;
import br.com.taskmanager.exception.UsuarioExistenteException;
import br.com.taskmanager.service.UsuarioService;

@Named
@ViewScoped
public class CadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(CadastroBean.class);

	@Inject
	FacesContext context;

	@Inject
	UsuarioService usuarioService;

	@Inject
	NovoUsuario novoUsuario;

	public String cadastrar() {
		if (!novoUsuario.getSenha().equals(novoUsuario.getConfirmaSenha())) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas",
					"A senha e a confirmação precisa ser igual"));
			return null;
		}

		try {
			usuarioService.criarUsuario(novoUsuario);
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Usuario criado com sucesso"));

			return "login.xhtml?faces-redirect=true";

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario", "Não foi possivel criar o usuario"));
			LOGGER.error("Erro na criação de um usuario", e);
		} catch (UsuarioExistenteException e) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario", "Email já cadastrado no sistema"));
			LOGGER.info(e.getMessage()+", Email = "+novoUsuario.getEmail());
		}

		return null;
	}

	public NovoUsuario getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(NovoUsuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}

}
