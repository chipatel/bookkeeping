package com.chipatel.bookkeeping.controllers;

import com.chipatel.bookkeeping.dao.BudgetDao;
import com.chipatel.bookkeeping.models.Budget;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class BudgetController {

  Logger logger = Logger.getLogger(this.getClass());

  @Autowired(required = true)
  private BudgetDao _budgetDao;

  @ResponseBody
  @RequestMapping(value = "add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addBudget(@RequestBody Budget budget) {
    try {
      _budgetDao.addBudget(budget);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return new ResponseEntity<>(budget, HttpStatus.OK);
  }

  @ResponseBody
  @RequestMapping(value = "get", method = RequestMethod.GET)
  public List<Budget> getAllBudget() {
    List<Budget> allBudget = null;
    try {
      allBudget = _budgetDao.getBudget();
      logger.info(allBudget.toString());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return allBudget;
  }
/*
  @ResponseBody
  @RequestMapping(value = "/name={incomename}", method = RequestMethod.GET)
  public Income getIncomeByName(@PathVariable String incomename) {
    Income income = null;
    try {
      income = _incomeDao.getIncomeName(incomename);
    } catch (Exception ex) {
      logger.error(ex);
    }
    return income;
  }

  @ResponseBody
  @RequestMapping(value = "/month={month}&year={year}", method = RequestMethod.GET)
  public List<Income> getAllIncomeByMonthYear(@PathVariable int month, @PathVariable int year) {
    List<Income> incomelist = null;
    try {
      incomelist = _incomeDao.getIncomeByMonthYear(month, year);
    } catch (Exception ex) {
      logger.error(ex.fillInStackTrace());
    }
    return incomelist;
  }


  @ResponseBody
  @RequestMapping(value = "/delete/{name}", method = RequestMethod.PUT)
  public String delete(int id, String name) {
    try {
      // _incomeDao.delete(new Income());
    } catch (Exception ex) {
      return ex.getMessage();
    }
    return "Income succesfully deleted!";
  }
  */

}
