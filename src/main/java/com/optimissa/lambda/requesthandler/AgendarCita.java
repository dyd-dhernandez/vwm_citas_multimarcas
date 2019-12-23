package com.optimissa.lambda.requesthandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.optimissa.lambda.dto.request.ReqAgendarCita;
import com.optimissa.lambda.dto.response.RespuestaDMS;

public class AgendarCita extends ManagerAmazonSQSQueues implements RequestHandler<ReqAgendarCita, RespuestaDMS> {

	private static String URL_COLA_SALIDA = "https://sqs.us-east-2.amazonaws.com/811219751427/vwmx_req_agendar_cita_to_dms";
	private static String URL_COLA_ENTRADA = "https://sqs.us-east-2.amazonaws.com/811219751427/vwmx_resp_agendar_cita_to_lambda";

	@Override
	public RespuestaDMS handleRequest(ReqAgendarCita params, Context context) {
		context.getLogger().log("AgendarCita.handleRequest()");
		context.getLogger().log("\n" + params.toString());

		sendMessageRequest(params, URL_COLA_SALIDA);

		RespuestaDMS respuestaDMS = searchRespuestaDMS(params.getIdConversacion(), URL_COLA_ENTRADA);

		return respuestaDMS;
	}
	
}
