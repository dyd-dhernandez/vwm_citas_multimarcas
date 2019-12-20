package com.optimissa.lambda.dto.response;

import java.util.List;

import com.optimissa.lambda.dto.Cita;

import lombok.Data;

/**
 * 
 * Respuesta generica para el API Gateway
 * 
 * @author Daniel.Hernandez
 *
 */
@Data
public class RespuestaDMS {

	private List<Cita> citas;
	
	private boolean exito;
	
	private Long idConversacion;
	
	private Long codigo;
	
	private String descripcion;
}
