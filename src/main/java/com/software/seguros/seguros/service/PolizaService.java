package com.software.seguros.seguros.service;

import com.software.seguros.seguros.constantes.ConstantesEtiquetas;
import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.*;
import com.software.seguros.seguros.persistence.model.DTO.PolizaDTO;
import com.software.seguros.seguros.persistence.model.DTO.PolizaDTOInt;
import com.software.seguros.seguros.persistence.repository.PolizaRepository;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PolizaService {

    public static final String NUEVO = "Nuevo";
    private final LogManagerClass log = new LogManagerClass(getClass());
    private final PolizaRepository repository;
    private final RegistroCuotasService registroCuotasService;
    
    public PolizaService(PolizaRepository repository, RegistroCuotasService registroCuotasService){
        this.repository = repository;
        this.registroCuotasService = registroCuotasService;
    }

    public List<Poliza> getPolizas() {
        return repository.findAllByOrderByIdDesc().stream()
            .map(this::procesar)
            .collect(Collectors.toList());
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
        if(producto.getNombre().equals(NUEVO)){
            return producto.getComisionNueva();
        }else{
            return producto.getComisionRenovacion();
        }
    }

    private double valorVendedor(Poliza poliza){
        if (poliza.getEstado().getNombre().equals(NUEVO)) {
            return poliza.getVendedor().getComisionNueva();
        }else{
            return poliza.getVendedor().getComisionRenovacion();
        }
    }

    public Poliza getPolizaByUuid(String uuid) throws SegurosException {
        log.info( "getPolizaById " + uuid);
        return repository
                .findByUuid(uuid)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La poliza uuid %s no existe", uuid);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public Poliza getPolizaById(Integer id) throws SegurosException {
        log.info( "getPolizaById " + id);
        return repository
                .findById(id)
                .orElseThrow(
                        () -> {
                            String msg = String.format("La poliza id %s no existe", id);
                            log.error(msg);
                            return new SegurosException(HttpStatus.NOT_FOUND, msg);
                        });
    }

    public List<Poliza> findByCliente(Integer clienteId){
        log.info( "findByCliente " + clienteId);
        return repository.findByCliente_Id(clienteId);
    }

    public List<Poliza> findByClienteNuevoYRenovacion(Integer clienteId){
        log.info( "findByClienteNuevoYRenovacion " + clienteId);
        return repository.findByClienteAndEstado_NombreOrEstado_Nombre(clienteId, NUEVO, "Renovacion");
    }

    public List<Poliza> findByClienteAndEstadoEndoso(Integer clienteId){
        log.info( "findByClienteAndAndEstado_Nombre " + clienteId);
        return repository.findByClienteAndEstado_Nombre(clienteId, "Endoso");
    }

    public List<PolizaDTO> getTotalPremioByFechasGroupByProductos(Date desde, Date hasta){
        log.info( "getTotalPremioByFechasGroupByProductos " + desde + " " + hasta);
        return repository.getTotalPremioByFechasGroupByProductos(desde, hasta);
    }

    public List<PolizaDTO> getTotalPrimaByFechasGroupByProductos(Date desde, Date hasta){
        log.info( "getTotalPrimaByFechasGroupByProductos " + desde + " " + hasta);
        return repository.getTotalPrimaByFechasGroupByProductos(desde, hasta);
    }

    public List<PolizaDTO> getCountProductos(Date desde, Date hasta){
        log.info( "getCountProductos " + desde + " " + hasta);
        return repository.getCountProductos(desde, hasta);
    }

    public List<Poliza> getPolizasByFecha(Date desde, Date hasta){
        log.info( "getPolizasByFecha " + desde + " " + hasta);
        return repository.getPolizasByFecha(desde, hasta);
    }

    public List<Poliza> getPolizasVencimientoByFecha(Date desde, Date hasta){
        log.info( "getPolizasVencimientoByFecha " + desde + " " + hasta);
        return repository.getPolizasVencimientoByFecha(desde, hasta);
    }

    public List<PolizaDTOInt> getSUMPrimaProductos(Date desde, Date hasta){
        log.info( "getSUMPrimaProductos " + desde + " " + hasta);
        return repository.getSUMPrimaProductos(desde, hasta);
    }

    public List<PolizaDTOInt> getPolizasComisionesByFecha(Date desde, Date hasta){
        log.info( "getPolizasComisionesByFecha " + desde + " " + hasta);
        return repository.getPolizasComisionesByFecha(desde, hasta);
    }

    public Poliza savePoliza(Poliza poliza){
        boolean update = poliza.getId()!=null;
        poliza = repository.save(poliza);
        if(!update){
            RegistroCuotas registroCuotas = new RegistroCuotas();
            registroCuotas.setNumeroCuotasPagas(0);
            registroCuotas.setPoliza(poliza);
            registroCuotasService.saveRegistroCuotas(registroCuotas);
        }
        return poliza;
    }

    public Poliza updatePoliza(Poliza poliza) throws SegurosException {
        if (poliza.getId() != null) {
            log.info( "updatePoliza " + poliza.toStringLog());
            if(poliza.getUuid()==null){
                poliza.setUuid(UUID.randomUUID().toString());
            }
            if(poliza.getCreated()==null){
                poliza.setCreated(LocalDateTime.now());
            }
            return repository.save(poliza);
        } else {
            String nombre = "La poliza";
            String msg = String.format("%s no se puede actualizar sin Id", nombre);
            log.error(msg);
            throw new SegurosException(HttpStatus.BAD_REQUEST, msg);
        }
    }

    public void deletePoliza(Integer id) {
        log.info( "deletePoliza " + id);
        repository.deleteById(id);
    }

    public Codigo validarDatos(Poliza poliza) {
        if (esEstadoNulo(poliza)) {
            return Codigo.FALTA_ESTADO;
        }
        if (esNumeroPolizaFaltante(poliza)) {
            return Codigo.NUMERO_POLIZA_FALTANTE;
        }
        if (sonFechasVacias(poliza)) {
            return Codigo.FECHAS_VACIAS;
        }
        if (esVencimientoAntesDeComienzo(poliza)) {
            return Codigo.VERIFICAR_FECHAS_VENCIMIENTO;
        }
        if (esFinCuotaAntesDeComienzoCuota(poliza)) {
            return Codigo.VERIFICAR_FECHAS_CUOTAS;
        }
        if (esFinCuotaMayorAUnAnoDeComienzoCuota(poliza)) {
            return Codigo.VERIFICAR_FIN_CUOTA_VENCIMIENTO;
        }
        if (esFaltaProductoOCliente(poliza)) {
            return Codigo.FALTA_PRODUCTO;
        }
        if (esFaltaMoneda(poliza)) {
            return Codigo.FALTA_MONEDA;
        }
        if (esPremioMayorPrima(poliza)) {
            return Codigo.PREMIO_MAYOR_PRIMA;
        }
        if (esComienzoCuotaAntesDeComienzo(poliza)) {
            return Codigo.COMIENZO_CUOTA_DESPUES_FECHA_COMIENZO;
        }
        if (esCuotasNumeroIncorrecto(poliza)) {
            return Codigo.CUOTAS_NUMERO;
        }

        return Codigo.OK;
    }

    private boolean esEstadoNulo(Poliza poliza) {
        return poliza.getEstado() == null;
    }

    private boolean esNumeroPolizaFaltante(Poliza poliza) {
        return noEsEstado(poliza, ConstantesEtiquetas.NOMINACION) && ObjectUtils.isEmpty(poliza.getNumeroPoliza());
    }

    private boolean sonFechasVacias(Poliza poliza) {
        return noEsEstado(poliza, ConstantesEtiquetas.NOMINACION) &&
                (poliza.getVencimiento() == null || poliza.getComienzoCuota() == null || poliza.getFinCuota() == null);
    }

    private boolean esVencimientoAntesDeComienzo(Poliza poliza) {
        return poliza.getVencimiento().isBefore(poliza.getComienzo());
    }

    private boolean esFinCuotaAntesDeComienzoCuota(Poliza poliza) {
        return !poliza.getEstado().getNombre().equals(ConstantesEtiquetas.NOMINACION) &&
                poliza.getFinCuota().isBefore(poliza.getComienzoCuota());
    }

    private boolean esFinCuotaMayorAUnAnoDeComienzoCuota(Poliza poliza) {
        return noEsEstado(poliza, ConstantesEtiquetas.NOMINACION) &&
                noEsEstado(poliza, ConstantesEtiquetas.ENDOSO) &&
                poliza.getComienzoCuota().plusYears(1).isBefore(poliza.getFinCuota());
    }

    private boolean esFaltaProductoOCliente(Poliza poliza) {
        return poliza.getProducto() == null || poliza.getCliente() == null;
    }

    private boolean esFaltaMoneda(Poliza poliza) {
        return noEsEstado(poliza, ConstantesEtiquetas.NOMINACION) && poliza.getMoneda() == null;
    }

    private boolean esPremioMayorPrima(Poliza poliza) {
        return noEsEstado(poliza, ConstantesEtiquetas.NOMINACION) &&
                noEsEstado(poliza, ConstantesEtiquetas.ENDOSO) &&
                !poliza.getCompania().getNombre().equals(ConstantesEtiquetas.MAPFRE) &&
                poliza.getPremio() < poliza.getPrima();
    }

    private boolean esComienzoCuotaAntesDeComienzo(Poliza poliza) {
        return noEsEstado(poliza, ConstantesEtiquetas.NOMINACION) &&
                exception517(poliza) &&
                poliza.getComienzoCuota().isBefore(poliza.getComienzo());
    }

    private boolean esCuotasNumeroIncorrecto(Poliza poliza) {
        return noEsEstado(poliza, ConstantesEtiquetas.NOMINACION);
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

}
