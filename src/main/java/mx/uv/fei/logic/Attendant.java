package mx.uv.fei.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Attendant {
    private String attendantName;
    private String attendantLastName;
    private String attendantSecondLastName;
    private String attendantEmail;
    private int eventId;



    public boolean isEmailValid(String attendantEmail) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(attendantEmail);
        return matcher.matches();
    }

    public String getAttendantName() {
        return attendantName;
    }

    public String getAttendantLastName() {
        return attendantLastName;
    }

    public String getAttendantSecondLastName() {
        return attendantSecondLastName;
    }

    public String getAttendantEmail() {
        return attendantEmail;
    }

    public int getEventId() {
        return eventId;
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
