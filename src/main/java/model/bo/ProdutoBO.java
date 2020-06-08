package model.bo;

import model.dao.ProdutoDAO;
import model.vo.ProdutoVO;

public class ProdutoBO {
	
	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public String salvar(ProdutoVO novoProduto) {
		String mensagem = "";
		
		novoProduto = produtoDAO.salvar(novoProduto);
			
		if(novoProduto.getId() > 0) {
			mensagem = "Produto cadastro com sucesso";
		}else {
			mensagem = "Erro ao cadastrar o produto";
		}
		
		return mensagem;
	}

	public String excluir(int idSelecionado) {
		String mensagem = "";

		if (produtoDAO.excluir(idSelecionado)) {
			mensagem = "Excluído com sucesso";
		} else {
			mensagem = "Erro ao excluir";
		}

		return mensagem;
	}

}
