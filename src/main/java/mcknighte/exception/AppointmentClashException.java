package mcknighte.exception;

import mcknighte.entity.Appointment;

/**
 * AppointmentClashException
 *
 * @author Edward McKnight (UP608985)
 */
public class AppointmentClashException extends AppointmentException {

    /**
     * Constructor
     *
     * @param appointment Appointment
     */
    public AppointmentClashException(Appointment appointment) {
        super(appointment);
    }

    /**
     * Get message
     *
     * @return String
     */
    @Override
    public String getMessage() {
        return "Attendees: One or more attendees are already in an appointment during this time.";
    }
}
