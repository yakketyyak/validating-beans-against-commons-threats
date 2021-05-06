package com.yakketyyak.validate.beans.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import lombok.Data;

@Documented
@Constraint(validatedBy = NoXssContentConstraintValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoXssContent {

	String message() default "{noXssContent}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

@Data
class NoXssContentConstraintValidator implements ConstraintValidator<NoXssContent, String> {

	private NoXssContent noXSSContent;

	@Override
	public void initialize(NoXssContent noSafe) {
		this.noXSSContent = noSafe;
	}

	@Override
	public boolean isValid(String safeContent, ConstraintValidatorContext cxt) {

		if (StringUtils.isBlank(safeContent)) {
			return true;
		}

		return Jsoup.isValid(safeContent, Whitelist.none());
	}

}
