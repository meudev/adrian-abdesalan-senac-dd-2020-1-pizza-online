package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.ClienteController;
import model.vo.ClienteVO;
import model.vo.CodigoPaisVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.awt.event.ActionEvent;

public class ClienteAlterar extends JPanel {
	
	ClienteVO cliente = new ClienteVO();
	CodigoPaisVO codigoPais = null;
	
	private static final long serialVersionUID = 1L;
	private JTextField txtTelefone;
	private JTextField txtNome;
	private JTextField txtCep;
	private JLabel lblLogradouro;
	private JTextField txtLogradouro;
	private JLabel lblNmero;
	private JTextField txtNumero;
	private JLabel lblComplemento;
	private JTextField txtComplemento;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JLabel lblEstado;
	private JLabel lblObservaes;
	private JTextArea txtObservacao;
	private JButton btnSalvarAlteracao;
	private JLabel lblClientesNovo;
	private JButton btnBuscarCep;
	private JTextField txtEstado;
	
	String logradouro;
	String bairro;
	String cidade;
	String uf;
	private JTextField txtCodigoPais;
	private JLabel lblCdigo;

	public ClienteAlterar(ClienteVO clienteSelecionado) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("60px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				RowSpec.decode("max(30dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
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
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),}));
			
		lblClientesNovo = new JLabel("Clientes > Alterar");
		lblClientesNovo.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblClientesNovo, "4, 2, 13, 1");
		
		lblCdigo = new JLabel("C\u00F3digo");
		add(lblCdigo, "4, 4");
		
		JLabel lblTelefone = new JLabel("Telefone");
		add(lblTelefone, "6, 4");
		
		txtCodigoPais = new JTextField();
		add(txtCodigoPais, "4, 6, fill, fill");
		txtCodigoPais.setColumns(10);
		txtCodigoPais.disable();
		
		txtTelefone = new JTextField();
		add(txtTelefone, "6, 6, fill, fill");
		txtTelefone.setColumns(10);
		txtTelefone.disable();
		
		JLabel lblNome = new JLabel("Nome");
		add(lblNome, "4, 10, 3, 1");
		
		txtNome = new JTextField();
		add(txtNome, "4, 12, 5, 1, fill, fill");
		txtNome.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP");
		add(lblCep, "4, 16, 3, 1");
		
		txtCep = new JTextField();
		add(txtCep, "4, 18, 3, 1, fill, fill");
		txtCep.setColumns(10);
		
		btnBuscarCep = new JButton("\u2315");
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//BUSCA CEP
				buscarCep(txtCep.getText());
				
			}

		});
		btnBuscarCep.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(btnBuscarCep, "8, 18, left, fill");
		
		lblLogradouro = new JLabel("Logradouro");
		add(lblLogradouro, "4, 20, 3, 1");
		
		lblNmero = new JLabel("N\u00FAmero");
		add(lblNmero, "12, 20");
		
		lblComplemento = new JLabel("Complemento");
		add(lblComplemento, "16, 20");
		
		txtLogradouro = new JTextField();
		add(txtLogradouro, "4, 22, 5, 1, fill, fill");
		txtLogradouro.setEditable(false);
		txtLogradouro.setColumns(10);
		
		txtNumero = new JTextField();
		add(txtNumero, "12, 22, fill, fill");
		txtNumero.setColumns(10);
		
		txtComplemento = new JTextField();
		add(txtComplemento, "16, 22, fill, fill");
		txtComplemento.setColumns(10);
		
		lblBairro = new JLabel("Bairro");
		add(lblBairro, "4, 24, 3, 1");
		
		lblCidade = new JLabel("Cidade");
		add(lblCidade, "12, 24");
		
		lblEstado = new JLabel("Estado");
		add(lblEstado, "16, 24");
		
		txtBairro = new JTextField();
		add(txtBairro, "4, 26, 5, 1, fill, fill");
		txtBairro.setEditable(false);
		txtBairro.setColumns(10);
		
		txtCidade = new JTextField();
		add(txtCidade, "12, 26, fill, fill");
		txtCidade.setEditable(false);
		txtCidade.setColumns(10);
		
		txtEstado = new JTextField();
		add(txtEstado, "16, 26, fill, fill");
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		
		lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		add(lblObservaes, "4, 30, 3, 1");
		
		txtObservacao = new JTextArea();
		add(txtObservacao, "4, 32, 13, 1, fill, fill");
		
		btnSalvarAlteracao = new JButton("Salvar Altera\u00E7\u00F5es");
		btnSalvarAlteracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//SALVAR DADOS
				ClienteController controllerCliente = new ClienteController();
				String mensagem = controllerCliente.cadastrarAlteracaoCliente(codigoPais ,cliente.getId(), txtTelefone.getText(), txtNome.getText(), txtCep.getText(), txtLogradouro.getText(), txtNumero.getText(), txtComplemento.getText(), txtBairro.getText(), txtCidade.getText(), txtEstado.getText(), txtObservacao.getText() );

				JOptionPane.showMessageDialog(null, mensagem);
				
				
				//MANDAR PARA TELA DE BUSCA DE CLIENTES
				
				
			}


		});
		add(btnSalvarAlteracao, "4, 36, 13, 1, default, fill");
		
		popularCampos(clienteSelecionado);

	}
	
	private void popularCampos(ClienteVO clienteSelecionado) {
		txtCodigoPais.setText(clienteSelecionado.getCodigo().getCodigo());
		txtTelefone.setText(clienteSelecionado.getTelefone());
		txtNome.setText(clienteSelecionado.getNome());
		txtCep.setText(Integer.toString(clienteSelecionado.getCep()));
		txtLogradouro.setText(clienteSelecionado.getLogradouro());
		txtNumero.setText(clienteSelecionado.getNumero());
		txtComplemento.setText(clienteSelecionado.getComplemento());
		txtBairro.setText(clienteSelecionado.getBairro());
		txtCidade.setText(clienteSelecionado.getCidade());
		txtEstado.setText(clienteSelecionado.getEstado());
		txtObservacao.setText(clienteSelecionado.getObservacao());
		
		cliente.setId(clienteSelecionado.getId());
		
		codigoPais = clienteSelecionado.getCodigo();
	}

	public void buscarCep(String cep) {
        String json;        

        try {
            URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();

            br.lines().forEach(l -> jsonSb.append(l.trim()));
            json = jsonSb.toString();
            
            json = json.replaceAll("[{},:]", "");
            json = json.replaceAll("\"", "\n");                       
            String array[] = new String[30];
            array = json.split("\n");
                             
            logradouro = array[7];            
            bairro = array[15];
            cidade = array[19]; 
            uf = array[23];
            
            txtLogradouro.setText(logradouro);
            txtBairro.setText(bairro);
            txtCidade.setText(cidade);
            txtEstado.setText(uf);
            
        } catch (Exception e) {
        	
            throw new RuntimeException(e);
            
        }
    }

}
