package mcknighte.exception;

import mcknighte.entity.Appointment;

/**
 * AppointmentClashException, exception thrown for when an attendee 
 * of an appointment already has an appointment at a certain time
 *
 * @author Edward McKnight (UP608985)
 * @see AppointmentException
 * @since 2017
 * @version 1.0
 */
public class AppointmentClashException extends AppointmentException {

    /**
     * Constructor
     *
     * @param appointment the appointment which this exception has been thrown for
     */
    public AppointmentClashException(Appointment appointment) {
        super(appointment);
    }

    /**
     * Get the message generated for this exception
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return "Attendees: One or more attendees are already in an appointment during this time.";
    }
}
