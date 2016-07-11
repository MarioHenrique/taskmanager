package br.com.taskmanager.dto;

import java.io.Serializable;

public class NovoUsuario extends Usuario implements Serializable{

	public NovoUsuario() {
		super();
	}

	private static final long serialVersionUID = 1L;

	private String confirmaSenha;

	@Override
	public String toString() {
		return "NovoUsuario [confirmaSenha=" + confirmaSenha + ", email=" + email + ", nome=" + nome + ", senha="
				+ senha + ", horario=" + horario + "]";
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	
	
}
