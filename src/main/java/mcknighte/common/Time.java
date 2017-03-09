package mcknighte.common;

import java.util.Calendar;
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
    private String date;
    
    /**
     * Get date in YYYY format
     * 
     * @return String
     */
    public String getDate() {
        date = Calendar.getInstance().get(Calendar.YEAR) + "";
        return date;
    }
    
    /**
     * Set the date
     * 
     * @param date String
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * Creates a new instance of Time
     */
    public Time() {
    }
    
}
