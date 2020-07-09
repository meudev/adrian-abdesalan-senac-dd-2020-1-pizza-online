package controller;

import java.util.ArrayList;

import model.dao.CodigoPaisDAO;
import model.vo.CodigoPaisVO;

public class CodigoPaisController {
	
	CodigoPaisDAO codigoPaisDAO = new CodigoPaisDAO();

	public ArrayList<CodigoPaisVO> listarTodasCodigoPais() {
		return codigoPaisDAO.consultarTodasCategorias();
	}

}
