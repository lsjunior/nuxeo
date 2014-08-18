package br.net.woodstock.nuxeo.test1;

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.event.EventContext;
import org.nuxeo.ecm.core.event.EventListener;
import org.nuxeo.ecm.core.event.impl.DocumentEventContext;

public abstract class AbstractDocumentEventListener implements EventListener {

	// org.nuxeo.ecm.core.api.event.DocumentEventTypes

	public AbstractDocumentEventListener() {
		super();

	}

	@Override
	public void handleEvent(final Event event) throws ClientException {
		EventContext context = event.getContext();
		if (context instanceof DocumentEventContext) {
			DocumentModel document = ((DocumentEventContext) context).getSourceDocument();
			if (document != null) {
				this.doHandleEvent(event, document);
			}
		}
	}

	protected abstract void doHandleEvent(final Event event, final DocumentModel document) throws ClientException;

}
