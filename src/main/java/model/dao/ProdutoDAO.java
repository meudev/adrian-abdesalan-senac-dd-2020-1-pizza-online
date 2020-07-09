package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.CategoriaProdutoVO;
import model.vo.ProdutoVO;

public class ProdutoDAO {

	public ProdutoVO salvar(ProdutoVO novoProduto) {
		Connection conexao = Banco.getConnection();

		String sql = " INSERT INTO produto (idCategoria, nome, descricao, quantidade, valor, disponivel) " + " VALUES (?,?,?,?,?,?)";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			
			stmt.setInt(1, novoProduto.getIdCategoria().getId());
			stmt.setString(2, novoProduto.getNome().toUpperCase());
			stmt.setString(3, novoProduto.getDescricao().toUpperCase());
			stmt.setInt(4, novoProduto.getQuantidade());
			stmt.setInt(5, novoProduto.getValor());
			stmt.setInt(6, novoProduto.isDisponivel() ? 1 : 0);
			stmt.execute();
			
			ResultSet resultado = stmt.getGeneratedKeys();

			if (resultado.next()) {
				novoProduto.setId(resultado.getInt(1));
				System.out.println(resultado.getInt(1));
			}
			
		} catch (SQLException e) {
			
			System.out.println(" Erro ao salvar novo produto. Causa: " + e.getMessage());
		}

		return novoProduto;
	}

	public ArrayList<ProdutoVO> consultarPorSeletor(String busca) {
		String likeBusca = "%"+ busca + "%";
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM produto WHERE nome like '"+ likeBusca + "' ";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ProdutoVO produto = construirProdutoDoResultSet(rs);
				produtos.add(produto);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar clientes.");
			System.out.println("Erro: " + e.getMessage());
		}

		return produtos;
	}

	private ProdutoVO construirProdutoDoResultSet(ResultSet rs) {
		ProdutoVO p = new ProdutoVO();
		
		try {
			p.setId(rs.getInt("id"));
			p.setNome(rs.getString("nome"));
			p.setDescricao(rs.getString("descricao"));
			p.setQuantidade(rs.getInt("quantidade"));
			p.setValor(rs.getInt("valor"));
			p.setDisponivel(rs.getBoolean("disponivel"));
			
			//categoria
			CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();
			CategoriaProdutoVO categoriaProduto = categoriaProdutoDAO.consultarCategoriaPorId(rs.getInt("idCategoria"));
			p.setIdCategoria(categoriaProduto);	

		} catch (SQLException e) {
			
			System.out.println("Erro ao construir produto a partir do ResultSet. Causa: " + e.getMessage());
			
		}
		
		return p;
	}

	public boolean excluir(int idSelecionado) {
		String sql = " DELETE FROM produto WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		boolean excluiu = false;
		
		try {
			preparedStatement.setInt(1, idSelecionado);
			int codigoRetornoUpdate = preparedStatement.executeUpdate();

			excluiu = (codigoRetornoUpdate == Banco.CODIGO_RETORNO_SUCESSO_EXCLUSAO);
		} catch (SQLException ex) {
			System.out.println(" Erro ao excluir produto. Id: " + idSelecionado + " .Causa: " + ex.getMessage());
		}
		return excluiu;
	}

	public ProdutoVO consultarProdutoPorId(int id) {
		String sql = " SELECT * FROM produto WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		ProdutoVO produtoConsultado = null;
		try {
			preparedStatement.setInt(1, id);
			ResultSet conjuntoResultante = preparedStatement.executeQuery();

			if (conjuntoResultante.next()) {
				produtoConsultado = construirProdutoDoResultSet(conjuntoResultante);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar produto. Id: " + id + " .Causa: " + ex.getMessage());
		}
		return produtoConsultado;
	}


}
