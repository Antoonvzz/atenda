package model;

public class Produto {
	private Long id;
	private Integer idCategoria;
	private Long idMarca;
	private String nome;
	private Double prezo;
	private Integer desconto;
	private Double coste;
	private Integer iva;
	private Long stock;
	private String foto;
	private Boolean baixa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Long getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPrezo() {
		return prezo;
	}
	public void setPrezo(Double prezo) {
		this.prezo = prezo;
	}
	public Integer getDesconto() {
		return desconto;
	}
	public void setDesconto(Integer desconto) {
		this.desconto = desconto;
	}
	public Double getCoste() {
		return coste;
	}
	public void setCoste(Double coste) {
		this.coste = coste;
	}
	public Integer getIva() {
		return iva;
	}
	public void setIva(Integer iva) {
		this.iva = iva;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Boolean getBaixa() {
		return baixa;
	}
	public void setBaixa(Boolean baixa) {
		this.baixa = baixa;
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + ", idCategoria=" + idCategoria + ", idMarca=" + idMarca + ", nome=" + nome
				+ ", prezo=" + prezo + ", desconto=" + desconto + ", coste=" + coste + ", iva=" + iva + ", stock="
				+ stock + ", foto=" + foto + ", baixa=" + baixa + "]";
	}
	
	
	
	
	
}
