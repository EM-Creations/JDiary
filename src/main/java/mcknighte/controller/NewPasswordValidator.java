package mcknighte.controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * New password validator, to ensure that if a new password is being used,
 * that it meets the relevant criteria
 *
 * @author Edward McKnight (UP608985)
 * @since 2017
 * @version 1.0
 */
@FacesValidator("mcknighte.NewPasswordValidator")
public class NewPasswordValidator implements Validator {
    /**
     * Constructor
     */
    public NewPasswordValidator() {
    }

    /**
     * Validate the new password
     *
     * @param context the JSF context
     * @param component the component this validator is being called from
     * @param value the object to validate
     * @throws ValidatorException if the validation fails
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null || value.toString().equals("")) {
            return; // If there is no new password set, let it through!
        }
        // Otherwise
        if (value.toString().length() < 3) { // If the length isn't longer than 3
            FacesMessage msg = new FacesMessage("Regex invalid",
                    "Regex invalid");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}
