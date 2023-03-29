package mx.uv.fei.logic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private int eventId;
    private String eventName;
    private String speakerName;
    private int eventDuration;
    private String eventLocation;
    private String eventDate;
    private String eventTime;
    private String eventType;
    private int eventSlots;

    public Event() {}

    public List<Event> eventResultSetToList(ResultSet resultSet) throws SQLException {
        List<Event> eventList = new ArrayList<>();
        while (resultSet.next()) {
            Event event = new Event();
            event.setEventId(resultSet.getInt("idEvento"));
            event.setEventName(resultSet.getString("nombreEvento"));
            event.setSpeakerName(resultSet.getString("quienImparte"));
            event.setEventDuration(resultSet.getInt("duracion"));
            event.setEventLocation(resultSet.getString("lugar"));
            event.setEventDate(resultSet.getString("fecha"));
            event.setEventTime(resultSet.getString("hora"));
            event.setEventType(resultSet.getString("tipoEvento"));
            event.setEventSlots(resultSet.getInt("cupo"));
            eventList.add(event);
        }
        return eventList;
    }

    public int getEventSlots() {
        return eventSlots;
    }

    public void setEventSlots(int eventSlots) {
        this.eventSlots = eventSlots;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public int getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(int eventDuration) {
        this.eventDuration = eventDuration;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String date) {
        this.eventDate = date;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
