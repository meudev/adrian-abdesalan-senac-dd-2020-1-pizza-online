package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.UsuarioVO;

public class UsuarioDAO {

	public UsuarioVO buscar(UsuarioVO consultaUsuario) {
		
		String login = consultaUsuario.getLogin();
		String senha = consultaUsuario.getSenha();

		String sql = "SELECT * FROM usuario WHERE login = '"+ login +"' AND senha = '"+ senha+"'";
		
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
			usuarioLogado.setLogin(rs.getString("login"));
			usuarioLogado.setSenha(rs.getString("senha"));

		} catch (SQLException e) {
			
			System.out.println("Erro ao construir usuário a partir do ResultSet. Causa: " + e.getMessage());
			
		}
		
		return usuarioLogado;
	}

	public ArrayList<UsuarioVO> consultarUsuario(String parametroBusca) {
		String likeBusca = "%"+ parametroBusca + "%";
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM usuario WHERE id like '"+ likeBusca + "' ";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		ArrayList<UsuarioVO> ususarios = new ArrayList<UsuarioVO>();
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				UsuarioVO usuario = construirUsuarioDoResultSet(rs);
				ususarios.add(usuario);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar usuário.");
			System.out.println("Erro: " + e.getMessage());
		}

		return ususarios;
	}

	public UsuarioVO cadastrarUsuario(UsuarioVO novoUsuario) {
		Connection conexao = Banco.getConnection();

		String sql = " INSERT INTO usuario (nome, email, login, senha) " + " VALUES (?,?,?,?)";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			
			stmt.setString(1, novoUsuario.getNome());
			stmt.setString(2, novoUsuario.getEmail());
			stmt.setString(3, novoUsuario.getLogin());
			stmt.setString(4, novoUsuario.getSenha());
			stmt.execute();
			
			ResultSet resultado = stmt.getGeneratedKeys();

			if (resultado.next()) {
				novoUsuario.setIdUsuario(resultado.getInt(1));
			}
			
		} catch (SQLException e) {
			
			System.out.println(" Erro ao salvar novo usuário. Causa: " + e.getMessage());
		}

		return novoUsuario;
	}
}
