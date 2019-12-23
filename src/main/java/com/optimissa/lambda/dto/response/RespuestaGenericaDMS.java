package com.optimissa.lambda.dto.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class RespuestaGenericaDMS implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean exito;

	private Long idConversacion;

	private int codigo;

	private String descripcion;
}
