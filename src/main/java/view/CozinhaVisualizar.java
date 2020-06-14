package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import model.vo.PedidoItemVO;
import model.vo.PedidoVO;
import model.vo.StatusPedidoVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.Sizes;

import controller.PedidoController;
import controller.PedidoItemController;
import controller.StatusPedidoController;

public class CozinhaVisualizar extends JPanel {
	
	TelaPrincipal telaPrincipal = new TelaPrincipal();

	private static final long serialVersionUID = 1L;
	private JTextField txtPedidoNumero;
	private JTextField txtCliente;
	private JTextField txtEndereco;
	private JTextField txtObservacao;
	private JTable tabelaItensPedido;
	
	private ArrayList<PedidoItemVO> itens;
	private String[] nomesColunas = { "QUANTIDADE", "PRODUTO", "DESCRICAO"};

	public CozinhaVisualizar(PedidoVO pedidoSelecionado) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("200dlu", true), Sizes.constant("400dlu", true)), 0),
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(40dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(80dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:max(20dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(40dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
		
		JLabel lblCozinhaPreparar = new JLabel("Cozinha > Preparar Pedido");
		lblCozinhaPreparar.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblCozinhaPreparar, "4, 2, 5, 1, fill, center");
		
		StatusPedidoController controllerStatusPedido = new StatusPedidoController();
		ArrayList<StatusPedidoVO> listStatusPedido = controllerStatusPedido.listarTodosStatusPedido();
		
		tabelaItensPedido = new JTable();
		add(tabelaItensPedido, "8, 4, 1, 21, fill, fill");
		
		JLabel lblPedidoN = new JLabel("Pedido N\u00BA");
		add(lblPedidoN, "4, 6");
		
		txtPedidoNumero = new JTextField();
		add(txtPedidoNumero, "4, 8, fill, fill");
		txtPedidoNumero.setColumns(10);
		txtPedidoNumero.setEditable(false);
		
		JLabel lblCliente = new JLabel("Cliente");
		add(lblCliente, "4, 10");
		
		txtCliente = new JTextField();
		add(txtCliente, "4, 12, fill, fill");
		txtCliente.setColumns(10);
		txtCliente.setEditable(false);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		add(lblEndereo, "4, 14");
		
		txtEndereco = new JTextField();
		add(txtEndereco, "4, 16, fill, fill");
		txtEndereco.setColumns(10);
		txtEndereco.setEditable(false);
		
		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o");
		add(lblObservao, "4, 18");
		
		txtObservacao = new JTextField();
		add(txtObservacao, "4, 20, fill, fill");
		txtObservacao.setColumns(10);
		txtObservacao.setEditable(false);
		
		JButton btnPedidoPronto = new JButton("PEDIDO PRONTO");
		btnPedidoPronto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PEDIDO PRONTO
				PedidoController controllerPedido = new PedidoController();
				String mensagem = controllerPedido.atualizarStatusPedido(pedidoSelecionado.getId(), (StatusPedidoVO) listStatusPedido.get(3));	
				
				System.out.println(mensagem);
				telaPrincipal.abrirCozinhaListar();
			}
		});
		add(btnPedidoPronto, "4, 24, fill, fill");

		popularCampos(pedidoSelecionado);
		iniciarTabelaPedidoItens(pedidoSelecionado.getId());

		PedidoController controllerPedido = new PedidoController();
		String mensagem = controllerPedido.atualizarStatusPedido(pedidoSelecionado.getId(), (StatusPedidoVO) listStatusPedido.get(2));
		
		System.out.println(mensagem);
	}
	
	private void iniciarTabelaPedidoItens(int id) {
			
		PedidoItemController controller = new PedidoItemController();
		itens = controller.listarItensPedido(id);

		atualizarTabelaPedidoItens();
		
	}

	private void atualizarTabelaPedidoItens() {
		limparTabelaPedidoItens();
		DefaultTableModel model = (DefaultTableModel) tabelaItensPedido.getModel();

		for (PedidoItemVO i : itens) {

			Object[] novaLinhaDaTabela = new Object[3];
			novaLinhaDaTabela[0] = i.getQuantidade();
			novaLinhaDaTabela[1] = i.getIdProduto().getNome();
			novaLinhaDaTabela[2] = i.getIdProduto().getDescricao();

			model.addRow(novaLinhaDaTabela);
		}
	}
	
	private void limparTabelaPedidoItens() {
		tabelaItensPedido.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void popularCampos(PedidoVO pedidoSelecionado) {
		txtPedidoNumero.setText(Integer.toString(pedidoSelecionado.getId()));
		txtCliente.setText(pedidoSelecionado.getIdCliente().getNome());
		txtEndereco.setText(pedidoSelecionado.getEndereco());
		txtObservacao.setText(pedidoSelecionado.getObservacao());
		
	}

}
