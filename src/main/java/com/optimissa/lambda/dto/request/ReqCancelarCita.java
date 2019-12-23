package com.optimissa.lambda.dto.request;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * Parametros para Cancelar Cita
 * 
 * @author Daniel.Hernandez
 *
 */
@Data
public class ReqCancelarCita implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Número autoincremental generado por el aplicativo móvil con el fin de
	 * identificar la cita por si surgen búsquedas cambios o cancelaciones.
	 */
	private Long idConversacion;

	/** Identificador del concesionario */
	private String idConcesionario;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReqCancelarCita [idConversacion=");
		builder.append(idConversacion);
		builder.append(", idConcesionario=");
		builder.append(idConcesionario);
		builder.append("]");
		return builder.toString();
	}
	
}
