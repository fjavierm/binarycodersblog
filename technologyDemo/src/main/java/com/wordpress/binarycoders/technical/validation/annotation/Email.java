package com.wordpress.binarycoders.technical.validation.annotation;

import com.wordpress.binarycoders.technical.validation.ValidationPattern;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Pattern.List({
	@Pattern(regexp = ValidationPattern.Email.VALID)
})
@Constraint(validatedBy = {})
@Documented
@Target({ElementType.METHOD,
	ElementType.FIELD,
	ElementType.ANNOTATION_TYPE,
	ElementType.CONSTRUCTOR,
	ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

	String message() default "{Invalid email.}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ElementType.METHOD,
		ElementType.FIELD,
		ElementType.ANNOTATION_TYPE,
		ElementType.CONSTRUCTOR,
		ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {

		Email[] value();
	}
}
