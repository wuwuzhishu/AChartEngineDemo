package com.sptpc.achartenginedemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sptpc.achartenginedemo.chart.AverageCubicTemperatureChart;
import com.sptpc.achartenginedemo.chart.AverageTemperatureChart;
import com.sptpc.achartenginedemo.chart.BudgetDoughnutChart;
import com.sptpc.achartenginedemo.chart.BudgetPieChart;
import com.sptpc.achartenginedemo.chart.CombinedTemperatureChart;
import com.sptpc.achartenginedemo.chart.IDemoChart;
import com.sptpc.achartenginedemo.chart.MultipleTemperatureChart;
import com.sptpc.achartenginedemo.activity.PieChartBuilder;
import com.sptpc.achartenginedemo.chart.ProjectStatusBubbleChart;
import com.sptpc.achartenginedemo.chart.ProjectStatusChart;
import com.sptpc.achartenginedemo.chart.SalesBarChart;
import com.sptpc.achartenginedemo.chart.SalesComparisonChart;
import com.sptpc.achartenginedemo.chart.SalesGrowthChart;
import com.sptpc.achartenginedemo.chart.SalesStackedBarChart;
import com.sptpc.achartenginedemo.chart.ScatterChart;
import com.sptpc.achartenginedemo.chart.SensorValuesChart;
import com.sptpc.achartenginedemo.chart.TemperatureChart;
import com.sptpc.achartenginedemo.chart.TrigonometricFunctionsChart;
import com.sptpc.achartenginedemo.chart.WeightDialChart;
import com.sptpc.achartenginedemo.activity.XYChartBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartDemo extends ListActivity {
    private IDemoChart[] mCharts = new IDemoChart[] { new AverageTemperatureChart(),
            new AverageCubicTemperatureChart(), new SalesStackedBarChart(), new SalesBarChart(),
            new TrigonometricFunctionsChart(), new ScatterChart(), new SalesComparisonChart(),
            new ProjectStatusChart(), new SalesGrowthChart(), new BudgetPieChart(),
            new BudgetDoughnutChart(), new ProjectStatusBubbleChart(), new TemperatureChart(),
            new WeightDialChart(), new SensorValuesChart(), new CombinedTemperatureChart(),
            new MultipleTemperatureChart() };
    private String[] mMenuText;

    private String[] mMenuSummary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        int length = mCharts.length;
        mMenuText = new String[length + 3];
        mMenuSummary = new String[length + 3];
        mMenuText[0] = "Random values charts(随机值的图表)";
        mMenuSummary[0] = "Chart demos using randomly generated values(使用随机生成值的图表演示)";
        mMenuText[1] = "Embedded line chart demo(嵌入式线形图演示)";
        mMenuSummary[1] = "A demo on how to include a clickable line chart into a graphical activity(演示如何将可单击的折线图包含到图形化活动中)";
        mMenuText[2] = "Embedded pie chart demo(嵌入式饼图演示)";
        mMenuSummary[2] = "A demo on how to include a clickable pie chart into a graphical activity(演示如何将可单击的饼图包含到图形活动中)";

        for (int i = 0; i < length; i++) {
            mMenuText[i + 3] = mCharts[i].getName();
            mMenuSummary[i + 3] = mCharts[i].getDesc();
        }

        setListAdapter(new SimpleAdapter(this, getListValues(), android.R.layout.simple_list_item_2,
                new String[] { IDemoChart.NAME, IDemoChart.DESC }, new int[] { android.R.id.text1,
                android.R.id.text2 }));
    }

    private List<Map<String, String>> getListValues() {
        List<Map<String, String>> values = new ArrayList<Map<String, String>>();
        int length = mMenuText.length;
        for (int i = 0; i < length; i++) {
            Map<String, String> v = new HashMap<String, String>();
            v.put(IDemoChart.NAME, mMenuText[i]);
            v.put(IDemoChart.DESC, mMenuSummary[i]);
            values.add(v);
        }
        return values;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = null;
        if (position == 0){
            intent = new Intent(this, GeneratedChartDemo.class);
        }else if (position == 1) {
            intent = new Intent(this, XYChartBuilder.class);
        } else if (position == 2) {
            intent = new Intent(this, PieChartBuilder.class);
        } else  {
            intent = mCharts[position - 3].execute(this);
        }
        startActivity(intent);
    }
}
