package br.net.woodstock.nuxeo.test2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.ajax4jsf.model.SerializableDataModel;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.core.api.DocumentRef;

public class RichFacesDataModel extends SerializableDataModel {

	private static final long				serialVersionUID	= Version.CURRENT_VERSION;

	private CoreSession						session;

	private String							query;

	private int								rows;

	private DocumentModelList				list;

	private DocumentRef						currentId;

	private Map<DocumentRef, DocumentModel>	wrappedData;

	private List<Object>					wrappedKeys;

	public RichFacesDataModel(final CoreSession session, final String query, final int rows) {
		super();
		this.session = session;
		this.query = query;
		this.rows = rows;
		this.wrappedKeys = new ArrayList<>();
		this.wrappedData = new HashMap<>();
	}

	@Override
	public void walk(final FacesContext context, final DataVisitor visitor, final Range range, final Object argument) throws IOException {
		try {
			SequenceRange sequenceRange = (SequenceRange) range;
			int first = sequenceRange.getFirstRow();
			int rows = sequenceRange.getRows();

			this.list = this.session.query(this.query, null, rows, first, true);

			this.wrappedKeys.clear();
			this.wrappedData.clear();

			for (DocumentModel e : this.list) {
				this.wrappedKeys.add(e.getRef());
				this.wrappedData.put(e.getRef(), e);
				visitor.process(context, e.getRef(), argument);
			}
		} catch (ClientException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public boolean isRowAvailable() {
		if (this.currentId == null) {
			return false;
		}
		return true;
	}

	@Override
	public int getRowCount() {
		try {
			if (this.list == null) {
				this.list = this.session.query(this.query, null, this.rows, 0, true);
			}

			return (int) this.list.totalSize();
		} catch (ClientException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public DocumentModel getRowData() {
		try {
			if (this.currentId == null) {
				return null;
			}
			DocumentModel d = this.wrappedData.get(this.currentId);
			if (d == null) {
				d = this.session.getDocument(this.currentId);
				this.wrappedData.put(this.currentId, d);
			}
			return d;
		} catch (ClientException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public Object getRowKey() {
		return this.currentId;
	}

	@Override
	public void setRowKey(final Object key) {
		this.currentId = (DocumentRef) key;
	}

	@Override
	public int getRowIndex() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setRowIndex(final int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getWrappedData() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setWrappedData(final Object wrappedData) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update() {
		// throw new UnsupportedOperationException();
	}

}
