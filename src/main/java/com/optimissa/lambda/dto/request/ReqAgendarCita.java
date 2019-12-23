package com.optimissa.lambda.dto.request;

import com.optimissa.lambda.dto.Cita;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * Parametros para Agendar Cita
 * 
 * @author Daniel.Hernandez
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReqAgendarCita extends Cita {

	private static final long serialVersionUID = 1L;

}
