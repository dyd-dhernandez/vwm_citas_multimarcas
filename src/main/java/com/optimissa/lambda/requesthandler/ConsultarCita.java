package com.optimissa.lambda.requesthandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.optimissa.lambda.dto.request.ReqConsultarCita;
import com.optimissa.lambda.dto.response.RespuestaDMS;
import static com.optimissa.lambda.util.Constantes.*;

public class ConsultarCita extends ManagerAmazonSQSQueues implements RequestHandler<ReqConsultarCita, RespuestaDMS> {

	@Override
	public RespuestaDMS handleRequest(ReqConsultarCita params, Context context) {
		context.getLogger().log("ConsultarCita.handleRequest()");
		context.getLogger().log("\n" + params);

		sendMessageRequest(params, URL_VWMX_REQ_CONSULTAR_CITA_TO_DMS);

		RespuestaDMS respuestaDMS = searchRespuestaDMS(params.getIdConversacion(), URL_VWMX_RESP_CONSULTAR_CITA_TO_LAMBDA);

		return respuestaDMS;
	}

}
