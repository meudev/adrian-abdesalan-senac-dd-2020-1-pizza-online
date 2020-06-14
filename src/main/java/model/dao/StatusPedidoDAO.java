package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.StatusPedidoVO;

public class StatusPedidoDAO {

	public ArrayList<StatusPedidoVO> consultarTodosStatusPedido() {
		
		String sql = " SELECT * FROM statusPedido ";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		ArrayList<StatusPedidoVO> statusPedido = new ArrayList<StatusPedidoVO>();
		try {
			ResultSet conjuntoResultante = preparedStatement.executeQuery();

			while (conjuntoResultante.next()) {
				StatusPedidoVO categoriaProdutoConsultado = construirStatusPedidoDoResultSet(conjuntoResultante);
				statusPedido.add(categoriaProdutoConsultado);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar status pedido. Causa: " + ex.getMessage());
		}
		return statusPedido;
	}

	private StatusPedidoVO construirStatusPedidoDoResultSet(ResultSet conjuntoResultante) {
		StatusPedidoVO e = new StatusPedidoVO();
		try {
			e.setId(conjuntoResultante.getInt("id"));
			e.setDescricao(conjuntoResultante.getString("descricao"));
		} catch (SQLException ex) {
			System.out.println(" Erro ao construir status pedido a partir do ResultSet. Causa: " + ex.getMessage());
		}
		return e;
	}

	public StatusPedidoVO consultarStatusPorId(int id) {
		String sql = " SELECT * FROM statusPedido WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		StatusPedidoVO statusConsultado = null;
		try {
			preparedStatement.setInt(1, id);
			ResultSet conjuntoResultante = preparedStatement.executeQuery();

			if (conjuntoResultante.next()) {
				statusConsultado = construirStatusPedidoDoResultSet(conjuntoResultante);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar cliente. Id: " + id + " .Causa: " + ex.getMessage());
		}
		return statusConsultado;
	}

}
