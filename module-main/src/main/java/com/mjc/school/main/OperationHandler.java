package com.mjc.school.main;

import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.ControllerHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class OperationHandler {

    private final ControllerHandler controllerHandler;

    void executeOperation(int operation) {
        Method[] methods = ControllerHandler.class.getDeclaredMethods();
        Method method = Arrays.stream(methods).filter(x -> x.isAnnotationPresent(CommandHandler.class))
                .filter(x -> x.getAnnotation(CommandHandler.class).command() == operation).findFirst().orElse(null);
        try {
            method.invoke(controllerHandler);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}