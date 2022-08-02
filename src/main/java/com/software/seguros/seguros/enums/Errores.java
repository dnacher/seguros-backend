package com.software.seguros.seguros.enums;

import com.software.seguros.seguros.constantes.ConstantesErrores;

/**
 *
 * @author Daniel
 */
public enum Errores {

	//Login											000 - 020
	ERROR_LOGIN_GENERAL("Hubo un error General", 0),
	ERROR_LOGIN_FALTAN_DATOS_NOMBRE("Falta Ingresar el nombre de usuario", 1),
	ERROR_LOGIN_FALTAN_DATOS_PASS("Falta Ingresar la contraseña", 2),
	ERROR_LOGIN_DATOS_INCORRECTOS("El nombre o contraseña son incorrectos", 3),
	ERROR_GENERAL("Hubo un error", 0),

	//Principal Siniestros							021 - 040
	NUMERO_SINIESTRO_EXISTE(ConstantesErrores.NUMERO_SINIESTRO_EXISTE, 21),
	NUMERO_SINIESTRO_VACIO(ConstantesErrores.NUMERO_SINIESTRO_VACIO, 22),
	CLIENTE_NO_EXISTE(ConstantesErrores.CLIENTE_NO_EXISTE, 23),
	POLIZA_NO_EXISTE(ConstantesErrores.POLIZA_NO_EXISTE, 24),
	VERIFIQUE_DEDUCIBLE("Verifique el valor del importe deducible", 25),
	SINIESTRO_YA_ACTUALIZADO("El siniestro ya fue actualizado por otra persona, vuelva a intentar", 26),
	SINIESTRO_ASOCIADO_OTRO_REGISTRO("El siniestro esta asociado en otro registro",27),

	//Principal Poliza								041 - 060
	NUMERO_POLIZA_FALTANTE("Falta numero de poliza", 41),
	NUMERO_POLIZA_YA_EXISTE("Numero de poliza ya existe", 42),
	VERIFICAR_FECHAS_VENCIMIENTO("Verificar Fechas de Vencimiento", 43),
	VERIFICAR_FIN_CUOTA_VENCIMIENTO("Fin de cuota no puede ser despues del vencimiento",44),
	VERIFICAR_FECHAS_CUOTAS("Verificar Fechas de Cuota", 45),
	FALTA_ESTADO("Debe seleccionar estado",46),
	FALTA_VENDEDOR("Debe seleccionar vendedor",47),
	CUOTAS_NUMERO("Numero de cuotas debe ser numerico",48),
	PREMIO_NUMERO("Premio debe ser numerico",49),
	PRIMA_NUMERO("Prima debe ser numerico",50),
	FECHAS_VACIAS("Fechas no pueden estar vacias", 51),
	FALTA_PRODUCTO("Seleccione el Producto/Compañia/Cliente", 52),
	FALTA_MONEDA("Seleccione moneda", 53),
	ELEGIR_PRODUCTO("Debe seleccionar Producto primero", 54),
	PREMIO_MAYOR_PRIMA("Premio debe ser mayor a prima", 55),
	COMIENZO_CUOTA_DESPUES_FECHA_COMIENZO("Comienzo de cuota posterior a comienzo", 56),
	POLIZA_YA_ACTUALIZADA("La poliza ya fue actualizada por otra persona, vuelva a intentar", 57),
	FECHAS_DISTINTAS("Las fechas con mismo numero de poliza deben ser distintas", 58),
	POLIZA_ASOCIADA_OTRO_REGISTRO("La poliza esta asociada en otro registro", 59),

	//Banco											061 - 080
	FALTA_NOMBRE_BANCO("Falta nombre del banco", 61),
	NOMBRE_BANCO_EXISTE("Ya existe el nombre del banco", 62),
	BANCO_ASOCIADO_OTRO_REGISTRO("El banco esta asociado en otro registro",63),

	//Cliente										081 - 100
	FALTA_NOMBRE_CLIENTE("Falta nombre de cliente", 81),
	CLIENTE_EXISTE("Ya existe el cliente", 82),
	CLIENTE_ASOCIADO_OTRO_REGISTRO("El cliente esta asociado en otro registro",83),
	FALTA_CEDULA_CLIENTE("Falta cedula de cliente", 84),
	CLIENTE_YA_ACTUALIZADO("El cliente ya fue actualizado por otra persona, vuelva a intentar", 85),
	CLIENTE_IGUAL("Verifique que el cliente no sea el mismo", 86),

	//Compañias										101 - 120
	FALTA_NOMBRE_COMPANIA("Falta nombre de compañia", 101),
	COMPANIA_EXISTE("Ya existe la compañia", 102),
	COMPANIA_ASOCIADA_OTRO_REGISTRO("La compañia esta asociada en otro registro",83),

	//Cotizacion Vendedores							121 - 140
	FALTA_VENDEDOR_COTIZACION("Falta vendedor", 121),
	COTIZACION_EXISTE("Ya existe la cotizacion para este vendedor", 122),
	COMISION_NUMERO("Comision debe ser numerico",123),
	COMISION_ASOCIADA_OTRO_REGISTRO("La comision esta asociada en otro registro",124),

	//Estado de Poliza								141 - 160
	FALTA_NOMBRE_ESTADO_POLIZA("Falta nombre de estado", 141),
	ESTADO_POLIZA_EXISTE("Ya existe el estado", 142),
	ESTADO_POLIZA_ASOCIADO_OTRO_REGISTRO("El estado esta asociado en otro registro",143),

	//Estado Siniestro								161 - 180
	FALTA_NOMBRE_ESTADO("Falta nombre del estado", 161),
	ESTADO_SINIESTRO_EXISTE("Ya existe el estado", 162),
	ESTADO_SINIESTRO_ASOCIADO_OTRO_REGISTRO("El estado esta asociado en otro registro",163),

	//Forma de Pago									181 - 200
	FALTA_NOMBRE_FORMA_DE_PAGO("Falta nombre de f. pago", 181),
	FORMA_DE_PAGO_EXISTE("Ya existe la forma de pago", 182),
	FORMA_PAGO_ASOCIADO_OTRO_REGISTRO("El estado esta asociado en otro registro",183),

	//Ingreso 										201 - 220
	FALTA_BANCO("Seleccionar un banco",201),
	ANIO_NUMERICO("El año debe ser un valor numerico",202),
	VALOR_NUMERICO("El valor debe ser numerico",203),

	//Moneda										221 - 240
	FALTA_NOMBRE_MONEDA("Falta nombre del banco", 221),
	NOMBRE_MONEDA_EXISTE("Ya existe el nombre del banco", 222),
	MONEDA_ASOCIADA_OTRO_REGISTRO("La moneda esta asociada en otro registro", 223),

	//Producto										241 - 260
	FALTA_NOMBRE_PRODUCTO("Falta nombre del producto", 241),
	NOMBRE_PRODUCTO_EXISTE("Ya existe el nombre del producto", 242),
	COMISION_NUMERICO("Los valores de comision deben ser numericos",243),
	FALTA_FECHA("Faltan fechas",244),
	FECHA_COMIENZO_FINAL_ORDEN("Verificar fecha inicio y fecha final",245),
	FECHA_DESDE_MENOR_FECHA_HASTA("Fecha desde debe ser anterior a la fecha hasta",246),
	COMISION_PORCENTAJE("Los valores de comision deben estar entre 0 y 100",247),
	SELECCIONAR_COMPANIA("Debe seleccionar una compañia", 248),
	SELECCIONAR_TIPO_PRODUCTO("Debe seleccionar tipo de producto", 249),
	PRODUCTO_ASOCIADO_OTRO_REGISTRO("El producto esta asociado en otro registro", 250),

	//Reportes 										261 - 280
	ELEGIR_ARCHIVO("Debe elegir un archivo",261),
	SELECCIONE_OPCION("Seleccione una opción",262),
	NO_IMPLEMENTADO("todavia no implementado",263),
	ELEGIR_REPORTE("Debe elegir un reporte",264),

	//Seguridad										281 - 300
	MARCAR_VALOR_PERMISO("Debe marcar uno de los valores de permisos",281),
	PAGINA_EXISTE("La pagina ya esta en los permisos",282),
	SELECCIONAR_COMBOS("debe seleccionar los combos",283),
	SELECCIONAR_PERMISO("Debe seleccionar un permiso a borrar",284),

	//Tipo Poliza									301 - 320
	FALTA_NOMBRE_TIPO_POLIZA("Falta nombre del tipo de poliza", 301),
	NOMBRE_TIPO_POLIZA_EXISTE("Ya existe el nombre del tipo de poliza", 302),

	//Tipo Producto									321 - 340
	FALTA_NOMBRE_TIPO_PRODUCTO("Falta nombre del tipo de producto", 321),
	NOMBRE_TIPO_PRODUCTO_EXISTE("Ya existe el nombre del tipo de producto", 322),
	TIPO_PRODUCTO_ASOCIADO_OTRO_REGISTRO("El tipo de producto esta asociado en otro registro", 323),

	//Tipo Usuarios									341 - 360
	FALTA_NOMBRE_TIPO_USUARIO("Falta nombre del tipo de usuario", 341),
	NOMBRE_TIPO_USUARIO_EXISTE("Ya existe el nombre del tipo de usuario", 342),
	TIPO_USUARIO_ASOCIADO_OTRO_REGISTRO("El tipo de usuario esta asociado en otro registro", 343),


	//Usuario										361 - 380
	FALTA_NOMBRE_USUARIO("Agregar Nombre al Usuario",361),
	PASS_USUARIO("Agregar contraseña al Usuario",362),
	PASS_USUARIO_MENOR_4("la contraseña debe tener por lo menos 4 caracteres",363),
	PASS_NO_COINCIDE("Deben coincidir ambos passwords.",364),
	USUARIO_EXISTE("Nombre de usuario ya existe", 365),
	USUARIO_ASOCIADO_OTRO_REGISTRO("El usuario esta asociado en otro registro", 366),

	//Venedor										381 - 400
	FALTA_NOMBRE_VENDEDOR("Falta nombre del vendedor", 381),
	NOMBRE_VENDEDOR_EXISTE("Ya existe el nombre del vendedor", 382),
	VENDEDOR_ASOCIADO_OTRO_REGISTRO("El vendedor esta asociado en otro registro",383),

	SIN_PERMISOS("Sin permisos, contacte al administrador", 403),
	LOGUEO_ERROR ("Credenciales incorrectas",0),
	VERIFICAR("Verificar", 1),
	FALTA_TIPO_MONEDA("Falta tipo de moneda", 2),
	FALTA_CONCEPTO("Falta concepto", 5),
	FECHA_FIN_MENOR_INICIO("La fecha fin no puede ser menor a la de comienzo", 7),
	ERROR("Error", 8),
	WARNING("Warning", 9);

	private final String error;
	private final int codigo;

	Errores(String error, int codigo) {
		this.error = error;
		this.codigo = codigo;
	}

	public String getError() {
		return error;
	}

	public int getCodigo() {
		return codigo;
	}

}
