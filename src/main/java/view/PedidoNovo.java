package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.ClienteController;
import controller.PedidoController;
import controller.ProdutoController;
import controller.StatusPedidoController;
import model.vo.ClienteVO;
import model.vo.PedidoItemVO;
import model.vo.ProdutoVO;
import model.vo.StatusPedidoVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PedidoNovo extends JPanel {
	
	ClienteVO cliente;

	private static final long serialVersionUID = 1L;
	private JTextField txtDataPedido;
	private JTextField txtTelefone;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtValorEntrega;
	private JTextField txtTotalPedido;
	private JTextField txtProdutoSelecionado;
	
	private JTable tableProdutos;
	private ArrayList<ProdutoVO> produtos;
	private String[] nomesColunas = { "NOME", "VALOR", "QUANTIDADE" };
	
	private JTable tablePedido;
	private List<PedidoItemVO> listPedidoItem = new ArrayList<PedidoItemVO>();
	private String[] nomesColunasPedido = { "NOME", "VALOR", "QUANTIDADE" };
	
	JButton btnFinalizarPedido;
	JButton btnAdicionarNoPedido;
	
	String nome;
	String endereco;
	String numero;
	String bairro;
	String cidade;

	public PedidoNovo() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(136dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(136dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(53dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(28dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
		
		JLabel lblPedidosNovo = new JLabel("Pedidos > Novo Pedido");
		lblPedidosNovo.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblPedidosNovo, "4, 2, 13, 1");
		
		StatusPedidoController controllerStatusPedido = new StatusPedidoController();
		ArrayList<StatusPedidoVO> listStatusPedido = controllerStatusPedido.listarTodosStatusPedido();
		
		JLabel lblDataPedido = new JLabel("Data Pedido");
		add(lblDataPedido, "4, 4, right, default");
		
		txtDataPedido = new JTextField();
		txtDataPedido.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		txtDataPedido.setEditable(false);
		add(txtDataPedido, "6, 4, fill, fill");
		txtDataPedido.setColumns(10);
		
		JLabel lblPedido = new JLabel("Pedido");
		add(lblPedido, "12, 4");
		
		JLabel lblProdutos = new JLabel("Produtos");
		add(lblProdutos, "16, 4");
		
		tablePedido = new JTable();
//		tablePedido.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int indiceSelecionado = tablePedido.getSelectedRow();
//				
//				int linhaSelecionadaNaTabela = tablePedido.getSelectedRow();
//				PedidoItemVO itemPedidoSelecionado = itensPedido.get(linhaSelecionadaNaTabela - 1);
//
////				if (indiceSelecionado > 0) {
////					txtProdutoSelecionado.setText(itemPedidoSelecionado.getNome());
////					btnAdicionarNoPedido.setEnabled(true);
////				} else {
////					txtProdutoSelecionado.setText("");
////					btnAdicionarNoPedido.setEnabled(false);
////				}
//			}
//		});
		add(tablePedido, "12, 6, 1, 19, fill, fill");
		
		tableProdutos = new JTable();
		tableProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tableProdutos.getSelectedRow();
				
				int linhaSelecionadaNaTabela = tableProdutos.getSelectedRow();
				ProdutoVO produtoSelecionado = produtos.get(linhaSelecionadaNaTabela - 1);

				if (indiceSelecionado > 0) {
					txtProdutoSelecionado.setText(produtoSelecionado.getNome());
					btnAdicionarNoPedido.setEnabled(true);
				} else {
					txtProdutoSelecionado.setText("");
					btnAdicionarNoPedido.setEnabled(false);
				}
			}
		});
		add(tableProdutos, "16, 6, 1, 27, fill, fill");
		
		JLabel lblTelefone = new JLabel("Telefone");
		add(lblTelefone, "4, 8, right, default");
		
		txtTelefone = new JTextField();
		add(txtTelefone, "6, 8, left, fill");
		txtTelefone.setColumns(10);
		
		JButton btnProcurarCliente = new JButton("Procurar Cliente");
		btnProcurarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PROCURAR CLIENTE
				ClienteController controllerCliente = new ClienteController();
				
				cliente = controllerCliente.buscarCliente(txtTelefone.getText());
				
				if(cliente == null) {
					
					txtNome.setText(null);
					txtEndereco.setText(null);
					txtNumero.setText(null);
					txtBairro.setText(null);
					txtCidade.setText(null);
					
					JOptionPane.showMessageDialog(null, "Telefone não cadastrado no sistema.");
					
				} else {
					
					txtNome.setText(cliente.getNome());
					txtEndereco.setText(cliente.getLogradouro());
					txtNumero.setText(cliente.getNumero());
					txtBairro.setText(cliente.getBairro());
					txtCidade.setText(cliente.getCidade());
					
					validaFinalizarPedido();
				}
				
				
			}
		});
		add(btnProcurarCliente, "8, 8, left, fill");
		
		JLabel lblNomeDoCliente = new JLabel("Nome do Cliente");
		add(lblNomeDoCliente, "4, 12, 5, 1");
		
		txtNome = new JTextField();
		add(txtNome, "4, 14, 5, 1, fill, fill");
		txtNome.setEditable(false);
		txtNome.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		add(lblEndereo, "4, 16, 5, 1");
		
		txtEndereco = new JTextField();
		txtEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validaFinalizarPedido();
			}
		});
		add(txtEndereco, "4, 18, 5, 1, fill, fill");
		txtEndereco.setColumns(10);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		add(lblNmero, "4, 20");
		
		JLabel lblBairro = new JLabel("Bairro");
		add(lblBairro, "6, 20");
		
		JLabel lblCidade = new JLabel("Cidade");
		add(lblCidade, "8, 20");
		
		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validaFinalizarPedido();
			}
		});
		add(txtNumero, "4, 22, center, fill");
		txtNumero.setColumns(10);
		
		txtBairro = new JTextField();
		add(txtBairro, "6, 22, fill, fill");
		txtBairro.setColumns(10);
		
		txtCidade = new JTextField();
		add(txtCidade, "8, 22, fill, fill");
		txtCidade.setColumns(10);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		add(lblObservaes, "12, 26");
		
		JTextArea txtObservacao = new JTextArea();
		add(txtObservacao, "12, 28, 1, 5, fill, fill");
		
		JLabel lblTaxaEntrega = new JLabel("Taxa Entrega");
		add(lblTaxaEntrega, "4, 34");
		
		JLabel lblValorTotalDo = new JLabel("Valor Total do Pedido");
		add(lblValorTotalDo, "6, 34, 3, 1");
		
		JLabel lblProdutoSelecionado = new JLabel("Produto Selecionado");
		add(lblProdutoSelecionado, "16, 34");
		
		txtValorEntrega = new JTextField();
		txtValorEntrega.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				atualizarTabelaPedidos();
			}
		});
		add(txtValorEntrega, "4, 36, fill, fill");
		txtValorEntrega.setColumns(10);
		txtValorEntrega.setText("0,00");
		
		txtTotalPedido = new JTextField();
		add(txtTotalPedido, "6, 36, 3, 1, fill, fill");
		txtTotalPedido.setEditable(false);
		txtTotalPedido.setColumns(10);
		
		txtProdutoSelecionado = new JTextField();
		add(txtProdutoSelecionado, "16, 36, fill, fill");
		txtProdutoSelecionado.setEditable(false);
		txtProdutoSelecionado.setColumns(10);
		
		btnFinalizarPedido = new JButton("Finalizar Pedido");
		btnFinalizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//FINALIZAR PEDIDO
				
				String enderecoEntrega = txtEndereco.getText() +", "+ txtNumero.getText() +" - "+ txtBairro.getText() +" - "+ txtCidade.getText();
				PedidoController controllerPedido = new PedidoController();
				
				String mensagem = controllerPedido.salvarPedido((ClienteVO) cliente, txtDataPedido.getText(), (StatusPedidoVO) listStatusPedido.get(0), txtObservacao.getText(), enderecoEntrega, txtValorEntrega.getText(), txtTotalPedido.getText(), listPedidoItem );

				JOptionPane.showMessageDialog(null, mensagem);
				
				limparCampos();
				
				
			}

		});
		add(btnFinalizarPedido, "4, 38, 5, 1, default, fill");
		btnFinalizarPedido.setEnabled(false);
		
		btnAdicionarNoPedido = new JButton("Adicionar no Pedido");
		btnAdicionarNoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//ADICIONAR PRODUTO AO PEDIDO
				
				int linhaSelecionadaNaTabela = tableProdutos.getSelectedRow();
				ProdutoVO produtoSelecionado = produtos.get(linhaSelecionadaNaTabela - 1);
				
				PedidoItemVO itemAdicionado = new PedidoItemVO();
				itemAdicionado.setIdProduto(produtoSelecionado);
				itemAdicionado.setQuantidade(1);
				itemAdicionado.setValor(produtoSelecionado.getValor());
				
				listPedidoItem.add(itemAdicionado);
				atualizarTabelaPedidos();
				atualizarTabelaProdutos();
				
				JOptionPane.showMessageDialog(null, "Produto Adicionado");
			}
		});
		add(btnAdicionarNoPedido, "16, 38, fill, fill");
		btnAdicionarNoPedido.setEnabled(false);

		iniciarTabelaProdutos();
		iniciarTabelaPedidos();
	}
	
	// MONTA A TABELA PRODUTOS
	private void iniciarTabelaProdutos() {
		
		ProdutoController controller = new ProdutoController();
		produtos = controller.listarProdutos("");

		atualizarTabelaProdutos();
		
	}

	private void atualizarTabelaProdutos() {
		limparTabelaProdutos();
		DefaultTableModel model = (DefaultTableModel) tableProdutos.getModel();

		for (ProdutoVO p : produtos) {

			Object[] novaLinhaDaTabela = new Object[4];
			novaLinhaDaTabela[0] = p.getNome();
			novaLinhaDaTabela[1] = p.getValor();
			novaLinhaDaTabela[2] = p.getQuantidade();

			model.addRow(novaLinhaDaTabela);
		}
	}
	
	private void limparTabelaProdutos() {
		tableProdutos.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}
	
	// MONTA A TABELA DE ITEM DOS PEDIDO
	private void iniciarTabelaPedidos() {
		
		atualizarTabelaPedidos();
		
	}

	private void atualizarTabelaPedidos() {
		limparTabelaPedidos();
		DefaultTableModel model = (DefaultTableModel) tablePedido.getModel();
		
		int somaTotal = 0;
		String taxaTexto = txtValorEntrega.getText();
		taxaTexto = taxaTexto.replace(",", "");
		taxaTexto = taxaTexto.replace(".", "");
		taxaTexto = (taxaTexto != null) ? taxaTexto : "0";
		int taxa = Integer.parseInt(taxaTexto);

		for (PedidoItemVO p : listPedidoItem) {

			Object[] novaLinhaDaTabela = new Object[4];
			novaLinhaDaTabela[0] = p.getIdProduto().getNome();
			novaLinhaDaTabela[1] = p.getValor();
			novaLinhaDaTabela[2] = p.getQuantidade();
			
			somaTotal = somaTotal + p.getValor();
			
			model.addRow(novaLinhaDaTabela);
		}
		
		somaTotal = somaTotal + taxa;
		String somaTotalTexto = Integer.toString(somaTotal);
		txtTotalPedido.setText(somaTotalTexto);
		
		validaFinalizarPedido();
	}
	
	private void limparTabelaPedidos() {
		tablePedido.setModel(new DefaultTableModel(new Object[][] { nomesColunasPedido, }, nomesColunasPedido));
	}
	
	private void validaFinalizarPedido() {
		
		int totalPedido = Integer.parseInt(txtTotalPedido.getText());
		String nome = txtNome.getText();
		String endereco = txtEndereco.getText();
		String numero = txtNumero.getText();
		
		System.out.println(totalPedido);
		System.out.println(nome);
		System.out.println(endereco);
		System.out.println(numero);
		
		if(totalPedido > 0) {
			if(nome.isEmpty() && endereco.isEmpty() && numero.isEmpty()) {
				System.out.println("VERDADEIRO");
				btnFinalizarPedido.setEnabled(false);
			} else {
				System.out.println("FALSE2");
				btnFinalizarPedido.setEnabled(true);
			}
		} else {
			System.out.println("FALSE");
			btnFinalizarPedido.setEnabled(false);
		}
		
	}
	
	private void limparCampos() {
		txtDataPedido.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		txtTelefone.setText(null);
		txtNome.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		txtValorEntrega.setText(null);
		txtTotalPedido.setText(null);
		txtProdutoSelecionado.setText(null);
		listPedidoItem = null;
	}

}
