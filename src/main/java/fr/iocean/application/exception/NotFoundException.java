package fr.iocean.application.exception;

import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

public class NotFoundException extends NoSuchRequestHandlingMethodException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2776592159099294392L;

	public NotFoundException(){
		super("resource not found", NotFoundException.class);
	}
}
