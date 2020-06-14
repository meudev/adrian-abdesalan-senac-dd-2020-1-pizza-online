package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.PedidoController;
import controller.StatusPedidoController;
import model.vo.PedidoVO;
import model.vo.StatusPedidoVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntregaRegistrar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNumeroPedido;
	private JButton btnConfirmar;
	private JTable tableEntregas;
	private JComboBox cbStatusEntrega;
	private int numeroPedido = 0;
	
	private ArrayList<PedidoVO> pedidos;
	private String[] nomesColunas = { "PEDIDO Nº", "DATA PEDIDO", "CLIENTE", "VALOR", "STATUS" };

	public EntregaRegistrar() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(100dlu;default)"),
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
				RowSpec.decode("max(20dlu;default)"),}));
		
		JLabel lblEntregarRegistrar = new JLabel("Entregar > Registrar");
		lblEntregarRegistrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblEntregarRegistrar, "4, 2, 9, 1");
		
		JLabel lblConfirmarRetirada = new JLabel("Confirmar Retirada / Entrega");
		add(lblConfirmarRetirada, "6, 4");
		
		txtNumeroPedido = new JTextField();
		txtNumeroPedido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String texto = txtNumeroPedido.getText();

		        try {
		        	numeroPedido = Integer.parseInt(texto);  
		        } catch (NumberFormatException ex) {
		        	JOptionPane.showMessageDialog(null, "Digite apenas números.");
		        }
				
				if (numeroPedido <= 0 ) {
					btnConfirmar.setEnabled(false);
				} else {
					btnConfirmar.setEnabled(true);
				}
				
			}
		});
		add(txtNumeroPedido, "6, 6, fill, fill");
		txtNumeroPedido.setColumns(10);
		
		StatusPedidoController controllerStatusPedido = new StatusPedidoController();
		ArrayList<StatusPedidoVO> listStatusPedido = controllerStatusPedido.listarTodosStatusPedido();
		cbStatusEntrega = new JComboBox(listStatusPedido.toArray());
		add(cbStatusEntrega, "8, 6, fill, fill");
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//ATUALIZAR STATUS DO PEDIDO	
				String mensagem = "";

				PedidoController controllerPedido = new PedidoController();
				mensagem = controllerPedido.atualizarStatusPedido(numeroPedido, (StatusPedidoVO) cbStatusEntrega.getSelectedItem());	
							
				JOptionPane.showMessageDialog(null, mensagem);
				
				iniciarTabelaPedidos();
				
				numeroPedido = 0;
				txtNumeroPedido.setText("");
				btnConfirmar.setEnabled(false);
			}
		});
		add(btnConfirmar, "10, 6, default, fill");
		btnConfirmar.setEnabled(false);
		
		
		tableEntregas = new JTable();
		add(tableEntregas, "4, 10, 9, 1, fill, fill");
		
		iniciarTabelaPedidos();

	}
	
	private void iniciarTabelaPedidos() {
		
		PedidoController controller = new PedidoController();
		pedidos = controller.listarPedidosCozinha(4, 5);

		atualizarTabelaPedidos();
		
	}

	private void atualizarTabelaPedidos() {
		limparTabelaPedidos();
		DefaultTableModel model = (DefaultTableModel) tableEntregas.getModel();

		for (PedidoVO p : pedidos) {

			Object[] novaLinhaDaTabela = new Object[5];
			novaLinhaDaTabela[0] = p.getId();
			novaLinhaDaTabela[1] = p.getDataPedido();
			novaLinhaDaTabela[2] = p.getIdCliente().getNome();
			novaLinhaDaTabela[3] = p.getValorTotal();
			novaLinhaDaTabela[4] = p.getIdStatus().getDescricao();

			model.addRow(novaLinhaDaTabela);
		}
	}
	
	private void limparTabelaPedidos() {
		tableEntregas.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

}
