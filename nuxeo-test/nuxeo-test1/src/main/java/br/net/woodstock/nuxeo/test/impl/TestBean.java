package br.net.woodstock.nuxeo.test.impl;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("testBean")
@Scope(ScopeType.CONVERSATION)
public class TestBean implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private String				message;

	public TestBean() {
		super();
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

}
