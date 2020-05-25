package controller;

import model.bo.ClienteBO;
import model.dao.ClienteDAO;
import model.vo.ClienteVO;

public class ClienteController {
	
	private static final int TAMANHO_MINIMO_CAMPO_TELEFONE = 10;
	private static final int TAMANHO_MAXIMO_CAMPO_TELEFONE = 11;
	
	private static final int TAMANHO_MINIMO_CAMPO_NOME = 2;
	private static final int TAMANHO_MAXIMO_CAMPO_NOME = 100;
	
	private static final int TAMANHO_MINIMO_CAMPO_NUMERO = 1;
	private static final int TAMANHO_MAXIMO_CAMPO_NUMERO = 10;

	public String cadastrarNovoCliente(String telefone, String nome, String cepTXT, String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String observacao) {
		String mensagem = "";
		
		int cep = Integer.parseInt(cepTXT);
			
		mensagem += validarCampoDeTexto("Telefone", telefone, TAMANHO_MINIMO_CAMPO_TELEFONE, TAMANHO_MAXIMO_CAMPO_TELEFONE, true);
		mensagem += validarCampoDeTexto("Nome do Cliente", nome, TAMANHO_MINIMO_CAMPO_NOME, TAMANHO_MAXIMO_CAMPO_NOME, true);
		mensagem += validarCampoDeTexto("Número do Endereço", numero, TAMANHO_MINIMO_CAMPO_NUMERO, TAMANHO_MAXIMO_CAMPO_NUMERO, true);
		
		if (mensagem.isEmpty()) {

			ClienteVO novoCliente = new ClienteVO(telefone, nome, cep, logradouro, numero, complemento, bairro, cidade, estado, observacao);
			
			mensagem = ClienteBO.salvar(novoCliente);
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

	public ClienteVO buscarCliente(String telefone) {
		ClienteVO cliente = null;
		
		if(telefone.length() > 9 && telefone.length() < 12) {
			
			cliente = ClienteBO.buscarCliente(telefone);
			
		}

		return cliente;
	}



}
