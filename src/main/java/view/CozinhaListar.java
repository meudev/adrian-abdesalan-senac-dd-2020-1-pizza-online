package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.PedidoController;
import model.vo.PedidoVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class CozinhaListar extends JPanel {
	
	TelaPrincipal telaPrincipal = new TelaPrincipal();

	private static final long serialVersionUID = 1L;
	private JTable tablePedidosCozinha;
	private JButton btnPrepararPedido;
	private ArrayList<PedidoVO> pedidos;
	private String[] nomesColunas = { "PEDIDO Nº", "DATA PEDIDO", "CLIENTE", "VALOR", "STATUS" };

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
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
		
		JLabel lblCozinhaConsultar = new JLabel("Cozinha > Consultar Produ\u00E7\u00E3o");
		lblCozinhaConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblCozinhaConsultar, "4, 2");
		
		tablePedidosCozinha = new JTable();
		tablePedidosCozinha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tablePedidosCozinha.getSelectedRow();

				if (indiceSelecionado > 0) {
					btnPrepararPedido.setEnabled(true);
				} else {
					btnPrepararPedido.setEnabled(false);
				}
			}
		});
		add(tablePedidosCozinha, "4, 4, fill, fill");
		
		btnPrepararPedido = new JButton("Preparar Pedido");
		btnPrepararPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//ABRIR VISUALIZAÇÃO DO PEDIDO E MUDAR STATUS
				int linhaSelecionadaNaTabela = tablePedidosCozinha.getSelectedRow();
				PedidoVO pedidoSelecionado = pedidos.get(linhaSelecionadaNaTabela - 1);
				
				telaPrincipal.abrirCozinhaVisualizar(pedidoSelecionado);
				
			}
		});
		btnPrepararPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(btnPrepararPedido, "4, 8, fill, fill");
		btnPrepararPedido.setEnabled(false);
		
		JButton btnAtualizarLista = new JButton("Atualizar Lista");
		btnAtualizarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				iniciarTabelaPedidos();
				
			}
		});
		btnAtualizarLista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnAtualizarLista, "4, 12, fill, fill");

		iniciarTabelaPedidos();
		
	}
	
	private void iniciarTabelaPedidos() {
		
		PedidoController controller = new PedidoController();
		pedidos = controller.listarPedidosCozinha(1, 3);

		atualizarTabelaPedidos();
		
	}

	private void atualizarTabelaPedidos() {
		limparTabelaPedidos();
		DefaultTableModel model = (DefaultTableModel) tablePedidosCozinha.getModel();
		
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
		tablePedidosCozinha.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

}
