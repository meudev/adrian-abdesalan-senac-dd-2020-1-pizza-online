package controller;

import model.bo.ProdutoBO;
import model.vo.CategoriaProdutoVO;
import model.vo.ProdutoVO;

public class ProdutoController {
	
	private static final int TAMANHO_MINIMO_CAMPO_NOME_PRODUTO = 2;
	private static final int TAMANHO_MAXIMO_CAMPO_NOME_PRODUTO = 100;

	private static final int TAMANHO_MINIMO_CAMPO_DESCRICAO = 2;
	private static final int TAMANHO_MAXIMO_CAMPO_DESCRICAO= 250;
	
	private ProdutoBO produtoBO = new ProdutoBO();

	public String salvar(CategoriaProdutoVO categoria, String nomeProduto, String descricao, String valorTXT, String quantidadeTXT, String disponivelTXT) {
		String mensagem = "";
		boolean disponivel = false;
		int quantidade = Integer.parseInt(quantidadeTXT);
		int valor;
		
		if(disponivelTXT != null) {
			disponivel = true;
		}
		
		valor = removerFormatacaoValor(valorTXT);

		mensagem += validarCampoDeTexto("Nome do Produto", nomeProduto, TAMANHO_MINIMO_CAMPO_NOME_PRODUTO, TAMANHO_MAXIMO_CAMPO_NOME_PRODUTO, true);
		mensagem += validarCampoDeTexto("Sobrenome", descricao, TAMANHO_MINIMO_CAMPO_DESCRICAO, TAMANHO_MAXIMO_CAMPO_DESCRICAO, true);
		
		if(categoria == null) {
			mensagem += "Selecione uma categoria" + categoria;
		} else {
		
			if (mensagem.isEmpty()) {

				ProdutoVO novoProduto = new ProdutoVO(categoria, nomeProduto, descricao, quantidade, valor, disponivel);
				
				mensagem = produtoBO.salvar(novoProduto);
			}
		}


		return mensagem;
	}

	private String validarCampoDeTexto(String nomeDoCampo, String valor, int tamanhoMinimo, int tamanhoMaximo, boolean obrigatorio) {
		String mensagemValidacao = "";

		if (!obrigatorio && valor == null && valor.isEmpty() || valor.length() < tamanhoMinimo || valor.length() > tamanhoMaximo) {
		mensagemValidacao = nomeDoCampo +"  "+ valor + " deve possuir pelo menos " + tamanhoMinimo + " e no máximo "
				+ tamanhoMaximo + " caracteres \n";
		}

		return mensagemValidacao;
	}

	private int removerFormatacaoValor(String valorFormatado) {
		
		int valorInteiro = Integer.parseInt(valorFormatado.replace(",", ""));
		
		return valorInteiro;
	}
	
}
