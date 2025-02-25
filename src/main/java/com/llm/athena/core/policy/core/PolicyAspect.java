package com.llm.athena.core.policy.core;

import com.llm.athena.core.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PolicyAspect {

    @Around("execution(* com.llm.athena.core.policy.core.Policy+.*(..))")
    public Object beforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        if(args.length > 0 && args[0] instanceof User user){
            Policy<?> policy = (Policy<?>) joinPoint.getTarget();
            if(policy.before(user)){
               return true;
            }
        }

        return joinPoint.proceed();
    }

}
