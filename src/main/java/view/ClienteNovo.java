package view;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.ClienteController;
import controller.CodigoPaisController;
import model.vo.ClienteVO;
import model.vo.CodigoPaisVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ClienteNovo extends JPanel {
	
	ClienteVO cliente = new ClienteVO();

	private static final long serialVersionUID = 1L;
	private JFormattedTextField txtTelefone;
	private JTextField txtNome;
	private JFormattedTextField txtCep;
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
	private JButton btnSalvarCliente;
	private JLabel lblClientesNovo;
	private JButton btnBuscarCep;
	private JTextField txtEstado;
	
	private MaskFormatter mascaraNula;
	private MaskFormatter mascaraTelefoneFixo;
	private MaskFormatter mascaraTelefoneCelular;
		
	String logradouro;
	String bairro;
	String cidade;
	String uf;
	private JLabel lblPas;
	private JComboBox<?> cbPais;

	public ClienteNovo() {
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
		
		lblClientesNovo = new JLabel("Clientes > Novo");
		lblClientesNovo.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblClientesNovo, "4, 2, 13, 1");
		
		lblPas = new JLabel("País");
		add(lblPas, "4, 4");
		
		JLabel lblTelefone = new JLabel("Telefone");
		add(lblTelefone, "6, 4");
		
		CodigoPaisController controllerCodigoPais = new CodigoPaisController();
		ArrayList<CodigoPaisVO> listCodigoPais = controllerCodigoPais.listarTodasCodigoPais();
		cbPais = new JComboBox(listCodigoPais.toArray());
		cbPais.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent  e) {
				//atualizarMascaraTelefone(cbPais.getSelectedItem());
			}

		});
		add(cbPais, "4, 6, fill, fill");
		
		
		txtTelefone = new JFormattedTextField();
		txtTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				atualizarMascaraTelefone();
			}
		});
		txtTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validaMascaraTelefone();
			}

		});
		add(txtTelefone, "6, 6, fill, fill");
		txtTelefone.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		add(lblNome, "4, 10, 3, 1");
		
		txtNome = new JTextField();
		add(txtNome, "4, 12, 5, 1, fill, fill");
		txtNome.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP");
		add(lblCep, "4, 16, 3, 1");
		
		try {
			MaskFormatter mascaraTxtCep = new MaskFormatter("#####-###");

			txtCep = new JFormattedTextField(mascaraTxtCep);
			add(txtCep, "4, 18, 3, 1, fill, fill");
			txtCep.setColumns(10);

		} catch (ParseException e) {
			System.out.println(" Erro ao montar a mascara do cep. Causa: " + e.getMessage());
		}
				
		btnBuscarCep = new JButton("►");
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//BUSCA CEP
				buscarCep(txtCep.getText());
				
			}

		});
		btnBuscarCep.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
		
		btnSalvarCliente = new JButton("Salvar Cliente");
		btnSalvarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//SALVAR DADOS
				ClienteController controllerCliente = new ClienteController();
				
				String mensagem = controllerCliente.cadastrarNovoCliente((CodigoPaisVO) cbPais.getSelectedItem(), txtTelefone.getText(), txtNome.getText(), txtCep.getText(), txtLogradouro.getText(), txtNumero.getText(), txtComplemento.getText(), txtBairro.getText(), txtCidade.getText(), txtEstado.getText(), txtObservacao.getText() );


				JOptionPane.showMessageDialog(null, mensagem);
				
				if(mensagem == "Cliente cadastro com sucesso") {
					
					limparCampos();
					
				}

				
			}


		});
		add(btnSalvarCliente, "4, 36, 13, 1, default, fill");

	}
	
	public void buscarCep(String cep) {
        String json;        

        try {
            URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

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
            
    		if( (logradouro == null || logradouro.isEmpty()) && cidade != null) {
    			txtLogradouro.setEditable(true);
    		} else {
    			txtLogradouro.setEditable(false);
    		}
    		
    		if( (bairro == null || bairro.isEmpty()) && cidade != null) {
    			txtBairro.setEditable(true);
    		} else {
    			txtBairro.setEditable(false);
    		}
            
        } catch (Exception e) {
        	
            throw new RuntimeException(e);
            
        }
    }
	
	private void limparCampos() {
		txtTelefone.setText("");
		txtNome.setText("");
		txtCep.setText("");
		txtLogradouro.setText("");
		txtNumero.setText("");
		txtComplemento.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		txtObservacao.setText("");
		txtEstado.setText("");
	}

	private void atualizarMascaraTelefone() {
		String codigoPais = cbPais.getSelectedItem().toString();
		
		if(codigoPais.equals("+55")) {
			mascararCampo(1);
		} else {
			mascararCampo(0);
		}
		
	}
	
	private void definirMascaramento(){
		try{
			mascaraNula = new MaskFormatter();
			mascaraTelefoneCelular = new MaskFormatter("(##) # ####-####");
			mascaraTelefoneFixo = new MaskFormatter("(##) ####-####");
		}catch(ParseException pex){
			System.out.println("ERRO: " + pex.getMessage());
		}
	}
	
	private void mascararCampo(int tipoMascaramento){
		definirMascaramento();
		
		switch(tipoMascaramento){
			case 0:
				txtTelefone.setValue(null);
				txtTelefone.setFormatterFactory(null);
				System.out.println("sem mascara");
				break;
			case 1:
				txtTelefone.setValue(null);
				txtTelefone.setFormatterFactory(new DefaultFormatterFactory(mascaraTelefoneCelular));
				System.out.println("celular");
				break;
			case 2:
				txtTelefone.setValue(null);
				txtTelefone.setFormatterFactory(new DefaultFormatterFactory(mascaraTelefoneFixo));
				System.out.println("fixo");
		}
	}
	
	private void validaMascaraTelefone() {
		String codigoPais = cbPais.getSelectedItem().toString();
		int tamanhoNumero = txtTelefone.getText().trim().length();
		
		if(codigoPais.equals("+55") && tamanhoNumero == 15) {
			String numeroDigitado = txtTelefone.getText().substring(0, txtTelefone.getText().length() - 1);
			numeroDigitado = numeroDigitado.replaceAll(" ", "");
			numeroDigitado = numeroDigitado.replaceAll("[(,),-]", "");
			
			mascararCampo(2);
			
			txtTelefone.setText(numeroDigitado);
			
		}
	}
	
}

			

