package com.dwn.dogworld.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.services.ExceptionReporter;

/**
 * @author Nnanna Madu (14/03/2015)
 * Exception Report Page which overrides the exception report
 * provided with the framework. It gracefully handles exceptions
 */
public class ExceptionReport implements ExceptionReporter {
	
	@Property
	private Throwable exception;

	public void reportException(Throwable exception) {
		// TODO Auto-generated method stub
		this.exception = exception;
	}
	
}
