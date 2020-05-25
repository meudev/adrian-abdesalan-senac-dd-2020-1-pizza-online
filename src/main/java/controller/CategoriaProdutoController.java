package controller;

import java.util.ArrayList;

import model.dao.CategoriaProdutoDAO;
import model.vo.CategoriaProdutoVO;

public class CategoriaProdutoController {
	
	private CategoriaProdutoDAO dao = new CategoriaProdutoDAO();

	public ArrayList<CategoriaProdutoVO> listarTodasCategoriasProdutos() {
		return dao.consultarTodasCategorias();
	}

}
