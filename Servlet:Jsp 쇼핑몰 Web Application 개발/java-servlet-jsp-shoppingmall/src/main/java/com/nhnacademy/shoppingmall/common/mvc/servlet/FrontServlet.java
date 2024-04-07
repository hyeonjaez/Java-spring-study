package com.nhnacademy.shoppingmall.common.mvc.servlet;

import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.mvc.controller.ControllerFactory;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.mvc.view.ViewResolver;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = {"*.do"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100,
        location = "/Users/hyeon/Desktop/시험과제 /java-servlet-jsp-shoppingmall/src/main/webapp/resources")
public class FrontServlet extends HttpServlet {
    private ControllerFactory controllerFactory;
    private ViewResolver viewResolver;

    @Override
    public void init() throws ServletException {
        this.controllerFactory = (ControllerFactory) getServletContext().getAttribute("controllerFactory");
        this.viewResolver = new ViewResolver();

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DbConnectionThreadLocal.initialize();
        try {
            BaseController baseController = (BaseController) controllerFactory.getController(req);
            String viewName = baseController.execute(req, resp);

            if (viewResolver.isRedirect(viewName)) {
                String redirectUrl = viewResolver.getRedirectUrl(viewName);
                log.debug("redirectUrl:{}", redirectUrl);
                resp.sendRedirect(redirectUrl);

            } else {
                String layout = viewResolver.getLayOut(viewName);
                log.debug("viewName:{}", viewResolver.getPath(viewName));
                req.setAttribute(ViewResolver.LAYOUT_CONTENT_HOLDER, viewResolver.getPath(viewName));
                RequestDispatcher rd = req.getRequestDispatcher(layout);
                rd.include(req, resp);
            }
        } catch (Exception e) {
            log.error("error:{}", e);
            DbConnectionThreadLocal.setSqlError(true);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad Request");
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");

        } finally {
            DbConnectionThreadLocal.reset();

        }
    }

}