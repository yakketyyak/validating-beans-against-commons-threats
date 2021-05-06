package com.yakketyyak.validate.beans.constant;

import java.util.regex.Pattern;

public final class PatternsConstant {

	// pattern de validation des requêtes contenant des caractères d'injection SPEL
	private static final Pattern SPEL_INJECTION_PATTERN = Pattern.compile("([\\\\{}])");

	// pattern de validation des requêtes contenant des caractères d'injection LDAP
	private static final String LDAP_INJECTION_PATTERN = "[\\w\\s]*";

	private PatternsConstant() {
		throw new UnsupportedOperationException();
	}

	// Utiliser le pattern method factory to protéger le pattern de validation
	public static Pattern getSpelInjection() {
		return SPEL_INJECTION_PATTERN;
	}

	// Utiliser le pattern method factory to protéger le pattern de validation
	public static String getLdapInjection() {
		return LDAP_INJECTION_PATTERN;
	}

}
