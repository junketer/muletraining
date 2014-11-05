package com.mulesoft.training;

import java.util.List;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

import edu.emory.mathcs.backport.java.util.Collections;

public class FlightSortingTransformer extends AbstractTransformer {

	@Override
	protected Object doTransform(Object src, String enc)
			throws TransformerException {
		/*
		 * from our flow, this component can only be called 
		 * after the Combine Collections component, so 
		 * we know that we are safe to cast to a List of some kind
		 * We leave the types open to cater for a change of type for
		 * any reason
		 */
		List<?> flights = (List<?>) src;
		Collections.sort(flights);
		return flights;
	}

}
