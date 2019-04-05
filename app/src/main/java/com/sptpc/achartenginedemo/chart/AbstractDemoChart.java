/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sptpc.achartenginedemo.chart;

import java.util.Date;
import java.util.List;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * An abstract class for the demo charts to extend. It contains some methods for
 * building datasets and renderers.
 */
public abstract class AbstractDemoChart implements IDemoChart {

  /**
   * Builds an XY multiple dataset using the provided values.
   * 使用提供的值构建XY多个数据集。
   *
   * @param titles the series titles
   * @param xValues the values for the X axis
   * @param yValues the values for the Y axis
   * @return the XY multiple dataset
   */
  protected XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues,
      List<double[]> yValues) {
    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();/* 创建图表数据集 */
    addXYSeries(dataset, titles, xValues, yValues, 0); /* 添加单条曲线数据到图表数据集中 */
    return dataset;
  }

  public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<double[]> xValues,
      List<double[]> yValues, int scale) {
    int length = titles.length;  /* 获取标题个数 */
    for (int i = 0; i < length; i++) {
      XYSeries series = new XYSeries(titles[i], scale);/* 单条曲线数据 */
      double[] xV = xValues.get(i);/* 获取该条曲线的x轴坐标数组 */
      double[] yV = yValues.get(i);/* 获取该条曲线的y轴坐标数组 */
      int seriesLength = xV.length;
      for (int k = 0; k < seriesLength; k++) {
        series.add(xV[k], yV[k]);/* 将该条曲线的 x,y 轴数组存放到 单条曲线数据中 */
      }
      dataset.addSeries(series);/* 将单条曲线数据存放到 图表数据集中 */
    }
  }

  /**
   * Builds an XY multiple series renderer.
   * 曲线图：构建XY曲线图渲染器
   *
   * @param colors the series rendering colors
   * @param styles the series point styles
   * @return the XY multiple series renderers
   */
  protected XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles) {
    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();/* 创建曲线图图表渲染器 */
    setRenderer(renderer, colors, styles);/* 为曲线图渲染器设置颜色和样式 */
    return renderer;
  }

  /**
   * 曲线图(渲染器 - 被调用方法) : 设置坐标轴渲染器
   * @param renderer
   * @param colors
   * @param styles
   */

  protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, PointStyle[] styles) {
    renderer.setAxisTitleTextSize(16);/* 设置XY轴标题字体大小 */
    renderer.setChartTitleTextSize(20);/* 设置图表标题文字大小 */
    renderer.setLabelsTextSize(15);/* 设置标签文字大小 */
    renderer.setLegendTextSize(15);/* 设置说明文字大小 */
    renderer.setPointSize(5f);/* 设置点的大小 */
    renderer.setMargins(new int[] { 20, 30, 15, 20 });/* 设置 margin, 单位像素 */
    int length = colors.length;/* 获取渲染器的个数, 即有多少条曲线 */
    for (int i = 0; i < length; i++) {
      XYSeriesRenderer r = new XYSeriesRenderer();/* 单个曲线的渲染器 */
      r.setColor(colors[i]);/* 为单个曲线渲染器设置曲线颜色 */
      r.setPointStyle(styles[i]);/* 为单个曲线渲染器设置曲线风格 */
      renderer.addSeriesRenderer(r);/* 将单个曲线渲染器设置到渲染器集合中 */
    }
  }

  /**
   * Sets a few of the series renderer settings.
   * 对曲线图渲染器进行配置, 主要配置坐标轴
   *
   * @param renderer the renderer to set the properties to
   * @param title the chart title
   * @param xTitle the title for the X axis
   * @param yTitle the title for the Y axis
   * @param xMin the minimum value on the X axis
   * @param xMax the maximum value on the X axis
   * @param yMin the minimum value on the Y axis
   * @param yMax the maximum value on the Y axis
   * @param axesColor the axes color
   * @param labelsColor the labels color
   */
  protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
      String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
      int labelsColor) {
    renderer.setChartTitle(title);/* 设置图表标题 */
    renderer.setXTitle(xTitle);/* 设置x轴标题 */
    renderer.setYTitle(yTitle);/* 设置y轴标题 */
    renderer.setXAxisMin(xMin);/* 设置x轴最小值 */
    renderer.setXAxisMax(xMax);/* 设置x轴最大值 */
    renderer.setYAxisMin(yMin);/* 设置y轴最小值 */
    renderer.setYAxisMax(yMax);/* 设置y轴最大值 */
    renderer.setAxesColor(axesColor);/* 设置坐标轴颜色 */
    renderer.setLabelsColor(labelsColor);/* 设置标签颜色 */
  }

  /**
   * Builds an XY multiple time dataset using the provided values.
   * 构建XY多次数据集
   *
   * @param titles the series titles
   * @param xValues the values for the X axis
   * @param yValues the values for the Y axis
   * @return the XY multiple time dataset
   */
  protected XYMultipleSeriesDataset buildDateDataset(String[] titles, List<Date[]> xValues,
      List<double[]> yValues) {
    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    int length = titles.length;
    for (int i = 0; i < length; i++) {
      TimeSeries series = new TimeSeries(titles[i]);
      Date[] xV = xValues.get(i);
      double[] yV = yValues.get(i);
      int seriesLength = xV.length;
      for (int k = 0; k < seriesLength; k++) {
        series.add(xV[k], yV[k]);
      }
      dataset.addSeries(series);
    }
    return dataset;
  }

  /**
   * Builds a category series using the provided values.
   * 使用提供的值构建饼图数据集
   *
   * @param title the series titles
   * @param values the values
   * @return the category series
   */
  protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);/* 创建单个饼状图数据, 传入饼状图标题 */
    int k = 0;
    for (double value : values) {
      series.add("Project " + ++k, value);/* 键值对, 键是饼图元素的标题, 值是大小 */
    }

    return series;
  }

  /**
   * Builds a multiple category series using the provided values.
   * 使用提供的值构建多饼图数据集
   * @param titles the series titles
   * @param values the values
   * @return the category series
   */
  protected MultipleCategorySeries buildMultipleCategoryDataset(String title,
      List<String[]> titles, List<double[]> values) {
    MultipleCategorySeries series = new MultipleCategorySeries(title);/* 多个饼图组成的图表 */
    int k = 0;
    for (double[] value : values) {
      series.add(2007 + k + "", titles.get(k), value);
      k++;
    }
    return series;
  }

  /**
   * Builds a category renderer to use the provided colors.
   * 使用提供的值构建一个饼图渲染器
   *
   * @param colors the colors
   * @return the category renderer
   */
  protected DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();/* 默认的饼图图表渲染器 */
    renderer.setLabelsTextSize(15);/* 设置标签文字大小 */
    renderer.setLegendTextSize(15);/* 设置说明文字大小 */
    renderer.setMargins(new int[] { 20, 30, 15, 0 });/* 设置边距 */
    for (int color : colors) {
      SimpleSeriesRenderer r = new SimpleSeriesRenderer();/* 饼状图中单个数据的颜色渲染器 */
      r.setColor(color);/*设置颜色*/
      renderer.addSeriesRenderer(r);/* 将单个元素渲染器设置到饼图图表渲染器中 */
    }
    return renderer;
  }

  /**
   * Builds a bar multiple series dataset using the provided values.
   * 使用提供的值构建一个柱状图数据集
   *
   * @param titles the series titles
   * @param values the values
   * @return the XY multiple bar dataset
   */
  protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();/* 创建 XYMultipleSeriesDataset对象, 图表的总数据集 */
    int length = titles.length;/* 获取图表中柱状图个数 */
    for (int i = 0; i < length; i++) {
      CategorySeries series = new CategorySeries(titles[i]);/* 创建一个 CategorySeries对象, 单个柱状图数据 */
      double[] v = values.get(i);/* 获取本柱状图数值数组 */
      int seriesLength = v.length;/* 获取单个柱状图值的个数 */
      for (int k = 0; k < seriesLength; k++) {
        series.add(v[k]);/* 将具体的值设置给 CategorySeries对象, 单个柱状图数据 */
      }
      dataset.addSeries(series.toXYSeries());/* 将单个柱状图数据CategorySeries对象设置给图表数据集XYMultipleSeriesDataset对象 */
    }
    return dataset;
  }

  /**
   * Builds a bar multiple series renderer to use the provided colors.
   * 使用提供的值构建一个柱状图渲染器
   *
   * @param colors the series renderers colors
   * @return the bar multiple series renderer
   */
  protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
    XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();/* 创建图表渲染器 */
    renderer.setAxisTitleTextSize(16);/* 设置坐标轴标题字体大小 */
    renderer.setChartTitleTextSize(20);/* 设置图表标题字体大小 */
    renderer.setLabelsTextSize(15);/* 设置标签字体大小 */
    renderer.setLegendTextSize(15);/* 设置说明文字字体大小 */
    int length = colors.length;
    for (int i = 0; i < length; i++) {
      SimpleSeriesRenderer r = new SimpleSeriesRenderer();/* 单个柱状图渲染器 */
      r.setColor(colors[i]);/* 为单个柱状图渲染器设置颜色 */
      renderer.addSeriesRenderer(r);/* 将单个柱状图渲染器设置给图表渲染器 */
    }
    return renderer;
  }

}
