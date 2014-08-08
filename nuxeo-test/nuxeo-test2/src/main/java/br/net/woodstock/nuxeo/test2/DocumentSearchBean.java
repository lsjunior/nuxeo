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

	private static final long	serialVersionUID	= Version.CURRENT_VERSION;

	private static final String	DOCUMENT_QUERY		= "SELECT * FROM Document WHERE ecm:primaryType = '%s' AND dc:title LIKE '%s'";

	@In(create = true)
	private CoreSession			documentManager;

	private String				type;

	private String				filter;

	private DocumentModel		document;

	private RichFacesDataModel	dataModel;

	public DocumentSearchBean() {
		super();
	}

	public String search() throws Exception {
		String query = String.format(DocumentSearchBean.DOCUMENT_QUERY, this.type, NXQLHelper.getLikeValue(this.filter));
		this.dataModel = new RichFacesDataModel(this.documentManager, query, UtilBean.ROWS);
		return null;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(final String filter) {
		this.filter = filter;
	}

	public DocumentModel getDocument() {
		return this.document;
	}

	public void setDocument(final DocumentModel document) {
		this.document = document;
	}

	public RichFacesDataModel getDataModel() {
		return this.dataModel;
	}

	public void setDataModel(final RichFacesDataModel dataModel) {
		this.dataModel = dataModel;
	}

}
