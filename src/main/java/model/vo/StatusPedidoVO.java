package model.vo;

public class StatusPedidoVO {
	
	private int id;
	private String descricao;
	
	public StatusPedidoVO(int id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public StatusPedidoVO() {
		
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
