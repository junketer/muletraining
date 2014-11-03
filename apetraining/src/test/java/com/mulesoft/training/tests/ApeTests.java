package com.mulesoft.training.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.api.client.LocalMuleClient;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.transport.http.HttpConnector;

public class ApeTests extends FunctionalTestCase {

	@Override
	protected String getConfigFile() {

		return "apetraining.xml";
	}

	@Test
	public void testUnited() throws Exception {
		MuleClient client = muleContext.getClient();
		Map<String, Object> headers = new HashMap<String, Object>(1);
		headers.put(HttpConnector.HTTP_METHOD_PROPERTY, "GET");
		MuleMessage msg = client.send("http://localhost:8083/united/flight/SFO", "",
				headers, 10000);
		assertNotNull(msg);
		assertNotNull(msg.getPayload());
		assertTrue(msg.getPayload() instanceof InputStream);
		String payloadAsString = msg.getPayloadAsString();
		assertNotNull(payloadAsString);

		assertEquals(
				"[{\"airlineName\":\"United\",\"price\":400,\"departureDate\":\"2015/03/20\",\"planeType\":\"Boing 737\",\"origin\":\"MUA\",\"emptySeats\":0,\"code\":\"ER38sd\",\"destination\":\"SFO\"},{\"airlineName\":\"United\",\"price\":945,\"departureDate\":\"2015/09/11\",\"planeType\":\"Boing 757\",\"origin\":\"MUA\",\"emptySeats\":54,\"code\":\"ER39rk\",\"destination\":\"SFO\"},{\"airlineName\":\"United\",\"price\":954,\"departureDate\":\"2015/02/12\",\"planeType\":\"Boing 777\",\"origin\":\"MUA\",\"emptySeats\":23,\"code\":\"ER39rj\",\"destination\":\"SFO\"}]",
				payloadAsString);
		System.out.println("United: " + payloadAsString);
	}

	@Test
	public void testDelta() throws Exception {

		LocalMuleClient client = muleContext.getClient();
		Map<String, Object> headers = new HashMap<String, Object>(1);
		headers.put(HttpConnector.HTTP_METHOD_PROPERTY, "GET");
		MuleMessage msg = client.send("http://localhost:8082/delta?destination=SFO", "", headers,
				10000);
		assertNotNull(msg);
		assertNotNull(msg.getPayload());
		assertTrue(msg.getPayload() instanceof InputStream);
		String payloadAsString = msg.getPayloadAsString();
		assertNotNull(payloadAsString);
		assertEquals(
				"<?xml version='1.0' encoding='UTF-8'?><ns2:findFlightResponse xmlns:ns2=\"http://training.mulesoft.com/\"><return><airlineName>Delta</airlineName><code>A1B2C3</code><departureDate>2015/03/20</departureDate><destination>SFO</destination><emptySeats>40</emptySeats><origin>MUA</origin><planeType>Boing 737</planeType><price>400.0</price></return><return><airlineName>Delta</airlineName><code>A1BTT4</code><departureDate>2015/02/12</departureDate><destination>SFO</destination><emptySeats>30</emptySeats><origin>MUA</origin><planeType>Boing 777</planeType><price>593.0</price></return><return><airlineName>Delta</airlineName><code>A14244</code><departureDate>2015/02/12</departureDate><destination>SFO</destination><emptySeats>10</emptySeats><origin>MUA</origin><planeType>Boing 787</planeType><price>294.0</price></return></ns2:findFlightResponse>",
				payloadAsString);

		System.out.println("Delta: " + payloadAsString);
	}

	@Test
	public void testAmerican() throws Exception {

		LocalMuleClient client = muleContext.getClient();
		Map<String, Object> headers = new HashMap<String, Object>(1);
		headers.put(HttpConnector.HTTP_METHOD_PROPERTY, "GET");
		MuleMessage msg = client.send("http://localhost:8084/american?destination=SFO", "", headers,
				10000);
		assertNotNull(msg);
		assertNotNull(msg.getPayload());
		assertTrue(msg.getPayload() instanceof InputStream);
		String payloadAsString = msg.getPayloadAsString();
		assertNotNull(payloadAsString);

		System.out.println("Amercian: " + payloadAsString);
	}
}
