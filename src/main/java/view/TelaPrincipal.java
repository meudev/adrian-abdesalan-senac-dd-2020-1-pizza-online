package view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JMenuItem;

import model.vo.ClienteVO;
import model.vo.PedidoVO;
import model.vo.ProdutoVO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private ClienteNovo clienteNovo;
	private ClienteConsultar clienteConsultar;
	private ClienteAlterar clienteAlterar;
	private CozinhaListar cozinhaListar;
	private CozinhaVisualizar cozinhaVisualizar;
	private EntregaRegistrar entregaRegistrar;
	private PedidoNovo pedidoNovo;
	private PedidoConsultar pedidoConsultar;
	private ProdutoNovo produtoNovo;
	private ProdutoConsultar produtoConsultar;
	private ProdutoCategoria produtoCategoria;
	private ProdutoAlterar produtoAlterar;
	private ConfiguracaoUsuarioNovo configuracaoUsuarioNovo;


	public TelaPrincipal() {
		setTitle("Pizza Planet");
		setExtendedState(MAXIMIZED_BOTH);
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
		mnCozinha.setIcon(new ImageIcon("C:\\Users\\aferr\\git\\adrian-abdesalan-senac-dd-2020-1-pizza-online\\img\\icone_cozinha.png"));
		mnCozinha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnCozinha);
		
		JMenuItem mntmConsultarProduo = new JMenuItem("Consultar Produ\u00E7\u00E3o");
		mntmConsultarProduo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cozinhaListar = new CozinhaListar();
				setContentPane(cozinhaListar);
				revalidate();
				
			}
		});
		mnCozinha.add(mntmConsultarProduo);

		
		JMenu mnEntrega = new JMenu("Entrega");
		mnEntrega.setIcon(new ImageIcon("C:\\Users\\aferr\\git\\adrian-abdesalan-senac-dd-2020-1-pizza-online\\img\\icone_entrega.png"));
		mnEntrega.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnEntrega);
		
		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				entregaRegistrar = new EntregaRegistrar();
				setContentPane(entregaRegistrar);
				revalidate();
				
			}
		});
		mnEntrega.add(mntmRegistrar);
		
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
		
		JMenuItem mntmCadastrarCategoria = new JMenuItem("Cadastrar Categoria");
		mntmCadastrarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				produtoCategoria = new ProdutoCategoria();
				setContentPane(produtoCategoria);
				revalidate();
				
			}
		});
		mnProdutos.add(mntmCadastrarCategoria);
		
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
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
            	dispose();
            	new TelaInicial().setVisible(true);
            	
			}
		});
		mnConfiguraes.add(mntmSair);

	}


	public void abrirClienteAlterar(ClienteVO clienteSelecionado) {
		
		clienteAlterar = new ClienteAlterar(clienteSelecionado);
		getContentPane().add(clienteAlterar);
		setVisible(true);
		revalidate();
		
	}


	public void abrirProdutoAlterar(ProdutoVO produtoSelecionado) {
		
		produtoAlterar = new ProdutoAlterar(produtoSelecionado);
		getContentPane().add(produtoAlterar);
		setVisible(true);
		revalidate();
			
	}


	public void abrirCozinhaVisualizar(PedidoVO pedidoSelecionado) {
		
		cozinhaVisualizar = new CozinhaVisualizar(pedidoSelecionado);
		getContentPane().add(cozinhaVisualizar);
		setVisible(true);
		revalidate();
				
	}


	public void abrirCozinhaListar() {
		
		cozinhaListar = new CozinhaListar();
		getContentPane().add(cozinhaListar);
		setVisible(true);
		revalidate();
		
	}



	
}
