package controller;

import model.bo.UsuarioBO;
import model.vo.UsuarioVO;

public class UsuarioController {

	public String conectarUsuario(String login, String senha) {
		String mensagem = "";
		
		if(login.isEmpty()) {
			mensagem = "Login em branco.";
		} else if (senha.isEmpty()) {
			mensagem = "Senha em branco.";
		}
		
		if (mensagem.isEmpty()) {

			UsuarioVO consultaUsuario = new UsuarioVO(login, senha);
			
			mensagem = UsuarioBO.consultar(consultaUsuario);
		}
		
		return mensagem;
	}

}
