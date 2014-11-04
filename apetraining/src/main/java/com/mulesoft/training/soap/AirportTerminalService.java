package com.mulesoft.training.soap;

import java.util.Collection;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface AirportTerminalService {

	public Terminal findTerminalStatus(String terminalNumber);

	public Collection<Terminal> listAllTerminalStatuses();
}
