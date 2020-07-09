package controller;

import java.util.ArrayList;

import model.bo.ClienteBO;
import model.dao.ClienteDAO;
import model.vo.ClienteVO;
import model.vo.CodigoPaisVO;

public class ClienteController {
	
	private ClienteBO bo = new ClienteBO();
	private ClienteDAO dao = new ClienteDAO();
	
	private static final int TAMANHO_MINIMO_CAMPO_TELEFONE = 10;
	private static final int TAMANHO_MAXIMO_CAMPO_TELEFONE = 11;
	
	private static final int TAMANHO_MINIMO_CAMPO_NOME = 2;
	private static final int TAMANHO_MAXIMO_CAMPO_NOME = 100;
	
	private static final int TAMANHO_MINIMO_CAMPO_LOGRADOURO = 2;
	private static final int TAMANHO_MAXIMO_CAMPO_LOGRADOURO = 100;
	
	private static final int TAMANHO_MINIMO_CAMPO_NUMERO = 1;
	private static final int TAMANHO_MAXIMO_CAMPO_NUMERO = 10;
	
	private static final int TAMANHO_MINIMO_CAMPO_BAIRRO = 1;
	private static final int TAMANHO_MAXIMO_CAMPO_BAIRRO = 50;

	public String cadastrarNovoCliente(CodigoPaisVO codigoPaisVO, String telefone, String nome, String cepTXT, String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String observacao) {
		String mensagem = "";
		
		String telefoneLimpo = telefone.replaceAll(" ", "");
		telefoneLimpo = telefoneLimpo.replaceAll("[(,),-]", "");

		if(cepTXT.isEmpty()) {
			
			mensagem = "Cep inv�lido.";
			
		} else {
		
			String cepReplace = cepTXT.replace("-", "");
			int cep = Integer.parseInt(cepReplace);
				
			mensagem += validarCampoDeTexto("Telefone", telefoneLimpo, TAMANHO_MINIMO_CAMPO_TELEFONE, TAMANHO_MAXIMO_CAMPO_TELEFONE, true);
			mensagem += validarCampoDeTexto("Nome do Cliente", nome, TAMANHO_MINIMO_CAMPO_NOME, TAMANHO_MAXIMO_CAMPO_NOME, true);
			mensagem += validarCampoDeTexto("Logradouro", logradouro, TAMANHO_MINIMO_CAMPO_LOGRADOURO, TAMANHO_MAXIMO_CAMPO_LOGRADOURO, true);
			mensagem += validarCampoDeTexto("N�mero do Endere�o", numero, TAMANHO_MINIMO_CAMPO_NUMERO, TAMANHO_MAXIMO_CAMPO_NUMERO, true);
			mensagem += validarCampoDeTexto("Bairro", bairro, TAMANHO_MINIMO_CAMPO_BAIRRO, TAMANHO_MAXIMO_CAMPO_BAIRRO, true);
			
			if (mensagem.isEmpty()) {
	
				ClienteVO novoCliente = new ClienteVO(codigoPaisVO, telefoneLimpo, nome, cep, logradouro, numero, complemento, bairro, cidade, estado, observacao);
				
				mensagem = ClienteBO.salvar(novoCliente);
				
			}
		
		}
		
		return mensagem;
		
	}

	private String validarCampoDeTexto(String nomeDoCampo, String valor, int tamanhoMinimo, int tamanhoMaximo, boolean obrigatorio) {
		String mensagemValidacao = "";

		if (!obrigatorio && valor == null && valor.isEmpty() || valor.length() < tamanhoMinimo || valor.length() > tamanhoMaximo) {
		mensagemValidacao = nomeDoCampo +"  "+ valor + " deve possuir pelo menos " + tamanhoMinimo + " e no m�ximo "
				+ tamanhoMaximo + " caracteres \n";
		}

		return mensagemValidacao;
	}

	public ClienteVO buscarCliente(String telefone) {
		ClienteVO cliente = null;
		
		String telefoneLimpo = telefone.replaceAll(" ", "");
		telefoneLimpo = telefoneLimpo.replaceAll("[(,),-]", "");
		
		if(telefoneLimpo.length() > 9  && telefoneLimpo.length() < 12) {
			
			cliente = ClienteBO.buscarCliente(telefoneLimpo);
			
		}

		return cliente;
	}

	public ArrayList<ClienteVO> listarClientes(String busca) {
		return dao.consultarPorSeletor(busca);
	}

	public String excluir(int idSelecionado) {
		String mensagem = "";
		
		try {
			
			mensagem = bo.excluir(idSelecionado);
			
		} catch (NumberFormatException ex) {
			
			mensagem = "Selecione um cliente.";
		}
		
		return mensagem;
	}

	public String cadastrarAlteracaoCliente(CodigoPaisVO CodigoPaisVO, int id, String telefone, String nome, String cepTXT, String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String observacao) {
		String mensagem = "";
		
		int cep = Integer.parseInt(cepTXT);
		
		String telefoneLimpo = telefone.replaceAll(" ", "");
		telefoneLimpo = telefoneLimpo.replaceAll("[(,),-]", "");
			
		mensagem += validarCampoDeTexto("Telefone", telefoneLimpo, TAMANHO_MINIMO_CAMPO_TELEFONE, TAMANHO_MAXIMO_CAMPO_TELEFONE, true);
		mensagem += validarCampoDeTexto("Nome do Cliente", nome, TAMANHO_MINIMO_CAMPO_NOME, TAMANHO_MAXIMO_CAMPO_NOME, true);
		mensagem += validarCampoDeTexto("N�mero do Endere�o", numero, TAMANHO_MINIMO_CAMPO_NUMERO, TAMANHO_MAXIMO_CAMPO_NUMERO, true);
		
		if (mensagem.isEmpty()) {

			ClienteVO alterarCliente = new ClienteVO(CodigoPaisVO, telefoneLimpo, nome, cep, logradouro, numero, complemento, bairro, cidade, estado, observacao);
			alterarCliente.setId(id);

			mensagem = ClienteBO.salvarAlteracao(alterarCliente);
			
		}
		
		return mensagem;
	}

	public void gerarPlanilha(ArrayList<ClienteVO> clientes, String caminhoEscolhido) {
		ClienteBO bo = new ClienteBO();
		bo.gerarPlanilha(clientes, caminhoEscolhido);
	}



}
