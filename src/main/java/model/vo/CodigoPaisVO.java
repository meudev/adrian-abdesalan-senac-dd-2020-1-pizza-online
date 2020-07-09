package model.vo;

public class CodigoPaisVO {
	
	private int id;
	private String codigo;
	private String pais;
	
	public CodigoPaisVO(String codigo, String pais) {
		super();
		this.codigo = codigo;
		this.pais = pais;
	}
	
	public CodigoPaisVO() {

	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	@Override
	public String toString() {
		return codigo;
	}

}
