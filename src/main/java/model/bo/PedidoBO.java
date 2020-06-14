package model.bo;

import java.util.List;

import model.dao.PedidoDAO;
import model.dao.PedidoItemDAO;
import model.vo.PedidoItemVO;
import model.vo.PedidoVO;
import model.vo.StatusPedidoVO;

public class PedidoBO {
	
	private PedidoDAO pedidoDAO = new PedidoDAO();
	private PedidoItemDAO pedidoItemDAO = new PedidoItemDAO();

	public String salvarPedido(PedidoVO novoPedido, List<PedidoItemVO> listPeditoItem) {
		
		String mensagem = "";
		boolean itemSalvo = true;
		
		novoPedido = pedidoDAO.salvarPedido(novoPedido);
			
		if(novoPedido.getId() > 0) {
			
			for (PedidoItemVO item : listPeditoItem) {

				itemSalvo = pedidoItemDAO.salvarPedidoItem(item, novoPedido.getId());
				
				if(itemSalvo) {
					continue;
				} else {
					mensagem = "Erro ao salvar item do pedido.";
					break;
				}
			}	
			
			if (mensagem.isEmpty()) {
				
				mensagem = "Pedido cadastro com sucesso. Nº : "+ novoPedido.getId();
				
			}

			
		}else {
			
			mensagem = "Erro ao cadastrar o pedido";
		}
		
		return mensagem;
	}

	public String cancelarPedido(int idSelecionado, StatusPedidoVO statusPedidoVO) {
		String mensagem = "";

		if (pedidoDAO.cancelarPedido(idSelecionado, statusPedidoVO)) {
			mensagem = "Cancelado com sucesso";
		} else {
			mensagem = "Erro ao cancelar";
		}

		return mensagem;
	}

	public String atualizarPedido(int idSelecionado, StatusPedidoVO statusPedidoVO) {
		String mensagem = "";

		if (pedidoDAO.atualizarPedido(idSelecionado, statusPedidoVO)) {
			mensagem = "Atualizado com sucesso.";
		} else {
			mensagem = "Erro ao atualizar.";
		}

		return mensagem;
	}

}
