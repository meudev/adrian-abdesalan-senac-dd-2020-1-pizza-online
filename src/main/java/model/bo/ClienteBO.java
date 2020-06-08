package model.bo;

import model.dao.ClienteDAO;
import model.vo.ClienteVO;

public class ClienteBO {
	
	private static ClienteDAO dao = new ClienteDAO();

	public static String salvar(ClienteVO novoCliente) {
		String mensagem = "";
		
		if(ClienteDAO.telefoneJaCadastrado(novoCliente.getTelefone())) {
			
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
		
		if(ClienteDAO.telefoneJaCadastrado(alterarCliente.getTelefone())) {
			
			mensagem = "Telefone informado (" + alterarCliente.getTelefone() + ") já foi utilizado";
			
		} else {
		
			boolean alterado = dao.salvarAlteracao(alterarCliente);
				
			if(alterado) {
				
				mensagem = "Cliente alterado com sucesso";
				
			} else {
				
				mensagem = "Erro ao alterar o cliente";
				
			}
			
		}
		
		return mensagem;
	}

}
