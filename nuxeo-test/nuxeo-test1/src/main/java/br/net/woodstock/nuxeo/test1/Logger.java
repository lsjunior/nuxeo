package br.net.woodstock.nuxeo.test1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Logger {

	private static final Log	LOG	= LogFactory.getLog(Logger.class.getPackage().toString());

	private Logger() {
		//
	}

	public static Log getLog() {
		return Logger.LOG;
	}

}
