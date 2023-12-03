package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import dao.ProdutoDAO;
import dao.Results;
import dao.util.ConnectionManager;
import exceptions.DataException;
import model.Produto;
import service.ProdutoCriteria;

public class ProdutoDAOImpl implements ProdutoDAO{
	private static final Logger logger = Logger.getLogger(ProdutoDAOImpl.class.getName());
	
	@Override
	public Produto findById(Connection connection, Long idProduto) throws DataException {
		Produto produto = null;
		StringBuilder sql = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		sql = new StringBuilder();
		logger.info("Create Statement....");
		sql.append("SELECT p.id, p.id_categoria, p.id_marca,p.nome, p.prezo,");
		sql.append("p.desconto, p.coste, p.iva, p.stock, p.foto, p.baixa ");
		sql.append("FROM produto as p ");
		sql.append("WHERE p.id = ?");
		preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		int i = 1;
		preparedStatement.setLong(i++, idProduto);
		logger.info(preparedStatement.toString());
		resultSet= preparedStatement.executeQuery();
		if(resultSet.next()) {
			   produto = loadNext(connection, resultSet);
		}
		}catch(SQLException e){
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			
		}
		
		return produto;
	}

	@Override
	public ArrayList<Produto> findAll(Connection connection) throws DataException {
		 ArrayList<Produto> productList = new ArrayList<>();
	        StringBuilder sql = new StringBuilder();
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        try {
	            sql.append("SELECT p.id, p.id_categoria, p.id_marca, p.nome, p.prezo,");
	            sql.append("p.desconto, p.coste, p.iva, p.stock, p.foto, p.baixa ");
	            sql.append("FROM produto as p ");
	            preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
	                    ResultSet.CONCUR_READ_ONLY);
	            logger.info(preparedStatement.toString());
	            resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
	                Produto produto = loadNext(connection, resultSet);
	                productList.add(produto);
	            }

	        } catch (SQLException e) {
	            logger.log(Level.SEVERE, e.getMessage());
	        } finally {
	            if (resultSet != null) {
	                try {
						resultSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
	            }
	            if (preparedStatement != null) {
	                try {
						preparedStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
	            }
	        }

	        return productList;
	    }


	@Override
	public Results<Produto> findBy(Connection connection, ProdutoCriteria produtoCriteria, int startIndex, int count)
			throws DataException {
		List<Produto> resultsProdutos = null;
        StringBuilder sql = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Results<Produto> results = null;
        int total = 0;
        try {
        	sql = new StringBuilder();
        	logger.info("Create Statement....");
            sql.append("SELECT p.id, p.id_categoria, p.id_marca, p.nome, p.prezo,");
            sql.append("p.desconto, p.coste, p.iva, p.stock, p.foto, p.baixa ");
            sql.append("FROM produto as p ");
            boolean conWhere = false;
            if (produtoCriteria.getIdCategoria() != null) {
            	sql.append(conWhere ? "" : "WHERE");
            	conWhere = true;
            	sql.append(" p.id_categoria = ? ");
            } 
            if(produtoCriteria.getIdMarca() != null) {
            	sql.append(conWhere ? " AND " : " ? ");
            	conWhere = true;
            	sql.append(" p.id_marca = ? ");
            } 
            if (produtoCriteria.getNome() != null) {
                sql.append(conWhere ? " AND " : " WHERE ");
                conWhere = true;
                sql.append(" p.nome LIKE ? ");
            }
            if (produtoCriteria.getPrezoDende() != null) {
            	sql.append(conWhere ? " AND " : " WHERE ");
            	conWhere = true;
            	sql.append(" p.prezo >= ? ");
            }
            if (produtoCriteria.getPrezoAta() != null) {
            	sql.append(conWhere ? "AND" : "  ?  ");
            	conWhere = true;
            	sql.append(" p.prezo <= ? ");
            } 
            
            int i = 1;
            preparedStatement = connection.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            if (produtoCriteria.getIdCategoria() != null) {
            	preparedStatement.setInt(i++,produtoCriteria.getIdCategoria());
            } 
            if (produtoCriteria.getIdMarca() != null) {
            	preparedStatement.setInt(i++,produtoCriteria.getIdMarca());
            }
            if(produtoCriteria.getNome() != null) {
            	preparedStatement.setString(i++, produtoCriteria.getNome()+"%");
            }
            if (produtoCriteria.getPrezoAta() != null) {
            	preparedStatement.setDouble(i++, produtoCriteria.getPrezoDende());
            }
            if (produtoCriteria.getPrezoDende() != null) {
            	preparedStatement.setDouble(i++, produtoCriteria.getPrezoAta());
            }
            
    		logger.info(preparedStatement.toString());
           
    		resultSet = preparedStatement.executeQuery();
    		
    		Produto pro = null;
    		resultsProdutos = new ArrayList<Produto>();
    		int currentCount = 0;
    		if (startIndex >= 1 && resultSet.absolute(startIndex)) {
    			do {
    				pro = loadNext(connection, resultSet);
    				resultsProdutos.add(pro);
    				currentCount++;
    			 } while (currentCount < count && resultSet.next());
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
            if (resultSet != null) {
                try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        }
        return new Results<Produto>(resultsProdutos, startIndex, total);
    }

	@Override
	public int create(Connection connection, Produto produto) throws DataException {
		   int rowsAffected = 0;
		    StringBuilder sql = new StringBuilder();
		    PreparedStatement preparedStatement = null;
		    try {
		        sql.append("INSERT INTO produto (id_categoria, id_marca, nome, prezo, desconto, coste, iva, stock, foto, baixa) ");
		        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		        int i = 1;
		        preparedStatement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		        preparedStatement.setInt(i++, produto.getIdCategoria());
		        preparedStatement.setLong(i++, produto.getIdMarca());
		        preparedStatement.setString(i++, produto.getNome());
		        preparedStatement.setDouble(i++, produto.getPrezo());
		        preparedStatement.setInt(i++, produto.getDesconto());
		        preparedStatement.setDouble(i++, produto.getCoste());
		        preparedStatement.setInt(i++, produto.getIva());
		        preparedStatement.setLong(i++, produto.getStock());
		        preparedStatement.setString(i++, produto.getFoto());
		        preparedStatement.setBoolean(i++, produto.getBaixa());
		        logger.info(preparedStatement.toString());
		        rowsAffected = preparedStatement.executeUpdate();
		    } catch (SQLException e) {
		        logger.log(Level.SEVERE, e.getMessage());
		        throw new DataException("Error al crear el producto", e);
		    } finally {
		        if (preparedStatement != null) {
		            try {
		                logger.info("Producto " + produto.getNome() + " creado con ÉXITO");
		                preparedStatement.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		    return rowsAffected;
		}
	@Override
	public Produto update(Connection connection, Produto produto) throws DataException {
		 StringBuilder sql = new StringBuilder();
		    PreparedStatement preparedStatement = null;
		    Produto produtoActualizado = null;
		    
		    try {
		        sql.append("UPDATE produto SET ");
		        
		        if (produto.getIdCategoria() != null) {
		            sql.append("id_categoria=?, ");
		        }
		        if (produto.getIdMarca() != null) {
		            sql.append("id_marca=?, ");
		        }
		        if (produto.getNome() != null) {
		            sql.append("nome=?, ");
		        }
		        if (produto.getPrezo() != null) {
		            sql.append("prezo=?, ");
		        }
		        if (produto.getDesconto() != null) {
		            sql.append("desconto=?, ");
		        }
		        if (produto.getCoste() != null) {
		            sql.append("coste=?, ");
		        }
		        if (produto.getIva() != null) {
		            sql.append("iva=?, ");
		        }
		        if (produto.getStock() != null) {
		            sql.append("stock=?, ");
		        }
		        if (produto.getFoto() != null) {
		            sql.append("foto=?, ");
		        }
		        if (produto.getBaixa() != null) {
		            sql.append("baixa=?, ");
		        }
		        
		        sql.deleteCharAt(sql.length() - 2);

		        sql.append("WHERE id=?");

		        int i = 1;
		        preparedStatement = connection.prepareStatement(sql.toString());
		        
		        if (produto.getIdCategoria() != null) {
		            preparedStatement.setInt(i++, produto.getIdCategoria());
		        }
		        if (produto.getIdMarca() != null) {
		            preparedStatement.setLong(i++, produto.getIdMarca());
		        }
		        if (produto.getNome() != null) {
		            preparedStatement.setString(i++, produto.getNome());
		        }
		        if (produto.getPrezo() != null) {
		            preparedStatement.setDouble(i++, produto.getPrezo());
		        }
		        if (produto.getDesconto() != null) {
		            preparedStatement.setInt(i++, produto.getDesconto());
		        }
		        if (produto.getCoste() != null) {
		            preparedStatement.setDouble(i++, produto.getCoste());
		        }
		        if (produto.getIva() != null) {
		            preparedStatement.setInt(i++, produto.getIva());
		        }
		        if (produto.getStock() != null) {
		            preparedStatement.setLong(i++, produto.getStock());
		        }
		        if (produto.getFoto() != null) {
		            preparedStatement.setString(i++, produto.getFoto());
		        }
		        if (produto.getBaixa() != null) {
		            preparedStatement.setBoolean(i++, produto.getBaixa());
		        }

		        preparedStatement.setLong(i++, produto.getId());

		        logger.info(preparedStatement.toString());
		        preparedStatement.executeUpdate();
		        produtoActualizado = findById(connection, produto.getId());
		        logger.info("Produto"+produtoActualizado.getNome()+" actualizado con éxito");
		    } catch (SQLException e) {
		        logger.log(Level.SEVERE, e.getMessage());
		        throw new DataException("Error al actualizar el producto", e);
		    } finally {
		        if (preparedStatement != null) {
		            try {
		                preparedStatement.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		    return produtoActualizado;
		}

	@Override
	public void softDelete(Connection connection, Long idProduto) throws DataException {
	    StringBuilder sql = new StringBuilder();
	    PreparedStatement preparedStatement = null;

	    try {
	        sql.append("UPDATE produto SET baixa=true WHERE id=?");

	        preparedStatement = connection.prepareStatement(sql.toString());
	        preparedStatement.setLong(1, idProduto);

	        logger.info(preparedStatement.toString());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        logger.log(Level.SEVERE, e.getMessage());
	    } finally {
	        if (preparedStatement != null) {
	            try {
	            	logger.info("Producto de id "+idProduto+" dado de baja con éxito");
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
	    }
	}
	
	private Produto loadNext(Connection connection, ResultSet resultSet) throws SQLException {
		Produto produto = new Produto();
		int i =1;
		produto = new Produto();
		produto.setId(resultSet.getLong(i++));
        produto.setIdCategoria(resultSet.getInt(i++));
        produto.setIdMarca(resultSet.getLong(i++));
        produto.setNome(resultSet.getString(i++));
        produto.setPrezo(resultSet.getDouble(i++));
        produto.setDesconto(resultSet.getInt(i++));
        produto.setCoste(resultSet.getDouble(i++));
        produto.setIva(resultSet.getInt(i++));
        produto.setStock(resultSet.getLong(i++));
        produto.setFoto(resultSet.getString(i++));
        produto.setBaixa(resultSet.getBoolean(i++));
		return produto;
	}

	
}
