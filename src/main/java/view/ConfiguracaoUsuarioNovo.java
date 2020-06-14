package view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.UsuarioController;
import model.vo.UsuarioVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;

public class ConfiguracaoUsuarioNovo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JTextField txtEmail;
	private JTextField txtNome;
	private JButton btnCadastrar;
	private JTable tableUsuarios;
	
	private ArrayList<UsuarioVO> usuarios;
	private String[] nomesColunas = { "USUARIO", "EMAIL", "LOGIN" };

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
		
		txtSenha = new JPasswordField();
		add(txtSenha, "6, 18, fill, fill");
		txtSenha.setColumns(10);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				//CADASTRAR NOVO USUARIO
				String txtSenhaTratada = md5(txtSenha.getText());
				
				UsuarioController controllerUsuario = new UsuarioController();
				
				String mensagem = controllerUsuario.cadastrarUsuario(txtNome.getText(), txtEmail.getText(), txtLogin.getText(), txtSenhaTratada);

				iniciarTabelaUsuarios();
				
				JOptionPane.showMessageDialog(null, mensagem);
				
				limparCampos();
				
				
			}

		});
		add(btnCadastrar, "6, 22, default, fill");
		
		JSeparator separator = new JSeparator();
		add(separator, "6, 26");
		
		JLabel lblUsuriosCadastrados = new JLabel("Usu\u00E1rios Cadastrados");
		add(lblUsuriosCadastrados, "6, 28, default, bottom");
		
		tableUsuarios = new JTable();
		add(tableUsuarios, "6, 30, fill, fill");

		iniciarTabelaUsuarios();
		
	}
	
	private void iniciarTabelaUsuarios() {
		
		UsuarioController controller = new UsuarioController();
		usuarios = controller.listarUsusarios("");

		atualizarTabelaUsuario();
		
	}

	private void atualizarTabelaUsuario() {
		limparTabelaUsuario();
		DefaultTableModel model = (DefaultTableModel) tableUsuarios.getModel();

		for (UsuarioVO u : usuarios) {

			Object[] novaLinhaDaTabela = new Object[3];
			novaLinhaDaTabela[0] = u.getNome();
			novaLinhaDaTabela[1] = u.getEmail();
			novaLinhaDaTabela[2] = u.getLogin();
					
			model.addRow(novaLinhaDaTabela);
		}
	}
	
	private void limparTabelaUsuario() {
		tableUsuarios.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}
	
	private void limparCampos() {
		txtNome.setText("");
		txtEmail.setText("");
		txtLogin.setText("");
		txtSenha.setText("");
		
	}
	
	public static String md5(String valor) {
	     
	    String md5 = null;
	     
	    if(null == valor) return null;
	     
	    try {
	         
	    MessageDigest digest = MessageDigest.getInstance("MD5");   
	    digest.update(valor.getBytes(), 0, valor.length());
	    md5 = new BigInteger(1, digest.digest()).toString(16);

	    } catch (NoSuchAlgorithmException e) {

	        e.printStackTrace();
	    }
	    return md5;
	}

}
