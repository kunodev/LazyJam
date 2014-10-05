package de.kuno.lazyjam.tools.cdi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kuro
 *
 */
@Retention(RetentionPolicy.RUNTIME )
@Target(ElementType.TYPE_USE)
public @interface Service { }
