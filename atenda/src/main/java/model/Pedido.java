package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private Long id;
	private Long idPedidoDevol;
	private Usuario cliente;
	private LocalDateTime data;
	private List<LineaPedido> lineasPedido = new ArrayList<LineaPedido>();
	private Boolean pechado;
	private Boolean recibido;
}
