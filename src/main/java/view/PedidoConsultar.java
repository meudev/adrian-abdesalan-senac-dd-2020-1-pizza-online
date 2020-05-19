package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class PedidoConsultar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtNumeroPedido;
	private JLabel lblPedidosConsultar;

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
				RowSpec.decode("max(20dlu;default)"),}));
		
		lblPedidosConsultar = new JLabel("Pedidos > Consultar Pedido");
		lblPedidosConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblPedidosConsultar, "4, 2, 7, 1");
		
		JLabel lblBuscarPorNmero = new JLabel("Buscar por N\u00FAmero do Pedido");
		add(lblBuscarPorNmero, "6, 4");
		
		txtNumeroPedido = new JTextField();
		add(txtNumeroPedido, "6, 6, fill, fill");
		txtNumeroPedido.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		add(btnBuscar, "8, 6, default, fill");
		
		table = new JTable();
		add(table, "4, 10, 7, 1, fill, fill");

	}

}
