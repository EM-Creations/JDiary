package mcknighte.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Postcode validator, to ensure that the postcode is valid only if the user's
 * country is set to "United Kingdom"
 *
 * @author Edward McKnight (UP608985)
 * @since 2017
 * @version 1.0
 */
@FacesValidator("mcknighte.PostcodeValidator")
public class PostcodeValidator implements Validator {
    private final String postcodeRegex = "^([A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {1,2}[0-9][ABD-HJLN-UW-Z]{2}|GIR 0AA)$";

    /**
     * Constructor
     */
    public PostcodeValidator() {
    }

    /**
     * Validate the postcode
     *
     * @param context the JSF context
     * @param component the component this validator is being called from
     * @param value the object to validate
     * @throws ValidatorException if the validation fails
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!"United Kingdom".equals(component.getAttributes().get("country"))) { // If the country isn't set to United Kingdom
            return; // No need to validate the post code
        }

        // Otherwise
        if (!value.toString().matches(this.postcodeRegex)) { // If the postcode isn't valid
            FacesMessage msg = new FacesMessage("",
                    "Postcode: A valid postcode must be provided for UK addresses");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}
