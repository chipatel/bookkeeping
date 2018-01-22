package com.chipatel.bookkeeping.dao;

import com.chipatel.bookkeeping.models.BarChart;
import com.chipatel.bookkeeping.models.BarChartData;
import com.chipatel.bookkeeping.models.Budget;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

  public BarChart getTypeSumData(int year) {
    List<Object[]> result = this.entityManager.createNativeQuery(
        "select UPPER(budget_type) as budget_type, SUM(budget_amount) budget_amount, budget_month, budget_year from budget\n"
            + " WHERE budget_year = :year group by budget_type, budget_month, budget_year ORDER BY budget_month, budget_year")
        .setParameter("year", year)
        .getResultList();

    Map<String, BarChartData> test = new HashMap<>();
    test.put("INCOME", new BarChartData("INCOME"));
    test.put("EXPENSE", new BarChartData("EXPENSE"));

    List<String> axisLabel = new ArrayList<>();

    result.stream().forEach((record) -> {

      BarChartData tmp = test.get(record[0]);

      tmp.getData().add((Double)record[1]);
      if (!axisLabel.contains(record[2] + "-" + record[3])) {
        axisLabel.add(record[2] + "-" + record[3]);
      }

      test.put(tmp.getLabel(), tmp);
    });

    return new BarChart(new ArrayList<>(test.values()), axisLabel);
  }
}
