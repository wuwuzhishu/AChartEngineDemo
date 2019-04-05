# AChartEngineDemo

> AChartEngine图表引擎的demo

## 说明

1. 核心类：抽象类AbstractDemoChart封装了AChartEngine的数据集及渲染器
2. 此demo中演示的图表类型：
	* line chart (折线图)
	* area chart (面积图）
	* scatter chart ( 散点图)
	* time chart (时间图）
	* bar chart (条形图;柱状图)
	* pie chart (饼图)
	* bubble chart (气泡图)
	* doughnut chart (圆环图)
	* range (high-low) bar chart (范围条形图)
	* combined (any combination of line, cubic line, scatter, bar, range bar, bubble) chart(组合图)
	* cubic line chart (立方折线图)
3. 根据不同的图片类型，调用不同的方法：
```java
//不同的图表调用ChartFactory的所对应的不同方法
ChartFactory.getCubicLineChartIntent();
```
4. 包chart里面是不同图表类型的类，根据自己的需要来选取所需要用到的类
5. 包activity里面XYChartBuilder是折线图的示例，PieChartBuilder是饼图的示例
6. GeneratedChartDemo类是产生随机值的图表，包含了折线图、散点图、时间图和柱状图，可以通过这个类来入门学习
