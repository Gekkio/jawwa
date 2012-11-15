package fi.jawsy.jawwa.zk.highcharts.impl;

import org.zkoss.json.JSONObject;
import org.zkoss.json.JavaScriptValue;

import fi.jawsy.jawwa.zk.highcharts.Animation;
import fi.jawsy.jawwa.zk.highcharts.Color;
import fi.jawsy.jawwa.zk.highcharts.Highcharts;
import fi.jawsy.jawwa.zk.highcharts.Highcharts.Value;
import fi.jawsy.jawwa.zk.highcharts.Stacking;

public class PlotOptionsImpl extends OptionBase implements Highcharts.Options.PlotOptions {

    private Series series;

    static class SeriesImpl extends OptionBase implements Highcharts.Options.PlotOptions.Series {

        static class DataLabelsImpl extends OptionBase implements Highcharts.Options.PlotOptions.Series.DataLabels {
            private Value<JavaScriptValue> formatter;

            @Override
            public Value<JavaScriptValue> formatter() {
                if (formatter == null)
                    formatter = Value.create();
                return formatter;
            }

            @Override
            protected void writeJsonOutput(JSONObject json) {
                writeValue(json, "formatter", formatter);
            }
        }

        private Value<Boolean> allowPointSelect;
        private Value<Animation> animation;
        private Value<Color> color;
        private Value<Boolean> connectEnds;
        private Value<Boolean> connectNulls;
        private Value<Integer> cropThreshold;
        private Value<String> cursor;
        private Value<Boolean> enableMouseTracking;
        private Value<Color> fillColor;
        private Value<Double> fillOpacity;
        private Value<String> id;
        private Value<Integer> lineWidth;
        private Value<Double> pointInterval;
        private Value<Double> pointStart;
        private Value<Boolean> selected;
        private Value<Boolean> showCheckbox;
        private Value<Boolean> showInLegend;
        private Value<Stacking> stacking;
        private Value<Boolean> stickyTracking;
        private Value<Integer> turboThreshold;
        private Value<Boolean> visible;
        private Value<Integer> zIndex;

        private DataLabels dataLabels;

        @Override
        public Value<Boolean> allowPointSelect() {
            if (allowPointSelect == null)
                allowPointSelect = Value.create();
            return allowPointSelect;
        }

        @Override
        public Value<Animation> animation() {
            if (animation == null)
                animation = Value.create();
            return animation;
        }

        @Override
        public Value<Color> color() {
            if (color == null)
                color = Value.create();
            return color;
        }

        @Override
        public Value<Boolean> connectEnds() {
            if (connectEnds == null)
                connectEnds = Value.create();
            return connectEnds;
        }

        @Override
        public Value<Boolean> connectNulls() {
            if (connectNulls == null)
                connectNulls = Value.create();
            return connectNulls;
        }

        @Override
        public Value<Integer> cropThreshold() {
            if (cropThreshold == null)
                cropThreshold = Value.create();
            return cropThreshold;
        }

        @Override
        public Value<String> cursor() {
            if (cursor == null)
                cursor = Value.create();
            return cursor;
        }

        @Override
        public Value<Boolean> enableMouseTracking() {
            if (enableMouseTracking == null)
                enableMouseTracking = Value.create();
            return enableMouseTracking;
        }

        @Override
        public Value<Color> fillColor() {
            if (fillColor == null)
                fillColor = Value.create();
            return fillColor;
        }

        @Override
        public Value<Double> fillOpacity() {
            if (fillOpacity == null)
                fillOpacity = Value.create();
            return fillOpacity;
        }

        @Override
        public Value<String> id() {
            if (id == null)
                id = Value.create();
            return id;
        }

        @Override
        public Value<Integer> lineWidth() {
            if (lineWidth == null)
                lineWidth = Value.create();
            return lineWidth;
        }

        @Override
        public Value<Double> pointInterval() {
            if (pointInterval == null)
                pointInterval = Value.create();
            return pointInterval;
        }

        @Override
        public Value<Double> pointStart() {
            if (pointStart == null)
                pointStart = Value.create();
            return pointStart;
        }

        @Override
        public Value<Boolean> selected() {
            if (selected == null)
                selected = Value.create();
            return selected;
        }

        @Override
        public Value<Boolean> showCheckbox() {
            if (showCheckbox == null)
                showCheckbox = Value.create();
            return showCheckbox;
        }

        @Override
        public Value<Boolean> showInLegend() {
            if (showInLegend == null)
                showInLegend = Value.create();
            return showInLegend;
        }

        @Override
        public Value<Stacking> stacking() {
            if (stacking == null)
                stacking = Value.create();
            return stacking;
        }

        @Override
        public Value<Boolean> stickyTracking() {
            if (stickyTracking == null)
                stickyTracking = Value.create();
            return stickyTracking;
        }

        @Override
        public Value<Integer> turboThreshold() {
            if (turboThreshold == null)
                turboThreshold = Value.create();
            return turboThreshold;
        }

        @Override
        public Value<Boolean> visible() {
            if (visible == null)
                visible = Value.create();
            return visible;
        }

        @Override
        public Value<Integer> zIndex() {
            if (zIndex == null)
                zIndex = Value.create();
            return zIndex;
        }

        @Override
        public DataLabels dataLabels() {
            if (dataLabels == null)
                dataLabels = new DataLabelsImpl();
            return dataLabels;
        }

        @Override
        protected void writeJsonOutput(JSONObject json) {
            writeValue(json, "allowPointSelect", allowPointSelect);
            writeValue(json, "animation", animation);
            writeValue(json, "color", color);
            writeValue(json, "connectEnds", connectEnds);
            writeValue(json, "connectNulls", connectNulls);
            writeValue(json, "cropThreshold", cropThreshold);
            writeValue(json, "cursor", cursor);
            writeValue(json, "enableMouseTracking", enableMouseTracking);
            writeValue(json, "fillColor", fillColor);
            writeValue(json, "fillOpacity", fillOpacity);
            writeValue(json, "id", id);
            writeValue(json, "lineWidth", lineWidth);
            writeValue(json, "pointInterval", pointInterval);
            writeValue(json, "pointStart", pointStart);
            writeValue(json, "selected", selected);
            writeValue(json, "showCheckbox", showCheckbox);
            writeValue(json, "showInLegend", showInLegend);
            writeValue(json, "stacking", stacking);
            writeValue(json, "stickyTracking", stickyTracking);
            writeValue(json, "turboThreshold", turboThreshold);
            writeValue(json, "visible", visible);
            writeValue(json, "zIndex", zIndex);

            if (dataLabels != null)
                json.put("dataLabels", dataLabels);
        }

    }

    @Override
    public Series series() {
        if (series == null)
            series = new SeriesImpl();
        return series;
    }

    @Override
    protected void writeJsonOutput(JSONObject json) {
        if (series != null)
            json.put("series", series);
    }

}
