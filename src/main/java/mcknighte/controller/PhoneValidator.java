package mcknighte.controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Phone validator
 *
 * @author Edward McKnight (UP608985)
 */
@FacesValidator("mcknighte.PhoneValidator")
public class PhoneValidator implements Validator {
    private final String phoneRegex = "^([A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {1,2}[0-9][ABD-HJLN-UW-Z]{2}|GIR 0AA)$";

    /**
     * Constructor
     */
    public PhoneValidator() {
    }

    /**
     * Validate the postcode
     * 
     * @param context FacesContext
     * @param component UIComponent
     * @param value Object
     * @throws ValidatorException 
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value.toString().isEmpty()) { // If the phone number is empty
            return; // No need to validate it
        }
        
        // Otherwise
        if (!value.toString().matches(this.phoneRegex)) { // If the phone number isn't valid
            FacesMessage msg = new FacesMessage("",
                            "Phone: A valid phone number must be provided if being used");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}
