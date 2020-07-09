package model.bo;

import java.util.ArrayList;

import model.dao.ClienteDAO;
import model.util.GeradorPlanilha;
import model.vo.ClienteVO;

public class ClienteBO {
	
	private static ClienteDAO dao = new ClienteDAO();

	public static String salvar(ClienteVO novoCliente) {
		String mensagem = "";
		String telefoneConsultado = novoCliente.getTelefone().replaceAll("[()-]", "");
//		telefoneConsultado = telefoneConsultado.replace("(", "");
//		telefoneConsultado = telefoneConsultado.replace(")", "");
		telefoneConsultado = telefoneConsultado.replace(" ", "");
//		telefoneConsultado = telefoneConsultado.replace("-", "");
		
		if(ClienteDAO.telefoneJaCadastrado(telefoneConsultado)) {
			
			mensagem = "Telefone informado (" + novoCliente.getTelefone() + ") já foi utilizado";
			
		} else {
		
			novoCliente = dao.salvar(novoCliente);
				
			if(novoCliente.getId() > 0) {
				
				mensagem = "Cliente cadastro com sucesso";
				
			} else {
				
				mensagem = "Erro ao cadastrar o cliente";
				
			}
			
		}
		
		return mensagem;
	}

	public static ClienteVO buscarCliente(String telefone) {
		
		ClienteVO cliente = null;

		if (ClienteDAO.telefoneJaCadastrado(telefone)) {
			
			cliente = dao.buscarCliente(telefone);
			
		}

		return cliente;
	}

	public String excluir(int idSelecionado) {
		String mensagem = "";

		if (dao.excluir(idSelecionado)) {
			mensagem = "Excluído com sucesso";
		} else {
			mensagem = "Erro ao excluir";
		}

		return mensagem;
	}

	public static String salvarAlteracao(ClienteVO alterarCliente) {
		String mensagem = "";
		
			boolean alterado = dao.salvarAlteracao(alterarCliente);
				
			if(alterado) {
				
				mensagem = "Cliente alterado com sucesso";
				
			} else {
				
				mensagem = "Erro ao alterar o cliente";
				
			}
			
		
		return mensagem;
	}

	public void gerarPlanilha(ArrayList<ClienteVO> clientes, String caminhoEscolhido) {
		GeradorPlanilha geradorPlanilha = new GeradorPlanilha();
		
		geradorPlanilha.gerarPlanilhaClientes(clientes, caminhoEscolhido);
		
	}

}
