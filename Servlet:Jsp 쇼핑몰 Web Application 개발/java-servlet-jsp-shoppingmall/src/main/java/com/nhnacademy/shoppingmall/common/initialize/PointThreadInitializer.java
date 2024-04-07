package com.nhnacademy.shoppingmall.common.initialize;

import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.worker.WorkerThread;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

public class PointThreadInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        RequestChannel requestChannel = new RequestChannel(10);
        ctx.setAttribute("requestChannel", requestChannel);

        WorkerThread workerThread = new WorkerThread(requestChannel);
        workerThread.start();

    }
}
