package com.optimissa.lambda.requesthandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.optimissa.lambda.dto.request.ReqAgendarCita;
import com.optimissa.lambda.dto.response.RespuestaDMS;
import static com.optimissa.lambda.util.Constantes.*;

public class AgendarCita extends ManagerAmazonSQSQueues implements RequestHandler<ReqAgendarCita, RespuestaDMS> {

	@Override
	public RespuestaDMS handleRequest(ReqAgendarCita params, Context context) {
		context.getLogger().log("AgendarCita.handleRequest()");
		context.getLogger().log("\n" + params.toString());

		sendMessageRequest(params, URL_VWMX_REQ_AGENDAR_CITA_TO_DMS);

		RespuestaDMS respuestaDMS = searchRespuestaDMS(params.getIdConversacion(), URL_VWMX_RESP_AGENDAR_CITA_TO_LAMBDA);

		return respuestaDMS;
	}
	
}
