package com.nhnacademy.shoppingmall.thread.channel;

import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import java.util.LinkedList;
import java.util.Queue;

public class RequestChannel {
    private final Queue<ChannelRequest> queue;
    private final int queueMaxSize;

    public RequestChannel(int queueMaxSize) {
        this.queueMaxSize = queueMaxSize;
        this.queue = new LinkedList<>();
    }

    public synchronized ChannelRequest getRequest() throws InterruptedException {
        while (this.queue.isEmpty()) {
            wait();
        }

        return queue.poll();
    }

    public synchronized void addRequest(ChannelRequest request) throws InterruptedException {
        while (queue.size() == this.queueMaxSize) {
            wait();
        }

        queue.add(request);
        notifyAll();
    }

}
