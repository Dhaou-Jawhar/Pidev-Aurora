package pidev.tn.aurora.AspectAPI;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Pointcut(" execution(* pidev.tn.aurora.services.*.*(..)) ")
    public void methodCall(){
    }

    @After(" execution(* pidev.tn.aurora.services.*.*(..)) ")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info(" Montant Factutioure Calculé ");
    }

   /* @After(" execn(* pidev.tn.aurora.services.*.*(..)) ")
    public void logMethodExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info(" Out of method " + name + " : ");
    }

    @AfterReturning(" execution(* pidev.tn.aurora.services.*.*(..)) ")
    public void logMethodExitReturn(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info(" Out of method After Returning " + name + " : ");
    }

    @AfterThrowing(" execution(* pidev.tn.aurora.services.*.*(..)) ")
    public void logMethodExitthrow(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info(" Out of method After Throwing " + name + " : ");
    }*/
}