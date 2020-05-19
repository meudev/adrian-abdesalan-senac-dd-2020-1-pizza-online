package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;

public class ClienteNovo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTelefone;
	private JTextField txtNome;
	private JTextField txtCep;
	private JLabel lblLogradouro;
	private JTextField txtLogradouro;
	private JLabel lblNmero;
	private JTextField txtNumero;
	private JLabel lblComplemento;
	private JTextField txtComplemento;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JLabel lblEstado;
	private JComboBox cbEstado;
	private JLabel lblObservaes;
	private JTextArea txtObservacao;
	private JButton btnSalvarCliente;
	private JLabel lblClientesNovo;

	public ClienteNovo() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				RowSpec.decode("max(30dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
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
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
		
		lblClientesNovo = new JLabel("Clientes > Novo");
		lblClientesNovo.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblClientesNovo, "4, 2, 11, 1");
		
		JLabel lblTelefone = new JLabel("Telefone");
		add(lblTelefone, "4, 4");
		
		txtTelefone = new JTextField();
		add(txtTelefone, "4, 6, fill, fill");
		txtTelefone.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		add(lblNome, "4, 10");
		
		txtNome = new JTextField();
		add(txtNome, "4, 12, 3, 1, fill, fill");
		txtNome.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP");
		add(lblCep, "4, 16");
		
		txtCep = new JTextField();
		add(txtCep, "4, 18, fill, fill");
		txtCep.setColumns(10);
		
		lblLogradouro = new JLabel("Logradouro");
		add(lblLogradouro, "4, 20");
		
		lblNmero = new JLabel("N\u00FAmero");
		add(lblNmero, "10, 20");
		
		lblComplemento = new JLabel("Complemento");
		add(lblComplemento, "14, 20");
		
		txtLogradouro = new JTextField();
		add(txtLogradouro, "4, 22, 3, 1, fill, fill");
		txtLogradouro.setColumns(10);
		
		txtNumero = new JTextField();
		add(txtNumero, "10, 22, fill, fill");
		txtNumero.setColumns(10);
		
		txtComplemento = new JTextField();
		add(txtComplemento, "14, 22, fill, fill");
		txtComplemento.setColumns(10);
		
		lblBairro = new JLabel("Bairro");
		add(lblBairro, "4, 24");
		
		lblCidade = new JLabel("Cidade");
		add(lblCidade, "10, 24");
		
		lblEstado = new JLabel("Estado");
		add(lblEstado, "14, 24");
		
		txtBairro = new JTextField();
		add(txtBairro, "4, 26, 3, 1, fill, fill");
		txtBairro.setColumns(10);
		
		txtCidade = new JTextField();
		add(txtCidade, "10, 26, fill, fill");
		txtCidade.setColumns(10);
		
		cbEstado = new JComboBox();
		add(cbEstado, "14, 26, fill, fill");
		
		lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		add(lblObservaes, "4, 30");
		
		txtObservacao = new JTextArea();
		add(txtObservacao, "4, 32, 11, 1, fill, fill");
		
		btnSalvarCliente = new JButton("Salvar Cliente");
		add(btnSalvarCliente, "4, 36, 11, 1, default, fill");

	}

}
