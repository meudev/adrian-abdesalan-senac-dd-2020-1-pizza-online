package model.bo;

import model.dao.ClienteDAO;
import model.vo.ClienteVO;

public class ClienteBO {
	
	private static ClienteVO vo = new ClienteVO();
	private static ClienteDAO dao = new ClienteDAO();

	public static String salvar(ClienteVO novoCliente) {
		String mensagem = "";
		
		if(dao.telefoneJaCadastrado(novoCliente.getTelefone())) {
			
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

}
