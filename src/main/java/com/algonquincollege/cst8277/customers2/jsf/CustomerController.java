/*****************************************************************c******************o*******v******id********
 * File: CustomerController.java
 * Course materials (20F) CST 8277
 * 
 * @author (original) Mike Norman
 * 
 * updated by: Sajeel Nazir
 * student number: 040760897
 */

package com.algonquincollege.cst8277.customers2.jsf;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.algonquincollege.cst8277.customers2.dao.CustomerDao;
import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
 * Description: Responsible for collection of Customer Pojo's in XHTML (list) <h:dataTable> </br>
 * Delegates all C-R-U-D behaviour to DAO
 */
@Named("customerController")
@SessionScoped
public class CustomerController implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String UICONSTS_BUNDLE_EXPR = "#{uiconsts}";
    public static final String CUSTOMER_MISSING_REFRESH_BUNDLE_MSG = "refresh";
    public static final String CUSTOMER_OUTOFDATE_REFRESH_BUNDLE_MSG = "outOfDate";

    @Inject
    protected FacesContext facesContext;

    @Inject
    protected ServletContext sc;

    @Inject
    protected CustomerDao customerDao;

    @Inject
    @ManagedProperty(UICONSTS_BUNDLE_EXPR)
    protected ResourceBundle uiconsts;
    
    protected List<CustomerPojo> customers;
    protected boolean adding;

    /**
     * reads all custoemrs
     */
    public void loadCustomers() {
        logMsg("loadCustomers");
        customers = customerDao.readAllCustomers();
    }

    /**
     * 
     * @return the list of customers
     */
    public List<CustomerPojo> getCustomers() {
        return this.customers;
    }
    
    /**
     * 
     * @param customers customers to set
     */
    public void setCustomers(List<CustomerPojo> customers) {
        this.customers = customers;
    }

    /**
     * 
     * @return if a customer is being added
     */
    public boolean isAdding() {
        return adding;
    }
    
/**
 * 
 * @param adding set if teh customer is being added
 */
    public void setAdding(boolean adding) {
        this.adding = adding;
    }
    
    /**
     * 
     * @param customer check if the customer is editable
     * @return the editable state 
     */
    public boolean isEdit(CustomerPojo customer) {
        return customer.getEdit();
    }

    /**
     * Toggles the add customer mode which determines whether the addCustomer form is rendered
     */
    public void toggleAdding() {
        this.adding = !this.adding;
    }

    /**
     * 
     * @param cust customer that is being edited
     * @return the current page
     */
    public String editCustomer(CustomerPojo cust) {
        logMsg("editCustomer");
        cust.setEditable(true);
        return null; //current page
    }

    /**
     * 
     * @param customerWithEdits customer with the edits
     * @return the current page
     */
    public String updateCustomer(CustomerPojo customerWithEdits) {
        logMsg("updateCustomer");
        CustomerPojo customerToBeUpdated = customerDao.readCustomerById(customerWithEdits.getId());
        if (customerToBeUpdated == null) {
            // someone else deleted it
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                uiconsts.getString(CUSTOMER_MISSING_REFRESH_BUNDLE_MSG), null));
        }
        else {
            customerToBeUpdated = customerDao.updateCustomer(customerWithEdits);
            if (customerToBeUpdated == null) {
                //OptimisticLockException 
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    uiconsts.getString(CUSTOMER_OUTOFDATE_REFRESH_BUNDLE_MSG), null));
            }
            else {
                customerToBeUpdated.setEditable(false);
                int idx = customers.indexOf(customerWithEdits);
                customers.remove(idx);
                customers.add(idx, customerToBeUpdated);
            }
        }
        return null; //current page
    }
    
    /**
     * 
     * @param cust cancel the edit on the customer
     * @return current page
     */
    public String cancelUpdate(CustomerPojo cust) {
        logMsg("cancelUpdate");
        cust.setEditable(false);
        return null; //current page
    }

    /**
     * 
     * @param custId customer to be deleted
     */
    public void deleteCustomer(int custId) {
        logMsg("deleteCustomer: " + custId);
        CustomerPojo customerToBeRemoved = customerDao.readCustomerById(custId);
        if (customerToBeRemoved != null) {
            customerDao.deleteCustomerById(custId);
            customers.remove(customerToBeRemoved);
        }
    }

    /**
     * 
     * @param theNewCustomer the new customer to be added
     */
    public void addNewCustomer(CustomerPojo theNewCustomer) {
        logMsg("adding new Customer");
        CustomerPojo newCust = customerDao.createCustomer(theNewCustomer);
        customers.add(newCust);
    }

    /**
     * 
     * @return sends to the index.xhtml page
     */
    public String refreshCustomerForm() {
        logMsg("refreshCustomerForm");
        Iterator<FacesMessage> facesMessageIterator = facesContext.getMessages();
        while (facesMessageIterator.hasNext()) {
            facesMessageIterator.remove();
        }
        return "index.xhtml?faces-redirect=true";
    }

    /**
     * 
     * @param msg message to log
     */
    protected void logMsg(String msg) {
        sc.log(msg);
    }
}