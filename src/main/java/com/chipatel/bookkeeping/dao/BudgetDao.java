package com.chipatel.bookkeeping.dao;

import com.chipatel.bookkeeping.models.BarChart;
import com.chipatel.bookkeeping.models.Budget;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.hibernate.Query;
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
  private Query query;

  @PersistenceContext
  private EntityManager entityManager;

  private Session getSession() {
    return _sessionFactory.getCurrentSession();
  }

  public List<Budget> getBudget() {
    query = getSession().createQuery("from Budget");
    return query.list();
  }

  public void addBudget(Budget budget) {
    entityManager.persist(budget);
  }

  public void addBudget(List<Budget> budgets) {
    for (Budget budget : budgets) {
      entityManager.persist(budget);
    }
  }

  public List<Budget> getBudgetMonth(int month) {
    query = getSession().createQuery("from Budget where budgetMonth = :month")
        .setParameter("month", month);
    return query.list();
  }

  public List<Budget> getBudgetYear(int year) {
    query = getSession().createQuery("from Budget where budgetYear = :year").setParameter("year", year);
    return query.list();
  }

  public List<Budget> getBudget(int month, int year) {
    query = getSession()
        .createQuery("from Budget where budgetMonth = :month and budgetYear = :year")
        .setParameter("month", month).setParameter("year", year);
    return query.list();
  }

  public List<Budget> getBudget(String type) {
    return getSession().createQuery("from Budget where budgetType = :type").setParameter("type", type)
        .list();
  }

}
