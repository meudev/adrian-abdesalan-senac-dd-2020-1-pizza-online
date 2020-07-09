package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String BANCODADOS = "dbpizza";
	private static final String CONEXAO = "jdbc:mysql://localhost:3306/" + BANCODADOS
			+ "?useTimezone=true&serverTimezone=UTC&useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
//	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//	private static final String BANCODADOS = "meudev44_dbpizza";
//	private static final String CONEXAO = "jdbc:mysql://meudev.com.br:3306/" + BANCODADOS
//			+ "?useTimezone=true&serverTimezone=UTC&useSSL=false";
//	private static final String USER = "meudev44_cartao";
//	private static final String PASSWORD = "poa102030";

	public static final int CODIGO_RETORNO_ERRO_EXCLUSAO = 0;
	public static final int CODIGO_RETORNO_SUCESSO_EXCLUSAO = 1;

	public static Connection getConnection() {

		try {
			Connection conn = null;
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(CONEXAO, USER, PASSWORD);
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Classe do Driver n�o foi encontrada. Causa: " + e.getMessage());
			return null;
		} catch (SQLException e) {
			System.out.println("Erro ao obter a Connection. Causa: " + e.getMessage());
			return null;
		}
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento da conex�o. Causa: " + e.getMessage());
		}
	}

	public static Statement getStatement(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			return stmt;
		} catch (SQLException e) {
			System.out.println("Erro ao obter o Statement. Causa: " + e.getMessage());
			return null;
		}
	}

	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do Statement. Causa: " + e.getMessage());
		}
	}

	public static PreparedStatement getPreparedStatement(Connection conn) {
		try {
			PreparedStatement stmt = null;
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement. Causa: " + e.getMessage());
			return null;
		}
	}

	public static PreparedStatement getPreparedStatement(Connection conn, String sql) {
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement. Causa: " + e.getCause());
			return null;
		}
	}

	public static PreparedStatement getPreparedStatement(Connection conn, String sql, int tipoRetorno) {
		try {
			PreparedStatement stmt = conn.prepareStatement(sql, tipoRetorno);
			return stmt;
		} catch (Exception e) {
			System.out.println("Erro ao obter o PreparedStatement.");
			return null;
		}
	}

	public static void closePreparedStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do PreparedStatement. Causa: " + e.getMessage());
		}
	}

	public static void closeResultSet(ResultSet result) {
		try {
			if (result != null) {
				result.close();
			}
		} catch (SQLException e) {
			System.out.println("Problema no fechamento do ResultSet. Causa: " + e.getMessage());
		}
	}
}
