package com.software.seguros.seguros.service;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.persistence.dao.ProductoDAO;
import com.software.seguros.seguros.persistence.model.Banco;
import com.software.seguros.seguros.persistence.model.Compania;
import com.software.seguros.seguros.persistence.model.Producto;
import com.software.seguros.seguros.persistence.model.TipoProducto;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductoService {
    
    private final ProductoDAO productoDAO;
    
    public ProductoService(ProductoDAO productoDAO){
        this.productoDAO = productoDAO;
    }

    public List<Producto> getProductos(){
        return productoDAO.getProductos();
    }

    public Producto getProductoByUuid(String uuid){
        return productoDAO.getProductoByUuid(uuid);
    }

    public Producto getProductoById(Integer id){
        return productoDAO.getProductoById(id);
    }

    public List<Producto> findByCompania(Integer companiaId){
        return productoDAO.findByCompania(companiaId);
    }

    public List<Producto> findByTipoProducto(Integer tipoProductoId){
        return productoDAO.findByTipoProducto(tipoProductoId);
    }

    public Producto saveProducto(Producto producto){
        return productoDAO.saveProducto(producto);
    }

    public Producto updateProducto(Producto producto){
        return productoDAO.updateProducto(producto);
    }

    public void deleteProducto(Integer id){
        productoDAO.deleteProducto(id);
    }

    public Codigo validarDatos(Producto producto){
        if(producto.getNombre().isEmpty()) {
            return Codigo.FALTA_NOMBRE_PRODUCTO;
        } else if(productoDAO.countByNombre(producto.getNombre())>0){
            return Codigo.NOMBRE_PRODUCTO_EXISTE;
        }else if (isPorcentaje(producto.getComisionNueva()) || isPorcentaje(producto.getComisionRenovacion())) {
            return Codigo.COMISION_PORCENTAJE;
        } else if(producto.getFechaComienzo()==null || producto.getFechaFinal()==null){
            return Codigo.FALTA_FECHA;
        }else if(producto.getFechaFinal().isBefore(producto.getFechaComienzo())){
            return Codigo.FECHA_COMIENZO_FINAL_ORDEN;
        }else if(producto.getCompania()==null){
            return Codigo.SELECCIONAR_COMPANIA;
        }else if(producto.getTipoProducto()==null){
            return Codigo.SELECCIONAR_TIPO_PRODUCTO;
        }
        return Codigo.OK;
    }

    private boolean isPorcentaje(Double value) {
        return value >= 0 && value<=100;
    }

}
