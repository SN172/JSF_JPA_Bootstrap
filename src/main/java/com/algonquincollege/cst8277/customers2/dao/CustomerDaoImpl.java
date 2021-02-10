/*****************************************************************c******************o*******v******id********
 * File: CustomerDaoImpl.java
 * Course materials (20F) CST 8277
 * 
 * @author (original) Mike Norman
 * 
 * updated by: Sajeel Nazir
 * student number: 040760897
 */

package com.algonquincollege.cst8277.customers2.dao;


import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.customers2.ejb.CustomerService;
import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
* Description: Implements the C-R-U-D API for the database
*/
/**
 * @author Sajeel Nazir
 *
 */
@Named
@ApplicationScoped
public class CustomerDaoImpl implements CustomerDao, Serializable {
    /** explicitly set serialVersionUID */
    private static final long serialVersionUID = 1L;

    @EJB 
    protected CustomerService customerService;
  
    
    protected ServletContext sc;
/**
 * @param sc
 */
    @Inject
    public CustomerDaoImpl(ServletContext sc) {
        super();
        this.sc = sc;
    }
    
    /**
     * @param msg message to log 
     */
    protected void logMsg(String msg) {
        sc.log(msg);
    }
    

    // delegate all C-R-U-D operations to EntityManager

   /**
    * @return all the customers
    */
    @Override
    public List<CustomerPojo> readAllCustomers() {
        logMsg("reading all customers");
        return customerService.getAllCUstomers();
    }

   
    /**
     *@param customerToBeCreated the customer to be created
     *@return Customerpojo 
     */
    @Override
    @Transactional
    public CustomerPojo createCustomer(CustomerPojo customerToBeCreated) {
        logMsg("creating an customer");
        return customerService.insertNewCustomer(customerToBeCreated);
         }

    /**
     *@param customerId id for the customer to be read
     *@return Customerpojo
     */
    @Override
    public CustomerPojo readCustomerById(int customerId) {
      logMsg("read a specific customer");
      return customerService.readCustomer(customerId);
    }

    /**
     *@param customerWithUpdates customer that has updates
     *@return CustomerPojo
     */
    @Override
    public CustomerPojo updateCustomer(CustomerPojo customerWithUpdates) {
        logMsg("updating a specific customer");
        return customerService.updateCustomer(customerWithUpdates);
    }

    /**
     *@param customerId customer id of customer that will be deleted
     */
    @Override
    public void deleteCustomerById(int customerId) {
         logMsg("deleting a specific customer");
         CustomerPojo customer = readCustomerById(customerId);
        customerService.deleteCustomer(customer);

    }

}