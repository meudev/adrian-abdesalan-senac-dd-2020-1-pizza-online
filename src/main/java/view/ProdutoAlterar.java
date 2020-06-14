package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.CategoriaProdutoController;
import controller.ProdutoController;
import model.vo.CategoriaProdutoVO;
import model.vo.ProdutoVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProdutoAlterar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<?> cbCategoria;
	private JTextField txtNomeProduto;
	private JTextArea txtDescricaoProduto;
	private JFormattedTextField txtValor;
	private JTextField txtQuantidade;
	private JCheckBox checkDisponivel;

	public ProdutoAlterar(ProdutoVO produtoSelecionado) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(20dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(20dlu;default)"),}));
		
		JLabel lblProdutosAlterar = new JLabel("Produtos > Alterar Produto");
		lblProdutosAlterar.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblProdutosAlterar, "4, 2, 9, 1");
		
		JLabel lblCategoria = new JLabel("Categoria");
		add(lblCategoria, "4, 4, 7, 1");
		
		CategoriaProdutoController controllerCategoriaProduto = new CategoriaProdutoController();
		ArrayList<CategoriaProdutoVO> listCategoriaProduto = controllerCategoriaProduto.listarTodasCategoriasProdutos();
		cbCategoria = new JComboBox(listCategoriaProduto.toArray());
		add(cbCategoria, "4, 6, 7, 1, fill, fill");
		
		JLabel lblNomeAbreviadoapelido = new JLabel("Nome Abreviado (Apelido)");
		add(lblNomeAbreviadoapelido, "4, 8, 9, 1, fill, default");
		
		txtNomeProduto = new JTextField();
		add(txtNomeProduto, "4, 10, 9, 1, fill, fill");
		txtNomeProduto.setColumns(10);
		
		JLabel lblDescrioDoProduto = new JLabel("Descri\u00E7\u00E3o do Produto (Informa\u00E7\u00E3o Para Tela de Venda");
		add(lblDescrioDoProduto, "4, 12, 9, 1, fill, default");
		
		txtDescricaoProduto = new JTextArea();
		add(txtDescricaoProduto, "4, 14, 9, 1, fill, fill");
		
		JLabel lblValorVenda = new JLabel("Valor Venda");
		add(lblValorVenda, "4, 16");
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		add(lblQuantidade, "6, 16");
		
		JLabel lblDisponivel = new JLabel("Disponivel?");
		add(lblDisponivel, "8, 16, 5, 1, fill, default");
			
		try {
			
			MaskFormatter mascaraValor = new MaskFormatter("##,##");

			txtValor = new JFormattedTextField(mascaraValor);
			add(txtValor, "4, 18, fill, fill");
			txtValor.setColumns(10);
			
		} catch (ParseException ex) {
		    ex.printStackTrace();
		}
		
		txtQuantidade = new JTextField();
		add(txtQuantidade, "6, 18, default, fill");
		txtQuantidade.setColumns(10);
		
		checkDisponivel = new JCheckBox("Sim");
		checkDisponivel.setSelected(true);
		add(checkDisponivel, "8, 18");
		
		JButton btnSalvarAlteracoes = new JButton("Salvar Altera\u00E7\u00F5es");
		btnSalvarAlteracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProdutoController controllerProduto = new ProdutoController();
				
				String mensagem = controllerProduto.salvar((CategoriaProdutoVO) cbCategoria.getSelectedItem(), txtNomeProduto.getText(), txtDescricaoProduto.getText(), txtValor.getText(), txtQuantidade.getText(), checkDisponivel.getText());

				JOptionPane.showMessageDialog(null, mensagem);
				
				limparCampos();
				
			}

		});
		add(btnSalvarAlteracoes, "4, 22, 9, 1, default, fill");
		
		popularCampos(produtoSelecionado);

	}
	
	private void popularCampos(ProdutoVO produtoSelecionado) {
		txtNomeProduto.setText(produtoSelecionado.getNome());
		txtDescricaoProduto.setText(produtoSelecionado.getDescricao());
		txtValor.setText(Integer.toString(produtoSelecionado.getValor()));
		txtQuantidade.setText(Integer.toString(produtoSelecionado.getQuantidade()));
		
	}

	private void limparCampos() {
		txtNomeProduto.setText("");
		txtDescricaoProduto.setText("");
		txtValor.setText("");
		txtQuantidade.setText("");
	}
}
