package com.company.project.core.aspect;

import com.company.project.core.annotation.CheckPermissions;
import com.company.project.core.execption.CommonException;
import com.company.project.dao.MenuMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
public class CheckPermissionsAspect {

    @Autowired
    private MenuMapper menuMapper;

    @Pointcut("@annotation(com.company.project.core.annotation.CheckPermissions)")
    public void checkPermissions() {}

    @Before("checkPermissions()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        Long userId = null;
        Object[] args = joinPoint.getArgs();
        Object parobj = args[0];
        //用户请求参数实体类中的用户ID
        if(!Objects.isNull(parobj)){
            Class userCla = parobj.getClass();
            Field field = userCla.getDeclaredField("userId");
            field.setAccessible(true);
            userId = (Long) field.get(parobj);
        }
        if(!Objects.isNull(userId)){
            //获取方法上有CheckPermissions注解的参数
            Class clazz = joinPoint.getTarget().getClass();
            String methodName = joinPoint.getSignature().getName();
            Class[] parameterTypes = ((MethodSignature)joinPoint.getSignature()).getMethod().getParameterTypes();
            Method method = clazz.getMethod(methodName, parameterTypes);
            if(method.getAnnotation(CheckPermissions.class) != null){
                CheckPermissions annotation = method.getAnnotation(CheckPermissions.class);
                String menuCode = annotation.value();
                if (StringUtils.isNotBlank(menuCode)) {
                    //通过用户ID、菜单编码查询是否有关联
                    int count = menuMapper.selectAuthByUserIdAndMenuCode(userId, menuCode);
                    if(count == 0){
                        throw new CommonException("接口无访问权限");
                    }
                }
            }
        }
    }
}
