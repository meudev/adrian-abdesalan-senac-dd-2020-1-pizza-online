package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.CodigoPaisVO;

public class CodigoPaisDAO {

	public ArrayList<CodigoPaisVO> consultarTodasCategorias() {
		String sql = " SELECT * FROM codigoPais ";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		ArrayList<CodigoPaisVO> codigos = new ArrayList<CodigoPaisVO>();
		try {
			ResultSet conjuntoResultante = preparedStatement.executeQuery();

			while (conjuntoResultante.next()) {
				CodigoPaisVO codigoPaisConsultado = construirCodigoPaisDoResultSet(conjuntoResultante);
				codigos.add(codigoPaisConsultado);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar endereços. Causa: " + ex.getMessage());
		}
		return codigos;
	}

	public CodigoPaisVO consultarCodigoPaisPorId(int idCodigoPais) {
		String sql = " SELECT * FROM codigoPais WHERE id = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement preparedStatement = Banco.getPreparedStatement(conexao, sql);
		CodigoPaisVO codigoConsultado = null;
		try {
			preparedStatement.setInt(1, idCodigoPais);
			ResultSet conjuntoResultante = preparedStatement.executeQuery();

			if (conjuntoResultante.next()) {
				codigoConsultado = construirCodigoPaisDoResultSet(conjuntoResultante);
			}
		} catch (SQLException ex) {
			System.out.println(" Erro ao consultar código país. Id: " + idCodigoPais + " .Causa: " + ex.getMessage());
		}
		return codigoConsultado;
	}
	
	private CodigoPaisVO construirCodigoPaisDoResultSet(ResultSet conjuntoResultante) {
		CodigoPaisVO e = new CodigoPaisVO();
		try {
			e.setId(conjuntoResultante.getInt("id"));
			e.setCodigo(conjuntoResultante.getString("codigo"));
			e.setPais(conjuntoResultante.getString("pais"));
		} catch (SQLException ex) {
			System.out.println(" Erro ao construir codigo país a partir do ResultSet. Causa: " + ex.getMessage());
		}
		return e;
	}

}
