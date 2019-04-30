package cn.shaoqunliu.c.hub.msg.controller;

import cn.shaoqunliu.c.hub.msg.po.Envelope;
import cn.shaoqunliu.c.hub.msg.po.RegistryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsMessagingTemplate;
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
    private final JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    public MessageProducerController(Queue pullQueue, Queue pushQueue, JmsMessagingTemplate jmsMessagingTemplate) {
        this.pullQueue = pullQueue;
        this.pushQueue = pushQueue;
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = {"application/vnd.docker.distribution.events.v1+json"})
    public ResponseEntity message(@RequestBody Envelope envelope) {
        assert envelope != null && envelope.getEvents().size() > 1;
        envelope.getEvents().forEach(event -> {
            if (event.getTarget().getMediaType().equals("application/octet-stream")) {
                // the value application/octet-stream, which means the docker registry
                // are transferring data or response to the docker client
                // so that it can be ignored within our application
                return;
            }
            // we only need to parse and queue the event with mediaType:
            // application/vnd.docker.distribution.manifest.v2+json
            RegistryMessage message = new RegistryMessage();
            message.setId(event.getId());
            message.setAction(event.getAction().toLowerCase());
            message.setActor(event.getActor().getName());
            message.setDigest(event.getTarget().getDigest());
            message.setRepository(event.getTarget().getRepository());
            message.setSize(event.getTarget().getSize());
            message.setTimestamp(event.getTimestamp());
            message.setTag(event.getTarget().getTag());
            if (message.getAction().equals("pull")) {
                jmsMessagingTemplate.convertAndSend(pullQueue, message);
            } else if (message.getAction().equals("push")) {
                jmsMessagingTemplate.convertAndSend(pushQueue, message);
            }
        });
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
