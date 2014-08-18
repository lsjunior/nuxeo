package br.net.woodstock.nuxeo.test1;

import java.security.Principal;

import org.nuxeo.ecm.core.api.security.ACP;
import org.nuxeo.ecm.core.api.security.Access;
import org.nuxeo.ecm.core.model.Document;
import org.nuxeo.ecm.core.query.sql.model.SQLQuery.Transformer;
import org.nuxeo.ecm.core.security.AbstractSecurityPolicy;

public class LoadOwnerSecurityPolicy extends AbstractSecurityPolicy {

	public LoadOwnerSecurityPolicy() {
		super();
	}

	@Override
	public Access checkPermission(final Document doc, final ACP mergedAcp, final Principal principal, final String permission, final String[] resolvedPermissions, final String[] additionalPrincipals) {
		return Access.DENY;
	}

	@Override
	public boolean isRestrictingPermission(final String permission) {
		return true;
	}

	@Override
	public boolean isExpressibleInQuery(final String repositoryName) {
		return true;
	}

	@Override
	public Transformer getQueryTransformer(final String repositoryName) {
		return LoadOwnerTransformer.getInstance();
	}

	@Override
	public QueryTransformer getQueryTransformer(final String repositoryName, final String queryLanguage) {
		return LoadOwnerTransformer.getInstance();
	}

}
