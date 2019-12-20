package com.optimissa.lambda.dto.response;

import java.io.Serializable;
import java.util.SortedMap;

import lombok.Data;

/**
 * 
 * Respuesta para Consulta de Horarios
 * 
 * @author Daniel.Hernandez
 *
 */
@Data
public class RespConsultaHorarios implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fecha;

	private SortedMap<String, Boolean> horariosAtencion;

}
