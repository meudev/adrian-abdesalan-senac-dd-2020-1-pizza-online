package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class CozinhaListar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tablePedidosCozinha;

	public CozinhaListar() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
		
		JLabel lblCozinhaConsultar = new JLabel("Cozinha > Consultar Produ\u00E7\u00E3o");
		lblCozinhaConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblCozinhaConsultar, "4, 2");
		
		tablePedidosCozinha = new JTable();
		add(tablePedidosCozinha, "4, 4, fill, fill");
		
		JButton btnAtualizarLista = new JButton("Atualizar Lista");
		add(btnAtualizarLista, "4, 8, fill, fill");

	}

}
