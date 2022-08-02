package com.software.seguros.seguros.enums;

/**
 *
 * @author Daniel
 */

public enum MenuSeguridad {

    Inicio("Inicio","Inicio","Principal",null),
    Usuarios("Usuarios", "Usuarios","Seguridad", null),
    TipoUsuarios("TipoUsuarios", "Tipo de usuarios","Seguridad", null),
    Permisos("Permisos", "Permisos","Seguridad", null);

    private final String pagina;
    private final String menu;
	private final String carpeta;
	private final Class controller;

    MenuSeguridad(String pagina, String menu, String carpeta, Class controller) {
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
