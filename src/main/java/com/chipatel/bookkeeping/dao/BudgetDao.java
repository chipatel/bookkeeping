package com.chipatel.bookkeeping.dao;

import com.chipatel.bookkeeping.models.Budget;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Chiragkumar Patel (projects.cpatel@gmail.com) on 1/15/18.
 */

@Repository
@Transactional
public class BudgetDao {

  Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private SessionFactory _sessionFactory;

  @PersistenceContext
  private EntityManager entityManager;

  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public List<Budget> getBudget() {
    return getSession().createQuery("from Budget").list();
  }

  public void addBudget(Budget budget){
    entityManager.persist(budget);
  }

  public List<Budget> getBudget(int year) {
    return getSession().createQuery("from budget where year = :year").setParameter("year", year)
        .list();
  }

  public List<Budget> getBudget(int month, int year) {
    return getSession().createQuery("from budget where month = :month and year = :year")
        .setParameter("month", month).setParameter("year", year).list();
  }

  public List<Budget> getBudget(String type) {
    return getSession().createQuery("from budget where type = :type").setParameter("type", type)
        .list();
  }

}
