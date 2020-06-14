package controller;

import java.util.ArrayList;

import model.bo.UsuarioBO;
import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioController {

	UsuarioDAO usuarioDAO = new UsuarioDAO();
	UsuarioBO usuarioBO = new UsuarioBO();
	
	public String conectarUsuario(String login, String senha) {
		String mensagem = "";
		
		if(login.isEmpty()) {
			mensagem = "Login em branco.";
		} else if (senha.isEmpty()) {
			mensagem = "Senha em branco.";
		}
		
		if (mensagem.isEmpty()) {

			UsuarioVO consultaUsuario = new UsuarioVO(null, null, login, senha);
			
			mensagem = UsuarioBO.consultar(consultaUsuario);
		}
		
		return mensagem;
	}

	public ArrayList<UsuarioVO> listarUsusarios(String parametroBusca) {
		return usuarioDAO.consultarUsuario(parametroBusca);
	}

	public String cadastrarUsuario(String nome, String email, String login, String senha) {
		String mensagem = "";
		
		if(nome.isEmpty()) {
			mensagem = "Nome em branco.";
		} else if (email.isEmpty()) {
			mensagem = "Email em branco.";
		} else if (login.isEmpty()) {
			mensagem = "Login em branco.";
		} else if (senha.isEmpty()) {
			mensagem = "Senha em branco.";
		}
		
		if (mensagem.isEmpty()) {

			UsuarioVO novoUsuario = new UsuarioVO(nome, email, login, senha);
			
			mensagem = usuarioBO.cadastrarUsusario(novoUsuario);
		}
		
		return mensagem;
	}

}
