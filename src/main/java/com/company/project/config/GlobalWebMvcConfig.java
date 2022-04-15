package com.company.project.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.company.project.core.execption.CommonException;
import com.company.project.core.filter.LogFilter;
import com.company.project.core.interceptor.LogInterceptor;
import com.company.project.core.result.ResResult;
import com.company.project.core.result.ResResultCode;
import com.company.project.core.util.BeanToMapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class GlobalWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
    }

    /**
     * 重写父类提供的跨域请求处理的接口
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射路径
        registry.addMapping("/**")
                // 放行哪些原始域
                .allowedOrigins("*")
                // 是否发送Cookie信息
                .allowCredentials(true)
                // 放行哪些原始域(请求方式)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // 放行哪些原始域(头部信息)
                .allowedHeaders("*")
                // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                .exposedHeaders("Server", "Date", "Content-Length", "Set-Cookie2", "Set-Cookie", "addCookie",
                        "cookie", "JSESSIONID", "Access-Token", "Access-Control-Allow-Origin",
                        "Access-Control-Allow-Credentials");
    }

    /**
     * 统一异常处理
     * @param resolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
                log.error("【统一异常拦截】请求出现异常，内容如下：",e);
                ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
                String uri = request.getRequestURI();
                if(e instanceof CommonException){
                    //CommonExecption为自定义异常类
                    printWrite(ResResultCode.CUSTOM_ERROR_MESSAGE,((CommonException) e).getMsg(),((CommonException) e).getData(), uri, mv);
                } else {
                    printWrite(ResResultCode.DEFAULT_ERROR_MESSAGE,e.getMessage(),null, uri, mv);
                }
                return mv;
            }
        });
    }

    /**
     * 添加过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean helloFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("loginFilter");
        registrationBean.setFilter(new LogFilter());
        registrationBean.setOrder(1);
        // 没有配置setUrlPatterns，则是全部拦截
        return registrationBean;
    }


    /**
     * 异常封装相应结果
     * @param object
     */
    private void printWrite(ResResultCode errorEnum, String msg, Object object, String uri, ModelAndView mv){
        ResResult resResult = new ResResult(uri, errorEnum, object);
        if(msg != null){resResult.setMsg(msg);}
        if(log.isDebugEnabled()){
            log.debug("【response】异常输出结果:" + JSONObject.toJSONString(resResult, SerializerFeature.WriteMapNullValue));
        }
        Map resultMap = BeanToMapUtil.beanToMap(resResult);
        mv.addAllObjects(resultMap);
    }
}
