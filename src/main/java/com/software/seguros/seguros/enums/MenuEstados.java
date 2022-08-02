package com.software.seguros.seguros.enums;

/**
 *
 * @author Daniel
 */

public enum MenuEstados {

    Inicio("Inicio","Inicio","Principal",null),
    EstadoSiniestro("EstadoSiniestro", "Estado del Siniestro","Estados", null),
    EstadoPoliza("EstadoPoliza", "Estado de poliza","Estados", null),;

    private final String pagina;
    private final String menu;
	private final String carpeta;
	private final Class controller;

    MenuEstados(String pagina, String menu, String carpeta, Class controller) {
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
