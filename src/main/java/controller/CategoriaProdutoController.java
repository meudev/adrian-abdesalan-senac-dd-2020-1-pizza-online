package controller;

import java.util.ArrayList;

import model.bo.CategoriaProdutoBO;
import model.dao.CategoriaProdutoDAO;
import model.vo.CategoriaProdutoVO;

public class CategoriaProdutoController {
	
	CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();
	CategoriaProdutoBO categoriaProdutoBO = new CategoriaProdutoBO();

	public ArrayList<CategoriaProdutoVO> listarTodasCategoriasProdutos() {
		return categoriaProdutoDAO.consultarTodasCategorias();
	}

	public String cadastrarCategoriaProduto(String categoria) {
		String mensagem = "";

		if (!categoria.isEmpty()) {

			CategoriaProdutoVO novaCategoria = new CategoriaProdutoVO(categoria);
			
			mensagem = categoriaProdutoBO.salvarNovaCategoria(novaCategoria);
			
		} else {
			mensagem = "Categoria em branco.";
		}
		
		return mensagem;
	}

	public ArrayList<CategoriaProdutoVO> listarCategorias(String parametroBusca) {
		return CategoriaProdutoDAO.consultarCategoriaProduto(parametroBusca);
	}

}
