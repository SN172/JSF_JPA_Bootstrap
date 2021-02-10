/*****************************************************************c******************o*******v******id********
 * File: NewCustomerView.java
 * Course materials (20F) CST 8277
 * 
 * @author (original) Mike Norman
 * 
 * updated by: Sajeel Nazir
 * student number: 040760897
 */

package com.algonquincollege.cst8277.customers2.jsf;

import java.io.Serializable;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import com.algonquincollege.cst8277.customers2.model.CustomerPojo;
@Named("newCustomer")
@ViewScoped
public class NewCustomerView implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    // TODO - add additional required fields

    @Inject
    @ManagedProperty("#{customerController}")
    protected CustomerController employeeController;

    public NewCustomerView() {
    }

    /**
     * @return  firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName  firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return  lastName
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param LastName  LastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return phonenumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 
     * @param phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * sets the customer fields and adds send them to the controller
     */
    public void addCustomer() {
        if (allNotNullOrEmpty(firstName, lastName,email, phoneNumber)) {
            CustomerPojo theNewCustomer = new CustomerPojo();
            theNewCustomer.setFirstName(getFirstName());
            theNewCustomer.setLastName(getLastName());
            theNewCustomer.setPhoneNumber(getPhoneNumber());
            theNewCustomer.setEmail(getEmail());
            // TODO - additional fields

            // this Managed Bean does not know how to 'do' anything, ask controller
            employeeController.addNewCustomer(theNewCustomer);

            //clean up
            employeeController.toggleAdding();
            setFirstName(null);
            setLastName(null);
            setPhoneNumber(null);
            setEmail(null);
            // TODO clean up additional fields
        }
    }
    
    /**
     * 
     * @param values to check
     * @return if empty
     */
    static boolean allNotNullOrEmpty(final Object... values) {
        if (values == null) {
            return false;
        }
        for (final Object val : values) {
            if (val == null) {
                return false;
            }
            if (val instanceof String) {
                String str = (String)val;
                if (str.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}