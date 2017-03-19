package mcknighte.common;

import java.util.Calendar;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 * Time class
 * 
 * @author Edward McKnight (UP608985)
 */
@Named(value = "time")
@RequestScoped
public class Time {
    private Calendar time;

    /**
     * Get the value of time
     *
     * @return the value of time
     */
    public Date getTime() {
        return this.time.getTime(); 
    }

    /**
     * Set the value of time
     *
     * @param time new value of time
     */
    public void setTime(Calendar time) {
        this.time = time;
    }
    
    /**
     * Creates a new instance of Time
     */
    public Time() {
        this.time = Calendar.getInstance();
    }
    
}
