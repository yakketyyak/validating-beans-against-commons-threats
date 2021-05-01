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

import com.yakketyyak.validate.beans.constant.PatternsConstant;

import lombok.Data;

@Documented
@Constraint(validatedBy = NoSpELInjectionConstraintValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoSpELInjection {

	String message() default "{noSpELInjection}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

@Data
class NoSpELInjectionConstraintValidator implements ConstraintValidator<NoSpELInjection, String> {

	private NoSpELInjection noSpELInjection;

	@Override
	public void initialize(NoSpELInjection noSafe) {
		this.noSpELInjection = noSafe;
	}

	@Override
	public boolean isValid(String safeContent, ConstraintValidatorContext cxt) {

		if (StringUtils.isBlank(safeContent)) {
			return true;
		}

		return !PatternsConstant.getSpelInjection().matcher(safeContent).find();
	}

}
