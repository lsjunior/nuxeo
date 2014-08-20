package br.net.woodstock.nuxeo.test1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessage;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;

@Name(value = "copyDocumentBean")
@Scope(value = ScopeType.CONVERSATION)
public class CopyDocumentBean implements Serializable {

	private static final long	serialVersionUID	= Version.CURRENT_VERSION;

	@In(create = true)
	private DocumentModel		currentDocument;

	@In(create = true)
	private CoreSession			documentManager;

	@In
	private FacesMessages		facesMessages;

	public CopyDocumentBean() {
		super();
	}

	public String copy() {
		try {
			String title = this.currentDocument.getTitle() + "-Copy-" + System.currentTimeMillis();
			DocumentModel parent = this.documentManager.getDocument(this.currentDocument.getParentRef());
			DocumentModel model = this.documentManager.createDocumentModel(parent.getPathAsString(), title, "Copy");
			model.copyContent(this.currentDocument);
			model.copyContextData(this.currentDocument);
			model.setProperty("dublincore", "dc:title", title);
			model.setProperty("copy", "parentId", parent.getId());

			model = this.documentManager.createDocument(model);
			this.documentManager.saveDocument(model);

			String[] copyArray = (String[]) this.currentDocument.getProperty("copy", "copyList");
			List<String> copyList = new ArrayList<>();

			if ((copyArray != null) && (copyArray.length > 0)) {
				copyList.addAll(Arrays.asList(copyArray));
			}

			copyList.add(model.getId());

			this.currentDocument.setProperty("copy", "copyList", copyList.toArray(new String[copyList.size()]));

			this.facesMessages.add(StatusMessage.Severity.INFO, "Copy OK " + model.getId());
		} catch (Exception e) {
			Logger.getLog().error(e.getMessage(), e);
			this.facesMessages.add(StatusMessage.Severity.ERROR, e.getMessage());
		}
		return null;
	}
}
