package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.UsuarioVO;

public class UsuarioDAO {

	public UsuarioVO buscar(UsuarioVO consultaUsuario) {
		
		String login = consultaUsuario.getEmail();
		String senha = consultaUsuario.getSenha();

		String sql = "SELECT * FROM usuario WHERE email = '"+ login +"' AND senha = '"+ senha+"'";
		
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
        
		UsuarioVO usuario = null;
		
		try {
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				
				usuario = construirUsuarioDoResultSet(rs);
				
			}

		} catch (SQLException e) {
			
			System.out.println("ruim");
			System.out.println("Erro ao consultar usuario com login: " + login);
			System.out.println("Erro: " + e.getMessage());
			
		}

		return usuario;
	}

	private UsuarioVO construirUsuarioDoResultSet(ResultSet rs) {
		
		UsuarioVO usuarioLogado = new UsuarioVO();
		
		try {
			usuarioLogado.setIdUsuario(rs.getInt("id"));
			usuarioLogado.setNome(rs.getString("nome"));
			usuarioLogado.setEmail(rs.getString("email"));
			usuarioLogado.setSenha(rs.getString("senha"));

		} catch (SQLException e) {
			
			System.out.println("Erro ao construir usuário a partir do ResultSet. Causa: " + e.getMessage());
			
		}
		
		return usuarioLogado;
	}
}
