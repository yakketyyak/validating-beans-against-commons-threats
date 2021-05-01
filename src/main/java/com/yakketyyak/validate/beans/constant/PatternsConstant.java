package com.yakketyyak.validate.beans.constant;

import java.util.regex.Pattern;

public final class PatternsConstant {

	// pattern de validation des requêtes contenant des caractères d'injection SPEL
	private static final Pattern SPEL_INJECTION_PATTERN = Pattern.compile("([\\\\{}])");

	private PatternsConstant() {
		throw new UnsupportedOperationException();
	}

	// Utiliser le pattern method factory to protéger le pattern de validation
	public static Pattern getSpelInjection() {
		return SPEL_INJECTION_PATTERN;
	}

}
