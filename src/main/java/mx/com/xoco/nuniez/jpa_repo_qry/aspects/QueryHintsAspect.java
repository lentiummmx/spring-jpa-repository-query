package mx.com.xoco.nuniez.jpa_repo_qry.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpaQueryMethod;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
// @Component
public class QueryHintsAspect {

    // org.springframework.data.jpa.repository.query.AbstractJpaQuery.applyHints
    // org.hibernate.internal.AbstractSharedSessionContract.createQuery(java.lang.String)
    // org.hibernate.query.internal.AbstractProducedQuery.setHint
    // org.hibernate.query.internal.AbstractProducedQuery.setHint(String hintName, Object
    // value)

    // @Pointcut("target(org.springframework.data.jpa.repository.query.AbstractJpaQuery)")
    public void repositoryMethods() {
    }

    // @Pointcut("execution(*
    // org.springframework.data.jpa.repository.query.AbstractJpaQuery.*applyHints*(..)) &&
    // args(query, method)")
    public void repositoryMethods1(ProceedingJoinPoint joinPoint, Query query, JpaQueryMethod method) {
    }

    // @Pointcut("execution(* *..applyHints*(..)) && args(query, method)")
    public void firstLongParamMethods(ProceedingJoinPoint joinPoint, Query query, JpaQueryMethod method) {
    }

    // @Pointcut("repositoryMethods() && firstLongParamMethods(joinPoint, query, method)")
    public void entityCreationMethods(ProceedingJoinPoint joinPoint, Query query, JpaQueryMethod method) {
    }

    // @AfterReturning(value = "entityCreationMethods(joinPoint, query, method)",
    // returning = "query")
    // @AfterReturning(value = "execution(*
    // org.springframework.data.jpa.repository.query.AbstractJpaQuery.*applyHints*(..)) &&
    // args(query, method)", returning = "query")
    // @After(value = "execution(*
    // org.springframework.data.jpa.repository.query.AbstractJpaQuery.applyHints(..))")
    // @Around(value = "execution(*
    // org.springframework.data.jpa.repository.query.AbstractJpaQuery.applyHints(..))")
    public void logMethodCall(JoinPoint joinPoint) throws Throwable {
        System.out.println("joinPoint :: " + joinPoint);
        // System.out.println("query :: " + query);
        // System.out.println("method :: " + method);
    }

    @Before("execution(public * org.hibernate.query.internal.*.setHint(..))")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println("logBefore() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");
    }

    /*
     * @After("execution(* org.hibernate.query.internal.AbstractProducedQuery.setHint(..))"
     * ) public void logAfter(JoinPoint joinPoint) {
     *
     * System.out.println("logAfter() is running!"); System.out.println("hijacked : " +
     * joinPoint.getSignature().getName()); System.out.println("******");
     *
     * }
     *
     * @AfterReturning( pointcut =
     * "execution(* org.hibernate.query.internal.AbstractProducedQuery.setHint(..))",
     * returning= "result") public void logAfterReturning(JoinPoint joinPoint, Object
     * result) {
     *
     * System.out.println("logAfterReturning() is running!");
     * System.out.println("hijacked : " + joinPoint.getSignature().getName());
     * System.out.println("Method returned value is : " + result);
     * System.out.println("******");
     *
     * }
     */

    /*
     * @Around("execution(* org.hibernate.query.internal.AbstractProducedQuery.setHint(..))"
     * ) public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
     *
     * System.out.println("logAround() is running!");
     * System.out.println("hijacked method : " + joinPoint.getSignature().getName());
     * System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));
     *
     * System.out.println("Around before is running!"); joinPoint.proceed(); //continue on
     * the intercepted method System.out.println("Around after is running!");
     *
     * System.out.println("******");
     *
     * }
     */

    @Around("execution(public * org.hibernate.query.internal.*.setHint(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("logAround() is running!");
        System.out.println("hijacked method : " + joinPoint.getSignature().getName());
        System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

        System.out.println("Around before is running!");
        Object result = joinPoint.proceed(); // continue on the intercepted method
        System.out.println("Around after is running!");

        System.out.println("******");

        return result;

    }

}
