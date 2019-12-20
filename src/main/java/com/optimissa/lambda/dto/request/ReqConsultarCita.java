package com.optimissa.lambda.dto.request;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * Parametros para Consultar Cita
 * 
 * @author Daniel.Hernandez
 *
 */
@Data
public class ReqConsultarCita implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Número autoincremental generado por el aplicativo móvil con el fin de
	 * identificar la cita por si surgen búsquedas cambios o cancelaciones.
	 */
	private Long idConversacion;
	
	private String numeroChasis;

	private String teléfono;

	private String email;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReqConsultarCita [idConversacion=");
		builder.append(idConversacion);
		builder.append(", numeroChasis=");
		builder.append(numeroChasis);
		builder.append(", teléfono=");
		builder.append(teléfono);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
