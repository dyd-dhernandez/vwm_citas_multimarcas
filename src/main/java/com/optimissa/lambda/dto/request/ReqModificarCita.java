package com.optimissa.lambda.dto.request;

import com.optimissa.lambda.dto.Cita;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * Parametros para Modificar Cita
 * 
 * @author Daniel.Hernandez
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReqModificarCita extends Cita {

	private static final long serialVersionUID = 1L;



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReqModificarCita [getIdConversacion()=");
		builder.append(getIdConversacion());
		builder.append(", getNombre()=");
		builder.append(getNombre());
		builder.append(", getApellido()=");
		builder.append(getApellido());
		builder.append(", getNumeroChasis()=");
		builder.append(getNumeroChasis());
		builder.append(", getFecha()=");
		builder.append(getFecha());
		builder.append(", getHorario()=");
		builder.append(getHorario());
		builder.append(", getTeléfono()=");
		builder.append(getTeléfono());
		builder.append(", getEmail()=");
		builder.append(getEmail());
		builder.append(", getIdConcesionario()=");
		builder.append(getIdConcesionario());
		builder.append(", getKilometrajeAuto()=");
		builder.append(getKilometrajeAuto());
		builder.append(", getKilometrajeServicio()=");
		builder.append(getKilometrajeServicio());
		builder.append(", getTipoServicio()=");
		builder.append(getTipoServicio());
		builder.append(", getComentarios()=");
		builder.append(getComentarios());
		builder.append("]");
		return builder.toString();
	}
}
