package com.nabil.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nabil.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public List<Customer> getCustomers() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer>theQuery = currentSession.createQuery("from Customer order by lastName",Customer.class);
		
		List<Customer>customers = theQuery.getResultList();
		
		return customers;
	}


	@Override
	public void addCustomer(Customer customer) {
		Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(customer);
	}


	@Override
	public Customer getCustomer(int id) {
		Session currentSession = sessionFactory.getCurrentSession();  
		return currentSession.get(Customer.class, id);
	}


	@Override
	public void deleteCustomer(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();  
        Query theQuery = currentSession.createQuery("delete from Customer where id=:theCustomerId");
        theQuery.setParameter("theCustomerId", theId);
        
       theQuery.executeUpdate();
	}

}



