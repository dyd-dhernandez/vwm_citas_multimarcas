package com.optimissa.lambda.requesthandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.optimissa.lambda.dto.request.ReqModificarCita;
import com.optimissa.lambda.dto.response.RespuestaDMS;

public class ModificarCita extends ManagerAmazonSQSQueues implements RequestHandler<ReqModificarCita, RespuestaDMS> {

	@Override
	public RespuestaDMS handleRequest(ReqModificarCita params, Context context) {
		context.getLogger().log("ModificarCita.handleRequest()");
		context.getLogger().log("\n" + params);

		return null;
	}

}
