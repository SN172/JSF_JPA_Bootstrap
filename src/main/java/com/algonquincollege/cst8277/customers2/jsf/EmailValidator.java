/*****************************************************************c******************o*******v******id********
 * File: EmailValidator.java
 * Course materials (20F) CST 8277
 * 
 * @author (original) Mike Norman
 * 
 * updated by: Sajeel Nazir
 * student number: 040760897
 */

package com.algonquincollege.cst8277.customers2.jsf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator("com.algonquincollege.cst8277.customers2.jsf.EmailValidator")
public class EmailValidator implements Validator<Object>{

    // really really (!) simple email pattern: at-least-1-letter, '@', at-least-1-letter
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
     Matcher matcher = EMAIL_PATTERN.matcher(value.toString());
     System.out.println(matcher.matches());
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("Email should not be empty", "Invalid Email format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        
        
		// TODO - use Matcher and Pattern to figure out if the value is a valid email
    }

}