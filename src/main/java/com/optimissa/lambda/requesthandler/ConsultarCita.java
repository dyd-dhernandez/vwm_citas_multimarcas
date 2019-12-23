package com.optimissa.lambda.requesthandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.optimissa.lambda.dto.request.ReqConsultarCita;
import com.optimissa.lambda.dto.response.RespuestaDMS;

public class ConsultarCita extends ManagerAmazonSQSQueues implements RequestHandler<ReqConsultarCita, RespuestaDMS> {

	private static String URL_COLA_SALIDA = "https://sqs.us-east-2.amazonaws.com/811219751427/vwmx_req_consultar_cita_to_dms";
	private static String URL_COLA_ENTRADA = "https://sqs.us-east-2.amazonaws.com/811219751427/vwmx_resp_consultar_cita_to_lambda";
	
	@Override
	public RespuestaDMS handleRequest(ReqConsultarCita params, Context context) {
		context.getLogger().log("ConsultarCita.handleRequest()");
		context.getLogger().log("\n" + params);

		sendMessageRequest(params, URL_COLA_SALIDA);

		RespuestaDMS respuestaDMS = searchRespuestaDMS(params.getIdConversacion(), URL_COLA_ENTRADA);

		return respuestaDMS;
	}

}
