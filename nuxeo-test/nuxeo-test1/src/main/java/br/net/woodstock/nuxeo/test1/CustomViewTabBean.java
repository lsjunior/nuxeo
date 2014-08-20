package br.net.woodstock.nuxeo.test1;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;

@Name(value = "customViewTabBean")
@Scope(value = ScopeType.CONVERSATION)
public class CustomViewTabBean implements Serializable {

	private static final long	serialVersionUID	= Version.CURRENT_VERSION;

	@In(create = true)
	private NuxeoPrincipal		currentUser;

	@In(create = true)
	private DocumentModel		currentDocument;

	public CustomViewTabBean() {
		super();
	}

	public NuxeoPrincipal getCurrentUser() {
		return this.currentUser;
	}

	public void setCurrentUser(final NuxeoPrincipal currentUser) {
		this.currentUser = currentUser;
	}

	public DocumentModel getCurrentDocument() {
		return this.currentDocument;
	}

	public void setCurrentDocument(final DocumentModel currentDocument) {
		this.currentDocument = currentDocument;
	}

}
