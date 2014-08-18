package br.net.woodstock.nuxeo.test1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.core.event.Event;

public class CheckChangeEventListener extends AbstractDocumentEventListener {

	private static final String	DOCUMENT_COPY_QUERY	= "SELECT * FROM Copy WHERE copy:parentUuid = '%s'";

	private Log					log					= LogFactory.getLog(CheckChangeEventListener.class);

	public CheckChangeEventListener() {
		super();
	}

	@Override
	protected void doHandleEvent(final Event event, final DocumentModel document) throws ClientException {
		this.log.info("Checking for copies...");

		CoreSession session = event.getContext().getCoreSession();
		String query = String.format(CheckChangeEventListener.DOCUMENT_COPY_QUERY, document.getId());
		DocumentModelList list = session.query(query);

		this.log.info("Copies found " + list.size());
		for (DocumentModel copy : list) {
			this.updateCopy(document, copy);
		}
	}

	private void updateCopy(final DocumentModel document, final DocumentModel copy) throws ClientException {
		this.log.info("Copying " + document.getName() + " to " + copy.getName());
		for (String schema : document.getSchemas()) {
			copy.setProperties(schema, document.getProperties(schema));
		}
	}

}
