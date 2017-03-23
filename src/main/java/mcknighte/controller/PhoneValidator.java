package mcknighte.controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Phone validator, to ensure that if a phone number is being provided,
 * that it meets the relevant criteria
 *
 * @author Edward McKnight (UP608985)
 * @since 2017
 * @version 1.0
 */
@FacesValidator("mcknighte.PhoneValidator")
public class PhoneValidator implements Validator {
    // REGEX: https://www.aa-asterisk.org.uk/Regular_Expressions_for_Validating_and_Formatting_GB_Telephone_Numbers#Validating_GB_telephone_numbers
    private final String phoneRegex = "^\\(?(?:(?:0(?:0|11)\\)?[\\s-]?\\(?|\\+)44\\)?[\\s-]?\\(?(?:0\\)?[\\s-]?\\(?)?|0)(?:\\d{2}\\)?[\\s-]?\\d{4}[\\s-]?\\d{4}|\\d{3}\\)?[\\s-]?\\d{3}[\\s-]?\\d{3,4}|\\d{4}\\)?[\\s-]?(?:\\d{5}|\\d{3}[\\s-]?\\d{3})|\\d{5}\\)?[\\s-]?\\d{4,5}|8(?:00[\\s-]?11[\\s-]?11|45[\\s-]?46[\\s-]?4\\d))(?:(?:[\\s-]?(?:x|ext\\.?\\s?|\\#)\\d+)?)$";

    /**
     * Constructor
     */
    public PhoneValidator() {
    }

    /**
     * Validate the postcode, if given
     *
     * @param context the JSF context
     * @param component the component this validator is being called from
     * @param value the object to validate
     * @throws ValidatorException if the validation fails
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
