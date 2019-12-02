package com.wangkang.custom;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.actuate.endpoint.InvalidEndpointRequestException;
import org.springframework.boot.actuate.endpoint.InvocationContext;
import org.springframework.boot.actuate.endpoint.http.ApiVersion;
import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
import org.springframework.boot.actuate.endpoint.web.WebOperation;
import org.springframework.boot.actuate.endpoint.web.servlet.AbstractWebMvcEndpointHandlerMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.MatchableHandlerMapping;
import org.springframework.web.servlet.handler.RequestMatchResult;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-11-20
 */
//@Component
public class MyHandlerMapping extends RequestMappingInfoHandlerMapping implements InitializingBean, MatchableHandlerMapping {

    private final Method handleMethod = ReflectionUtils.findMethod(MyHandlerMapping.OperationHandler.class, "MyHandle",
            HttpServletRequest.class, Map.class);

    @Override
    protected boolean isHandler(Class<?> beanType) {
        return false;
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        return null;
    }

    @Override
    protected HandlerMethod createHandlerMethod(Object handler, Method method) {
        return null;
    }

    @Override
    protected void initHandlerMethods() {
        //填充 mappingRegistry
        registerMapping(null, new MyHandlerMapping.OperationHandler((k,v)->null), null);
    }

    public static void main(String[] args) throws Exception {
        MyHandlerMapping my = new MyHandlerMapping();
        my.getHandler(null);
    }

    @Override
    public RequestMatchResult match(HttpServletRequest request, String pattern) {
        return null;
    }

    private class MyServletWebOperationAdapter implements MyHandlerMapping.ServletWebOperation {

        private static final String PATH_SEPARATOR = AntPathMatcher.DEFAULT_PATH_SEPARATOR;

        private final WebOperation operation;

        MyServletWebOperationAdapter(WebOperation operation) {
            this.operation = operation;
        }

        @Override
        public Object handle(HttpServletRequest request, @RequestBody(required = false) Map<String, String> body) {
            return null;
        }
    }

    @FunctionalInterface
    protected interface ServletWebOperation {
        Object handle(HttpServletRequest request, Map<String, String> body);
    }

    private final class OperationHandler {

        private final MyHandlerMapping.ServletWebOperation operation;

        OperationHandler(MyHandlerMapping.ServletWebOperation operation) {
            this.operation = operation;
        }

        @ResponseBody
        Object handle(HttpServletRequest request, @RequestBody(required = false) Map<String, String> body) {
            return this.operation.handle(request, body);
        }

        @Override
        public String toString() {
            return this.operation.toString();
        }

    }
    private static class MyWebHandlerMethod extends HandlerMethod {

        MyWebHandlerMethod(Object bean, Method method) {
            super(bean, method);
        }

        @Override
        public String toString() {
            return getBean().toString();
        }

        @Override
        public HandlerMethod createWithResolvedBean() {
            return this;
        }

    }
}
