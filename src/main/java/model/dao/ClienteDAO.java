package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.ClienteVO;
import model.vo.CodigoPaisVO;

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
			
			System.out.println("Erro ao verificar se telefone já está cadastrado " + telefone);
			System.out.println("Causa: " + e.getMessage());
			
		} finally {
			
			Banco.closeResultSet(resultado);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
			
		}		

		return telefoneJaCadastrado;
	}
	
	public ClienteVO salvar(ClienteVO novoCliente) {

		String sql = " INSERT INTO cliente (idCodigo, telefone, nome, cep, logradouro, numero, complemento, bairro, cidade, estado, observacoes) " + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			
			System.out.println(novoCliente.getTelefone());
			stmt.setInt(1, novoCliente.getCodigo().getId());
			stmt.setString(2, novoCliente.getTelefone());
			stmt.setString(3, novoCliente.getNome().toUpperCase());
			stmt.setInt(4, novoCliente.getCep());
			stmt.setString(5, novoCliente.getLogradouro().toUpperCase());
			stmt.setString(6, novoCliente.getNumero().toUpperCase());
			stmt.setString(7, novoCliente.getComplemento().toUpperCase());
			stmt.setString(8, novoCliente.getBairro().toUpperCase());
			stmt.setString(9, novoCliente.getCidade().toUpperCase());
			stmt.setString(10, novoCliente.getEstado().toUpperCase());
			stmt.setString(11, novoCliente.getObservacao().toUpperCase());
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
			c.setNome(rs.getString("nome"));
			c.setCep(rs.getInt("cep"));
			c.setLogradouro(rs.getString("logradouro"));
			c.setNumero(rs.getString("numero"));
			c.setComplemento(rs.getString("complemento"));
			c.setBairro(rs.getString("bairro"));
			c.setCidade(rs.getString("cidade"));
			c.setEstado(rs.getString("estado"));
			c.setObservacao(rs.getString("observacoes"));
			
			if(rs.getInt("idCodigo") == 1) {
				if(rs.getString("telefone").length() == 10) {
					c.setTelefone("(" + rs.getString("telefone").substring(0, 2) + ") " + rs.getString("telefone").substring(2, 6) +"-"+ rs.getString("telefone").substring(6, 10));
				} else {
					c.setTelefone("(" + rs.getString("telefone").substring(0, 2) + ") " + rs.getString("telefone").substring(2, 6) +"-"+ rs.getString("telefone").substring(6, 11));					
				}
			} else {
				c.setTelefone(rs.getString("telefone"));
			}
			
			//codigo
			CodigoPaisDAO codigoPaisDAO = new CodigoPaisDAO();
			CodigoPaisVO codigoPais = codigoPaisDAO.consultarCodigoPaisPorId(rs.getInt("idCodigo"));
			c.setCodigo(codigoPais);					

		} catch (SQLException e) {
			
			System.out.println("Erro ao construir cliente a partir do ResultSet. Causa: " + e.getMessage());
			
		}
		
		return c;
	}

	public ArrayList<ClienteVO> consultarPorSeletor(String busca) {
		String likeBusca = "%"+ busca + "%";
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM cliente WHERE nome like '"+ likeBusca + "' OR telefone like '"+ likeBusca + "' OR cep like '"+ likeBusca + "' or logradouro like '"+ likeBusca + "' ";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		ArrayList<ClienteVO> clientes = new ArrayList<ClienteVO>();
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ClienteVO cliente = construirClienteDoResultSet(rs);
				clientes.add(cliente);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar clientes.");
			System.out.println("Erro: " + e.getMessage());
		}

		return clientes;
	}

	public boolean excluir(int idSelecionado) {
		String sql = " DELETE FROM cliente WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		boolean excluiu = false;
		
		try {
			preparedStatement.setInt(1, idSelecionado);
			int codigoRetornoUpdate = preparedStatement.executeUpdate();

			excluiu = (codigoRetornoUpdate == Banco.CODIGO_RETORNO_SUCESSO_EXCLUSAO);
		} catch (SQLException ex) {
			System.out.println(" Erro ao excluir cliente. Id: " + idSelecionado + " .Causa: " + ex.getMessage());
		}
		return excluiu;
	}

	public boolean salvarAlteracao(ClienteVO alterarCliente) {
		
		boolean alterado = false;
		int id = alterarCliente.getId();
		
		String sql = "UPDATE cliente SET idCodigo = ?, telefone = ?, nome = ?, cep = ?, logradouro = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ?, observacoes = ? WHERE id = "+ id;

		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		int registrosAlterados = 0;
		try {
			System.out.println(alterarCliente.getCodigo().getId());
			stmt.setInt(1, alterarCliente.getCodigo().getId());
			stmt.setString(2, alterarCliente.getTelefone());
			stmt.setString(3, alterarCliente.getNome().toUpperCase());
			stmt.setInt(4, alterarCliente.getCep());
			stmt.setString(5, alterarCliente.getLogradouro().toUpperCase());
			stmt.setString(6, alterarCliente.getNumero().toUpperCase());
			stmt.setString(7, alterarCliente.getComplemento().toUpperCase());
			stmt.setString(8, alterarCliente.getBairro().toUpperCase());
			stmt.setString(9, alterarCliente.getCidade().toUpperCase());
			stmt.setString(10, alterarCliente.getEstado().toUpperCase());
			stmt.setString(11, alterarCliente.getObservacao().toUpperCase());
			stmt.execute();
			
			registrosAlterados = stmt.executeUpdate();
			
			if(registrosAlterados > 0) {
				alterado = true;
			}
			
		} catch (SQLException e) {
			
			System.out.println(" Erro ao salvar alteração cliente. Causa: " + e.getMessage());
			
		}
		
		return alterado;
	}

	public ClienteVO consultarClientePorId(int id) {
		String sql = " SELECT * FROM cliente WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		ClienteVO clienteConsultado = null;
		try {
			preparedStatement.setInt(1, id);
			ResultSet conjuntoResultante = preparedStatement.executeQuery();

			if (conjuntoResultante.next()) {
				clienteConsultado = construirClienteDoResultSet(conjuntoResultante);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar cliente. Id: " + id + " .Causa: " + ex.getMessage());
		}
		return clienteConsultado;
	}
	
	
}
