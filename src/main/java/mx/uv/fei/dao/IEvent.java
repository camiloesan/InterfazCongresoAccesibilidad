package mx.uv.fei.dao;

import mx.uv.fei.logic.Event;

import java.sql.SQLException;
import java.util.List;

public interface IEvent {
    void addEvent(Event event) throws SQLException;
    List<Event> getAllEvents() throws SQLException;
}
