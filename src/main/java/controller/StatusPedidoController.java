package controller;

import java.util.ArrayList;

import model.dao.StatusPedidoDAO;
import model.vo.StatusPedidoVO;

public class StatusPedidoController {
	
	private StatusPedidoDAO statusPedidoDAO = new StatusPedidoDAO();
	
	public ArrayList<StatusPedidoVO> listarTodosStatusPedido() {

			return statusPedidoDAO.consultarTodosStatusPedido();
	}

}
