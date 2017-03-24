package mcknighte.common;

import java.util.Calendar;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * Time class to be used when outputting the current time
 *
 * @author Edward McKnight (UP608985)
 * @since 2017
 * @version 1.0
 */
@Named(value = "time")
@RequestScoped
public class Time {
    private Calendar time;

    /**
     * Get the max number of days in the current month
     * 
     * @return the max number of days in the current month
     */
    public int getMaxDaysInCurrentMonth() {
        return this.time.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * Get the the current time as a Date
     *
     * @return the Date value of the current time
     */
    public Date getTime() {
        return this.time.getTime();
    }

    /**
     * Set the current time
     *
     * @param time the new value for the current time
     */
    public void setTime(Calendar time) {
        this.time = time;
    }

    /**
     * Constructor, defaulting the reference time to the current time
     */
    public Time() {
        this.time = Calendar.getInstance();
    }

}
