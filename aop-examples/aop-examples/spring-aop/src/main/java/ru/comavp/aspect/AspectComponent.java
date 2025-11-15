package ru.comavp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectComponent {

    /*
    * This is an Advice in terms of AOP
    * joinPoint - store information about which method was called
    * @Around("@annotation(LogExecutionTime)") - add some logic before and after method with @LogExecutionTime
    * "@annotation(LogExecutionTime)" - this is a PointCut in terms of AOP
    * */
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed(); // continue execution of method from joinPoint

        long executionTime = System.currentTimeMillis() - start;

        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

    /*
    * "execution(* ru.comavp.service.CompanyService.*(*))" means: all methods from class CompanyService
    * with any returning type, name and arguments
    * */
    @AfterReturning(pointcut = "callAllMethodsInCompanyService()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info(joinPoint.getSignature().getName() + " result = " + result.toString());
    }

    @Before("callAllMethodsInCompanyService()")
    public void logMethodStart(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().getName() + " was started");
    }

    @After("callAllMethodsInCompanyService()")
    public void logMethodFinish(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().getName() + " was finished");
    }

    @AfterThrowing(pointcut = "callAllMethodsInCompanyService()", throwing = "ex")
    public void logAfterReturning(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature().getName() + " error message = " + ex.getMessage());
    }

    @Pointcut("execution(* ru.comavp.service.CompanyService.*(..))")
    public void callAllMethodsInCompanyService() {

    }
}
