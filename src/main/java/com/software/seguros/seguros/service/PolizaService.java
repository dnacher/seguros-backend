package com.software.seguros.seguros.service;

import com.software.seguros.seguros.constantes.ConstantesEtiquetas;
import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.persistence.dao.PolizaDAO;
import com.software.seguros.seguros.persistence.dao.RegsitroCuotasDAO;
import com.software.seguros.seguros.persistence.model.*;
import com.software.seguros.seguros.persistence.model.DTO.PolizaDTO;
import com.software.seguros.seguros.persistence.model.DTO.PolizaDTOInt;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    private final RegsitroCuotasDAO registroCuotasDAO;

    private static final DecimalFormat df = new DecimalFormat("0,00");
    
    public PolizaService(PolizaDAO polizaDAO, RegsitroCuotasDAO registroCuotasDAO){
        this.polizaDAO = polizaDAO;
        this.registroCuotasDAO = registroCuotasDAO;
    }

    public List<Poliza> getPolizas(){
        return polizaDAO.getPolizas().stream().map(poliza -> procesar(poliza)).collect(Collectors.toList());
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
        if(producto==null) return 0;
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

    public Poliza getPolizaByUuid(String uuid){
        return polizaDAO.getPolizaByUuid(uuid);
    }

    public Poliza getPolizaById(Integer id){
        return polizaDAO.getPolizaById(id);
    }

    public Poliza savePoliza(Poliza poliza){
        boolean update = poliza.getId()!=null;
        poliza = polizaDAO.savePoliza(poliza);
//        saveTipoProductoCliente(poliza);
        if(!update){
            RegistroCuotas registroCuotas = new RegistroCuotas();
            registroCuotas.setNumeroCuotasPagas(0);
            registroCuotas.setPoliza(poliza);
            registroCuotasDAO.saveRegistroCuotas(registroCuotas);
        }
        return poliza;
    }

    public Poliza updatePoliza(Poliza poliza){
        return polizaDAO.updatePoliza(poliza);
    }

    public void deletePoliza(Integer id){
        polizaDAO.deletePoliza(id);
    }

    public List<Poliza> findByCliente(Cliente cliente){
        return polizaDAO.findByCliente(cliente);
    }

    public List<Poliza> findByClienteNuevoYRenovacion(Cliente cliente){
        return polizaDAO.findByClienteAndAndEstadoNombreOrEstadoNombre(cliente, "Nuevo", "Renovacion");
    }

    public List<Poliza> findByClienteAndEstadoEndoso(Cliente cliente){
        return polizaDAO.findByClienteAndAndEstadoNombre(cliente,"Endoso" );
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

    public List<PolizaDTOInt> getSUMPrimaProductos(Date desde, Date hasta){
        return polizaDAO.getSUMPrimaProductos(desde, hasta);
    }

    public List<PolizaDTOInt> getPolizasComisionesByFecha(Date desde, Date hasta){
        return polizaDAO.getPolizasComisionesByFecha(desde, hasta);
    }

    public Codigo validarDatos(Poliza poliza) {
        if(noEsEstado(poliza, ConstantesEtiquetas.NOMINACION) && ObjectUtils.isEmpty(poliza.getNumeroPoliza())) {
            return Codigo.NUMERO_POLIZA_FALTANTE;
//        }else if(pol.isPresent() && isNull() && ex!=0){
//            switch (ex){
//                case 1:
//                    return UtilsGeneral.error(Errores.FECHAS_DISTINTAS);
//                case 2:
//                    return UtilsGeneral.error(Errores.NUMERO_POLIZA_YA_EXISTE);
//                default:
//                    return 0;
//            }
        } else if(noEsEstado(poliza, ConstantesEtiquetas.NOMINACION) &&
                (poliza.getVencimiento()==null || poliza.getComienzoCuota()==null || poliza.getFinCuota()==null)){
            return Codigo.FECHAS_VACIAS;
        }else if(poliza.getVencimiento().isBefore(poliza.getComienzo())) {
            return Codigo.VERIFICAR_FECHAS_VENCIMIENTO;
        } else if(noEsEstado(poliza, ConstantesEtiquetas.NOMINACION) &&
                noEsEstado(poliza, ConstantesEtiquetas.ENDOSO) &&
                poliza.getComienzoCuota().plusYears(1).isBefore(poliza.getFinCuota())) {
            return Codigo.VERIFICAR_FIN_CUOTA_VENCIMIENTO;
        } else if (!poliza.getEstado().getNombre().equals(ConstantesEtiquetas.NOMINACION) &&
                poliza.getFinCuota().isBefore(poliza.getComienzoCuota())) {
            return Codigo.VERIFICAR_FECHAS_CUOTAS;
        } else if(poliza.getEstado()==null){
            return Codigo.FALTA_ESTADO;
//        }else if(poliza.gev && cmbVendedor.getValue()==null){
//            cmbVendedor.requestFocus();
//            return UtilsGeneral.error(Errores.FALTA_VENDEDOR);
        } else if(noEsEstado(poliza, ConstantesEtiquetas.NOMINACION)) {
            return Codigo.CUOTAS_NUMERO;
        } else if (noEsEstado(poliza, ConstantesEtiquetas.NOMINACION)) {
            return Codigo.PREMIO_NUMERO;
        } else if (noEsEstado(poliza, ConstantesEtiquetas.NOMINACION)) {
            return Codigo.PRIMA_NUMERO;
        } else if (poliza.getProducto()==null || poliza.getCliente()==null) {
            return Codigo.FALTA_PRODUCTO;
        } else if (noEsEstado(poliza, ConstantesEtiquetas.NOMINACION) && poliza.getMoneda()==null) {
            return Codigo.FALTA_MONEDA;
        } else if (noEsEstado(poliza, ConstantesEtiquetas.NOMINACION) &&
                noEsEstado(poliza, ConstantesEtiquetas.ENDOSO) &&
                !poliza.getCompania().getNombre().equals(ConstantesEtiquetas.MAPFRE) &&
                (poliza.getPremio() < poliza.getPrima())) {
            return Codigo.PREMIO_MAYOR_PRIMA;
        } else if (noEsEstado(poliza, ConstantesEtiquetas.NOMINACION) && exception517(poliza) &&
                poliza.getComienzoCuota().isBefore(poliza.getComienzo())) {
            return Codigo.COMIENZO_CUOTA_DESPUES_FECHA_COMIENZO;
        } else{
            return Codigo.OK;
        }
    }

    private boolean exception517(Poliza poliza){
        if (poliza.getProducto() != null) {
            return !poliza.getProducto().getNombre().equals(ConstantesEtiquetas.VIAJES);
        }
        return true;
    }

    public boolean noEsEstado(Poliza poliza, String estado) {
        return !poliza.getEstado().getNombre().equals(estado);
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
