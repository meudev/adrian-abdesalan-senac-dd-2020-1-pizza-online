package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.CategoriaProdutoController;
import controller.ProdutoController;
import model.vo.CategoriaProdutoVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProdutoNovo extends JPanel {
	
	Locale localeBR = new Locale("pt","BR");
	NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

	private static final long serialVersionUID = 1L;
	private JComboBox<?> cbCategoria;
	private JTextField txtNomeProduto;
	private JTextArea txtDescricaoProduto;
	private JTextField txtValor;
	private JTextField txtQuantidade;
	private JCheckBox checkDisponivel;

	public ProdutoNovo() {
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
		
		JLabel lblProdutosNovo = new JLabel("Produtos > Novo Produto");
		lblProdutosNovo.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblProdutosNovo, "4, 2, 9, 1");
		
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
			
		txtValor = new JTextField();
		txtValor.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				atualizarMascaraValor();
			}

		});
		add(txtValor, "4, 18, fill, fill");
		txtValor.setColumns(10);
		
		txtQuantidade = new JTextField();
		add(txtQuantidade, "6, 18, default, fill");
		txtQuantidade.setColumns(10);
		
		checkDisponivel = new JCheckBox("Sim");
		checkDisponivel.setSelected(true);
		add(checkDisponivel, "8, 18");
		
		JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProdutoController controllerProduto = new ProdutoController();
				
				String mensagem = controllerProduto.salvar((CategoriaProdutoVO) cbCategoria.getSelectedItem(), txtNomeProduto.getText(), txtDescricaoProduto.getText(), txtValor.getText(), txtQuantidade.getText(), checkDisponivel.getText());

				JOptionPane.showMessageDialog(null, mensagem);
				
				limparCampos();
				
			}

		});
		add(btnCadastrarProduto, "4, 22, 9, 1, default, fill");

	}
	
	private void atualizarMascaraValor() {
		
		if(txtValor.getText().isEmpty()) {
			txtValor.setText(dinheiro.format(0.00));
		} else {
			double txtValorFormatado = Double.parseDouble(txtValor.getText().replace(",", "."));
			txtValor.setText(dinheiro.format(txtValorFormatado));
		}
		
	}
	
	private void limparCampos() {
		txtNomeProduto.setText("");
		txtDescricaoProduto.setText("");
		txtValor.setText("");
		txtQuantidade.setText("");
	}
}
