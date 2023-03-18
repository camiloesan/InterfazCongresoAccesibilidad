package mx.uv.fei.logic;

import mx.uv.fei.dataaccess.DatabaseManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Attendant {
    private String attendantName;
    private String attendantLastName;
    private String attendantSecondLastName;
    private String attendantEmail;
    private int eventId;

    public void saveAttendant() throws SQLException {
        String query = "insert into asistente(nombre, apellidoPaterno, apellidoMaterno, correoElectronico, idEvento) values(?,?,?,?,?)";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, attendantName);
        preparedStatement.setString(2, attendantLastName);
        preparedStatement.setString(3, attendantSecondLastName);
        preparedStatement.setString(4, attendantEmail);
        preparedStatement.setInt(5, eventId);
        preparedStatement.executeUpdate();
    }

    public boolean isEmailValid(String attendantEmail) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(attendantEmail);
        return matcher.matches();
    }

    public void setAttendantName(String attendantName) {
        this.attendantName = attendantName;
    }

    public void setAttendantLastName(String attendantLastName) {
        this.attendantLastName = attendantLastName;
    }

    public void setAttendantSecondLastName(String attendantSecondLastName) {
        this.attendantSecondLastName = attendantSecondLastName;
    }

    public void setAttendantEmail(String attendantEmail) {
        this.attendantEmail = attendantEmail;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
