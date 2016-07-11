package br.com.taskmanager.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.taskmanager.dto.NovoUsuario;

@Named
@ViewScoped
public class CadastroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject 
	FacesContext context;
	
	@Inject
	NovoUsuario novoUsuario;

	public void cadastrar() {
		if (!novoUsuario.getSenha().equals(novoUsuario.getConfirmaSenha())) {
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senhas","A senha e a confirmação precisa ser igual"));
			return;
		}
		System.out.println(getNovoUsuario());
	}

	public NovoUsuario getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(NovoUsuario novoUsuario) {
		this.novoUsuario = novoUsuario;
	}

}
