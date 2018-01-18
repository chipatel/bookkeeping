package com.chipatel.bookkeeping.models;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.Type;

/**
 * @author chipatel (Chiragkumar Patel)
 */

@Entity
@Table(name = "Budget")
public class Budget {

  private long budgetId;
  private String budgetType;
  private String budgetTag;
  private String budgetDescription;
  private double budgetAmount;
  private int budgetMonth;
  private int budgetYear;
  private Date budgetLastUpdateTime;
  private String budgetLastUpdateBy;

  @Id
  @Column(name = "budget_id", unique = true)
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long getBudgetId() {
    return budgetId;
  }

  public void setBudgetId(Long budgetId) {
    this.budgetId = budgetId;
  }

  @Column(name = "budget_type")
  public String getBudgetType() {
    return budgetType;
  }

  public void setBudgetType(String budgetType) {
    this.budgetType = budgetType;
  }

  @Column(name = "budget_tag")
  public String getBudgetTag() {
    return budgetTag;
  }

  public void setBudgetTag(String budgetTag) {
    this.budgetTag = budgetTag;
  }

  @Column(name = "budget_description")
  public String getBudgetDescription() {
    return budgetDescription;
  }

  public void setBudgetDescription(String budgetDescription) {
    this.budgetDescription = budgetDescription;
  }

  @Type(type = "double")
  @Column(name = "budget_amount")
  public double getBudgetAmount() {
    return budgetAmount;
  }

  public void setBudgetAmount(double budgetAmount) {
    this.budgetAmount = budgetAmount;
  }

  @Column(name = "budget_month")
  public int getBudgetMonth() {
    return budgetMonth;
  }

  public void setBudgetMonth(int budgetMonth) {
    this.budgetMonth = budgetMonth;
  }

  @Column(name = "budget_year")
  public int getBudgetYear() {
    return budgetYear;
  }

  public void setBudgetYear(int budgetYear) {
    this.budgetYear = budgetYear;
  }


  @Column(name = "last_update_time")
  public Date getBudgetLastUpdateTime() {
    return budgetLastUpdateTime;
  }

  public void setBudgetLastUpdateTime(Date budgetLastUpdateTime) {
    this.budgetLastUpdateTime = budgetLastUpdateTime;
  }

  @Column(name = "last_update_by")
  public String getBudgetLastUpdateBy() {
    return budgetLastUpdateBy;
  }

  public void setBudgetLastUpdateBy(String budgetLastUpdateBy) {
    this.budgetLastUpdateBy = budgetLastUpdateBy;
  }

  public String toString() {
    return "\nbudgetId:- " + budgetId + "\nbudgetType:- " + budgetType + ",\nbudgetDescription:- "
        + budgetDescription + ",\nbudgetAmount:- " + budgetAmount + ",\nbudgetMonth:- "
        + budgetMonth + ",\nbudgetYear:- " + budgetYear + ",\nbudgetLastUpdateTime:- "
        + budgetLastUpdateTime + ",\nbudgetLastUpdateBy:- " + budgetLastUpdateBy;
  }

}
