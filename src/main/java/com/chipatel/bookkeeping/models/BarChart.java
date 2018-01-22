package com.chipatel.bookkeeping.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chiragkumar Patel (projects.cpatel@gmail.com) on 1/18/18.
 */
public class BarChart {

  private List<Double> data;
  private String label;
  private List<String> axisTitle;

  public BarChart(String label){
    this.data = new ArrayList<>();
    this.axisTitle = new ArrayList<>();
    this.label = label;
  }

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

  public List<String> getAxisTitle() {
    return axisTitle;
  }

  public void setAxisTitle(List<String> axisTitle) {
    this.axisTitle = axisTitle;
  }

  public String toString(){
    return data.toString() + label + axisTitle.toString();
  }
}
