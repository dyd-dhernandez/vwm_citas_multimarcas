package com.optimissa.lambda.dto.response;

import java.io.Serializable;
import java.util.List;

import com.optimissa.lambda.dto.Cita;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RespuestaDMS extends RespuestaGenericaDMS implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Cita> citas;

}
