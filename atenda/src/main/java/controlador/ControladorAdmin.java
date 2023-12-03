package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Produto;
import service.ProdutoService;
import service.impl.ProdutoServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exceptions.DataException;

/**
 * Servlet implementation class ControladorAdmin
 */
public class ControladorAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProdutoServiceImpl produtoService =new ProdutoServiceImpl() ;
        ArrayList<Produto> listaProdutos = null;
		try {
			listaProdutos = produtoService.findAll();
		} catch (DataException e) {
			e.printStackTrace();
		}
        String action = request.getParameter("action");
        String destino = "/WEB-INF/admin.jsp"; 

        if (action != null) {
			switch (action) {
			case "crear":
			    try {
			        Produto novoProduto = new Produto();
			        novoProduto.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
			        novoProduto.setIdMarca(Long.parseLong(request.getParameter("marca")));
			        novoProduto.setNome(request.getParameter("nome"));
			        novoProduto.setPrezo(Double.parseDouble(request.getParameter("prezo")));
			        novoProduto.setDesconto(Integer.parseInt(request.getParameter("desconto")));
			        novoProduto.setCoste(Double.parseDouble(request.getParameter("coste")));
			        novoProduto.setIva(Integer.parseInt(request.getParameter("iva")));
			        novoProduto.setStock(Long.parseLong(request.getParameter("stock")));
			        novoProduto.setFoto(request.getParameter("foto"));
			        novoProduto.setBaixa(Boolean.parseBoolean(request.getParameter("estado"))); 	
			        produtoService.create(novoProduto);
			        response.sendRedirect(request.getContextPath() + "/admin");
			    } catch (NumberFormatException | DataException e) {
			        e.printStackTrace();
			        response.sendRedirect(request.getContextPath() + "/WEB-INF/error.jsp");
			        return;
			    }
			    break;
                case "editar":
                    String idProdutoStr = request.getParameter("idProduto");
                    if (idProdutoStr != null && !idProdutoStr.isEmpty()) {
                        Long idProduto = Long.parseLong(idProdutoStr);
                        Produto produtoExistente = null;
                        try {
                            produtoExistente = produtoService.findById(idProduto);
                        } catch (DataException e) {
                            e.printStackTrace();
                        }
                        if (produtoExistente != null) {
                            request.setAttribute("produtoEditar", produtoExistente);
                            destino = "/WEB-INF/admin.jsp";
                        } else {
                            destino = "/WEB-INF/error.jsp";
                        }
                    } else {
                        destino = "/WEB-INF/error.jsp";
                    }
                    break;
                case "actualizar":
                	try {
                        Long idProdutoActualizar = Long.parseLong(request.getParameter("idProduto"));
                        Produto produtoExistente = produtoService.findById(idProdutoActualizar);
                        if (produtoExistente != null) {
                            produtoExistente.setNome(request.getParameter("nome"));
                            produtoExistente.setPrezo(Double.parseDouble(request.getParameter("prezo")));
                            produtoExistente.setDesconto(Integer.parseInt(request.getParameter("desconto")));
                            produtoExistente.setCoste(Double.parseDouble(request.getParameter("coste")));
                            produtoExistente.setIva(Integer.parseInt(request.getParameter("iva")));
                            produtoExistente.setStock(Long.parseLong(request.getParameter("stock")));
                            produtoExistente.setFoto(request.getParameter("foto"));
                            produtoExistente.setIdMarca(Long.parseLong(request.getParameter("idMarca")));
                            produtoExistente.setIdCategoria(Integer.parseInt(request.getParameter("idCategoria")));
                            produtoExistente.setBaixa(Boolean.parseBoolean(request.getParameter("estado")));

                            try {
                                Produto produtoActualizado = produtoService.update(produtoExistente);
                                request.setAttribute("produtoAtualizado", produtoActualizado);
                            } catch (DataException e) {
                                e.printStackTrace();
                                response.sendRedirect(request.getContextPath() + "/WEB-INF/error.jsp");
                                return;
                            }
                        } else {
                            response.sendRedirect(request.getContextPath() + "/WEB-INF/error.jsp");
                            return;
                        }
                    } catch (NumberFormatException | DataException e) {
                        e.printStackTrace();
                        response.sendRedirect(request.getContextPath() + "/WEB-INF/error.jsp");
                        return;
                    }
                    break;
                case "eliminar":
                    try {
                        Long idProdutoEliminar = Long.parseLong(request.getParameter("idProduto"));
                        Produto produtoEliminar = produtoService.findById(idProdutoEliminar);

                        if (produtoEliminar != null) {
                            produtoService.softDelete(produtoEliminar.getId());
                        } else {
                            response.sendRedirect(request.getContextPath() + "/WEB-INF/error.jsp");
                            return;
                        }
                    } catch (NumberFormatException | DataException e) {
                        e.printStackTrace();
                        response.sendRedirect(request.getContextPath() + "/WEB-INF/error.jsp");
                        return;
                    }
                case "cancelar":
                	
                    break;
            }
        }
        request.setAttribute("listaProdutos", listaProdutos);

        request.getRequestDispatcher(destino).forward(request, response);
    }
}