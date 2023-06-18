package com.software.seguros.seguros.persistence.model.DTO;


import com.software.seguros.seguros.persistence.model.*;

public interface PolizaDTOInt {

    Producto getProducto();
    TipoProducto getTipoProducto();
    Cliente getCliente();
    Compania getCompania();
    Vendedor getVendedor();
    Double getTotal();
    String getMoneda();
    String getCerradoPor();
    Double getComisionValor();

}
