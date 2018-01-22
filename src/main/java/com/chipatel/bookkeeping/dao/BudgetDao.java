package com.chipatel.bookkeeping.dao;

import com.chipatel.bookkeeping.models.BarChart;
import com.chipatel.bookkeeping.models.Budget;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    query = getSession().createQuery("from Budget where budgetYear = :year")
        .setParameter("year", year);
    return query.list();
  }

  public List<Budget> getBudget(int month, int year) {
    query = getSession()
        .createQuery("from Budget where budgetMonth = :month and budgetYear = :year")
        .setParameter("month", month).setParameter("year", year);
    return query.list();
  }

  public List<BarChart> getTypeSumData(int year) {
    List<Object[]> result = this.entityManager.createNativeQuery(
        "select UPPER(budget_type) as budget_type, SUM(budget_amount) budget_amount, budget_month, budget_year from budget\n"
            + " WHERE budget_year = :year group by budget_type, budget_month, budget_year ORDER BY budget_month, budget_year").setParameter("year", year)
        .getResultList();

    Map<String, BarChart> test = new HashMap<>();
    test.put("INCOME", new BarChart("INCOME"));
    test.put("EXPENSE", new BarChart("EXPENSE"));

    result.stream().forEach((record) -> {
      BarChart tmp = test.get( record[0]);
      tmp.getData().add((Double) record[1]);
      tmp.getAxisTitle().add(record[2] + "-" + record[3]);
      test.put(tmp.getLabel(), tmp);
    });
    return new ArrayList<>(test.values());
  }
}
