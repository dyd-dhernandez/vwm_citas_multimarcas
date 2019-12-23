package com.optimissa.lambda.dto.request;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * Parametros para Consulta de Horarios de un Concesionario
 * 
 * @author Daniel.Hernandez
 *
 */
@Data
public class ReqConsultaHorarios implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Número autoincremental generado por el aplicativo móvil con el fin de
	 * identificar la cita por si surgen búsquedas cambios o cancelaciones.
	 */
	private Long idConversacion;

	/**
	 * La primera fecha en el rango de fechas que se desea consultar
	 */
	private String fechaInicial;

	/**
	 * La última fecha en el rango de fechas que se desea consultar
	 */
	private String fechaFinal;

	/**
	 * String [Reparación General, Servicio de Mantenimiento, Hojalatería y Pintura]
	 */
	private String tipoServicio;

	/**
	 * Identificador del Concesionario a consultar
	 */
	private Long idConcesionario;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReqConsultaHorarios [idConversacion=");
		builder.append(idConversacion);
		builder.append(", fechaInicial=");
		builder.append(fechaInicial);
		builder.append(", fechaFinal=");
		builder.append(fechaFinal);
		builder.append(", tipoServicio=");
		builder.append(tipoServicio);
		builder.append(", idConcesionario=");
		builder.append(idConcesionario);
		builder.append("]");
		return builder.toString();
	}

}
