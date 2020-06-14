package model.vo;

public class PedidoItemVO {
	
	private int id;
	private PedidoVO idPedido;
	private ProdutoVO idProduto;
	private int quantidade;
	private int valor;
	
	public PedidoItemVO(PedidoVO idPedido, ProdutoVO idProduto, int quantidade, int valor) {
		super();
		this.idPedido = idPedido;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public PedidoItemVO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PedidoVO getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(PedidoVO idPedido) {
		this.idPedido = idPedido;
	}

	public ProdutoVO getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(ProdutoVO idProduto) {
		this.idProduto = idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
