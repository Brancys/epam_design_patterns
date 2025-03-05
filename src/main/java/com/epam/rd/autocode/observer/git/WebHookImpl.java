package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;

public class WebHookImpl implements WebHook {
    private final String branch;
    private final Event.Type type;
    private final List<Event> caughtEvents = new ArrayList<>();

    public WebHookImpl(String branch, Event.Type type) {
        this.branch = branch;
        this.type = type;
    }

    @Override
    public String branch() {
        return branch;
    }

    @Override
    public Event.Type type() {
        return type;
    }

    @Override
    public List<Event> caughtEvents() {
        return caughtEvents;
    }

    @Override
    public void onEvent(Event event) {
        if (event.type() == this.type && event.branch().equals(this.branch)) {
            caughtEvents.add(event);
        }
    }
}
