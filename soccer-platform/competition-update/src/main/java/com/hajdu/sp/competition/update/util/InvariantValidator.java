package com.hajdu.sp.competition.update.util;

import com.hajdu.sp.competition.update.validation.Invariant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InvariantValidator {
    Class<? extends Invariant> clazz();
}
