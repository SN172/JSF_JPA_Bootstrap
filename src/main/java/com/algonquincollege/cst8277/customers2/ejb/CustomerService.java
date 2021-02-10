/**
 * File: CustomerService.java
 * Course materials (20F) CST 8277
 * 
 * @author (original) Mike Norman
 * 
 * updated by: Sajeel Nazir
 * student number: 040760897
 */

package com.algonquincollege.cst8277.customers2.ejb;

import static com.algonquincollege.cst8277.customers2.model.CustomerPojo.ALL_CUSTOMERS_QUERY_NAME;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.algonquincollege.cst8277.customers2.model.CustomerPojo;

/**
 * 
 * Stateless Signleton Session Bean - CustomerService
 *
 */
    
@Singleton
public class CustomerService implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    public static final String CUSTOMER_PU = "acmeCustomers-PU";

    @PersistenceContext(name = CUSTOMER_PU)
    protected EntityManager em;

    /**
     * 
     * @return list of all the customers
     */
    public List<CustomerPojo> getAllCUstomers() {
        TypedQuery<CustomerPojo> allCustomersQuery = em.createNamedQuery(ALL_CUSTOMERS_QUERY_NAME, CustomerPojo.class);
        return allCustomersQuery.getResultList();
    }
    
    /**
     * 
     * @param customerToBeCreated the customer to be inserted
     * @return the customer to be created
     */
    @Transactional
    public CustomerPojo insertNewCustomer(CustomerPojo customerToBeCreated) {
        em.persist(customerToBeCreated);
        return customerToBeCreated;
    }

    /**
     * 
     * @param customerToBeDeleted the customer to be deleted
     */
    @Transactional
    public void deleteCustomer(CustomerPojo customerToBeDeleted) {
      if(!em.contains(customerToBeDeleted)) {
          customerToBeDeleted = em.merge(customerToBeDeleted);
      }
      em.remove(customerToBeDeleted);
       
        
    }

    /**
     * 
     * @param customerId the id of the customer to be read
     * @return the custome that was found
     */
    @Transactional
    public CustomerPojo readCustomer(int customerId) {
        return em.find(CustomerPojo.class, customerId);
    }

    /**
     * 
     * @param customerWithUpdates customer that has updates
     * @return the customer with updates
     */
    public CustomerPojo updateCustomer(CustomerPojo customerWithUpdates) {
       return em.merge(customerWithUpdates);
    }
}
