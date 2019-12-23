package com.optimissa.lambda.requesthandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.optimissa.lambda.dto.request.ReqCancelarCita;
import com.optimissa.lambda.dto.response.RespuestaGenericaDMS;

public class CancelarCita extends ManagerAmazonSQSQueues implements RequestHandler<ReqCancelarCita, RespuestaGenericaDMS> {

	private static String URL_COLA_ENTRADA = "https://sqs.us-east-2.amazonaws.com/811219751427/vwmx_resp_cancelar_cita_to_lambda";
	private static String URL_COLA_SALIDA = "https://sqs.us-east-2.amazonaws.com/811219751427/vwmx_req_cancelar_cita_to_dms";

	@Override
	public RespuestaGenericaDMS handleRequest(ReqCancelarCita params, Context context) {

		context.getLogger().log("CancelarCita.handleRequest()");
		context.getLogger().log("\n" + params);

		// Envia el mensaje a la Queue del DMS
		sendMessageRequest(params, URL_COLA_SALIDA);

		RespuestaGenericaDMS respuestaGenericaDMS = searchRespuestaDMS(params.getIdConversacion(), URL_COLA_ENTRADA);

		return respuestaGenericaDMS;
	}

}
