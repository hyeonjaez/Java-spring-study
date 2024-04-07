package com.nhnacademy.shoppingmall.thread.request.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PointChannelRequest extends ChannelRequest {

    @Override
    public void execute() {
        DbConnectionThreadLocal.initialize();
        //todo#14-5 포인트 적립구현, connection은 point적립이 완료되면 반납합니다.



        log.debug("pointChannel execute");
        DbConnectionThreadLocal.reset();
    }
}
