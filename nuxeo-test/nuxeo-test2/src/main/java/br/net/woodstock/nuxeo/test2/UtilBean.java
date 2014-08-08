package br.net.woodstock.nuxeo.test2;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("utilBean")
@Scope(ScopeType.CONVERSATION)
public class UtilBean implements Serializable {

	private static final long	serialVersionUID	= Version.CURRENT_VERSION;

	public static final int		ROWS				= 5;

	public UtilBean() {
		super();
	}

	public int getRows() {
		return UtilBean.ROWS;
	}
}
