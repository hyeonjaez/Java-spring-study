package com.nhnacademy.shoppingmall.thread.worker;


import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;

public class WorkerThread extends Thread {
    private final RequestChannel requestChannel;

    public WorkerThread(RequestChannel requestChannel) {
        this.requestChannel = requestChannel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ChannelRequest channelRequest = requestChannel.getRequest();
                channelRequest.execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
