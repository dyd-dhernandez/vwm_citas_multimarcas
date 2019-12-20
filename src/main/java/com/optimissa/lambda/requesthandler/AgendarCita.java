package com.optimissa.lambda.requesthandler;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.optimissa.lambda.dto.request.ReqConsultaHorarios;
import com.optimissa.lambda.dto.response.RespConsultaHorarios;

public class AgendarCita implements RequestHandler<ReqConsultaHorarios, List<RespConsultaHorarios>> {

	@Override
	public List<RespConsultaHorarios> handleRequest(ReqConsultaHorarios paramReq, Context context) {

		context.getLogger().log("AgendarCita.handleRequest()");
		context.getLogger().log("\t- Fecha Inicial: " + paramReq.getFechaInicial());
		context.getLogger().log("\t- Fecha Final: " + paramReq.getFechaFinal());
		context.getLogger().log("\t- Tipo Servicio: " + paramReq.getTipoServicio());
		context.getLogger().log("\t- Id Concesionario: " + paramReq.getIdConcesionario());

		List<RespConsultaHorarios> lstRespConsultaHorarios = new ArrayList<RespConsultaHorarios>();

		RespConsultaHorarios respConsultaHorarios01 = new RespConsultaHorarios();
		RespConsultaHorarios respConsultaHorarios02 = new RespConsultaHorarios();
		RespConsultaHorarios respConsultaHorarios03 = new RespConsultaHorarios();
		RespConsultaHorarios respConsultaHorarios04 = new RespConsultaHorarios();
		RespConsultaHorarios respConsultaHorarios05 = new RespConsultaHorarios();

		SortedMap<String, Boolean> horariosAtencion = new TreeMap<String, Boolean>();
		horariosAtencion.put("09:00", true);
		horariosAtencion.put("10:00", true);
		horariosAtencion.put("11:00", false);
		horariosAtencion.put("12:00", true);
		horariosAtencion.put("13:00", false);

		respConsultaHorarios01.setFecha("2015-08-01");
		respConsultaHorarios01.setHorariosAtencion(horariosAtencion);
		lstRespConsultaHorarios.add(respConsultaHorarios01);
		respConsultaHorarios02.setFecha("2015-08-02");
		respConsultaHorarios02.setHorariosAtencion(horariosAtencion);
		lstRespConsultaHorarios.add(respConsultaHorarios02);
		respConsultaHorarios03.setFecha("2015-08-03");
		respConsultaHorarios03.setHorariosAtencion(horariosAtencion);
		lstRespConsultaHorarios.add(respConsultaHorarios03);
		respConsultaHorarios04.setFecha("2015-08-04");
		respConsultaHorarios04.setHorariosAtencion(horariosAtencion);
		lstRespConsultaHorarios.add(respConsultaHorarios04);
		respConsultaHorarios05.setFecha("2015-08-05");
		respConsultaHorarios05.setHorariosAtencion(horariosAtencion);
		lstRespConsultaHorarios.add(respConsultaHorarios05);

		return lstRespConsultaHorarios;
	}

}
