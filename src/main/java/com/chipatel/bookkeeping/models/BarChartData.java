package com.chipatel.bookkeeping.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chiragkumar Patel (projects.cpatel@gmail.com) on 1/21/18.
 */
public class BarChartData {
  private List<Double> data;
  private String label;

  public List<Double> getData() {
    return data;
  }

  public void setData(List<Double> data) {
    this.data = data;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public BarChartData(String label){
    this.label = label;
    data = new ArrayList<>();
  }

}
