package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.ClienteVO;

public class ClienteDAO {

	public static boolean telefoneJaCadastrado(String telefone) {

		String sql = " SELECT * FROM cliente WHERE telefone = " + telefone;
		
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);

		ResultSet resultado = null;
		boolean telefoneJaCadastrado = false;
		
		try {
			
			resultado = stmt.executeQuery(sql);
			telefoneJaCadastrado = resultado.next();

		} catch (SQLException e) {
			
			System.out.println("Erro ao verificar se telefone j� est� cadastrado " + telefone);
			System.out.println("Causa: " + e.getMessage());
			
		} finally {
			
			Banco.closeResultSet(resultado);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
			
		}		

		return telefoneJaCadastrado;
	}
	
	public ClienteVO salvar(ClienteVO novoCliente) {

		String sql = " INSERT INTO cliente (telefone, nome, cep, logradouro, numero, complemento, bairro, cidade, estado, observacoes) " + " VALUES (?,?,?,?,?,?,?,?,?,?)";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			
			stmt.setString(1, novoCliente.getTelefone());
			stmt.setString(2, novoCliente.getNome());
			stmt.setInt(3, novoCliente.getCep());
			stmt.setString(4, novoCliente.getLogradouro());
			stmt.setString(5, novoCliente.getNumero());
			stmt.setString(6, novoCliente.getComplemento());
			stmt.setString(7, novoCliente.getBairro());
			stmt.setString(8, novoCliente.getCidade());
			stmt.setString(9, novoCliente.getEstado());
			stmt.setString(10, novoCliente.getObservacao());
			stmt.execute();
			
			ResultSet resultado = stmt.getGeneratedKeys();

			if (resultado.next()) {
				
				novoCliente.setId(resultado.getInt(1));
				
			}
			
		} catch (SQLException e) {
			
			System.out.println(" Erro ao salvar novo cliente. Causa: " + e.getMessage());
			
		}

		return novoCliente;
		
	}

	public ClienteVO buscarCliente(String telefone) {
		

		String sql = " SELECT * FROM cliente WHERE telefone = " + telefone;
		
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		ClienteVO cliente = null;
		
		try {
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				
				cliente = construirClienteDoResultSet(rs);
				
			}

		} catch (SQLException e) {
			
			System.out.println("Erro ao consultar cliente com telefone: " + telefone);
			System.out.println("Erro: " + e.getMessage());
			
		}

		return cliente;
	}
	
	private ClienteVO construirClienteDoResultSet(ResultSet rs) {
		
		ClienteVO c = new ClienteVO();
		
		try {
			c.setId(rs.getInt("id"));
			c.setTelefone(rs.getString("telefone"));
			c.setNome(rs.getString("nome"));
			c.setCep(rs.getInt("cep"));
			c.setLogradouro(rs.getString("logradouro"));
			c.setNumero(rs.getString("numero"));
			c.setComplemento(rs.getString("complemento"));
			c.setBairro(rs.getString("bairro"));
			c.setCidade(rs.getString("cidade"));
			c.setEstado(rs.getString("estado"));
			c.setObservacao(rs.getString("observacoes"));
			

		} catch (SQLException e) {
			
			System.out.println("Erro ao construir cliente a partir do ResultSet. Causa: " + e.getMessage());
			
		}
		
		return c;
	}
	
	
}
