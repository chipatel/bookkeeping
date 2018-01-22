package com.chipatel.bookkeeping.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Chiragkumar Patel (projects.cpatel@gmail.com) on 1/18/18.
 */
public class BarChart {

  private List<BarChartData> chartData;
  private List<String> axisLabel;

  public BarChart(List<BarChartData> chartData, List<String> axisLabel){
    this.chartData = chartData;
    this.axisLabel = axisLabel;
  }

  public List<BarChartData> getChartData() {
    return chartData;
  }

  public void setChartData(List<BarChartData> chartData) {
    this.chartData = chartData;
  }

  public List<String> getAxisLabel() {
    return axisLabel;
  }

  public void setAxisLabel(List<String> axisLabel) {
    this.axisLabel = axisLabel;
  }
}
