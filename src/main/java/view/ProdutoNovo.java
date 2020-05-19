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

public class ProdutoNovo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNomeProduto;
	private JTextField txtValor;

	public ProdutoNovo() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
		
		JLabel lblCategoria = new JLabel("Categoria");
		add(lblCategoria, "4, 4, 7, 1");
		
		JComboBox cbCategoria = new JComboBox();
		add(cbCategoria, "4, 6, 7, 1, fill, default");
		
		JLabel lblNomeAbreviadoapelido = new JLabel("Nome Abreviado (Apelido)");
		add(lblNomeAbreviadoapelido, "4, 8, 7, 1");
		
		txtNomeProduto = new JTextField();
		add(txtNomeProduto, "4, 10, 9, 1, fill, default");
		txtNomeProduto.setColumns(10);
		
		JLabel lblDescrioDoProduto = new JLabel("Descri\u00E7\u00E3o do Produto (Informa\u00E7\u00E3o Para Tela de Venda");
		add(lblDescrioDoProduto, "4, 12, 9, 1");
		
		JTextArea txtDescricaoProduto = new JTextArea();
		add(txtDescricaoProduto, "4, 14, 9, 1, fill, fill");
		
		JLabel lblValorVenda = new JLabel("Valor Venda");
		add(lblValorVenda, "4, 16");
		
		txtValor = new JTextField();
		add(txtValor, "4, 18, fill, default");
		txtValor.setColumns(10);
		
		JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
		add(btnCadastrarProduto, "4, 22, 9, 1");

	}

}
