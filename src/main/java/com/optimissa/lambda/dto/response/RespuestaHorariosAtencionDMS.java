package com.optimissa.lambda.dto.response;

import java.io.Serializable;
import java.util.SortedMap;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RespuestaHorariosAtencionDMS extends RespuestaGenericaDMS implements Serializable {

	private static final long serialVersionUID = 1L;

	private SortedMap<String, SortedMap<String, Boolean>> horariosAtencion;

}
