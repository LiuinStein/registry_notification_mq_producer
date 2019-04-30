package cn.shaoqunliu.c.hub.msg.po;

import java.util.List;

public class Envelope {

    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
