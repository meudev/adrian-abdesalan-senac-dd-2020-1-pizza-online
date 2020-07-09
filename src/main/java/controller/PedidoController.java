package controller;

import java.util.ArrayList;
import java.util.List;

import model.bo.PedidoBO;
import model.dao.PedidoDAO;
import model.vo.ClienteVO;
import model.vo.PedidoItemVO;
import model.vo.PedidoVO;
import model.vo.StatusPedidoVO;

public class PedidoController {
	
	private PedidoBO pedidoBO = new PedidoBO();
	private PedidoDAO pedidoDAO = new PedidoDAO();

	public String salvarPedido(ClienteVO cliente, String dataPedido, StatusPedidoVO statusPedido, String observacao, String enderecoEntrega,
			String taxaEntrega, String valorTotal, List<PedidoItemVO> listPeditoItem) {
		
		String mensagem = null;
		
		taxaEntrega = taxaEntrega.replace(".", "");
		taxaEntrega = taxaEntrega.replace(",", "");
		taxaEntrega = taxaEntrega.replace("R$", "");
		taxaEntrega = taxaEntrega.substring(1, taxaEntrega.length());
		int valorEntrega = Integer.parseInt(taxaEntrega);
		
		valorTotal = valorTotal.replace(".", "");
		valorTotal = valorTotal.replace(",", "");
		valorTotal = valorTotal.replace("R$", "");
		valorTotal = valorTotal.substring(1, valorTotal.length());
		int valorPedido = Integer.parseInt(valorTotal);
		

		PedidoVO novoPedido = new PedidoVO((ClienteVO) cliente, dataPedido, (StatusPedidoVO) statusPedido, observacao, enderecoEntrega, valorEntrega, valorPedido);
		mensagem = pedidoBO.salvarPedido(novoPedido, listPeditoItem);
		
		
		return mensagem;
	}

	public ArrayList<PedidoVO> listarPedidos(String parametroBusca) {
		return pedidoDAO.consultarPorParametro(parametroBusca);
	}

	public String cancelarPedido(int idSelecionado, StatusPedidoVO statusPedidoVO) {
		String mensagem = "";
		
		try {
			
			mensagem = pedidoBO.cancelarPedido(idSelecionado, statusPedidoVO);
			
		} catch (NumberFormatException ex) {
			
			mensagem = "Selecione um pedido.";
		}
		
		return mensagem;
	}

	public String atualizarStatusPedido(int idSelecionado, StatusPedidoVO statusPedidoVO) {
		String mensagem = "";
		
		try {
			
			mensagem = pedidoBO.atualizarPedido(idSelecionado, statusPedidoVO);
			
		} catch (NumberFormatException ex) {
			
			mensagem = "Selecione um pedido.";
		}
		
		return mensagem;
	}

	public ArrayList<PedidoVO> listarPedidosCozinha(int status_1, int status_2) {
		return pedidoDAO.consultarPedidosCozinha(status_1, status_2);
	}



}
