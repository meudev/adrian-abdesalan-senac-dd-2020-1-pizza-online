package model.bo;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {
	
	private static UsuarioDAO dao = new UsuarioDAO();

	public static String consultar(UsuarioVO consultaUsuario) {
		String mensagem = "";
		
		consultaUsuario = dao.buscar(consultaUsuario);
		
		if(consultaUsuario == null) {
			mensagem = "Erro no login ou senha.";
		}
		
		return mensagem;
	}

}
