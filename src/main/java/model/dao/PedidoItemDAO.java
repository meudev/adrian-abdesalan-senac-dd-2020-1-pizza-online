package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.PedidoItemVO;
import model.vo.PedidoVO;
import model.vo.ProdutoVO;

public class PedidoItemDAO {

	public boolean salvarPedidoItem(PedidoItemVO item, int idPedido) {
		Connection conexao = Banco.getConnection();

		boolean itemSalvo = false;
		
		String sql = " INSERT INTO itemPedido (idPedido, idProduto, quantidade, valor) " + " VALUES (?, ?,?,?)";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			
			stmt.setInt(1, idPedido);
			stmt.setInt(2, item.getIdProduto().getId());
			stmt.setInt(3, item.getQuantidade());
			stmt.setInt(4, item.getValor());
			stmt.execute();
			
			ResultSet resultado = stmt.getGeneratedKeys();

			if (resultado.next()) {
				item.setId(resultado.getInt(1));
				
				itemSalvo = true;
			}
			
		} catch (SQLException e) {
			
			System.out.println(" Erro ao salvar novo item. Causa: " + e.getMessage());
		}

		return itemSalvo;
	}

	public ArrayList<PedidoItemVO> consultarPorParametro(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM itemPedido WHERE idPedido = ? ";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		ArrayList<PedidoItemVO> itens = new ArrayList<PedidoItemVO>();
		try {
			stmt.setInt(1, id);
			stmt.execute();
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				PedidoItemVO item = construirPedidoItemDoResultSet(rs);
				itens.add(item);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar itens do pedido.");
			System.out.println("Erro: " + e.getMessage());
		}

		return itens;
	}

	private PedidoItemVO construirPedidoItemDoResultSet(ResultSet rs) {
		PedidoItemVO i = new PedidoItemVO();
		
		try {
			i.setId(rs.getInt("id"));
			i.setQuantidade(rs.getInt("quantidade"));
			i.setValor(rs.getInt("valor"));
			
			//BUSCA O PEDIDO PELO ID
			PedidoDAO pedidoDAO = new PedidoDAO();
			PedidoVO pedido = pedidoDAO.consultarPedidoPorId(rs.getInt("idPedido"));
			i.setIdPedido(pedido);
			
			//BUSCA PRODUTO PELO ID
			ProdutoDAO produtoDAO = new ProdutoDAO();
			ProdutoVO produto = produtoDAO.consultarProdutoPorId(rs.getInt("idProduto"));
			i.setIdProduto(produto);

		} catch (SQLException e) {
			
			System.out.println("Erro ao construir itens do pedido a partir do ResultSet. Causa: " + e.getMessage());
			
		}
		
		return i;
	}
}
