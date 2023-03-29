package mx.uv.fei.dao;

import mx.uv.fei.logic.Attendant;

import java.sql.SQLException;

public interface IAttendant {
    void addAttendant(Attendant attendant) throws SQLException;
}
