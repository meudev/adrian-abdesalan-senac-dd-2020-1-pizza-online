package model.vo;

public class PedidoVO {
	
	private int id;
	private ClienteVO idCliente;
	private String dataPedido;
	private StatusPedidoVO idStatus;
	private String observacao;
	private String endereco;
	private int taxaEntrega;
	private int valorTotal;
	
	public PedidoVO(ClienteVO idCliente, String dataPedido, StatusPedidoVO idStatus, String observacao, String endereco,
			int taxaEntrega, int valorTotal) {
		super();
		this.idCliente = idCliente;
		this.dataPedido = dataPedido;
		this.idStatus = idStatus;
		this.observacao = observacao;
		this.endereco = endereco;
		this.taxaEntrega = taxaEntrega;
		this.valorTotal = valorTotal;
	}

	public PedidoVO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClienteVO getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(ClienteVO idCliente) {
		this.idCliente = idCliente;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public StatusPedidoVO getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(StatusPedidoVO idStatus) {
		this.idStatus = idStatus;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getTaxaEntrega() {
		return taxaEntrega;
	}

	public void setTaxaEntrega(int taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}

	public int getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}

}
