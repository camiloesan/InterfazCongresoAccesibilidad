package mx.uv.fei.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import mx.uv.fei.logic.Attendant;
import mx.uv.fei.logic.Event;

import java.sql.SQLException;
import java.time.LocalDate;

public class AccessibilityCongressController {
    private String eventDate;
    private int eventId;
    private final static ObservableList<String> observableListCombo = FXCollections.observableArrayList("Platica", "Taller");
    Event event;
    @FXML
    private Label labelEditEventSpeaker;
    @FXML
    private Label labelEditEventDuration;
    @FXML
    private Label labelEditEventLocation;
    @FXML
    private Label labelEditEventDate;
    @FXML
    private Label labelEditEventTime;
    @FXML
    private Label labelEditEventType;
    @FXML
    private Label labelEditSelectedItem;
    @FXML
    private Label labelEditEventSlots;
    @FXML
    private TextField textFieldEventName;
    @FXML
    private TextField textFieldEventSpeaker;
    @FXML
    private TextField textFieldEventDuration;
    @FXML
    private TextField textFieldEventLocation;
    @FXML
    private TextField textFieldEventTime;
    @FXML
    private TextField textFieldAttendantName;
    @FXML
    private TextField textFieldAttendantLastName;
    @FXML
    private TextField textFieldAttendantSecondLastName;
    @FXML
    private TextField textFieldAttendantEmail;
    @FXML
    private TextField textFieldEventSlots;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ListView<String> listViewEvents;
    @FXML
    private ComboBox<String> comboBoxEventType;
    @FXML
    private void updateLabels() throws SQLException {
        for(Event objectEvent : event.eventList(listViewEvents.getSelectionModel().getSelectedItem())) {
            labelEditEventSpeaker.setText(objectEvent.getSpeakerName());
            labelEditEventDuration.setText(String.valueOf(objectEvent.getEventDuration()));
            labelEditEventLocation.setText(objectEvent.getEventLocation());
            labelEditEventDate.setText(objectEvent.getEventDate());
            labelEditEventTime.setText(objectEvent.getEventTime());
            labelEditEventType.setText(objectEvent.getEventType());
            labelEditEventSlots.setText(String.valueOf(objectEvent.getEventSlots()));
            eventId = objectEvent.getEventId();
        }
        labelEditSelectedItem.setText(listViewEvents.getSelectionModel().getSelectedItem());
    }
    @FXML
    private void updateListView() throws SQLException {
        listViewEvents.getItems().clear();
        event = new Event();
        for(Event objetoEvent : event.eventList()) {
            listViewEvents.getItems().add(objetoEvent.getEventName());
        }
    }
    @FXML
    private void handleListViewClick() throws SQLException {
        updateLabels();
    }
    @FXML
    private void getDate() {
        LocalDate date = datePicker.getValue();
        eventDate = date.toString();
    }
    @FXML
    private void alert(String message, boolean isSuccessful) {
        Alert alert;
        if (isSuccessful) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Éxito");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
        }
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    protected void addEvent() {
        if (textFieldEventName.getText().isBlank() || textFieldEventSpeaker.getText().isBlank() || textFieldEventDuration.getText().isBlank() || textFieldEventLocation.getText().isBlank() || eventDate.isBlank() || textFieldEventTime.getText().isBlank() || comboBoxEventType.getValue().isBlank() || textFieldEventSlots.getText().isBlank() || Integer.parseInt(textFieldEventSlots.getText()) > 30 || Integer.parseInt(textFieldEventSlots.getText()) <= 0 || Integer.parseInt(textFieldEventSlots.getText()) <= 0) {
            alert("Revise que los campos sean correctos", false);
        } else {
            try {
                event = new Event();
                event.setEventName(textFieldEventName.getText());
                event.setSpeakerName(textFieldEventSpeaker.getText());
                event.setEventDuration(Integer.parseInt(textFieldEventDuration.getText()));
                event.setEventLocation(textFieldEventLocation.getText());
                event.setEventDate(eventDate);
                event.setEventTime(textFieldEventTime.getText());
                event.setEventType(comboBoxEventType.getValue());
                event.setEventSlots(Integer.parseInt(textFieldEventSlots.getText()));
                event.saveEvent();
                updateLabels();
                alert("El evento fue guardado correctamente", true);
            } catch (SQLException sqlException) {
                alert("No se pudo registrar el evento", true);
            }
        }
    }
    @FXML
    protected void addAttendant() {
        Attendant attendant = new Attendant();
        if (listViewEvents.getSelectionModel().getSelectedItem() != null) {
            if (textFieldAttendantName.getText().isBlank() || textFieldAttendantLastName.getText().isBlank() || textFieldAttendantSecondLastName.getText().isBlank() || textFieldAttendantEmail.getText().isBlank() || Integer.parseInt(labelEditEventSlots.getText()) <= 0 || !attendant.isEmailValid(textFieldAttendantEmail.getText())) {
                alert("Revise que los campos sean correctos", false);
            } else {
                try {
                    attendant.setAttendantName(textFieldAttendantName.getText());
                    attendant.setAttendantLastName(textFieldAttendantLastName.getText());
                    attendant.setAttendantSecondLastName(textFieldAttendantSecondLastName.getText());
                    attendant.setAttendantEmail(textFieldAttendantEmail.getText());
                    attendant.setEventId(eventId);
                    attendant.saveAttendant();
                    event.decreaseSlotAvailability(eventId);
                    updateLabels();
                    alert("El evento fue guardado exitosamente", true);
                } catch (SQLException sqlException) {
                    alert("No se pudo registrar el asistente", false);
                }
            }
        } else {
            alert("Por favor, seleccione un evento", false);
        }
    }
    @FXML
    private void initialize() throws SQLException {
        comboBoxEventType.setItems(observableListCombo);
        updateListView();
    }
}