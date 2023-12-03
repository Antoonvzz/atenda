package model;

import java.time.LocalDateTime;

public class Opinion {
	private Long id;
	private Long idProduto;
	private Usuario usuario;
	private Integer valoracion=1;
	private String texto;
	private LocalDateTime data;
}
