package com.hajdu.sp.competition.update.util;

import com.hajdu.sp.competition.update.validation.Invariant;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class InvariantAspect {

    private final ApplicationContext applicationContext;

    @Before("@annotation(InvariantValidator)")
    public void checkInvariant(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class<? extends Invariant> invariantValidatorClass = signature.getMethod().getAnnotation(InvariantValidator.class).clazz();
        Invariant invariantValidator = applicationContext.getBean(invariantValidatorClass);
        invariantValidator.check(joinPoint.getThis(), joinPoint.getArgs()[0]);
    }

}
