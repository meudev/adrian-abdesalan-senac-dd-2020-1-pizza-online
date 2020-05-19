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
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(136dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(136dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(53dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(28dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblDataPedido = new JLabel("Data Pedido");
		add(lblDataPedido, "2, 4, right, default");
		
		txtDataPedido = new JTextField();
		txtDataPedido.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		txtDataPedido.setEditable(false);
		add(txtDataPedido, "4, 4, left, default");
		txtDataPedido.setColumns(10);
		
		JLabel lblPedido = new JLabel("Pedido");
		add(lblPedido, "10, 4");
		
		JLabel lblProdutos = new JLabel("Produtos");
		add(lblProdutos, "14, 4");
		
		tablePedido = new JTable();
		add(tablePedido, "10, 6, 1, 19, fill, fill");
		
		tableProdutos = new JTable();
		add(tableProdutos, "14, 6, 1, 27, fill, fill");
		
		JLabel lblTelefone = new JLabel("Telefone");
		add(lblTelefone, "2, 8, right, default");
		
		txtTelefone = new JTextField();
		add(txtTelefone, "4, 8, left, default");
		txtTelefone.setColumns(10);
		
		JButton btnProcurarCliente = new JButton("Procurar Cliente");
		add(btnProcurarCliente, "6, 8, left, default");
		
		JLabel lblNomeDoCliente = new JLabel("Nome do Cliente");
		add(lblNomeDoCliente, "2, 12, 5, 1");
		
		txtNome = new JTextField();
		add(txtNome, "2, 14, 5, 1, fill, default");
		txtNome.setEditable(false);
		txtNome.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		add(lblEndereo, "2, 16, 5, 1");
		
		txtEndereco = new JTextField();
		add(txtEndereco, "2, 18, 5, 1, fill, default");
		txtEndereco.setColumns(10);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		add(lblNmero, "2, 20");
		
		JLabel lblBairro = new JLabel("Bairro");
		add(lblBairro, "4, 20");
		
		JLabel lblCidade = new JLabel("Cidade");
		add(lblCidade, "6, 20");
		
		txtNumero = new JTextField();
		add(txtNumero, "2, 22, center, default");
		txtNumero.setColumns(10);
		
		txtBairro = new JTextField();
		add(txtBairro, "4, 22, fill, default");
		txtBairro.setColumns(10);
		
		txtCidade = new JTextField();
		add(txtCidade, "6, 22, fill, default");
		txtCidade.setColumns(10);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		add(lblObservaes, "10, 26");
		
		JTextArea txtObservacao = new JTextArea();
		add(txtObservacao, "10, 28, 1, 5, fill, fill");
		
		JLabel lblTaxaEntrega = new JLabel("Taxa Entrega");
		add(lblTaxaEntrega, "2, 34");
		
		JLabel lblValorTotalDo = new JLabel("Valor Total do Pedido");
		add(lblValorTotalDo, "4, 34, 3, 1");
		
		txtValorEntrega = new JTextField();
		add(txtValorEntrega, "2, 36, fill, default");
		txtValorEntrega.setColumns(10);
		
		txtTotalPedido = new JTextField();
		add(txtTotalPedido, "4, 36, 3, 1, fill, default");
		txtTotalPedido.setEditable(false);
		txtTotalPedido.setColumns(10);
		
		JButton btnFinalizarPedido = new JButton("Finalizar Pedido");
		add(btnFinalizarPedido, "2, 38, 5, 1");

	}

}
