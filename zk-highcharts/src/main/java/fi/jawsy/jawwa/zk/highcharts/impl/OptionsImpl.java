package fi.jawsy.jawwa.zk.highcharts.impl;

import java.util.ArrayList;

import org.zkoss.json.JSONObject;

import com.google.common.collect.Lists;

import fi.jawsy.jawwa.zk.highcharts.Highcharts;

class OptionsImpl extends OptionBase implements Highcharts.Options {

    private Chart chart;
    private Credits credits;
    private Legend legend;
    private PlotOptions plotOptions;
    private ArrayList<Series> series = Lists.newArrayList();
    private Subtitle subtitle;
    private Title title;
    private Tooltip tooltip;
    private Axis xAxis;
    private Axis yAxis;

    @Override
    public Chart chart() {
        if (chart == null)
            chart = new ChartImpl();
        return chart;
    }

    @Override
    public Credits credits() {
        if (credits == null)
            credits = new CreditsImpl();
        return credits;
    }

    @Override
    public Legend legend() {
        if (legend == null)
            legend = new LegendImpl();
        return legend;
    }

    @Override
    public PlotOptions plotOptions() {
        if (plotOptions == null)
            plotOptions = new PlotOptionsImpl();
        return plotOptions;
    }

    @Override
    public Series series(int index) {
        if (series.size() > index) {
            Series result = series.get(index);
            if (result == null) {
                result = new SeriesImpl();
                series.set(index, result);
            }
            return result;
        }
        SeriesImpl newSeries = new SeriesImpl();
        for (int i = series.size(); i < index; i++) {
            series.add(null);
        }
        series.add(newSeries);
        return newSeries;
    }

    @Override
    public Subtitle subtitle() {
        if (subtitle == null)
            subtitle = new SubtitleImpl();
        return subtitle;
    }

    @Override
    public Title title() {
        if (title == null)
            title = new TitleImpl();
        return title;
    }

    @Override
    public Tooltip tooltip() {
        if (tooltip == null)
            tooltip = new TooltipImpl();
        return tooltip;
    }

    @Override
    public Axis xAxis() {
        if (xAxis == null)
            xAxis = new AxisImpl();
        return xAxis;
    }

    @Override
    public Axis yAxis() {
        if (yAxis == null)
            yAxis = new AxisImpl();
        return yAxis;
    }

    @Override
    public void writeJsonOutput(JSONObject json) {
        if (chart != null)
            json.put("chart", chart);
        if (credits != null)
            json.put("credits", credits);
        if (legend != null)
            json.put("legend", legend);
        if (plotOptions != null)
            json.put("plotOptions", plotOptions);
        if (!series.isEmpty())
            json.put("series", series);
        if (subtitle != null)
            json.put("subtitle", subtitle);
        if (title != null)
            json.put("title", title);
        if (tooltip != null)
            json.put("tooltip", tooltip);
        if (xAxis != null)
            json.put("xAxis", xAxis);
        if (yAxis != null)
            json.put("yAxis", yAxis);
    }

}
