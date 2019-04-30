package cn.shaoqunliu.c.hub.msg.po;

import cn.shaoqunliu.c.hub.msg.po.event.Actor;
import cn.shaoqunliu.c.hub.msg.po.event.Request;
import cn.shaoqunliu.c.hub.msg.po.event.Source;
import cn.shaoqunliu.c.hub.msg.po.event.Target;

import java.util.Date;

public class Event {

    private String id;
    private Date timestamp;
    private String action;
    private Target target;
    private Request request;
    private Actor actor;
    private Source source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
