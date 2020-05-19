package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtLogin;
	private JTextField txtSenha;


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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(20dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(75dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(47dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblTitulo = new JLabel("PIZZA PLANET");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 60));
		getContentPane().add(lblTitulo, "2, 2, 3, 1, center, bottom");
		
		JLabel lblLogin = new JLabel("Login: ");
		getContentPane().add(lblLogin, "2, 6, right, default");
		
		txtLogin = new JTextField();
		getContentPane().add(txtLogin, "4, 6, left, default");
		txtLogin.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha: ");
		getContentPane().add(lblSenha, "2, 8, right, default");
		
		txtSenha = new JTextField();
		getContentPane().add(txtSenha, "4, 8, left, default");
		txtSenha.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//entrar
			}
		});
		getContentPane().add(btnEntrar, "4, 10, left, default");
		
	}
	
}
