package br.com.taskmanager.builders;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.taskmanager.dto.NovoUsuario;
import br.com.taskmanager.entity.UsuarioEntity;

public class UsuarioEntityBuilder {

	public static UsuarioEntity build(NovoUsuario novoUsuario) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(novoUsuario.getSenha().getBytes("UTF-8"));
		 
		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
		  hexString.append(String.format("%02X", 0xFF & b));
		}
		
		String senha = hexString.toString();
		
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setEmail(novoUsuario.getEmail());
		usuario.setHorario(novoUsuario.getHorario());
		usuario.setNome(novoUsuario.getNome());
		usuario.setSenha(senha); 
		
		return usuario;
	}
	
}
