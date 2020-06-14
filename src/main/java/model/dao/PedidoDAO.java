package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.ClienteVO;
import model.vo.PedidoVO;
import model.vo.StatusPedidoVO;

public class PedidoDAO {

	public PedidoVO salvarPedido(PedidoVO novoPedido) {
		Connection conexao = Banco.getConnection();

		String sql = " INSERT INTO pedido (idCliente, dataPedido, idStatus, observacao, endereco, taxa, valorTotal) " + " VALUES (?,?,?,?,?,?,?)";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			
			stmt.setInt(1, novoPedido.getIdCliente().getId());
			stmt.setString(2, novoPedido.getDataPedido());
			stmt.setInt(3, novoPedido.getIdStatus().getId());
			stmt.setString(4, novoPedido.getObservacao());
			stmt.setString(5, novoPedido.getEndereco());
			stmt.setInt(6, novoPedido.getTaxaEntrega());
			stmt.setInt(7, novoPedido.getValorTotal());
			stmt.execute();
			
			ResultSet resultado = stmt.getGeneratedKeys();

			if (resultado.next()) {
				novoPedido.setId(resultado.getInt(1));
				System.out.println(resultado.getInt(1));
			}
			
		} catch (SQLException e) {
			
			System.out.println(" Erro ao salvar novo pedido. Causa: " + e.getMessage());
		}

		return novoPedido;
	}

	public ArrayList<PedidoVO> consultarPorParametro(String parametroBusca) {
		String likeBusca = "%"+ parametroBusca + "%";
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM pedido WHERE id like '"+ likeBusca + "' ";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		ArrayList<PedidoVO> pedidos = new ArrayList<PedidoVO>();
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PedidoVO produto = construirPedidoDoResultSet(rs);
				pedidos.add(produto);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar pedidos.");
			System.out.println("Erro: " + e.getMessage());
		}

		return pedidos;
	}

	private PedidoVO construirPedidoDoResultSet(ResultSet rs) {
		PedidoVO p = new PedidoVO();
		
		try {
			p.setId(rs.getInt("id"));
			p.setDataPedido(rs.getString("dataPedido"));
			p.setObservacao(rs.getString("observacao"));
			p.setEndereco(rs.getString("endereco"));
			p.setTaxaEntrega(rs.getInt("taxa"));
			p.setValorTotal(rs.getInt("valorTotal"));
			
			//BUSCA O CLIENTE PELO ID
			ClienteDAO clienteDAO = new ClienteDAO();
			ClienteVO cliente = clienteDAO.consultarClientePorId(rs.getInt("idCliente"));
			p.setIdCliente(cliente);
			
			//BUSCA STATUS DO PEDIDO PELO ID
			StatusPedidoDAO statusPedidoDAO = new StatusPedidoDAO();
			StatusPedidoVO status = statusPedidoDAO.consultarStatusPorId(rs.getInt("idStatus"));
			p.setIdStatus(status);

		} catch (SQLException e) {
			
			System.out.println("Erro ao construir pedido a partir do ResultSet. Causa: " + e.getMessage());
			
		}
		
		return p;
	}

	public boolean cancelarPedido(int idSelecionado, StatusPedidoVO statusPedidoVO) {
		String sql = " UPDATE pedido SET idStatus=? WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		boolean cancelar = false;
		
		try {
			preparedStatement.setInt(1, statusPedidoVO.getId());
			preparedStatement.setInt(2, idSelecionado);
			int codigoRetornoUpdate = preparedStatement.executeUpdate();

			cancelar = (codigoRetornoUpdate == Banco.CODIGO_RETORNO_SUCESSO_EXCLUSAO);
		} catch (SQLException ex) {
			System.out.println(" Erro ao cancelar pedido. Id: " + idSelecionado + " .Causa: " + ex.getMessage());
		}
		return cancelar;
	}

	public PedidoVO consultarPedidoPorId(int id) {
		String sql = " SELECT * FROM pedido WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		PedidoVO pedidoConsultado = null;
		try {
			preparedStatement.setInt(1, id);
			ResultSet conjuntoResultante = preparedStatement.executeQuery();

			if (conjuntoResultante.next()) {
				pedidoConsultado = construirPedidoDoResultSet(conjuntoResultante);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar pedido. Id: " + id + " .Causa: " + ex.getMessage());
		}
		return pedidoConsultado;
	}

	public boolean atualizarPedido(int idSelecionado, StatusPedidoVO statusPedidoVO) {
		String sql = " UPDATE pedido SET idStatus=? WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		boolean atualizar = false;
		
		try {
			preparedStatement.setInt(1, statusPedidoVO.getId());
			preparedStatement.setInt(2, idSelecionado);
			int codigoRetornoUpdate = preparedStatement.executeUpdate();

			atualizar = (codigoRetornoUpdate == Banco.CODIGO_RETORNO_SUCESSO_EXCLUSAO);
		} catch (SQLException ex) {
			System.out.println(" Erro ao atualizar status do pedido. Id: " + idSelecionado + " .Causa: " + ex.getMessage());
		}
		return atualizar;
	}

	public ArrayList<PedidoVO> consultarPedidosCozinha(int status_1, int status_2) {
		
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM pedido WHERE idStatus = ? OR idStatus = ?";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		ArrayList<PedidoVO> pedidos = new ArrayList<PedidoVO>();
		try {
			stmt.setInt(1, status_1);
			stmt.setInt(2, status_2);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				PedidoVO produto = construirPedidoDoResultSet(rs);
				pedidos.add(produto);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar pedidos.");
			System.out.println("Erro: " + e.getMessage());
		}

		return pedidos;
	}

}
