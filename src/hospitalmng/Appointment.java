package hospitalmng;

import java.sql.Date;

public class Appointment {

    private int appointmentId;

    private int patientId;

    private int doctorId;

    private Date appointmentDate;

    private String status;

    public Appointment(int appointmentId,
                       int patientId,
                       int doctorId,
                       Date appointmentDate,
                       String status) {

        this.appointmentId = appointmentId;

        this.patientId = patientId;

        this.doctorId = doctorId;

        this.appointmentDate = appointmentDate;

        this.status = status;
    }

    public int getAppointmentId() {

        return appointmentId;
    }

    public int getPatientId() {

        return patientId;
    }

    public int getDoctorId() {

        return doctorId;
    }

    public Date getAppointmentDate() {

        return appointmentDate;
    }

    public String getStatus() {

        return status;
    }
}