package com.weeklystamp.Log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// 서비스 레이어 로그 AOP
@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {

    @Around("execution(* com.weeklystamp..service..*(..))") // 서비스 패키지 이하 모두
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        log.info("▶ 실행 메서드: {} | 입력값: {}", methodName, Arrays.toString(args));

        Object result = joinPoint.proceed();

        log.info("⏹ 결과 반환: {} | 결과값: {}", methodName, result);
        return result;
    }
}

