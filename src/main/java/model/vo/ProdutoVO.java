package model.vo;

public class ProdutoVO {
	
	private int id;
	private CategoriaProdutoVO idCategoria;
	private String nome;
	private String descricao;
	private int quantidade;
	private int valor;
	private boolean disponivel;

	public ProdutoVO(CategoriaProdutoVO idCategoria, String nome, String descricao, int quantidade, int valor, boolean disponivel) {
		super();
		this.idCategoria = idCategoria;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valor = valor;
		this.disponivel = disponivel;
	}

	public ProdutoVO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CategoriaProdutoVO getIdCategoria() {
		return idCategoria;
	}


	public void setIdCategoria(CategoriaProdutoVO idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return nome;
	}

}
