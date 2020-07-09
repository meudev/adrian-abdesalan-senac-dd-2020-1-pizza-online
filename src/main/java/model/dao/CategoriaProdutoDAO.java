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

	public CategoriaProdutoVO cadastrarCategoria(CategoriaProdutoVO novaCategoria) {
		Connection conexao = Banco.getConnection();

		String sql = " INSERT INTO categoriaProduto (descricao) " + " VALUES (?)";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		
		try {
			
			stmt.setString(1, novaCategoria.getDescricao().toUpperCase());
			stmt.execute();
			
			ResultSet resultado = stmt.getGeneratedKeys();

			if (resultado.next()) {
				novaCategoria.setId(resultado.getInt(1));
			}
			
		} catch (SQLException e) {
			
			System.out.println(" Erro ao salvar nova categoria produto. Causa: " + e.getMessage());
		}

		return novaCategoria;
	}

	public static ArrayList<CategoriaProdutoVO> consultarCategoriaProduto(String parametroBusca) {
		String likeBusca = "%"+ parametroBusca + "%";
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM categoriaProduto WHERE descricao like '"+ likeBusca + "' ";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		ArrayList<CategoriaProdutoVO> categorias = new ArrayList<CategoriaProdutoVO>();
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				CategoriaProdutoVO categoria = construirCategoriaProdutoDoResultSet(rs);
				categorias.add(categoria);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar categoria produto.");
			System.out.println("Erro: " + e.getMessage());
		}

		return categorias;
	}

	public CategoriaProdutoVO consultarCategoriaPorId(int idCategoriaProduto) {
		String sql = " SELECT * FROM categoriaProduto WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		CategoriaProdutoVO categoriaProdutoConsultado = null;
		try {
			preparedStatement.setInt(1, idCategoriaProduto);
			ResultSet conjuntoResultante = preparedStatement.executeQuery();

			if (conjuntoResultante.next()) {
				categoriaProdutoConsultado = construirCategoriaProdutoDoResultSet(conjuntoResultante);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar categoria produto. Id: " + idCategoriaProduto + " .Causa: " + ex.getMessage());
		}
		return categoriaProdutoConsultado;
	}

	private static CategoriaProdutoVO construirCategoriaProdutoDoResultSet(ResultSet conjuntoResultante) {
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
