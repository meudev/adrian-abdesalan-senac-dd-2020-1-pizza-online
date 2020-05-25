package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.CategoriaProdutoVO;

public class CategoriaProdutoDAO {

	public ArrayList<CategoriaProdutoVO> consultarTodasCategorias() {
		String sql = " SELECT * FROM categoriaProduto ";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		ArrayList<CategoriaProdutoVO> categorias = new ArrayList<CategoriaProdutoVO>();
		try {
			ResultSet conjuntoResultante = preparedStatement.executeQuery();

			while (conjuntoResultante.next()) {
				CategoriaProdutoVO categoriaProdutoConsultado = construirCategoriaProdutoDoResultSet(conjuntoResultante);
				categorias.add(categoriaProdutoConsultado);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar endereços. Causa: " + ex.getMessage());
		}
		return categorias;
	}

	private CategoriaProdutoVO construirCategoriaProdutoDoResultSet(ResultSet conjuntoResultante) {
		CategoriaProdutoVO e = new CategoriaProdutoVO();
		try {
			e.setId(conjuntoResultante.getInt("id"));
			e.setDescricao(conjuntoResultante.getString("descricao"));
		} catch (SQLException ex) {
			System.out.println(" Erro ao construir categoria produto a partir do ResultSet. Causa: " + ex.getMessage());
		}
		return e;
	}

}
