package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;

public class EntregaRegistrar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNumeroPedido;
	private JTable table;

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
		add(txtNumeroPedido, "6, 6, fill, fill");
		txtNumeroPedido.setColumns(10);
		
		JComboBox cbStatusEntrega = new JComboBox();
		add(cbStatusEntrega, "8, 6, fill, fill");
		
		JButton btnConfirmar = new JButton("Confirmar");
		add(btnConfirmar, "10, 6, default, fill");
		
		table = new JTable();
		add(table, "4, 10, 9, 1, fill, fill");

	}

}
