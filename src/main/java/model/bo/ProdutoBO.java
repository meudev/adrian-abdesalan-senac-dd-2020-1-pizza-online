package model.bo;

import model.dao.ProdutoDAO;
import model.vo.ProdutoVO;

public class ProdutoBO {
	
	private ProdutoDAO dao = new ProdutoDAO();

	public String salvar(ProdutoVO novoProduto) {
		String mensagem = "";
		
		novoProduto = dao.salvar(novoProduto);
			
		if(novoProduto.getId() > 0) {
			mensagem = "Produto cadastro com sucesso";
		}else {
			mensagem = "Erro ao cadastrar o produto";
		}
		
		return mensagem;
	}

}
