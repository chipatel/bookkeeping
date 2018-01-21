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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/budget")
public class BudgetController {

  Logger logger = Logger.getLogger(this.getClass());

  @Autowired(required = true)
  private BudgetDao _budgetDao;

  @ResponseBody
  @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addBudget(@RequestBody Budget budget) {
    try {
      _budgetDao.addBudget(budget);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return new ResponseEntity<>(budget, HttpStatus.OK);
  }

  @ResponseBody
  @RequestMapping(value = "/multisave", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addBudget(@RequestBody List<Budget> budget) {
    try {
      _budgetDao.addBudget(budget);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return new ResponseEntity<>(budget, HttpStatus.OK);
  }

  @ResponseBody
  @RequestMapping(value = "/all", method = RequestMethod.GET)
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
    @RequestMapping(value = "/get", params = {"month"}, method = RequestMethod.GET)
    public List<Budget> getBudgetMonth(@RequestParam(value = "month") int month, ModelMap map) {
      map.
      return this._budgetDao.getBudgetMonth(month);
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"year"}, method = RequestMethod.GET)
    public List<Budget> getBudgetYear(@RequestParam(value = "year") int year) {
      return this._budgetDao.getBudgetYear(year);
    }
  */

  @ResponseBody
  @RequestMapping(value = "/get", params = {"month", "year"}, method = RequestMethod.GET)
  public List<Budget> getBudgetMonthYear(@RequestParam(value = "month") int month,
      @RequestParam(value = "year") int year) {
    return this._budgetDao.getBudget(month, year);
  }

}
