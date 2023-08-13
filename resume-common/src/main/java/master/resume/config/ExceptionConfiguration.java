package master.resume.config;

import jakarta.servlet.Servlet;
import lombok.extern.slf4j.Slf4j;
import master.resume.handler.AbstractGlobalExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;


/**
 * @description: 全局异常处理
 * @author: hdx
 * @date: 2023/3/31 17:48
 **/
@Configuration
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class ExceptionConfiguration extends AbstractGlobalExceptionHandler {

}
