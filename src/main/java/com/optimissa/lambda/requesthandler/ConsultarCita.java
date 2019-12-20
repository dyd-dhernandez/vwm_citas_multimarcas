package com.optimissa.lambda.requesthandler;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.util.json.Jackson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optimissa.lambda.dto.Cita;
import com.optimissa.lambda.dto.request.ReqConsultarCita;
import com.optimissa.lambda.dto.response.RespuestaDMS;

public class ConsultarCita implements RequestHandler<ReqConsultarCita, RespuestaDMS> {

	private static String URL_COLA_SALIDA = "https://sqs.us-east-2.amazonaws.com/811219751427/vwmx_req_consultar_cita_to_dms";
	private static String URL_COLA_ENTRADA = "https://sqs.us-east-2.amazonaws.com/811219751427/vwmx_resp_consultar_cita_to_lambda";
	
	public static final ObjectMapper mapperJson = new ObjectMapper();


	@Override
	public RespuestaDMS handleRequest(ReqConsultarCita params, Context context) {
		context.getLogger().log("ConsultarCita.handleRequest()");
		context.getLogger().log("\n" + params);

		// Envia el mensaje a la Queue del DMS
		sendMessageRequest(params);

		return getRespuestaDMS(params.getIdConversacion(), URL_COLA_ENTRADA);
	}

	/**
	 * Sondea la Queue del DMS, esperando recibir su respuesta y cuando la
	 * encuentre, la devuelve
	 * @param idConversacion 
	 */
	private RespuestaDMS getRespuestaDMS(Long idConversacion, String urlCola) {
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(urlCola);
		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
		
		RespuestaDMS respuestaDMS = new RespuestaDMS();
		List<Cita> citas = new ArrayList<Cita>();

		for (Message message : messages) {
			// Busca el idConversacion en cada mensaje de la cola de Entrada
			if(message.getMessageAttributes().get("idConversacion").getStringValue().equals(String.valueOf(idConversacion))) {
				try {
					Cita cita = mapperJson.readValue(message.getBody(), Cita.class);
					citas.add(cita);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				respuestaDMS.setCitas(citas);				
			}
		}
		respuestaDMS.setExito(true);
		respuestaDMS.setCodigo(200L);
		respuestaDMS.setDescripcion("Citas encontradas con exito");

		return respuestaDMS;
	}

	private void sendMessageRequest(ReqConsultarCita params) {
		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

		SendMessageRequest sendMsgRequest = new SendMessageRequest().withQueueUrl(URL_COLA_SALIDA)
				.withMessageBody(Jackson.toJsonPrettyString(params));
		sqs.sendMessage(sendMsgRequest);
	}

}
