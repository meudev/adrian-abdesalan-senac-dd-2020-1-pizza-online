package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.ClienteController;
import model.vo.ClienteVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ClienteConsultar extends JPanel {
	
	TelaPrincipal telaPrincipal = new TelaPrincipal();

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtBusca;
	private JLabel lblClienteConsultar;
	private ArrayList<ClienteVO> clientes;
	private String[] nomesColunas = { "NOME", "TELEFONE", "ENDEREÇO", "BAIRRO", "CIDADE" };
	private JButton btnEditarCliente;
	private JButton btnExcluirCliente;
	private JButton btnGerarPlanilha;

	public ClienteConsultar() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default)"),
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
		
		lblClienteConsultar = new JLabel("Clientes > Consultar Cliente");
		lblClienteConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblClienteConsultar, "4, 2, 5, 1, default, center");
		
		btnGerarPlanilha = new JButton("Gerar Planilha");
		btnGerarPlanilha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//GERAR PLANILHA
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Salvar planilha como...");
				
				int resultado = jfc.showSaveDialog(null);
				if(resultado == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = jfc.getSelectedFile().getAbsolutePath();
					
					ClienteController controller = new ClienteController();
					controller.gerarPlanilha(clientes, caminhoEscolhido);
					
				}			
				
			}
		});
		add(btnGerarPlanilha, "10, 2, right, fill");
				
		JLabel lblBuscarPorNome = new JLabel("Buscar por Nome, Telefone, CEP, Endere\u00E7o");
		add(lblBuscarPorNome, "6, 4");
		
		txtBusca = new JTextField();
		add(txtBusca, "6, 6, fill, fill");
		txtBusca.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ClienteController controller = new ClienteController();
				clientes = controller.listarClientes(txtBusca.getText());

				atualizarTabelaClientes();
						
			}
		});
		add(btnBuscar, "8, 6, default, fill");
		
		table = new JTable();
		limparTabelaClientes();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = table.getSelectedRow();
				
				System.out.println(indiceSelecionado);

				if (indiceSelecionado > 0) {
					btnEditarCliente.setEnabled(true);
					btnExcluirCliente.setEnabled(true);
				} else {
					btnEditarCliente.setEnabled(false);
					btnExcluirCliente.setEnabled(false);
				}
			}
		});
		add(table, "4, 10, 7, 1, fill, fill");
		
		btnEditarCliente = new JButton("Editar Cliente");
		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linhaSelecionadaNaTabela = table.getSelectedRow();
				ClienteVO clienteSelecionado = clientes.get(linhaSelecionadaNaTabela - 1);
				
				telaPrincipal.abrirClienteAlterar(clienteSelecionado);
		
			}
		});
		add(btnEditarCliente, "4, 12, right, fill");
		btnEditarCliente.setEnabled(false);
		
		btnExcluirCliente = new JButton("Excluir Cliente");
		btnExcluirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mensagem = null;

				int linhaSelecionadaNaTabela = table.getSelectedRow();
				ClienteVO clienteSelecionado = clientes.get(linhaSelecionadaNaTabela - 1);
								
				ClienteController controllerCliente = new ClienteController();
				mensagem = controllerCliente.excluir(clienteSelecionado.getId());
				
				iniciarTabelaClientes();
								
				JOptionPane.showMessageDialog(null, mensagem);
			}

		});
		add(btnExcluirCliente, "10, 12, left, fill");
		btnExcluirCliente.setEnabled(false);
		
		iniciarTabelaClientes();
		
	}
	
	private void iniciarTabelaClientes() {
		ClienteController controller = new ClienteController();
		clientes = controller.listarClientes(txtBusca.getText());

		atualizarTabelaClientes();
	}
	
	private void atualizarTabelaClientes() {
		limparTabelaClientes();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (ClienteVO c : clientes) {

			Object[] novaLinhaDaTabela = new Object[5];
			novaLinhaDaTabela[0] = c.getNome();
			novaLinhaDaTabela[1] = c.getCodigo().getCodigo() +" "+ c.getTelefone();
			novaLinhaDaTabela[2] = c.getLogradouro() +", "+ c.getNumero() +" - "+ c.getComplemento();
			novaLinhaDaTabela[3] = c.getBairro();
			novaLinhaDaTabela[4] = c.getCidade();

			model.addRow(novaLinhaDaTabela);
		}
	}
	
	private void limparTabelaClientes() {
		table.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
//		table = new JTable(table.getModel()) {
//			public boolean isCellEditable(int rowIndex, int colIndex) {
//				return false;
//			}
//		};
	}

}
