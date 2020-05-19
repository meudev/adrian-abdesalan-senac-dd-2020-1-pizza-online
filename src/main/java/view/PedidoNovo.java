package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;

public class PedidoNovo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtDataPedido;
	private JTextField txtTelefone;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTable tableProdutos;
	private JTable tablePedido;
	private JTextField txtValorEntrega;
	private JTextField txtTotalPedido;

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
		add(tablePedido, "12, 6, 1, 19, fill, fill");
		
		tableProdutos = new JTable();
		add(tableProdutos, "16, 6, 1, 27, fill, fill");
		
		JLabel lblTelefone = new JLabel("Telefone");
		add(lblTelefone, "4, 8, right, default");
		
		txtTelefone = new JTextField();
		add(txtTelefone, "6, 8, left, fill");
		txtTelefone.setColumns(10);
		
		JButton btnProcurarCliente = new JButton("Procurar Cliente");
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
		add(txtEndereco, "4, 18, 5, 1, fill, fill");
		txtEndereco.setColumns(10);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		add(lblNmero, "4, 20");
		
		JLabel lblBairro = new JLabel("Bairro");
		add(lblBairro, "6, 20");
		
		JLabel lblCidade = new JLabel("Cidade");
		add(lblCidade, "8, 20");
		
		txtNumero = new JTextField();
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
		
		txtValorEntrega = new JTextField();
		add(txtValorEntrega, "4, 36, fill, fill");
		txtValorEntrega.setColumns(10);
		
		txtTotalPedido = new JTextField();
		add(txtTotalPedido, "6, 36, 3, 1, fill, fill");
		txtTotalPedido.setEditable(false);
		txtTotalPedido.setColumns(10);
		
		JButton btnFinalizarPedido = new JButton("Finalizar Pedido");
		add(btnFinalizarPedido, "4, 38, 5, 1, default, fill");

	}

}
