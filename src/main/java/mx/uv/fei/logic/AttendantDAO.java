package mx.uv.fei.logic;

import mx.uv.fei.dao.IAttendant;
import mx.uv.fei.dataaccess.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AttendantDAO implements IAttendant {

    @Override
    public void addAttendant(Attendant attendant) throws SQLException {
        String query = "insert into asistente(nombre, apellidoPaterno, apellidoMaterno, correoElectronico, idEvento) values(?,?,?,?,?)";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, attendant.getAttendantName());
        preparedStatement.setString(2, attendant.getAttendantLastName());
        preparedStatement.setString(3, attendant.getAttendantSecondLastName());
        preparedStatement.setString(4, attendant.getAttendantEmail());
        preparedStatement.setInt(5, attendant.getEventId());
        preparedStatement.executeUpdate();
    }

}
