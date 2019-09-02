package com.hajdu.sp.competition.update.util;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;

@Aspect
@RequiredArgsConstructor
public class InvariantAspect {

    private final ApplicationContext applicationContext;

    @Before("@annotation(com.hajdu.sp.competition.update.util.InvariantValidator)")
    public void checkInvariant(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class<? extends Invariant> invariantValidatorClass = signature.getMethod().getAnnotation(InvariantValidator.class).clazz();
        Invariant invariantValidator = applicationContext.getBean(invariantValidatorClass);
        try {
            invariantValidator.check(joinPoint.getThis(), joinPoint.getArgs()[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
