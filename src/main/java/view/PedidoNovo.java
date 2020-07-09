package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.ClienteController;
import controller.CodigoPaisController;
import controller.PedidoController;
import controller.ProdutoController;
import controller.StatusPedidoController;
import model.vo.ClienteVO;
import model.vo.CodigoPaisVO;
import model.vo.PedidoItemVO;
import model.vo.ProdutoVO;
import model.vo.StatusPedidoVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class PedidoNovo extends JPanel {
	
	ClienteVO cliente;
	
	Locale localeBR = new Locale("pt","BR");
	NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

	private static final long serialVersionUID = 1L;
	private JTextField txtDataPedido;
	private JFormattedTextField txtTelefone;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtValorEntrega;
	private JTextField txtTotalPedido;
	private JTextField txtProdutoSelecionado;
	
	private JComboBox<?> cbPais;
	
	private JTable tableProdutos;
	private ArrayList<ProdutoVO> produtos;
	private String[] nomesColunas = { "PRODUTO", "VALOR", "ESTOQUE" };
	
	private JTable tablePedido;
	private List<PedidoItemVO> listPedidoItem = new ArrayList<PedidoItemVO>();
	private String[] nomesColunasPedido = { "PRODUTO", "VALOR", "QUANTIDADE" };
	
	private MaskFormatter mascaraNula;
	private MaskFormatter mascaraTelefoneFixo;
	private MaskFormatter mascaraTelefoneCelular;
	
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
				ColumnSpec.decode("70px"),
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
		
		JLabel label = new JLabel("País");
		add(label, "4, 6, default, bottom");
		
		JLabel lblTelefone = new JLabel("Telefone");
		add(lblTelefone, "6, 6, left, bottom");
		
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
		
		CodigoPaisController controllerCodigoPais = new CodigoPaisController();
		ArrayList<CodigoPaisVO> listCodigoPais = controllerCodigoPais.listarTodasCodigoPais();
		cbPais = new JComboBox(listCodigoPais.toArray());
		cbPais.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent  e) {
				//atualizarMascaraTelefone(cbPais.getSelectedItem());
			}

		});
		add(cbPais, "4, 8, fill, fill");
		
		txtTelefone = new JFormattedTextField();
		txtTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				atualizarMascaraTelefone();
			}
		});
		txtTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validaMascaraTelefone();
			}

		});
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
		txtValorEntrega.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				atualizarMascaraValor(txtValorEntrega.getText());
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
			
			String valor = Integer.toString(p.getValor());
			String valorDecimal = "R$ "+ valor.substring(0, valor.length()-2) +","+ valor.substring(valor.length()-2, valor.length());

			Object[] novaLinhaDaTabela = new Object[4];
			novaLinhaDaTabela[0] = p.getNome();
			novaLinhaDaTabela[1] = valorDecimal;
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
		taxaTexto = taxaTexto.replace("R$", "");
		taxaTexto = taxaTexto.replace(",", "");
		taxaTexto = taxaTexto.replace(".", "");
		if(taxaTexto.length() > 3) {
			taxaTexto = taxaTexto.substring(1, taxaTexto.length());
		} else {
			taxaTexto = (taxaTexto != null) ? taxaTexto : "0";
		}

		int taxa = 0;

		try {
			taxa = Integer.parseInt(taxaTexto);
		} catch (NumberFormatException ex) {
			System.out.println("Erro na conversão do parseInt. Causa: "+ ex);
		}

		for (PedidoItemVO p : listPedidoItem) {
			
			String valor = Integer.toString(p.getValor());
			String valorDecimal = "R$ "+ valor.substring(0, valor.length()-2) +","+ valor.substring(valor.length()-2, valor.length());	

			Object[] novaLinhaDaTabela = new Object[4];
			novaLinhaDaTabela[0] = p.getIdProduto().getNome();
			novaLinhaDaTabela[1] = valorDecimal;
			novaLinhaDaTabela[2] = p.getQuantidade();
			
			somaTotal = somaTotal + p.getValor();
			
			model.addRow(novaLinhaDaTabela);
		}
		
		somaTotal = somaTotal + taxa;
		String somaTotalTexto = Integer.toString(somaTotal);
		
		if(somaTotalTexto.length() < 2) {
			somaTotalTexto = "00" + somaTotalTexto;
		} else if (somaTotalTexto.length() == 2) {
			somaTotalTexto = "0" + somaTotalTexto;
		}
		
		String valorTotalDecimal = "R$ "+ somaTotalTexto.substring(0, somaTotalTexto.length()-2) +","+ somaTotalTexto.substring(somaTotalTexto.length()-2, somaTotalTexto.length());	
		txtTotalPedido.setText(valorTotalDecimal);

		
		validaFinalizarPedido();
	}
	
	private void limparTabelaPedidos() {
		tablePedido.setModel(new DefaultTableModel(new Object[][] { nomesColunasPedido, }, nomesColunasPedido));
	}
	
	private void validaFinalizarPedido() {
		
		String valorLimpo = txtTotalPedido.getText();
		valorLimpo = valorLimpo.replace("R$", "");
		valorLimpo = valorLimpo.replace(",", "");
		valorLimpo = valorLimpo.replace(".", "");
		valorLimpo = valorLimpo.substring(1, valorLimpo.length());
		
		int totalPedido = Integer.parseInt(valorLimpo);
		String nome = txtNome.getText();
		String endereco = txtEndereco.getText();
		String numero = txtNumero.getText();
		
		if(totalPedido > 0) {
			if(nome.isEmpty() && endereco.isEmpty() && numero.isEmpty()) {
				btnFinalizarPedido.setEnabled(false);
			} else {
				btnFinalizarPedido.setEnabled(true);
			}
		} else {
			btnFinalizarPedido.setEnabled(false);
		}
		
	}
	
	private void atualizarMascaraValor(String valor) {
		String valorTaxaTexto = valor;
		valorTaxaTexto = valorTaxaTexto.replace(",", "");
		valorTaxaTexto = valorTaxaTexto.replace(".", "");
		
		if(valorTaxaTexto.length() < 2) {
			valorTaxaTexto = "00" + valorTaxaTexto;
		} else if (valor.length() == 2) {
			valorTaxaTexto = "0" + valorTaxaTexto;
		}
		
		if(valor.isEmpty()) {
			txtValorEntrega.setText(dinheiro.format(0.00));
		} else {
			String valorTotalDecimal = "R$ "+ valorTaxaTexto.substring(0, valorTaxaTexto.length()-2) +","+ valorTaxaTexto.substring(valorTaxaTexto.length()-2, valorTaxaTexto.length());	
			txtValorEntrega.setText(valorTotalDecimal);
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
	
	private void atualizarMascaraTelefone() {
		String codigoPais = cbPais.getSelectedItem().toString();
		
		if(codigoPais.equals("+55")) {
			mascararCampo(1);
		} else {
			mascararCampo(0);
		}
		
	}
	
	private void definirMascaramento(){
		try{
			mascaraNula = new MaskFormatter();
			mascaraTelefoneCelular = new MaskFormatter("(##) # ####-####");
			mascaraTelefoneFixo = new MaskFormatter("(##) ####-####");
		}catch(ParseException pex){
			System.out.println("ERRO: " + pex.getMessage());
		}
	}
	
	private void mascararCampo(int tipoMascaramento){
		definirMascaramento();
		
		switch(tipoMascaramento){
			case 0:
				txtTelefone.setValue(null);
				txtTelefone.setFormatterFactory(null);
				System.out.println("sem mascara");
				break;
			case 1:
				txtTelefone.setValue(null);
				txtTelefone.setFormatterFactory(new DefaultFormatterFactory(mascaraTelefoneCelular));
				System.out.println("celular");
				break;
			case 2:
				txtTelefone.setValue(null);
				txtTelefone.setFormatterFactory(new DefaultFormatterFactory(mascaraTelefoneFixo));
				System.out.println("fixo");
		}
	}
	
	private void validaMascaraTelefone() {
		String codigoPais = cbPais.getSelectedItem().toString();
		int tamanhoNumero = txtTelefone.getText().trim().length();
		
		if(codigoPais.equals("+55") && tamanhoNumero == 15) {
			String numeroDigitado = txtTelefone.getText().substring(0, txtTelefone.getText().length() - 1);
			numeroDigitado = numeroDigitado.replaceAll(" ", "");
			numeroDigitado = numeroDigitado.replaceAll("[(,),-]", "");
			
			mascararCampo(2);
			
			txtTelefone.setText(numeroDigitado);
			
		}
	}

}
