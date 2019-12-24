package com.optimissa.lambda.requesthandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.optimissa.lambda.dto.request.ReqCancelarCita;
import com.optimissa.lambda.dto.response.RespuestaGenericaDMS;
import static com.optimissa.lambda.util.Constantes.*;

public class CancelarCita extends ManagerAmazonSQSQueues implements RequestHandler<ReqCancelarCita, RespuestaGenericaDMS> {

	@Override
	public RespuestaGenericaDMS handleRequest(ReqCancelarCita params, Context context) {
		context.getLogger().log("CancelarCita.handleRequest()");
		context.getLogger().log("\n" + params);

		// Envia el mensaje a la Queue del DMS
		sendMessageRequest(params, URL_VWMX_REQ_CANCELAR_CITA_TO_DMS);

		RespuestaGenericaDMS respuestaGenericaDMS = searchRespuestaDMS(params.getIdConversacion(), URL_VWMX_RESP_CANCELAR_CITA_TO_LAMBDA);

		return respuestaGenericaDMS;
	}

}
