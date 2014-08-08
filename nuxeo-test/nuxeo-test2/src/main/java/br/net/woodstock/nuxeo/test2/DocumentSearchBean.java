package br.net.woodstock.nuxeo.test2;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;

@Name("documentSearchBean")
@Scope(ScopeType.CONVERSATION)
public class DocumentSearchBean implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private static final String	DOCUMENT_QUERY		= "SELECT * FROM Document";

	@In(create = true)
	private CoreSession			session;

	private DocumentModel		document;

	private RichFacesDataModel	dataModel;

	public DocumentSearchBean() {
		super();
	}

	public String search() throws Exception {
		this.dataModel = new RichFacesDataModel(this.session, DocumentSearchBean.DOCUMENT_QUERY);
		return null;
	}

	public DocumentModel getDocument() {
		return document;
	}

	public void setDocument(final DocumentModel document) {
		this.document = document;
	}

	public RichFacesDataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(final RichFacesDataModel dataModel) {
		this.dataModel = dataModel;
	}

}
