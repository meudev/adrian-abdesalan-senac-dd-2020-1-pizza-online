package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.UsuarioController;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtLogin;
	private JPasswordField txtSenha;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setExtendedState(MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TelaInicial() {
		setTitle("Pizza Planet");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
		
		JLabel lblPizzaPlanet = new JLabel("PIZZA PLANET");
		lblPizzaPlanet.setFont(new Font("Tahoma", Font.PLAIN, 50));
		getContentPane().add(lblPizzaPlanet, "4, 4, 5, 1, center, fill");
		
		JLabel lblLogin = new JLabel("Login");
		getContentPane().add(lblLogin, "6, 8");
		
		txtLogin = new JTextField();
		getContentPane().add(txtLogin, "6, 10, fill, fill");
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		getContentPane().add(lblSenha, "6, 12");
		
		txtSenha = new JPasswordField();
		getContentPane().add(txtSenha, "6, 14, fill, fill");
		txtSenha.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//CONECTAR
				UsuarioController controllerUsuario = new UsuarioController();
				
				@SuppressWarnings("deprecation")
				String mensagem = controllerUsuario.conectarUsuario(txtLogin.getText(), txtSenha.getText());
				
				if(mensagem.isEmpty()) {
					
					//ENTRAR	
	            	dispose();
	            	new TelaPrincipal().setVisible(true);
	            	
				} else {
					
					//ERRO
					JOptionPane.showMessageDialog(null, mensagem);
					
				}
				

				
			}
		});
		getContentPane().add(btnEntrar, "6, 18, default, fill");
		
		JLabel lblDesenvoldidoPorAdrian = new JLabel("Desenvolvido por ADRIAN SALOMON FERREIRA ABDESALAN");
		lblDesenvoldidoPorAdrian.setFont(new Font("Tahoma", Font.BOLD, 10));
		getContentPane().add(lblDesenvoldidoPorAdrian, "4, 22, 5, 1, right, fill");
		
	}
	
}
