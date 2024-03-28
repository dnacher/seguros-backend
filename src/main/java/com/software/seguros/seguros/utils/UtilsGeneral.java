package com.software.seguros.seguros.utils;

import com.software.seguros.seguros.constantes.Constantes;
import com.software.seguros.seguros.constantes.ConstantesEtiquetas;
import com.software.seguros.seguros.enums.*;
import com.software.seguros.seguros.exceptions.SegurosException;
import com.software.seguros.seguros.persistence.model.Poliza;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class UtilsGeneral {

    public static boolean isNotNullEmptyUserInfo(String name, String password){
        if (name == null || name.isEmpty()) {
//            Errores.ERROR_LOGIN_FALTAN_DATOS_NOMBRE;
            return false;
        } else if (password == null || password.isEmpty()) {
//           Errores.ERROR_LOGIN_FALTAN_DATOS_PASS;
            return false;
        }
        return true;
    }

    public static void validateUrlIdEqualsBodyId(int urlId, Integer bodyId, String errorMsg){
        if (bodyId == null || urlId != bodyId.intValue()) {
            throw new SegurosException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }

    private static String crearEtiquetaError(Codigo message){
        return ConstantesEtiquetas.ERROR +  "1000- " + message.getCodigo();
    }

    private static String crearEtiquetaWarning(Codigo message){
        return ConstantesEtiquetas.INFO + "- " + message.getCodigo();
    }

    public static Date getDateFromLocalDate(LocalDate localDate) {
        if(localDate!=null){
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }else{
            return null;
        }
    }

    public static LocalDate getLocalDateFromDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static boolean esNumero(String s) {
        boolean esNumero;
        try {
            Double.parseDouble(s);
            esNumero = true;
        } catch (Exception ex) {
            esNumero = false;
        }
        return esNumero;
    }

    public static boolean esPorcentaje(String s){
        if(esNumero(s)){
            return Double.valueOf(s) >= 0 && Double.valueOf(s) <= 100;
        } else{
            return false;
        }
    }

    public static Date getLastDayNextMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 2);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getFirstDayNextMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 2);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Integer getFirstDayMonth(int month){
        month--;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.get(Calendar.DATE);
    }

    public static Integer getLastDayMonth(int month) {
        month--;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.get(Calendar.DATE);
    }

    public static String getMesString(Integer mes){
        switch(mes){
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Setiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                return "";
        }
    }

    public static Integer getMesInteger(String mes){
        switch(mes){
            case "Enero":
                return 1;
            case "Febrero":
                return 2;
            case "Marzo":
                return 3;
            case "Abril":
                return 4;
            case "Mayo":
                return 5;
            case "Junio":
                return 6;
            case "Julio":
                return 7;
            case "Agosto":
                return 8;
            case "Setiembre":
                return 9;
            case "Octubre":
                return 10;
            case "Noviembre":
                return 11;
            case "Diciembre":
                return 12;
            default:
                return -1;
        }
    }

    public static boolean numeroPositivo(String value){
        return esNumero(value) && Integer.valueOf(value)>0;
    }

    public static String getFechaFormato(Date date) {
        if(date!=null){
            String fecha="";
            LocalDate ld = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if(ld.getDayOfMonth()<10){
                fecha+= "0" + ld.getDayOfMonth() + "-";
            }else{
                fecha+= ld.getDayOfMonth() + "-";
            }
            if(ld.getMonthValue()<10){
                fecha+= "0" + ld.getMonthValue() + "-";
            }else{
                fecha+= ld.getMonthValue() + "-";
            }
            return fecha + ld.getYear();
        }else{
            return "";
        }
    }

    public static boolean isNullOrEmpty(String str){
        return (str == null || str.isEmpty());
    }

    public static String traeNombrePagina(String str, String menuActual) {
        String pagina = MenuPrincipal.Inicio.getPagina();
        switch (menuActual) {
            case Constantes.MENU_CONFIGURACION:
              pagina = getMenuConfiguracion(str);
              break;
            case Constantes.MENU_SEGURIDAD:
                pagina = getMenuSeguridad(str);
                break;
            case Constantes.MENU_PRINCIPAL:
                pagina = getMenuPrincipal(str);
                break;
            case Constantes.MENU_ESTADOS:
                pagina = getMenuEstados(str);
                break;
          }
        return pagina;
    }

    private static String getMenuEstados(String str){
        Optional<MenuEstados> optional = Arrays.stream(MenuEstados.values()).
                filter(menu -> menu.getPagina().equals(str)).
                findFirst();
        if(optional.isPresent()){
            return optional.get().getPagina();
        }else return ConstantesEtiquetas.VACIO;
    }

    private static String getMenuConfiguracion(String str){
        Optional<MenuConfiguracion> optional = Arrays.stream(MenuConfiguracion.values()).
                filter(menu -> menu.getPagina().equals(str)).
                findFirst();
        if(optional.isPresent()){
            return optional.get().getPagina();
        }else return ConstantesEtiquetas.VACIO;
    }

    private static String getMenuSeguridad(String str){
        Optional<MenuSeguridad> optional = Arrays.stream(MenuSeguridad.values()).
                filter(menu -> menu.getPagina().equals(str)).
                findFirst();
        if(optional.isPresent()){
            return optional.get().getPagina();
        }else return ConstantesEtiquetas.VACIO;
    }

    private static String getMenuPrincipal(String str){
        Optional<MenuPrincipal> optional = Arrays.stream(MenuPrincipal.values()).
                filter(menu -> menu.getPagina().equals(str)).
                findFirst();
        if(optional.isPresent()){
            return optional.get().getPagina();
        }else return ConstantesEtiquetas.VACIO;
    }

    public static Poliza creaPoliza(Poliza poliza){
        Poliza newPoliza = new Poliza();
        newPoliza.setId(poliza.getId());
        newPoliza.setCompania(poliza.getCompania());
        newPoliza.setCliente(poliza.getCliente());
        newPoliza.setNumeroPoliza(poliza.getNumeroPoliza());
        newPoliza.setComienzo(poliza.getComienzo());
        newPoliza.setVencimiento(poliza.getVencimiento());
        newPoliza.setProducto(poliza.getProducto());
        newPoliza.setTipoProducto(poliza.getTipoProducto());
        newPoliza.setPremio(poliza.getPremio());
        newPoliza.setPrima(poliza.getPrima());
        newPoliza.setMoneda(poliza.getMoneda());
        newPoliza.setComisionPorcentaje(poliza.getComisionPorcentaje());
        newPoliza.setFormaPago(poliza.getFormaPago());
        newPoliza.setCuotas(poliza.getCuotas());
        newPoliza.setComienzoCuota(poliza.getComienzoCuota());
        newPoliza.setFinCuota(poliza.getFinCuota());
        newPoliza.setImporteCuota(poliza.getImporteCuota());
        newPoliza.setCerradoPor(poliza.getCerradoPor());
        newPoliza.setEsApp(poliza.getEsApp());
        newPoliza.setEstado(poliza.getEstado());
        newPoliza.setVendedor(poliza.getVendedor());
        newPoliza.setPolizaMadre(poliza.getPolizaMadre());
        return newPoliza;
    }

    public static String creaNumeroPolizaEndoso(String numeroPoliza){
        String nuevoNumeroPoliza;
        String[] parts = numeroPoliza.split("_");
        if(parts.length>1){
            int nuevo = Integer.parseInt(parts[1]);
            nuevo++;
            nuevoNumeroPoliza = parts[0] + "_" + nuevo;
        }else{
            nuevoNumeroPoliza = numeroPoliza + "_1";
        }
        return nuevoNumeroPoliza;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String generateUuid(){
        return UUID.randomUUID().toString();
    }

}
