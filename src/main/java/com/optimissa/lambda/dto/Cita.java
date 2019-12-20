package com.optimissa.lambda.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class Cita implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Número autoincremental generado por el aplicativo móvil con el fin de
	 * identificar la cita por si surgen búsquedas cambios o cancelaciones.
	 */
	private Long idConversacion;

	/** Nombre de la persona que desea agendar la cita */
	private String nombre;

	/** Apellido de la persona que desea agendar la cita */
	private String apellido;

	/** Número de chasis del vehículo para el cual se desea agendar la cita */
	private String numeroChasis;

	/** Fecha en la que se desea agendar la cita */
	private String fecha;

	/** Horario en el que se desea agendar la cita */
	private String horario;

	/** Teléfono de la persona que desea agendar la cita */
	private String teléfono;

	/** Email de la persona que desea agendar la cita */
	private String email;

	/** Identificador del concesionario */
	private String idConcesionario;

	/** Kilometraje de la unidad */
	private Long kilometrajeAuto;

	/**
	 * Kilometraje del servicio a realizar [si tipoServicio=’Servicio de
	 * Mantenimiento’]
	 */
	private Long kilometrajeServicio;

	/**
	 * Tipo de servicio a realizar [Reparación General | Servicio de Mantenimiento |
	 * Hojalatería y Pintura]
	 */
	private String tipoServicio;

	/** Notas del cliente */
	private String comentarios;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cita [idConversacion=");
		builder.append(idConversacion);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido=");
		builder.append(apellido);
		builder.append(", numeroChasis=");
		builder.append(numeroChasis);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append(", horario=");
		builder.append(horario);
		builder.append(", teléfono=");
		builder.append(teléfono);
		builder.append(", email=");
		builder.append(email);
		builder.append(", idConcesionario=");
		builder.append(idConcesionario);
		builder.append(", kilometrajeAuto=");
		builder.append(kilometrajeAuto);
		builder.append(", kilometrajeServicio=");
		builder.append(kilometrajeServicio);
		builder.append(", tipoServicio=");
		builder.append(tipoServicio);
		builder.append(", comentarios=");
		builder.append(comentarios);
		builder.append("]");
		return builder.toString();
	}

}
