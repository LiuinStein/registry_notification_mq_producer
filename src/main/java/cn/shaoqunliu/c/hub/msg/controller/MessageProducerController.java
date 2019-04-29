package cn.shaoqunliu.c.hub.msg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RestController
@RequestMapping("/v1/message")
public class MessageProducerController {

    private final Queue pullQueue;
    private final Queue pushQueue;

    @Autowired
    public MessageProducerController(Queue pullQueue, Queue pushQueue) {
        this.pullQueue = pullQueue;
        this.pushQueue = pushQueue;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = {"application/vnd.docker.distribution.events.v1+json"})
    public ResponseEntity message() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
