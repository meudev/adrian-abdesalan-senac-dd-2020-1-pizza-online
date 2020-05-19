package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;

public class ConfiguracaoUsuarioNovo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtLogin;
	private JTextField txtSenha;
	private JTextField txtEmail;
	private JTextField txtNome;
	private JTable tableUsuarios;

	public ConfiguracaoUsuarioNovo() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(200dlu;default):grow"),
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
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
		
		JLabel lblConfiguraesCadastrar = new JLabel("Configura\u00E7\u00F5es > Cadastrar Usu\u00E1rio");
		lblConfiguraesCadastrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblConfiguraesCadastrar, "4, 2, 5, 1");
		
		JLabel lblNome = new JLabel("Nome");
		add(lblNome, "6, 4");
		
		txtNome = new JTextField();
		add(txtNome, "6, 6, fill, fill");
		txtNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		add(lblEmail, "6, 8");
		
		txtEmail = new JTextField();
		add(txtEmail, "6, 10, fill, fill");
		txtEmail.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login");
		add(lblLogin, "6, 12");
		
		txtLogin = new JTextField();
		add(txtLogin, "6, 14, fill, fill");
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		add(lblSenha, "6, 16");
		
		txtSenha = new JTextField();
		add(txtSenha, "6, 18, fill, fill");
		txtSenha.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		add(btnCadastrar, "6, 22, default, fill");
		
		JSeparator separator = new JSeparator();
		add(separator, "6, 26");
		
		JLabel lblUsuriosCadastrados = new JLabel("Usu\u00E1rios Cadastrados");
		add(lblUsuriosCadastrados, "6, 28, default, bottom");
		
		tableUsuarios = new JTable();
		add(tableUsuarios, "6, 30, fill, fill");

	}

}
