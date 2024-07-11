package com.project.shell.customvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.project.shell.customvalidationimpl.UniqEmailValidationImpl;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqEmailValidationImpl.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqEmail {

	public String message() default "Invalid Data";

	public Class<? extends Payload>[] payload() default {};

	public Class<?>[] groups() default {};
}
