package model.bo;

import model.dao.CategoriaProdutoDAO;
import model.vo.CategoriaProdutoVO;

public class CategoriaProdutoBO {
	
	CategoriaProdutoDAO categoriaProdutoDAO = new CategoriaProdutoDAO();

	public String salvarNovaCategoria(CategoriaProdutoVO novaCategoria) {
		String mensagem = "";
		
		novaCategoria = categoriaProdutoDAO.cadastrarCategoria(novaCategoria);
			
		if(novaCategoria.getId() > 0) {
			mensagem = "Categoria cadastrada com sucesso.";
		}else {
			mensagem = "Erro ao cadastrar nova categoria.";
		}
		
		return mensagem;
	}
	
	

}
