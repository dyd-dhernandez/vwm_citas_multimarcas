package com.optimissa.lambda.util;

import lombok.Getter;

public enum CodigosRespuesta {

	HORARIO_DE_CITA_NO_DISPONIBLE(100, "El horario de la cita ya no está disponible. Por favor, cambia la fecha de tu cita."),
	CONCESIONARIO_INEXISTENTE(110, "El concesionario no existe o no está disponible"),
	ID_DE_CITA_DUPLICADO(120, "No se pudo atender tu solicitud. Intenta de nuevo más tarde."),
	ERROR_DE_AUTENTICACION(300, "No se pudo atender tu solicitud. Intenta de nuevo más tarde."),
	// Caso de cancelacion y edicion
	ID_DE_CITA_NO_ENCONTRADO(404, "No se pudo atender tu solicitud. Intenta de nuevo más tarde."),
	ERROR_DE_PARAMETROS(422, "No se pudo atender tu solicitud. Intenta de nuevo más tarde."),
	NO_SE_ENCONTRARON_CITAS(423, "No se encontraron citas para el vehículo."),
	SOLICITUD_ATENDIDA_CON_EXITO(200, "Solicitud atendida con Exito."),
	// Aqui caen todos los errores adicionales no listados arriba
	NO_SE_PUDO_ATENDER_SU_SOLICITUD(500, "No se pudo atender tu solicitud. Intenta de nuevo más tarde.");
	
	@Getter
	private final int codigo;
	@Getter
	private final String descripcion;

	private CodigosRespuesta(int codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
}
