package mx.uv.fei.logic;

import mx.uv.fei.dataaccess.DatabaseManager;

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

    public void saveEvent() throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        String query = "insert into eventos(nombreEvento, quienImparte, duracion, lugar, fecha, hora, tipoEvento, cupo) values(?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, eventName);
        statement.setString(2, speakerName);
        statement.setInt(3, eventDuration);
        statement.setString(4, eventLocation);
        statement.setDate(5, java.sql.Date.valueOf(eventDate));
        statement.setTime(6, java.sql.Time.valueOf(eventTime));
        statement.setString(7, eventType);
        statement.setInt(8, eventSlots);
        statement.executeUpdate();
        databaseManager.closeConnection();
    }

    private List<Event> resultSetToList(ResultSet resultSet) throws SQLException {
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

    public List<Event> eventList() throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        String query = "SELECT * FROM eventos";
        Statement statement = connection.createStatement();
        ResultSet resultSetEvent = statement.executeQuery(query);
        databaseManager.closeConnection();
        return resultSetToList(resultSetEvent);
    }

    public List<Event> eventList(String eventName) throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        String query = "SELECT * FROM eventos WHERE nombreEvento=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, eventName);
        ResultSet resultSetEvent = statement.executeQuery();
        databaseManager.closeConnection();
        return resultSetToList(resultSetEvent);
    }

    public void decreaseSlotAvailability(int eventId) throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        String query = "update eventos set cupo=cupo-1 where idEvento=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, eventId);
        preparedStatement.executeQuery();
        databaseManager.closeConnection();
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
