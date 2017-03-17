package mcknighte.common;

// Briggs, J. (2016). MessageController.java [Computer software]. Retrieved from https://github.com/Jimbriggs/webp-examples/blob/master/jimairline/src/main/java/jim/common/ctrl/MessageController.java
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author briggsj
 */
public abstract class MessageController implements Serializable {
    protected void addError(String s) {
        addError((String) null, s);
    }

    /**
     * Add error with item and message
     * 
     * @author Edward McKnight (UP608985)
     * @param item String
     * @param errorMessage String 
     */
    protected void addError(String item, String errorMessage) {
        this.addError(item, errorMessage, errorMessage);
    }
    
    /**
     * Add error with item, title and message
     * 
     * @author Edward McKnight (UP608985)
     * @param item String
     * @param title String
     * @param errorMessage String 
     */
    protected void addError(String item, String title, String errorMessage) {
        FacesContext.getCurrentInstance().addMessage(item, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, errorMessage));
    }

    protected void addInfo(String item, String errorMessage) {
        FacesContext.getCurrentInstance().addMessage(item, new FacesMessage(FacesMessage.SEVERITY_INFO, errorMessage, errorMessage));
    }

    protected void addInfo(String infoMessage) {
        addInfo(null, infoMessage);
    }

    protected void addError(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addError(msg);
        } else {
            addError(defaultMsg);
        }
    }
}
