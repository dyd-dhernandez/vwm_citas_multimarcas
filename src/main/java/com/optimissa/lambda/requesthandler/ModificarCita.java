package com.optimissa.lambda.requesthandler;

import static com.optimissa.lambda.util.Constantes.URL_VWMX_REQ_AGENDAR_CITA_TO_DMS;
import static com.optimissa.lambda.util.Constantes.URL_VWMX_REQ_CANCELAR_CITA_TO_DMS;
import static com.optimissa.lambda.util.Constantes.URL_VWMX_RESP_AGENDAR_CITA_TO_LAMBDA;
import static com.optimissa.lambda.util.Constantes.URL_VWMX_RESP_CANCELAR_CITA_TO_LAMBDA;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.optimissa.lambda.dto.request.ReqCancelarCita;
import com.optimissa.lambda.dto.request.ReqModificarCita;
import com.optimissa.lambda.dto.response.RespuestaDMS;
import com.optimissa.lambda.dto.response.RespuestaGenericaDMS;

public class ModificarCita extends ManagerAmazonSQSQueues implements RequestHandler<ReqModificarCita, RespuestaDMS> {

	@Override
	public RespuestaDMS handleRequest(ReqModificarCita params, Context context) {
		context.getLogger().log("ModificarCita.handleRequest()");
		context.getLogger().log("\n" + params);

		// Cancelar la Cita a modificar
		System.out.println("\nCancelando Cita: [idConversacion = " + params.getIdConversacion() + "] ");
		ReqCancelarCita reqCancelarCita = new ReqCancelarCita();
		reqCancelarCita.setIdConversacion(params.getIdConversacion());
		reqCancelarCita.setIdConcesionario(params.getIdConcesionario());
		sendMessageRequest(reqCancelarCita, URL_VWMX_REQ_CANCELAR_CITA_TO_DMS);
		RespuestaGenericaDMS respuestaGenericaDMS = searchRespuestaDMS(params.getIdConversacion(),
				URL_VWMX_RESP_CANCELAR_CITA_TO_LAMBDA);

		RespuestaDMS respuestaDMS = new RespuestaDMS();
		// Si la cancelacion se realiza con exito, entonces agenda una nueva Cita
		if (respuestaGenericaDMS.isExito()) {
			System.out.println("Agendando nueva Cita: [idConversacion = " + params.getIdConversacion() + "] ");
			sendMessageRequest(params, URL_VWMX_REQ_AGENDAR_CITA_TO_DMS);
			respuestaDMS = searchRespuestaDMS(params.getIdConversacion(), URL_VWMX_RESP_AGENDAR_CITA_TO_LAMBDA);
		}

		return respuestaDMS;
	}

}
