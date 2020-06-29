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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PedidoConsultar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtNumeroPedido;
	private JLabel lblPedidosConsultar;
	private ArrayList<PedidoVO> pedidos;
	private String[] nomesColunas = { "PEDIDO Nº", "DATA PEDIDO", "CLIENTE", "VALOR", "STATUS" };
	private JButton btnCancelarPedido;

	public PedidoConsultar() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default):grow"),
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
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
		
		lblPedidosConsultar = new JLabel("Pedidos > Consultar Pedido");
		lblPedidosConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblPedidosConsultar, "4, 2, 7, 1");
		
		StatusPedidoController controllerStatusPedido = new StatusPedidoController();
		ArrayList<StatusPedidoVO> listStatusPedido = controllerStatusPedido.listarTodosStatusPedido();
		
		JLabel lblBuscarPorNmero = new JLabel("Buscar por N\u00FAmero do Pedido");
		add(lblBuscarPorNmero, "6, 4");
		
		txtNumeroPedido = new JTextField();
		add(txtNumeroPedido, "6, 6, fill, fill");
		txtNumeroPedido.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PedidoController controllerPedido = new PedidoController();
				pedidos = controllerPedido.listarPedidos(txtNumeroPedido.getText());

				atualizarTabelaPedidos();
			}
		});
		add(btnBuscar, "8, 6, default, fill");
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = table.getSelectedRow();

				if (indiceSelecionado > 0) {
					btnCancelarPedido.setEnabled(true);
				} else {
					btnCancelarPedido.setEnabled(false);
				}
			}
		});
		add(table, "4, 10, 7, 1, fill, fill");
		
		btnCancelarPedido = new JButton("Cancelar Pedido");
		btnCancelarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//CANCELAR PEDIDO
				String mensagem = null;

				int linhaSelecionadaNaTabela = table.getSelectedRow();
				PedidoVO pedidoSelecionado = pedidos.get(linhaSelecionadaNaTabela - 1);
								
				PedidoController controllerPedido = new PedidoController();
				mensagem = controllerPedido.cancelarPedido(pedidoSelecionado.getId(), (StatusPedidoVO) listStatusPedido.get(1));
				
				iniciarTabelaPedidos();
								
				JOptionPane.showMessageDialog(null, mensagem);
				
			}
		});
		add(btnCancelarPedido, "10, 12, left, fill");
		btnCancelarPedido.setEnabled(false);

		iniciarTabelaPedidos();
	}
	
	private void iniciarTabelaPedidos() {
		
		PedidoController controller = new PedidoController();
		pedidos = controller.listarPedidos(txtNumeroPedido.getText());

		atualizarTabelaPedidos();
		
	}

	private void atualizarTabelaPedidos() {
		limparTabelaPedidos();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		for (PedidoVO p : pedidos) {

			Object[] novaLinhaDaTabela = new Object[5];
			novaLinhaDaTabela[0] = p.getId();
			novaLinhaDaTabela[1] = p.getDataPedido();
			novaLinhaDaTabela[2] = p.getIdCliente().getNome();
			novaLinhaDaTabela[3] = Integer.toString(p.getValorTotal()).substring(0,2)+","+Integer.toString(p.getValorTotal()).substring(2,Integer.toString(p.getValorTotal()).length());
			novaLinhaDaTabela[4] = p.getIdStatus().getDescricao();

			model.addRow(novaLinhaDaTabela);
		}
	}
	
	private void limparTabelaPedidos() {
		table.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

}
