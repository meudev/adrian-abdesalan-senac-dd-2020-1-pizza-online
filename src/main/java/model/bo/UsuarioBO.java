package model.bo;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {
	
	private static UsuarioDAO usuarioDAO = new UsuarioDAO();

	public static String consultar(UsuarioVO consultaUsuario) {
		String mensagem = "";
		
		consultaUsuario = usuarioDAO.buscar(consultaUsuario);
		
		if(consultaUsuario == null) {
			mensagem = "Erro no login ou senha.";
		}
		
		return mensagem;
	}

	public String cadastrarUsusario(UsuarioVO novoUsuario) {
		String mensagem = "";
		
		novoUsuario = usuarioDAO.cadastrarUsuario(novoUsuario);
			
		if(novoUsuario.getIdUsuario() > 0) {
			mensagem = "Usu�rio cadastro com sucesso.";
		}else {
			mensagem = "Erro ao cadastrar o usu�rio.";
		}
		
		return mensagem;
	}

}
