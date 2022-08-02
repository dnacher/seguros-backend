package com.software.seguros.seguros.service.DTO;


import com.software.seguros.seguros.persistence.model.*;

public interface PolizaDTO {

    Producto getProducto();
    TipoProducto getTipoProducto();
    Double getPremio();
    Double getPrima();
    Cliente getCliente();
    Compania getCompania();
    Vendedor getVendedor();
    Double getTotal();
    String getMoneda();
    String getCerradoPor();
    Double getComisionValor();

}
