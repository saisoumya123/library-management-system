package com.capgemini.librarymanagementsystemspring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagementsystemspring.dto.BookBean;
import com.capgemini.librarymanagementsystemspring.dto.UserBean;
//import com.capgemini.librarymanagementsystemspring.factory.UserFactory;
import com.capgemini.librarymanagementsystemspring.service.UserService;
@Repository
public class UserDAOImp implements UserDAO{
@PersistenceUnit
EntityManagerFactory factory;
	@Override
	public boolean register(UserBean bean) {
		EntityManagerFactory factory=null;
		EntityManager manager=null;
		EntityTransaction transaction=null;
		boolean isRegistered = false;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			transaction.begin();
			manager.persist(bean);
			transaction.commit();
			isRegistered = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isRegistered;
	}

	@Override
	public UserBean auth(String email, String password) {
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		//String res = null;
		try {
			factory=Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			UserBean b = manager.find(UserBean.class, email);
			b.setEmail(email);
			manager.persist(b);
			if(b.getPassword().equals(password)) {
				return b;
			}
			transaction.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return null;
			
		//return res;
	}

}
