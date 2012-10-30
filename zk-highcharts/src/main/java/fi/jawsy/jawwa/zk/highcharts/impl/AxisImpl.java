package fi.jawsy.jawwa.zk.highcharts.impl;

import org.zkoss.json.JSONObject;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import fi.jawsy.jawwa.zk.highcharts.Align;
import fi.jawsy.jawwa.zk.highcharts.AxisTitleAlign;
import fi.jawsy.jawwa.zk.highcharts.Color;
import fi.jawsy.jawwa.zk.highcharts.Highcharts;
import fi.jawsy.jawwa.zk.highcharts.Highcharts.Value;

class AxisImpl extends OptionBase implements Highcharts.Options.Axis {

    private Value<Boolean> allowDecimals;
    private Value<Color> alternateGridColor;
    private Value<Boolean> endOnTick;
    private Value<ImmutableList<String>> categories;
    private Value<Color> gridLineColor;
    private Value<Double> max;
    private Value<Double> maxPadding;
    private Value<Double> min;
    private Value<Double> minPadding;
    private Value<Double> minRange;
    private Value<Double> minTickInterval;
    private Value<Color> minorGridLineColor;
    private Value<Integer> minorGridLineWidth;
    private Value<Color> minorTickColor;
    private Value<Integer> minorTickLength;
    private Value<Integer> minorTickWidth;
    private Value<Integer> offset;
    private Value<Boolean> opposite;
    private Value<Boolean> reversed;
    private Value<Boolean> showEmpty;
    private Value<Boolean> showFirstLabel;
    private Value<Boolean> showLastLabel;
    private Value<Integer> startOfWeek;
    private Value<Boolean> startOnTick;
    private Value<Color> tickColor;
    private Value<Integer> tickLength;
    private Value<Integer> tickPixelInterval;
    private Value<Integer> tickWidth;

    private Labels labels;
    private Title title;

    static class LabelsImpl extends OptionBase implements Highcharts.Options.Axis.Labels {

        private Value<Align> align;
        private Value<Boolean> enabled;
        private Value<Double> rotation;
        private Value<ImmutableMap<String, String>> style;
        private Value<Integer> x;
        private Value<Integer> y;

        @Override
        public Value<Align> align() {
            if (align == null)
                align = new Value<Align>();
            return align;
        }

        @Override
        public Value<Boolean> enabled() {
            if (enabled == null)
                enabled = new Value<Boolean>();
            return enabled;
        }

        @Override
        public Value<Double> rotation() {
            if (rotation == null)
                rotation = new Value<Double>();
            return rotation;
        }

        @Override
        public Value<ImmutableMap<String, String>> style() {
            if (style == null)
                style = new Value<ImmutableMap<String, String>>();
            return style;
        }

        @Override
        public Value<Integer> x() {
            if (x == null)
                x = new Value<Integer>();
            return x;
        }

        @Override
        public Value<Integer> y() {
            if (y == null)
                y = new Value<Integer>();
            return y;
        }

        @Override
        protected void writeJsonOutput(JSONObject json) {
            writeValue(json, "align", align);
            writeValue(json, "enabled", enabled);
            writeValue(json, "rotation", rotation);
            writeValue(json, "style", style);
            writeValue(json, "x", x);
            writeValue(json, "y", y);
        }

    }

    static class TitleImpl extends OptionBase implements Highcharts.Options.Axis.Title {

        private Value<AxisTitleAlign> align;
        private Value<Integer> margin;
        private Value<Double> rotation;
        private Value<ImmutableMap<String, String>> style;
        private Value<String> text;

        @Override
        public Value<AxisTitleAlign> align() {
            if (align == null)
                align = new Value<AxisTitleAlign>();
            return align;
        }

        @Override
        public Value<Integer> margin() {
            if (margin == null)
                margin = new Value<Integer>();
            return margin;
        }

        @Override
        public Value<Double> rotation() {
            if (rotation == null)
                rotation = new Value<Double>();
            return rotation;
        }

        @Override
        public Value<ImmutableMap<String, String>> style() {
            if (style == null)
                style = new Value<ImmutableMap<String, String>>();
            return style;
        }

        @Override
        public Value<String> text() {
            if (text == null)
                text = new Value<String>();
            return text;
        }

        @Override
        protected void writeJsonOutput(JSONObject json) {
            writeValue(json, "align", align);
            writeValue(json, "margin", margin);
            writeValue(json, "rotation", rotation);
            writeValue(json, "style", style);
            writeValue(json, "text", text);
        }

    }

    @Override
    public Value<Boolean> allowDecimals() {
        if (allowDecimals == null)
            allowDecimals = new Value<Boolean>();
        return allowDecimals;
    }

    @Override
    public Value<Color> alternateGridColor() {
        if (alternateGridColor == null)
            alternateGridColor = new Value<Color>();
        return alternateGridColor;
    }

    @Override
    public Value<ImmutableList<String>> categories() {
        if (categories == null)
            categories = new Value<ImmutableList<String>>();
        return categories;
    }

    @Override
    public Value<Boolean> endOnTick() {
        if (endOnTick == null)
            endOnTick = new Value<Boolean>();
        return endOnTick;
    }

    @Override
    public Value<Color> gridLineColor() {
        if (gridLineColor == null)
            gridLineColor = new Value<Color>();
        return gridLineColor;
    }

    @Override
    public Value<Double> max() {
        if (max == null)
            max = new Value<Double>();
        return max;
    }

    @Override
    public Value<Double> maxPadding() {
        if (maxPadding == null)
            maxPadding = new Value<Double>();
        return maxPadding;
    }

    @Override
    public Value<Double> min() {
        if (min == null)
            min = new Value<Double>();
        return min;
    }

    @Override
    public Value<Double> minPadding() {
        if (minPadding == null)
            minPadding = new Value<Double>();
        return minPadding;
    }

    @Override
    public Value<Double> minRange() {
        if (minRange == null)
            minRange = new Value<Double>();
        return minRange;
    }

    @Override
    public Value<Double> minTickInterval() {
        if (minTickInterval == null)
            minTickInterval = new Value<Double>();
        return minTickInterval;
    }

    @Override
    public Value<Color> minorGridLineColor() {
        if (minorGridLineColor == null)
            minorGridLineColor = new Value<Color>();
        return minorGridLineColor;
    }

    @Override
    public Value<Integer> minorGridLineWidth() {
        if (minorGridLineWidth == null)
            minorGridLineWidth = new Value<Integer>();
        return minorGridLineWidth;
    }

    @Override
    public Value<Color> minorTickColor() {
        if (minorTickColor == null)
            minorTickColor = new Value<Color>();
        return minorTickColor;
    }

    @Override
    public Value<Integer> minorTickLength() {
        if (minorTickLength == null)
            minorTickLength = new Value<Integer>();
        return minorTickLength;
    }

    @Override
    public Value<Integer> minorTickWidth() {
        if (minorTickWidth == null)
            minorTickWidth = new Value<Integer>();
        return minorTickWidth;
    }

    @Override
    public Value<Integer> offset() {
        if (offset == null)
            offset = new Value<Integer>();
        return offset;
    }

    @Override
    public Value<Boolean> opposite() {
        if (opposite == null)
            opposite = new Value<Boolean>();
        return opposite;
    }

    @Override
    public Value<Boolean> reversed() {
        if (reversed == null)
            reversed = new Value<Boolean>();
        return reversed;
    }

    @Override
    public Value<Boolean> showEmpty() {
        if (showEmpty == null)
            showEmpty = new Value<Boolean>();
        return showEmpty;
    }

    @Override
    public Value<Boolean> showFirstLabel() {
        if (showFirstLabel == null)
            showFirstLabel = new Value<Boolean>();
        return showFirstLabel;
    }

    @Override
    public Value<Boolean> showLastLabel() {
        if (showLastLabel == null)
            showLastLabel = new Value<Boolean>();
        return showLastLabel;
    }

    @Override
    public Value<Integer> startOfWeek() {
        if (startOfWeek == null)
            startOfWeek = new Value<Integer>();
        return startOfWeek;
    }

    @Override
    public Value<Boolean> startOnTick() {
        if (startOnTick == null)
            startOnTick = new Value<Boolean>();
        return startOnTick;
    }

    @Override
    public Value<Color> tickColor() {
        if (tickColor == null)
            tickColor = new Value<Color>();
        return tickColor;
    }

    @Override
    public Value<Integer> tickLength() {
        if (tickLength == null)
            tickLength = new Value<Integer>();
        return tickLength;
    }

    @Override
    public Value<Integer> tickPixelInterval() {
        if (tickPixelInterval == null)
            tickPixelInterval = new Value<Integer>();
        return tickPixelInterval;
    }

    @Override
    public Value<Integer> tickWidth() {
        if (tickWidth == null)
            tickWidth = new Value<Integer>();
        return tickWidth;
    }

    @Override
    public Labels labels() {
        if (labels == null)
            labels = new LabelsImpl();
        return labels;
    }

    @Override
    public Title title() {
        if (title == null)
            title = new TitleImpl();
        return title;
    }

    @Override
    protected void writeJsonOutput(JSONObject json) {
        writeValue(json, "allowDecimals", allowDecimals);
        writeValue(json, "alternateGridColor", alternateGridColor);
        writeValue(json, "endOnTick", endOnTick);
        writeValue(json, "categories", categories);
        writeValue(json, "gridLineColor", gridLineColor);
        writeValue(json, "max", max);
        writeValue(json, "maxPadding", maxPadding);
        writeValue(json, "min", min);
        writeValue(json, "minPadding", minPadding);
        writeValue(json, "minRange", minRange);
        writeValue(json, "minTickInterval", minTickInterval);
        writeValue(json, "minorGridLineColor", minorGridLineColor);
        writeValue(json, "minorGridLineWidth", minorGridLineWidth);
        writeValue(json, "minorTickColor", minorTickColor);
        writeValue(json, "minorTickLength", minorTickLength);
        writeValue(json, "minorTickWidth", minorTickWidth);
        writeValue(json, "offset", offset);
        writeValue(json, "opposite", opposite);
        writeValue(json, "reversed", reversed);
        writeValue(json, "showEmpty", showEmpty);
        writeValue(json, "showFirstLabel", showFirstLabel);
        writeValue(json, "showLastLabel", showLastLabel);
        writeValue(json, "startOfWeek", startOfWeek);
        writeValue(json, "startOnTick", startOnTick);
        writeValue(json, "tickColor", tickColor);
        writeValue(json, "tickLength", tickLength);
        writeValue(json, "tickPixelInterval", tickPixelInterval);
        writeValue(json, "tickWidth", tickWidth);

        if (labels != null)
            json.put("labels", labels);
        if (title != null)
            json.put("title", title);
    }

}
