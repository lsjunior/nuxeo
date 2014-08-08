package br.net.woodstock.nuxeo.test2;

public abstract class NXQLHelper {

	private NXQLHelper() {
		//
	}

	public static String getLikeValue(final String value) {
		if (value == null) {
			return "%";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("%");
		builder.append(value.trim());
		builder.append("%");
		return builder.toString();
	}

}
