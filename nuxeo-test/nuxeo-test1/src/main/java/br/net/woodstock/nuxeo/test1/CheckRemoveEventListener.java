package br.net.woodstock.nuxeo.test1;

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.schema.DocumentType;
import org.nuxeo.ecm.core.schema.types.Type;

public class CheckRemoveEventListener extends AbstractDocumentEventListener {

	private static final String	MESSAGE	= "Não é possivel deletar este documento";

	public CheckRemoveEventListener() {
		super();
	}

	@Override
	protected void doHandleEvent(final Event event, final DocumentModel document) throws ClientException {
		DocumentType documentType = document.getDocumentType();
		if (this.isDocument(documentType)) {
			event.markRollBack(CheckRemoveEventListener.MESSAGE, new IllegalArgumentException(CheckRemoveEventListener.MESSAGE));
		}
	}

	private boolean isDocument(final Type type) {
		if (type.getName().equals(Constants.DOCTYPE_COPY)) {
			return true;
		}

		Type p = type.getSuperType();
		if (p != null) {
			return this.isDocument(p);
		}
		return false;
	}

}
