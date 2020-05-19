package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private ClienteNovo clienteNovo;
	private ClienteConsultar clienteConsultar;
	private CozinhaListar cozinhaListar;
	private EntregaRegistrar entregaRegistrar;
	private PedidoNovo pedidoNovo;
	private PedidoConsultar pedidoConsultar;
	private ProdutoNovo produtoNovo;
	private ProdutoConsultar produtoConsultar;
	private ConfiguracaoUsuarioNovo configuracaoUsuarioNovo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setExtendedState(MAXIMIZED_BOTH);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setTitle("Pizza Planet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnClientes.setIcon(new ImageIcon("C:\\Users\\aferr\\git\\adrian-abdesalan-senac-dd-2020-1-pizza-online\\img\\icone_cliente.png"));
		menuBar.add(mnClientes);
		
		JMenuItem mntmNovoCliente = new JMenuItem("Novo Cliente");
		mntmNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				clienteNovo = new ClienteNovo();
				setContentPane(clienteNovo);
				revalidate();
				
			}
		});
		mnClientes.add(mntmNovoCliente);
		
		JMenuItem mntmConsultarCliente = new JMenuItem("Consultar Cliente");
		mntmConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				clienteConsultar = new ClienteConsultar();
				setContentPane(clienteConsultar);
				revalidate();
				
			}
		});
		mnClientes.add(mntmConsultarCliente);
		
		JMenu mnPedidos = new JMenu("Pedidos");
		mnPedidos.setIcon(new ImageIcon("C:\\Users\\aferr\\git\\adrian-abdesalan-senac-dd-2020-1-pizza-online\\img\\icone_pedido.png"));
		mnPedidos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnPedidos);
		
		JMenuItem mntmNovoPedido = new JMenuItem("Novo Pedido");
		mntmNovoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pedidoNovo = new PedidoNovo();
				setContentPane(pedidoNovo);
				revalidate();
				
			}
		});
		mnPedidos.add(mntmNovoPedido);
		
		JMenuItem mntmConsultarPedido = new JMenuItem("Consultar Pedido");
		mntmConsultarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pedidoConsultar = new PedidoConsultar();
				setContentPane(pedidoConsultar);
				revalidate();
				
			}
		});
		mnPedidos.add(mntmConsultarPedido);
		
		JMenu mnCozinha = new JMenu("Cozinha");
		mnCozinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cozinhaListar = new CozinhaListar();
				setContentPane(cozinhaListar);
				revalidate();
				
			}
		});

		
		JMenu mnEntrega = new JMenu("Entrega");
		mnEntrega.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				entregaRegistrar = new EntregaRegistrar();
				setContentPane(entregaRegistrar);
				revalidate();
				
			}
		});
		mnEntrega.setIcon(new ImageIcon("C:\\Users\\aferr\\git\\adrian-abdesalan-senac-dd-2020-1-pizza-online\\img\\icone_entrega.png"));
		mnEntrega.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnEntrega);
		
		JMenu mnProdutos = new JMenu("Produtos");
		mnProdutos.setIcon(new ImageIcon("C:\\Users\\aferr\\git\\adrian-abdesalan-senac-dd-2020-1-pizza-online\\img\\icone_produto.png"));
		mnProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnProdutos);
		
		JMenuItem mntmNovoProduto = new JMenuItem("Novo Produto");
		mntmNovoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				produtoNovo = new ProdutoNovo();
				setContentPane(produtoNovo);
				revalidate();
				
			}
		});
		mnProdutos.add(mntmNovoProduto);
		
		JMenuItem mntmConsultarProduto = new JMenuItem("Consultar Produto");
		mntmConsultarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				produtoConsultar = new ProdutoConsultar();
				setContentPane(produtoConsultar);
				revalidate();
				
			}
		});
		mnProdutos.add(mntmConsultarProduto);
		
		JMenu mnConfiguraes = new JMenu("Configura\u00E7\u00F5es");
		mnConfiguraes.setIcon(new ImageIcon("C:\\Users\\aferr\\git\\adrian-abdesalan-senac-dd-2020-1-pizza-online\\img\\icone_configuracoes.png"));
		mnConfiguraes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnConfiguraes);
		
		JMenuItem mntmCadastrarUsurio = new JMenuItem("Cadastrar Usu\u00E1rio");
		mntmCadastrarUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				configuracaoUsuarioNovo = new ConfiguracaoUsuarioNovo();
				setContentPane(configuracaoUsuarioNovo);
				revalidate();
				
			}
		});
		mnConfiguraes.add(mntmCadastrarUsurio);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//SAIR
			}
		});
		mnSair.setIcon(new ImageIcon("C:\\Users\\aferr\\git\\adrian-abdesalan-senac-dd-2020-1-pizza-online\\img\\icone_sair.png"));
		mnSair.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnSair);

	}

}
