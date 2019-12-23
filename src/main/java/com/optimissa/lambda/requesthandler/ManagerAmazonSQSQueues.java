package com.optimissa.lambda.requesthandler;

import java.util.List;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.util.json.Jackson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optimissa.lambda.dto.response.RespuestaDMS;
import com.optimissa.lambda.dto.response.RespuestaGenericaDMS;
import com.optimissa.lambda.util.CodigosRespuesta;

public class ManagerAmazonSQSQueues {

	public static final ObjectMapper mapperJson = new ObjectMapper();
	public static final int WITH_WAIT_TIME_SECONDS = 10; // Segundos de espera

	/**
	 * Envia unmensaje a la Cola
	 * 
	 * @param params
	 * @param urlCola
	 */
	public void sendMessageRequest(Object params, String urlCola) {
		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

		SendMessageRequest sendMsgRequest = new SendMessageRequest().withQueueUrl(urlCola)
				.withMessageBody(Jackson.toJsonPrettyString(params));
		sqs.sendMessage(sendMsgRequest);
	}

	/**
	 * Elimina el mensaje indicado de la Cola.
	 * 
	 * @param message
	 * @param urlCola
	 */
	public void deleteMessageRequest(Message message, String urlCola) {
		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

		sqs.deleteMessage(
				new DeleteMessageRequest().withQueueUrl(urlCola).withReceiptHandle(message.getReceiptHandle()));
	}

	/**
	 * Configura un objeto RespuestaDMS.
	 * 
	 * @param respuesta
	 * @param estadoRespuesta
	 * @param codigoRespuesta
	 */
	public void configureRespuestaDMS(RespuestaGenericaDMS respuesta, boolean estadoRespuesta, CodigosRespuesta codigoRespuesta) {
		respuesta.setExito(estadoRespuesta);
		respuesta.setCodigo(codigoRespuesta.getCodigo());
		respuesta.setDescripcion(codigoRespuesta.getDescripcion());
	}

	/**
	 * Busca en la Queue de Entrada por unos segundos, esperando una respuesta del DMS.
	 * 
	 * @param idConversacion
	 * @param urlCola
	 * @return
	 */
	public RespuestaDMS searchRespuestaDMS(Long idConversacion, String urlCola) {
		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(urlCola)
				.withWaitTimeSeconds(WITH_WAIT_TIME_SECONDS);
		List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();

		System.out.println("\n" + messages.size() + " mensajes de respuesta encontrados.");
		System.out.print("Buscando: [idConversacion = " + idConversacion + "]");

		RespuestaDMS respuestaDMS = new RespuestaDMS();
		for (Message message : messages) {
			// Busca el idConversacion en el mensaje
			String msgBody = message.getBody();
			if (msgBody.contains("idConversacion") && msgBody.contains(String.valueOf(idConversacion))) {
				try {
					System.out.println(", mensaje encontrado... ");
					respuestaDMS = mapperJson.readValue(msgBody, RespuestaDMS.class);
					deleteMessageRequest(message, urlCola);
					if (respuestaDMS.isExito()) {
						configureRespuestaDMS(respuestaDMS, true, CodigosRespuesta.SOLICITUD_ATENDIDA_CON_EXITO);
					}
					// TODO Enviar este mensaje en una cola de INSERTS para Postgress
				} catch (Exception e) {
					System.out.println("\tOcurrio un error inesperado...");
					e.printStackTrace();
				}
			}
		}

		return respuestaDMS;
	}

}
