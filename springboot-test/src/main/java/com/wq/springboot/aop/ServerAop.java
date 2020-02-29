package com.wq.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: wq
 * @Desc:
 * @Date: 2020/1/5 16:17
 * @Version 1.0
 */

@Slf4j
@Aspect
@Component
public class ServerAop {


    /**
     * 指定切点
     * 匹配 com.example.demo.controller包及其子包下的所有类的所有方法
     */
    @Pointcut("execution(public * com.wq.springboot*.*(..))")
    public void method() {
    }

    /**
     * 前置通知，方法调用前被调用
     *
     * @param joinPoint
     */
    @Before("method()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("我是前置通知!!!");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        log.debug("方法：{}", signature.getName());
        //AOP代理类的名字
       log.debug("方法所在包:{}" , signature.getDeclaringTypeName());
        //AOP代理类的类（class）信息
        signature.getDeclaringType();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] strings = methodSignature.getParameterNames();
        log.debug("参数名：{}",Arrays.toString(strings));
        log.debug("参数值ARGS : {}", Arrays.toString(joinPoint.getArgs()));
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();
        // 记录下请求内容
       log.debug("请求URL :{} " , req.getRequestURL().toString());
       log.debug("HTTP_METHOD :{}" , req.getMethod());
       log.debug("IP : {}" , req.getRemoteAddr());
       log.debug("CLASS_METHOD :{}" ,
               joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

    }

    /**
     * 处理完请求返回内容
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "method()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
       log.debug("方法的返回值 : {}", ret);
    }

    /**
     * 后置异常通知
     *
     * @param jp
     */
    @AfterThrowing("method()")
    public void throwss(JoinPoint jp) {
       log.debug("方法异常时执行.....");
    }

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     *
     * @param jp
     */
    @After("method()")
    public void after(JoinPoint jp) {

    }

    /**
     * 环绕通知,环绕增强，相当于MethodInterceptor
     *
     * @param pjp
     * @return
     */
    @Around("method()")
    public Object arround(ProceedingJoinPoint pjp) {
        try {
            Object o = pjp.proceed();
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

}
