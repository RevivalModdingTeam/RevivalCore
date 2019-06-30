package com.revivalmodding.revivalcore.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JsonGenerator {
	
	/**
	 * This is the name every {@code PropertyInterger} will get as prefix.
	 * Should start and end with _ character
	 * Default value: {@code _stage_}
	 */
	String intName() default "_stage_";
	
	/**
	 * This is the name every {@code PropertyBool} will have as prefix.
	 * Applies <b>only for TRUE</b> values, otherwise is ignored.
	 * Should start with _ character.
	 * Default value: {@code _on}
	 */
	String boolName() default "_on";
}
