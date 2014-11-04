package com.mulesoft.training;

import java.util.List;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import edu.emory.mathcs.backport.java.util.Collections;

public class FlightSortingTransformer extends AbstractTransformer {

	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {
		
		if (src instanceof List<?>) {
			List<Flight> flights = (List<Flight>) src;
			Collections.sort(flights);
			return flights;
		}
		return src;
	}

}
