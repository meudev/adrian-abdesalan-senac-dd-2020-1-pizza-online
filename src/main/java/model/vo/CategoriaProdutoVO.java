package model.vo;

public class CategoriaProdutoVO {
	
	private int id;
	private String descricao;

	public CategoriaProdutoVO(String descricao) {
		super();
		this.descricao = descricao;
	}

	public CategoriaProdutoVO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
