package com.software.seguros.seguros.service;

import com.software.seguros.seguros.persistence.dao.PolizaDAO;
import com.software.seguros.seguros.persistence.dao.RegsitroCuotasDAO;
import com.software.seguros.seguros.persistence.dao.TipoProductoClienteDAO;
import com.software.seguros.seguros.persistence.model.*;
import com.software.seguros.seguros.service.DTO.PolizaDTO;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PolizaService {
    
    private final PolizaDAO polizaDAO;
    private final TipoProductoClienteDAO tipoProductoClienteDAO;
    private final RegsitroCuotasDAO registroCuotasDAO;

    private static final DecimalFormat df = new DecimalFormat("0,00");
    
    public PolizaService(PolizaDAO polizaDAO, TipoProductoClienteDAO tipoProductoClienteDAO, RegsitroCuotasDAO registroCuotasDAO){
        this.polizaDAO = polizaDAO;
        this.tipoProductoClienteDAO = tipoProductoClienteDAO;
        this.registroCuotasDAO = registroCuotasDAO;
    }

    public List<Poliza> getPolizas(){
        return polizaDAO.getPolizas().stream().map(poliza -> {
            return procesar(poliza);
        }).collect(Collectors.toList());
    }

    private Poliza procesar(Poliza poliza){
        double comisionPorcentaje;
        if (poliza.getVendedor()!=null) {
            double comisionVendedorPorcentaje;
            comisionVendedorPorcentaje = valorVendedor(poliza);
            poliza.setComisionVendedorPorcentaje(comisionVendedorPorcentaje);
            poliza.setComisionVendedorValor( calculaPorcentaje (comisionVendedorPorcentaje,poliza.getPremio()));
            comisionPorcentaje = valorProducto(poliza.getProducto()) - valorVendedor(poliza);
            poliza.setComisionPorcentaje(comisionPorcentaje);
            if(poliza.getPrima()!=null){
                poliza.setComisionValor(calculaPorcentaje(comisionPorcentaje,poliza.getPrima()));
            }
        }else{
            comisionPorcentaje = valorProducto(poliza.getProducto());
            poliza.setComisionPorcentaje(comisionPorcentaje);
            if(poliza.getPrima()!=null){
                poliza.setComisionValor(calculaPorcentaje(comisionPorcentaje,poliza.getPrima()));
            }
        }
        return poliza;
    }

    private double calculaPorcentaje(double porcentaje, double valor){
        double d = (porcentaje/100*(valor));
        return UtilsGeneral.round(d, 2);
    }

    private double valorProducto(Producto producto){
        if(producto.getNombre().equals("Nuevo")){
            return producto.getComisionNueva();
        }else{
            return producto.getComisionRenovacion();
        }
    }

    private double valorVendedor(Poliza poliza){
        if (poliza.getEstado().getNombre().equals("Nuevo")) {
            return poliza.getVendedor().getComisionNueva();
        }else{
            return poliza.getVendedor().getComisionRenovacion();
        }
    }

    public Poliza getPolicyById(Integer id){
        return polizaDAO.getPolizaById(id);
    }

    public Poliza savePolicy(Poliza poliza){
        boolean update = poliza.getId()!=null;
        poliza = polizaDAO.savePoliza(poliza);
        saveTipoProductoCliente(poliza);
        if(!update){
            RegistroCuotas registroCuotas = new RegistroCuotas();
            registroCuotas.setNumeroCuotasPagas(0);
            registroCuotas.setPoliza(poliza);
            registroCuotasDAO.saveRegistroCuotas(registroCuotas);
        }
        return poliza;
    }

    private void saveTipoProductoCliente(Poliza poliza){
        TipoProductoCliente tpc = new TipoProductoCliente();
        tpc.setNombre(poliza.getTipoProducto().getNombre() + "-" + poliza.getProducto().getNombre());
        tpc.setCliente(poliza.getCliente());
        tpc.setPoliza(poliza);
        tipoProductoClienteDAO.saveTipoProductoCliente(tpc);
    }

    public Poliza updatePolicy(Poliza poliza){
        return polizaDAO.updatePoliza(poliza);
    }

    public void deletePolicy(Poliza poliza){
        polizaDAO.deletePoliza(poliza);
    }

    public List<Poliza> findByCliente(Cliente cliente){
        return polizaDAO.findByCliente(cliente);
    }

    public List<Poliza> findByClienteNuevoYRenovacion(Cliente cliente){
        return polizaDAO.findByClienteAndAndEstado_NombreOrEstado_Nombre(cliente, "Nuevo", "Renovacion");
    }

    public List<Poliza> findByClienteAndEstadoEndoso(Cliente cliente){
        return polizaDAO.findByClienteAndAndEstado_Nombre(cliente,"Endoso" );
    }

    public List<PolizaDTO> getTotalPremioByFechasGroupByProductos(Date desde, Date hasta){
        return polizaDAO.getTotalPremioByFechasGroupByProductos(desde, hasta);
    }

    public List<PolizaDTO> getTotalPrimaByFechasGroupByProductos(Date desde, Date hasta){
        return polizaDAO.getTotalPrimaByFechasGroupByProductos(desde, hasta);
    }

    public List<PolizaDTO> getCountProductos(Date desde, Date hasta){
        return polizaDAO.getCountProductos(desde, hasta);
    }

    public List<Poliza> getPolizasByFecha(Date desde, Date hasta){
        return polizaDAO.getPolizasByFecha(desde, hasta);
    }

    public List<Poliza> getPolizasVencimientoByFecha(Date desde, Date hasta){
        return polizaDAO.getPolizasVencimientoByFecha(desde, hasta);
    }

    public List<PolizaDTO> getSUMPrimaProductos(Date desde, Date hasta){
        return polizaDAO.getSUMPrimaProductos(desde, hasta);
    }

    public List<PolizaDTO> getPolizasComisionesByFecha(Date desde, Date hasta){
        return polizaDAO.getPolizasComisionesByFecha(desde, hasta);
    }

    //uuid
    private String generateUuid(){
        long most64SigBits = get64MostSignificantBitsForVersion1();
        long least64SigBits = get64LeastSignificantBitsForVersion1();
        UUID uuid= new UUID(most64SigBits, least64SigBits);
        return uuid.toString();
    }

    private long get64LeastSignificantBitsForVersion1() {
        Random random = new Random();
        long random63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
        long variant3BitFlag = 0x8000000000000000L;
        return random63BitLong + variant3BitFlag;
    }

    private long get64MostSignificantBitsForVersion1() {
        LocalDateTime start = LocalDateTime.of(1582, 10, 15, 0, 0, 0);
        Duration duration = Duration.between(start, LocalDateTime.now());
        long seconds = duration.getSeconds();
        long nanos = duration.getNano();
        long timeForUuidIn100Nanos = seconds * 10000000 + nanos * 100;
        long least12SignificatBitOfTime = (timeForUuidIn100Nanos & 0x000000000000FFFFL) >> 4;
        long version = 1 << 12;
        return (timeForUuidIn100Nanos & 0xFFFFFFFFFFFF0000L) + version + least12SignificatBitOfTime;
    }
}
