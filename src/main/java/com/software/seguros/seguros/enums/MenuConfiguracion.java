package com.software.seguros.seguros.enums;

/**
 *
 * @author Daniel
 */
public enum MenuConfiguracion {

    Inicio("Inicio","Inicio","Principal",null),
    Banco("Banco", "Banco","Configuracion", null),
    Companias("Companias", "Compañias","Configuracion", null),
    CotizacionVendedores("CotizacionVendedores", "Cotiz. de vendedores","Configuracion", null),
    Estados("Estados", "Estados","Configuracion", null),
    FormaPago("FormaPago", "Forma de Pago","Configuracion", null),
    Moneda("Moneda", "Moneda","Configuracion", null),
    Productos("Productos", "Productos","Configuracion", null),
    TipoProductos("TipoProductos", "Tipo de productos","Configuracion", null),
    Vendedores("Vendedores", "Vendedores","Configuracion", null);

    private final String pagina;
    private final String menu;
	private final String carpeta;
	private final Class controller;

    MenuConfiguracion(String pagina, String menu, String carpeta, Class controller) {
        this.pagina = pagina;
        this.menu = menu;
		this.carpeta = carpeta;
		this.controller = controller;
    }

    public String getPagina() {
        return pagina;
    }

    public String getMenu() {
        return menu;
    }

	public String getCarpeta() {
		return carpeta;
	}

    public Class getController() {
        return controller;
    }
}
