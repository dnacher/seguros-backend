package com.software.seguros.seguros.enums;

import com.software.seguros.seguros.constantes.Constantes;
import com.software.seguros.seguros.constantes.ConstantesErrores;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Daniel
 */
public enum Codigo {

	//Login											000 - 020
	OK("OK", 0, HttpStatus.OK),
	ERROR_LOGIN_GENERAL("Hubo un error General", 1, HttpStatus.BAD_REQUEST),
	ERROR_LOGIN_FALTAN_DATOS_NOMBRE("Falta Ingresar el nombre de usuario", 2, HttpStatus.BAD_REQUEST),
	ERROR_LOGIN_FALTAN_DATOS_PASS("Falta Ingresar la contraseña", 3, HttpStatus.BAD_REQUEST),
	ERROR_LOGIN_DATOS_INCORRECTOS("El nombre o contraseña son incorrectos", 4, HttpStatus.BAD_REQUEST),
	ERROR_GENERAL("Hubo un error inesperado", 5, HttpStatus.BAD_REQUEST),

	//Siniestros									021 - 040
	NUMERO_SINIESTRO_EXISTE(ConstantesErrores.NUMERO_SINIESTRO_EXISTE, 21, HttpStatus.BAD_REQUEST),
	NUMERO_SINIESTRO_VACIO(ConstantesErrores.NUMERO_SINIESTRO_VACIO, 22, HttpStatus.BAD_REQUEST),
	CLIENTE_NO_EXISTE(ConstantesErrores.CLIENTE_NO_EXISTE, 23, HttpStatus.BAD_REQUEST),
	POLIZA_NO_EXISTE(ConstantesErrores.POLIZA_NO_EXISTE, 24, HttpStatus.BAD_REQUEST),
	VERIFIQUE_DEDUCIBLE("Verifique el valor del importe deducible", 25, HttpStatus.BAD_REQUEST),
	SINIESTRO_YA_ACTUALIZADO("El siniestro ya fue actualizado por otra persona, vuelva a intentar", 26, HttpStatus.BAD_REQUEST),
	SINIESTRO_ASOCIADO_OTRO_REGISTRO("El siniestro esta asociado en otro registro",27, HttpStatus.BAD_REQUEST),
	SINIESTRO_CON_ID_NO_SE_PUEDE_GUARDAR("El siniestro nuevo no puede tener un valor Id",28, HttpStatus.BAD_REQUEST),
	SINIESTRO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El siniestro debe tener un id asociado para poderse actualizar",29, HttpStatus.BAD_REQUEST),
	VERIFIQUE_ESTADO("Verifique el valor del estado", 30, HttpStatus.BAD_REQUEST),

	//Poliza										041 - 060
	NUMERO_POLIZA_FALTANTE("Falta numero de poliza", 41, HttpStatus.BAD_REQUEST),
	NUMERO_POLIZA_YA_EXISTE("Numero de poliza ya existe", 42, HttpStatus.BAD_REQUEST),
	VERIFICAR_FECHAS_VENCIMIENTO("Verificar Fechas de Vencimiento", 43, HttpStatus.BAD_REQUEST),
	VERIFICAR_FIN_CUOTA_VENCIMIENTO("Fin de cuota no puede ser despues del vencimiento",44, HttpStatus.BAD_REQUEST),
	VERIFICAR_FECHAS_CUOTAS("Verificar Fechas de Cuota", 45, HttpStatus.BAD_REQUEST),
	FALTA_ESTADO("Debe seleccionar estado",46, HttpStatus.BAD_REQUEST),
	FALTA_VENDEDOR("Debe seleccionar vendedor",47, HttpStatus.BAD_REQUEST),
	CUOTAS_NUMERO("Numero de cuotas debe ser numerico",48, HttpStatus.BAD_REQUEST),
	PREMIO_NUMERO("Premio debe ser numerico",49, HttpStatus.BAD_REQUEST),
	PRIMA_NUMERO("Prima debe ser numerico",50, HttpStatus.BAD_REQUEST),
	FECHAS_VACIAS("Fechas no pueden estar vacias", 51, HttpStatus.BAD_REQUEST),
	FALTA_PRODUCTO("Seleccione el Producto/Compañia/Cliente", 52, HttpStatus.BAD_REQUEST),
	FALTA_MONEDA("Seleccione moneda", 53, HttpStatus.BAD_REQUEST),
	ELEGIR_PRODUCTO("Debe seleccionar Producto primero", 54, HttpStatus.BAD_REQUEST),
	PREMIO_MAYOR_PRIMA("Premio debe ser mayor a prima", 55, HttpStatus.BAD_REQUEST),
	COMIENZO_CUOTA_DESPUES_FECHA_COMIENZO("Comienzo de cuota posterior a comienzo", 56, HttpStatus.BAD_REQUEST),
	POLIZA_YA_ACTUALIZADA("La poliza ya fue actualizada por otra persona, vuelva a intentar", 57, HttpStatus.BAD_REQUEST),
	FECHAS_DISTINTAS("Las fechas con mismo numero de poliza deben ser distintas", 58, HttpStatus.BAD_REQUEST),
	POLIZA_ASOCIADA_OTRO_REGISTRO("La poliza esta asociada en otro registro", 59, HttpStatus.BAD_REQUEST),
	POLIZA_CON_ID_NO_SE_PUEDE_GUARDAR("El estado de siniestro nuevo no puede tener un valor Id",60, HttpStatus.BAD_REQUEST),
	POLIZA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("La poliza debe tener un id asociado para poderse actualizar",61, HttpStatus.BAD_REQUEST),

	//Banco											061 - 080
	FALTA_NOMBRE_BANCO("Falta nombre del banco", 61, HttpStatus.BAD_REQUEST),
	NOMBRE_BANCO_EXISTE("Ya existe el nombre del banco", 62, HttpStatus.BAD_REQUEST),
	BANCO_ASOCIADO_OTRO_REGISTRO("El banco esta asociado en otro registro",63, HttpStatus.BAD_REQUEST),
	BANCO_CON_ID_NO_SE_PUEDE_GUARDAR("El banco nuevo no puede tener un valor Id",64, HttpStatus.BAD_REQUEST),
	BANCO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El banco debe tener un id asociado para poderse actualizar",65, HttpStatus.BAD_REQUEST),

	//Cliente										081 - 100
	FALTA_NOMBRE_CLIENTE("Falta nombre de cliente", 81, HttpStatus.BAD_REQUEST),
	CLIENTE_EXISTE("Ya existe el cliente", 82, HttpStatus.BAD_REQUEST),
	CLIENTE_ASOCIADO_OTRO_REGISTRO("El cliente esta asociado en otro registro",83, HttpStatus.BAD_REQUEST),
	FALTA_CEDULA_CLIENTE("Falta cedula de cliente", 84, HttpStatus.BAD_REQUEST),
	CLIENTE_YA_ACTUALIZADO("El cliente ya fue actualizado por otra persona, vuelva a intentar", 85, HttpStatus.BAD_REQUEST),
	CLIENTE_IGUAL("Verifique que el cliente no sea el mismo", 86, HttpStatus.BAD_REQUEST),
	CLIENTE_CON_ID_NO_SE_PUEDE_GUARDAR("El cliente nuevo no puede tener un valor Id",87, HttpStatus.BAD_REQUEST),
	CLIENTE_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El cliente debe tener un id asociado para poderse actualizar",88, HttpStatus.BAD_REQUEST),

	//Compañias										101 - 120
	FALTA_NOMBRE_COMPANIA("Falta nombre de compañia", 101, HttpStatus.BAD_REQUEST),
	COMPANIA_EXISTE("Ya existe la compañia", 102, HttpStatus.BAD_REQUEST),
	COMPANIA_ASOCIADA_OTRO_REGISTRO("La compañia esta asociada en otro registro",103, HttpStatus.BAD_REQUEST),
	COMPANIA_CON_ID_NO_SE_PUEDE_GUARDAR("La compañia nuevo no puede tener un valor Id",104, HttpStatus.BAD_REQUEST),
	COMPANIA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("La compañia debe tener un id asociado para poderse actualizar",105, HttpStatus.BAD_REQUEST),

	//Cotizacion Vendedores							121 - 140
	FALTA_VENDEDOR_COTIZACION("Falta vendedor", 121, HttpStatus.BAD_REQUEST),
	COTIZACION_EXISTE("Ya existe la cotizacion para este vendedor", 122, HttpStatus.BAD_REQUEST),
	COMISION_NUMERO("Comision debe ser numerico",123, HttpStatus.BAD_REQUEST),
	COMISION_ASOCIADA_OTRO_REGISTRO("La comision esta asociada en otro registro",124, HttpStatus.BAD_REQUEST),
	COTIZACION_CON_ID_NO_SE_PUEDE_GUARDAR("La cotizacion nueva no puede tener un valor Id",104, HttpStatus.BAD_REQUEST),
	COTIZACION_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("La cotizacion debe tener un id asociado para poderse actualizar",105, HttpStatus.BAD_REQUEST),

	//Estado de Poliza								141 - 160
	FALTA_NOMBRE_ESTADO_POLIZA("Falta nombre de estado", 141, HttpStatus.BAD_REQUEST),
	ESTADO_POLIZA_EXISTE("Ya existe el estado", 142, HttpStatus.BAD_REQUEST),
	ESTADO_POLIZA_ASOCIADO_OTRO_REGISTRO(Constantes.ESTADO,143, HttpStatus.BAD_REQUEST),
	ESTADO_POLIZA_CON_ID_NO_SE_PUEDE_GUARDAR("El estado de poliza nuevo no puede tener un valor Id",144, HttpStatus.BAD_REQUEST),
	ESTADO_POLIZA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El estado de poliza debe tener un id asociado para poderse actualizar",145, HttpStatus.BAD_REQUEST),

	//Estado Siniestro								161 - 180
	FALTA_NOMBRE_ESTADO_SINIESTRO("Falta nombre del estado", 161, HttpStatus.BAD_REQUEST),
	ESTADO_SINIESTRO_EXISTE("Ya existe el estado", 162, HttpStatus.BAD_REQUEST),
	ESTADO_SINIESTRO_ASOCIADO_OTRO_REGISTRO(Constantes.ESTADO,163, HttpStatus.BAD_REQUEST),
	ESTADO_SINIESTRO_CON_ID_NO_SE_PUEDE_GUARDAR("El estado de siniestro nuevo no puede tener un valor Id",164, HttpStatus.BAD_REQUEST),
	ESTADO_SINIESTRO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El estado de siniestro debe tener un id asociado para poderse actualizar",165, HttpStatus.BAD_REQUEST),

	//Forma de Pago									181 - 200
	FALTA_NOMBRE_FORMA_DE_PAGO("Falta nombre de forma pago", 181, HttpStatus.BAD_REQUEST),
	FORMA_DE_PAGO_EXISTE("Ya existe la forma de pago", 182, HttpStatus.BAD_REQUEST),
	FORMA_PAGO_ASOCIADO_OTRO_REGISTRO("La forma de pago esta asociada a otro registro",183, HttpStatus.BAD_REQUEST),
	FORMA_PAGO_CON_ID_NO_SE_PUEDE_GUARDAR("La forma de pago nueva no puede tener un valor Id",184, HttpStatus.BAD_REQUEST),
	FORMA_PAGO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("La forma de pago debe tener un id asociado para poderse actualizar",185, HttpStatus.BAD_REQUEST),

	//Ingreso 										201 - 220
	FALTA_BANCO("Seleccionar un banco",201, HttpStatus.BAD_REQUEST),
	ANIO_NUMERICO("El año debe ser un valor numerico",202, HttpStatus.BAD_REQUEST),
	VALOR_NUMERICO("El valor debe ser numerico",203, HttpStatus.BAD_REQUEST),

	//Moneda										221 - 240
	FALTA_NOMBRE_MONEDA("Falta nombre de la moneda", 221, HttpStatus.BAD_REQUEST),
	NOMBRE_MONEDA_EXISTE("Ya existe el nombre de la moneda", 222, HttpStatus.BAD_REQUEST),
	MONEDA_ASOCIADA_OTRO_REGISTRO("La moneda esta asociada en otro registro", 223, HttpStatus.BAD_REQUEST),
	FALTA_SIMBOLO_MONEDA("Falta simbolo de la moneda", 224, HttpStatus.BAD_REQUEST),
	MONEDA_CON_ID_NO_SE_PUEDE_GUARDAR("La moneda nueva no puede tener un valor Id",225, HttpStatus.BAD_REQUEST),
	MONEDA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("La moneda debe tener un id asociado para poderse actualizar",226, HttpStatus.BAD_REQUEST),

	//Producto										241 - 260
	FALTA_NOMBRE_PRODUCTO("Falta nombre del producto", 241, HttpStatus.BAD_REQUEST),
	NOMBRE_PRODUCTO_EXISTE("Ya existe el nombre del producto", 242, HttpStatus.BAD_REQUEST),
	COMISION_NUMERICO("Los valores de comision deben ser numericos",243, HttpStatus.BAD_REQUEST),
	FALTA_FECHA("Faltan fechas",244, HttpStatus.BAD_REQUEST),
	FECHA_COMIENZO_FINAL_ORDEN("Verificar fecha inicio y fecha final",245, HttpStatus.BAD_REQUEST),
	FECHA_DESDE_MENOR_FECHA_HASTA("Fecha desde debe ser anterior a la fecha hasta",246, HttpStatus.BAD_REQUEST),
	COMISION_PORCENTAJE("Los valores de comision deben estar entre 0 y 100",247, HttpStatus.BAD_REQUEST),
	SELECCIONAR_COMPANIA("Debe seleccionar una compañia", 248, HttpStatus.BAD_REQUEST),
	SELECCIONAR_TIPO_PRODUCTO("Debe seleccionar tipo de producto", 249, HttpStatus.BAD_REQUEST),
	PRODUCTO_ASOCIADO_OTRO_REGISTRO("El producto esta asociado en otro registro", 250, HttpStatus.BAD_REQUEST),
	PRODUCTO_CON_ID_NO_SE_PUEDE_GUARDAR("El producto nuevo no puede tener un valor Id",251, HttpStatus.BAD_REQUEST),
	PRODUCTO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El producto debe tener un id asociado para poderse actualizar",252, HttpStatus.BAD_REQUEST),

	//Reportes 										261 - 280
	ELEGIR_ARCHIVO("Debe elegir un archivo",261, HttpStatus.BAD_REQUEST),
	SELECCIONE_OPCION("Seleccione una opción",262, HttpStatus.BAD_REQUEST),
	NO_IMPLEMENTADO("todavia no implementado",263, HttpStatus.BAD_REQUEST),
	ELEGIR_REPORTE("Debe elegir un reporte",264, HttpStatus.BAD_REQUEST),

	//Seguridad										281 - 300
	MARCAR_VALOR_PERMISO("Debe marcar uno de los valores de permisos",281, HttpStatus.BAD_REQUEST),
	PAGINA_EXISTE("La pagina ya esta en los permisos",282, HttpStatus.BAD_REQUEST),
	SELECCIONAR_COMBOS("debe seleccionar los combos",283, HttpStatus.BAD_REQUEST),
	SELECCIONAR_PERMISO("Debe seleccionar un permiso a borrar",284, HttpStatus.BAD_REQUEST),

	//Tipo Poliza									301 - 320
	FALTA_NOMBRE_TIPO_POLIZA("Falta nombre del tipo de poliza", 301, HttpStatus.BAD_REQUEST),
	NOMBRE_TIPO_POLIZA_EXISTE("Ya existe el nombre del tipo de poliza", 302, HttpStatus.BAD_REQUEST),

	//Tipo Producto									321 - 340
	FALTA_NOMBRE_TIPO_PRODUCTO("Falta nombre del tipo de producto", 321, HttpStatus.BAD_REQUEST),
	NOMBRE_TIPO_PRODUCTO_EXISTE("Ya existe el nombre del tipo de producto", 322, HttpStatus.BAD_REQUEST),
	TIPO_PRODUCTO_ASOCIADO_OTRO_REGISTRO("El tipo de producto esta asociado en otro registro", 323, HttpStatus.BAD_REQUEST),
	TIPO_PRODUCTO_CON_ID_NO_SE_PUEDE_GUARDAR("El producto nuevo no puede tener un valor Id",251, HttpStatus.BAD_REQUEST),
	TIPO_PRODUCTO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El producto debe tener un id asociado para poderse actualizar",252, HttpStatus.BAD_REQUEST),

	//Tipo Usuarios									341 - 360
	FALTA_NOMBRE_TIPO_USUARIO("Falta nombre del tipo de usuario", 341, HttpStatus.BAD_REQUEST),
	NOMBRE_TIPO_USUARIO_EXISTE("Ya existe el nombre del tipo de usuario", 342, HttpStatus.BAD_REQUEST),
	TIPO_USUARIO_ASOCIADO_OTRO_REGISTRO("El tipo de usuario esta asociado en otro registro", 343, HttpStatus.BAD_REQUEST),
	TIPO_USUARIO_CON_ID_NO_SE_PUEDE_GUARDAR("El tipo de usuario nuevo no puede tener un valor Id",344, HttpStatus.BAD_REQUEST),
	TIPO_USUARIO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El tipo de usuario debe tener un id asociado para poderse actualizar",345, HttpStatus.BAD_REQUEST),


	//Usuario										361 - 380
	FALTA_NOMBRE_USUARIO("Agregar Nombre al Usuario",361, HttpStatus.BAD_REQUEST),
	PASS_USUARIO("Agregar contraseña al Usuario",362, HttpStatus.BAD_REQUEST),
	PASS_USUARIO_MENOR_4("la contraseña debe tener por lo menos 4 caracteres",363, HttpStatus.BAD_REQUEST),
	PASS_NO_COINCIDE("Deben coincidir ambos passwords.",364, HttpStatus.BAD_REQUEST),
	USUARIO_EXISTE("Nombre de usuario ya existe", 365, HttpStatus.BAD_REQUEST),
	USUARIO_ASOCIADO_OTRO_REGISTRO("El usuario esta asociado en otro registro", 366, HttpStatus.BAD_REQUEST),
	USUARIO_CON_ID_NO_SE_PUEDE_GUARDAR("El usuario nuevo no puede tener un valor Id",367, HttpStatus.BAD_REQUEST),
	USUARIO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El usuario debe tener un id asociado para poderse actualizar",368, HttpStatus.BAD_REQUEST),

	//Venedor										381 - 400
	FALTA_NOMBRE_VENDEDOR("Falta nombre del vendedor", 381, HttpStatus.BAD_REQUEST),
	NOMBRE_VENDEDOR_EXISTE("Ya existe el nombre del vendedor", 382, HttpStatus.BAD_REQUEST),
	VENDEDOR_ASOCIADO_OTRO_REGISTRO("El vendedor esta asociado en otro registro",383, HttpStatus.BAD_REQUEST),
	VENDEDOR_CON_ID_NO_SE_PUEDE_GUARDAR("El vendedor nuevo no puede tener un valor Id",384, HttpStatus.BAD_REQUEST),
	VENDEDOR_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El vendedor debe tener un id asociado para poderse actualizar",385, HttpStatus.BAD_REQUEST),

	//Cuota poliza									401 - 420
	CUOTA_POLIZA_FALTA_POLIZA("Falta poliza", 401, HttpStatus.BAD_REQUEST),
	CUOTA_POLIZA_CON_ID_NO_SE_PUEDE_GUARDAR("El vendedor nuevo no puede tener un valor Id",384, HttpStatus.BAD_REQUEST),
	CUOTA_POLIZA_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El vendedor debe tener un id asociado para poderse actualizar",385, HttpStatus.BAD_REQUEST),

	//Permiso usuario 								421 - 440
	PERMISO_USUARIO_CON_ID_NO_SE_PUEDE_GUARDAR("El permiso usuario nuevo no puede tener un valor Id",421, HttpStatus.BAD_REQUEST),
	PERMISO_USUARIO_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El permiso usuario debe tener un id asociado para poderse actualizar",422, HttpStatus.BAD_REQUEST),

	//Registro cuotas								441 - 460
	REGISTRO_CUOTAS_CON_ID_NO_SE_PUEDE_GUARDAR("El registro cuotas nuevo no puede tener un valor Id",441, HttpStatus.BAD_REQUEST),
	REGISTRO_CUOTAS_SIN_ID_NO_SE_PUEDE_ACTUALIZAR("El registro cuotas debe tener un id asociado para poderse actualizar",442, HttpStatus.BAD_REQUEST),
	REGISTRO_CUOTAS_SIN_POLIZA("El registro cuotas debe tener poliza",443, HttpStatus.BAD_REQUEST),


	SIN_PERMISOS("Sin permisos, contacte al administrador", 403, HttpStatus.BAD_REQUEST),
	LOGUEO_ERROR ("Credenciales incorrectas",0, HttpStatus.BAD_REQUEST),
	VERIFICAR("Verificar", 1, HttpStatus.BAD_REQUEST),
	FALTA_TIPO_MONEDA("Falta tipo de moneda", 2, HttpStatus.BAD_REQUEST),
	FALTA_CONCEPTO("Falta concepto", 5, HttpStatus.BAD_REQUEST),
	FECHA_FIN_MENOR_INICIO("La fecha fin no puede ser menor a la de comienzo", 7, HttpStatus.BAD_REQUEST),
	ERROR("Error", 8, HttpStatus.BAD_REQUEST),
	WARNING("Warning", 9, HttpStatus.BAD_REQUEST);

	private final String error;
	private final int codigoError;
	private final HttpStatus httpStatus;

	Codigo(String error, int codigoError, HttpStatus httpStatus) {
		this.error = error;
		this.codigoError = codigoError;
		this.httpStatus = httpStatus;
	}

	public String getError() {
		return "[" + codigoError + "] " + error;
	}

	public int getCodigoError() {
		return codigoError;
	}

	public HttpStatus getHttpStatus() { return httpStatus; }

}
