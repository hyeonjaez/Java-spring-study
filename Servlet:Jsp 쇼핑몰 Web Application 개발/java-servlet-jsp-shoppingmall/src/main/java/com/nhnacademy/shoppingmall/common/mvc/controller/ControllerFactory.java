package com.nhnacademy.shoppingmall.common.mvc.controller;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.exception.ControllerNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ControllerFactory {
    public static final String CONTEXT_CONTROLLER_FACTORY_NAME = "CONTEXT_CONTROLLER_FACTORY";
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void initialize(Set<Class<?>> c, ServletContext ctx) {

        if (Objects.isNull(c)) {
            log.info("Controller not found");
            return;
        }

        for (Class clazz : c) {
            try {
                Object controllerInstance = clazz.getConstructor().newInstance();
                RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);


                String key = getKey(requestMapping.method().toString(), requestMapping.value()[0]);

                this.beanMap.put(key.toString(), controllerInstance);

            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        ctx.setAttribute(CONTEXT_CONTROLLER_FACTORY_NAME, this.beanMap);
    }

    private Object getBean(String key) {
        if (Objects.isNull(key)) {
            throw new IllegalArgumentException("getBean() parameter is null");
        }
        if (this.beanMap.containsKey(key)) {
            return this.beanMap.get(key);
        }
        throw new ControllerNotFoundException("key is not exist");
    }

    public Object getController(HttpServletRequest request) {
        if (Objects.isNull(request)) {
            throw new IllegalArgumentException("getController() parameter is null");
        }
        String key = getKey(request.getMethod(), request.getServletPath());

        return getBean(key);
    }

    public Object getController(String method, String path) {
        if (Objects.isNull(method) || Objects.isNull(path)) {
            throw new IllegalArgumentException("getController() parameter is null");
        }
        String key = getKey(method, path);

        if (this.beanMap.containsKey(key)) {
            return getBean(key);
        }
        throw new ControllerNotFoundException("getController() is not exist");
    }

    private String getKey(String method, String path) {
        if (Objects.isNull(method) || Objects.isNull(path)) {
            throw new IllegalArgumentException("getKey() parameter is null");
        }

        return method + "-" + path;
    }
}
