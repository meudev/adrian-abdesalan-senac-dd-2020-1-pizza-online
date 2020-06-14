package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.CategoriaProdutoController;
import model.vo.CategoriaProdutoVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ProdutoCategoria extends JPanel {

	private static final long serialVersionUID = 1808487871304958059L;
	private JTextField txtCategoria;
	private JTable tabelaCategorias;
	
	private ArrayList<CategoriaProdutoVO> categorias;
	private String[] nomesColunas = { "IDENTIFICADOR", "CATEGORIA" };

	public ProdutoCategoria() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(200dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
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
		
		JLabel lblProdutoCadastrar = new JLabel("Produto > Cadastrar Categoria");
		lblProdutoCadastrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblProdutoCadastrar, "4, 2, 5, 1, fill, default");
		
		JLabel lblNovaCategoria = new JLabel("Nova Categoria");
		add(lblNovaCategoria, "6, 6");
		
		txtCategoria = new JTextField();
		add(txtCategoria, "6, 8, fill, fill");
		txtCategoria.setColumns(10);
		
		JButton btnAdicionarCategoria = new JButton("Adicionar Categoria");
		btnAdicionarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//CADASTRAR NOVA CATEGORIA
				String mensagem = "";
				
				CategoriaProdutoController controllerCategoriaProduto = new CategoriaProdutoController();	
				mensagem = controllerCategoriaProduto.cadastrarCategoriaProduto(txtCategoria.getText());

				iniciarTabelaCategorias();
				
				JOptionPane.showMessageDialog(null, mensagem);
				
				limparCampos();
				
				
			}


		});
		add(btnAdicionarCategoria, "6, 10, default, fill");
		
		tabelaCategorias = new JTable();
		add(tabelaCategorias, "6, 14, fill, fill");
		
		iniciarTabelaCategorias();

	}
	
	private void iniciarTabelaCategorias() {
		
		CategoriaProdutoController controllerCategoriaProduto = new CategoriaProdutoController();
		categorias = controllerCategoriaProduto.listarCategorias("");

		atualizarTabelaCategorias();
		
	}

	private void atualizarTabelaCategorias() {
		limparTabelaCategorias();
		DefaultTableModel model = (DefaultTableModel) tabelaCategorias.getModel();

		for (CategoriaProdutoVO c : categorias) {

			Object[] novaLinhaDaTabela = new Object[2];
			novaLinhaDaTabela[0] = c.getId();
			novaLinhaDaTabela[1] = c.getDescricao();
					
			model.addRow(novaLinhaDaTabela);
		}
	}
	
	private void limparTabelaCategorias() {
		tabelaCategorias.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}
	
	private void limparCampos() {
		
		txtCategoria.setText("");
		
	}

}
