package com.software.seguros.seguros.enums;


/**
 *
 * @author Daniel
 */
public enum MenuPrincipal {

    Inicio("Inicio", "Inicio","Principal", null),
    Clientes("Clientes", "Clientes","Principal", null),
    ClienteVisualizar("ClienteVisualizar", "Clientes Visualizar","Principal", null),
    Polizas("Polizas", "Polizas","Principal", null),
    PagoCuotas("PagoCuotas", "Pago de cuotas","Principal", null),
    Siniestros("Siniestros", "Siniestros","Principal", null),
    Ingresos("Ingresos", "Ingresos","Principal", null),
    Reportes("Reportes", "Reportes","Principal", null),
    Configuracion("Configuracion", "Configuracion","Principal", null),
    Seguridad("Seguridad", "Seguridad","Principal", null),;

    private final String pagina;
    private final String menu;
	private final String carpeta;
	private final Class controller;

    MenuPrincipal(String pagina, String menu, String carpeta, Class controller) {
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
