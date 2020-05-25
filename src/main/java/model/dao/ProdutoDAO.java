package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.ProdutoVO;

public class ProdutoDAO {

	public ProdutoVO salvar(ProdutoVO novoProduto) {
		Connection conexao = Banco.getConnection();

		String sql = " INSERT INTO produto (idCategoria, nome, descricao, quantidade, valor, disponivel) " + " VALUES (?,?,?,?,?,?)";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			
			stmt.setInt(1, novoProduto.getIdCategoria().getId());
			stmt.setString(2, novoProduto.getNome());
			stmt.setString(3, novoProduto.getDescricao());
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

}
