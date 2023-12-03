package service;

public class ProdutoCriteria {
	private Double prezoDende;
	private Double prezoAta;
	private Integer idCategoria;
	private Integer idMarca;
	private String nome;
	
	public ProdutoCriteria(Double prezoDende, Double prezoAta, Integer idCategoria, Integer idMarca, String nome) {
		super();
		this.prezoDende = prezoDende;
		this.prezoAta = prezoAta;
		this.idCategoria = idCategoria;
		this.idMarca = idMarca;
		this.nome = nome;
	}

	public Double getPrezoDende() {
		return prezoDende;
	}

	public void setPrezoDende(Double prezoDende) {
		this.prezoDende = prezoDende;
	}

	public Double getPrezoAta() {
		return prezoAta;
	}

	public void setPrezoAta(Double prezoAta) {
		this.prezoAta = prezoAta;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "ProdutoCriteria [prezoDende=" + prezoDende + ", prezoAta=" + prezoAta + ", idCategoria=" + idCategoria
				+ ", idMarca=" + idMarca + ", nome=" + nome + "]";
	}
	
}
