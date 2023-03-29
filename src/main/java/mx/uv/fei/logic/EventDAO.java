package mx.uv.fei.logic;

import mx.uv.fei.dao.IEvent;
import mx.uv.fei.dataaccess.DatabaseManager;

import java.sql.*;
import java.util.List;

public class EventDAO implements IEvent {

    @Override
    public void addEvent(Event event) throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        String query = "insert into eventos(nombreEvento, quienImparte, duracion, lugar, fecha, hora, tipoEvento, cupo) values(?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, event.getEventName());
        statement.setString(2, event.getSpeakerName());
        statement.setInt(3, event.getEventDuration());
        statement.setString(4, event.getEventLocation());
        statement.setDate(5, java.sql.Date.valueOf(event.getEventDate()));
        statement.setTime(6, java.sql.Time.valueOf(event.getEventTime()));
        statement.setString(7, event.getEventType());
        statement.setInt(8, event.getEventSlots());
        statement.executeUpdate();
        databaseManager.closeConnection();
    }

    @Override
    public List<Event> getAllEvents() throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        String query = "SELECT * FROM eventos";
        Statement statement = connection.createStatement();
        ResultSet resultSetEvent = statement.executeQuery(query);
        databaseManager.closeConnection();
        Event event = new Event();
        return event.eventResultSetToList(resultSetEvent);
    }

    public List<Event> getEventByName(String eventName) throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        String query = "SELECT * FROM eventos WHERE nombreEvento=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, eventName);
        ResultSet resultSetEvent = statement.executeQuery();
        databaseManager.closeConnection();
        Event event = new Event();
        return event.eventResultSetToList(resultSetEvent);
    }

    public void decreaseEventSlotAvailability(int eventId) throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        String query = "update eventos set cupo=cupo-1 where idEvento=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, eventId);
        preparedStatement.executeQuery();
        databaseManager.closeConnection();
    }
}
