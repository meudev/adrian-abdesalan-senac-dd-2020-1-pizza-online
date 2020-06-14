package controller;

import java.util.ArrayList;

import model.dao.PedidoItemDAO;
import model.vo.PedidoItemVO;

public class PedidoItemController {
	
	PedidoItemDAO pedidoItemDAO = new PedidoItemDAO();

	public ArrayList<PedidoItemVO> listarItensPedido(int id) {
		return pedidoItemDAO.consultarPorParametro(id);
	}

}
