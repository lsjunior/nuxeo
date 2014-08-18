package br.net.woodstock.nuxeo.test1;

import java.security.Principal;

import org.nuxeo.ecm.core.query.sql.NXQL;
import org.nuxeo.ecm.core.query.sql.model.Operator;
import org.nuxeo.ecm.core.query.sql.model.Predicate;
import org.nuxeo.ecm.core.query.sql.model.Reference;
import org.nuxeo.ecm.core.query.sql.model.SQLQuery;
import org.nuxeo.ecm.core.query.sql.model.SQLQuery.Transformer;
import org.nuxeo.ecm.core.query.sql.model.StringLiteral;
import org.nuxeo.ecm.core.query.sql.model.WhereClause;
import org.nuxeo.ecm.core.security.SecurityPolicy.QueryTransformer;

public final class LoadOwnerTransformer implements Transformer, QueryTransformer {

	private static final long			serialVersionUID	= Version.CURRENT_VERSION;

	public static final Predicate		LOAD_OWNER			= new Predicate(new Reference(NXQL.ECM_LOCK_OWNER), Operator.NOTEQ, new StringLiteral("File"));

	private static LoadOwnerTransformer	instance			= new LoadOwnerTransformer();

	private LoadOwnerTransformer() {
		super();
	}

	@Override
	public SQLQuery transform(final Principal principal, final SQLQuery query) {
		WhereClause where = query.where;
		Predicate predicate;
		if (where == null || where.predicate == null) {
			predicate = LOAD_OWNER;
		} else {
			predicate = new Predicate(LOAD_OWNER, Operator.AND, where.predicate);
		}
		return new SQLQuery(query.select, query.from, new WhereClause(predicate), query.groupBy, query.having, query.orderBy, query.limit, query.offset);
	}

	@Override
	public String transform(final Principal principal, final String query) {
		return query;
	}

	public static LoadOwnerTransformer getInstance() {
		return LoadOwnerTransformer.instance;
	}

}
