package model.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import model.vo.ClienteVO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class GeradorPlanilha {

	public void gerarPlanilhaClientes(ArrayList<ClienteVO> clientes, String destinoArquivoNoDisco) {
		String[] nomesColunas = { "NOME", "TELEFONE", "ENDEREÇO", "BAIRRO", "CIDADE" };

		XSSFWorkbook planilha = new XSSFWorkbook();
		XSSFSheet abaPlanilha = planilha.createSheet("Clientes");

		Row linhaCabecalho = abaPlanilha.createRow(0);

		for (int i = 0; i < nomesColunas.length; i++) {
			Cell celula = linhaCabecalho.createCell(i);
			celula.setCellValue(nomesColunas[i]);
		}
		
		int linhaAtual = 1;

		for (ClienteVO clienteAtual : clientes) {
			Row novaLinha = abaPlanilha.createRow(linhaAtual++);

			novaLinha.createCell(0).setCellValue(clienteAtual.getNome());
			novaLinha.createCell(1).setCellValue(clienteAtual.getCodigo().getCodigo() +" "+ clienteAtual.getTelefone());
			novaLinha.createCell(2).setCellValue(clienteAtual.getLogradouro() +", "+ clienteAtual.getNumero() +" - "+ clienteAtual.getComplemento());
			novaLinha.createCell(3).setCellValue(clienteAtual.getBairro());
			novaLinha.createCell(4).setCellValue(clienteAtual.getTelefone());
		}

		for (int i = 0; i < nomesColunas.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}

		salvarNoDisco(planilha, destinoArquivoNoDisco, ".xlsx");
	}

	private String salvarNoDisco(XSSFWorkbook planilha, String caminhoArquivo, String extensao) {
		String mensagem = "";
		FileOutputStream saida = null;

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}
}
