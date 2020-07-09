package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.ProdutoController;
import model.vo.ProdutoVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class ProdutoConsultar extends JPanel {
	
	TelaPrincipal telaPrincipal = new TelaPrincipal();
	
	Locale localeBR = new Locale("pt","BR");
	NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private JLabel lblProdutosConsultar;
	private JButton btnEditarProduto;
	private JButton btnExcluirProduto;
	private ArrayList<ProdutoVO> produtos;
	private String[] nomesColunas = { "CATEGORIA", "NOME", "VALOR", "QUANTIDADE" };

	public ProdutoConsultar() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
		
		lblProdutosConsultar = new JLabel("Produtos > Consultar Produto");
		lblProdutosConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblProdutosConsultar, "4, 2, 7, 1");
		
		JLabel lblBuscarProdutoPor = new JLabel("Buscar Produto por Nome");
		add(lblBuscarProdutoPor, "6, 4");
		
		textField = new JTextField();
		add(textField, "6, 6, fill, fill");
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProdutoController controller = new ProdutoController();
				produtos = controller.listarProdutos(textField.getText());

				atualizarTabelaProdutos();
			}

		});
		add(btnBuscar, "8, 6, fill, fill");
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = table.getSelectedRow();

				if (indiceSelecionado > 0) {
					btnEditarProduto.setEnabled(true);
					btnExcluirProduto.setEnabled(true);
				} else {
					btnEditarProduto.setEnabled(false);
					btnExcluirProduto.setEnabled(false);
				}
			}
		});
		add(table, "4, 10, 7, 1, fill, fill");
		
		btnEditarProduto = new JButton("Editar Produto");
		btnEditarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linhaSelecionadaNaTabela = table.getSelectedRow();
				ProdutoVO produtoSelecionado = produtos.get(linhaSelecionadaNaTabela - 1);
				
				telaPrincipal.abrirProdutoAlterar(produtoSelecionado);
				
			}
		});
		add(btnEditarProduto, "4, 12, right, fill");
		btnEditarProduto.setEnabled(false);
		
		btnExcluirProduto = new JButton("Excluir Produto");
		btnExcluirProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mensagem = null;
			
				//EXLCUIR
				int linhaSelecionadaNaTabela = table.getSelectedRow();
				ProdutoVO produtoSelecionado = produtos.get(linhaSelecionadaNaTabela - 1);
								
				ProdutoController controllerProduto = new ProdutoController();
				mensagem = controllerProduto.excluirProduto(produtoSelecionado.getId());
				
				iniciarTabelaProdutos();
				
				JOptionPane.showMessageDialog(null, mensagem);
			}
		});
		add(btnExcluirProduto, "10, 12, left, fill");
		btnExcluirProduto.setEnabled(false);
		
		iniciarTabelaProdutos();

	}
	
	private void iniciarTabelaProdutos() {
		
		ProdutoController controller = new ProdutoController();
		produtos = controller.listarProdutos(textField.getText());

		atualizarTabelaProdutos();
		
	}

	private void atualizarTabelaProdutos() {
		limparTabelaProdutos();
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (ProdutoVO p : produtos) {
			
			String valor = Integer.toString(p.getValor());
			String valorDecimal = "R$ "+ valor.substring(0, valor.length()-2) +","+ valor.substring(valor.length()-2, valor.length());

			Object[] novaLinhaDaTabela = new Object[4];
			novaLinhaDaTabela[0] = p.getIdCategoria().getDescricao();
			novaLinhaDaTabela[1] = p.getNome();
			novaLinhaDaTabela[2] = valorDecimal;
			novaLinhaDaTabela[3] = p.getQuantidade();

			model.addRow(novaLinhaDaTabela);
		}
	}
	
	private void limparTabelaProdutos() {
		table.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

}
