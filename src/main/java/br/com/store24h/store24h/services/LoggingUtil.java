package br.com.store24h.store24h.services;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingUtil {

    private static final Logger logger = LoggerFactory.getLogger(LoggingUtil.class);
//    br.com.store24h.store24h.repository
    @Around("execution(* br.com.store24h.store24h.resources..*.*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        logger.info("Method {}.{}() called with args: {}", className, methodName, Arrays.toString(args));

        Object result = null;
        try {
            result = joinPoint.proceed();
            logger.info("Method {}.{}() returned: {}", className, methodName, result);
        } catch (Throwable e) {
            logger.error("Method {}.{}() threw an exception: {}", className, methodName, e.getMessage());
            throw e;
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        logger.info("Method {}.{}() execution time: {} ms", className, methodName, duration);

        return result;
    }
}