/*****************************************************************c******************o*******v******id********
 * File: CustomerPojo.java
 * Course materials (20F) CST 8277
 * 
 * @author (original) Mike Norman
 * 
 * updated by: Sajeel Nazir
 * student number: 040760897
 */

package com.algonquincollege.cst8277.customers2.model;

import static com.algonquincollege.cst8277.customers2.model.CustomerPojo.ALL_CUSTOMERS_QUERY_NAME;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.faces.view.ViewScoped;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
*
* Description: model for the Customer object
*/


@ViewScoped
@Entity(name = "CUSTOMER")
@Table(name = "customer")
@Access(AccessType.PROPERTY)
@NamedQueries({
    @NamedQuery(name=ALL_CUSTOMERS_QUERY_NAME, query = "select c from CUSTOMER c")
})
public class CustomerPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String ALL_CUSTOMERS_QUERY_NAME =
        "allCustomers";
    
    public static final String DELETE_CUSTOMER =
            "deleteCustomer";

    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected int version;
    protected LocalDateTime created;
    protected LocalDateTime updated;
    protected boolean edit;
    
    
    /**
     * 
     * @return the id of this customer
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }
    /**
     * @param id new value for id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the value for firstName
     */
    @Column(name = "FNAME")
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName new value for firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the value for lastName
     */
    @Column(name = "LNAME")
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName new value for lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the value for email
     */
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }
    /**
     * @param email new value for email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the phone number of this customer
     */
    @Column(name = "PHONENUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * 
     * @param phoneNumber set the phone number for this customer
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // these methods now used in Assignment 2
    
    /**
     * 
     * @return the version of the customer
     */
    @Version
    public int getVersion() {
        return version;
    }
    
    /**
     * 
     * @param version set the version of this customer
     */
    public void setVersion(int version) {
        this.version = version;
    }
    
    /**
     * 
     * @return the date the customer was created
     */
    @Column(name = "CREATED")
    public LocalDateTime getCreatedDate() {
        return created;
    }
    
    /**
     * 
     * @param created set the date the customer wass created
     */
    public void setCreatedDate(LocalDateTime created) {
        this.created = created;
    }
    
    /**
     * 
     * @return the date the customer was updated
     */
    @Column(name = "UPDATED")
    public LocalDateTime getUpdatedDate() {
        return updated;
    }
    
    /**
     * 
     * @param updated set the date the customer was updated
     */
    public void setUpdatedDate(LocalDateTime updated) {
        this.updated = updated;
    }

    
    /**
     * @return result
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    /**
     * @param obj object to compare to
     * @return if equals or not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerPojo)) {
            return false;
        }
        CustomerPojo other = (CustomerPojo) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * @return built string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
            .append("Customer [id=")
            .append(id)
            .append(", ");
        if (firstName != null) {
            builder
                .append("firstName=")
                .append(firstName)
                .append(", ");
        }
        if (lastName != null) {
            builder
                .append("lastName=")
                .append(lastName)
                .append(", ");
        }
        if (phoneNumber != null) {
            builder
                .append("phoneNumber=")
                .append(phoneNumber)
                .append(", ");
        }
        if (email != null) {
            builder
                .append("email=")
                .append(email)
                .append(", ");
        }
        builder.append("]");
        return builder.toString();
    }
    
    /**
     * 
     * @return if the customer is editable 
     */
    public Boolean getEdit() {
        return edit;
    }
    
    /**
     * 
     * @param edit set if the customer is editable
     */
    public void setEditable(Boolean edit) {
        this.edit = edit;
    }

}