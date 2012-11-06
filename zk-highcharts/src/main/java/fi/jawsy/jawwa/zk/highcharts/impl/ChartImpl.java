package fi.jawsy.jawwa.zk.highcharts.impl;

import org.zkoss.json.JSONObject;

import com.google.common.collect.ImmutableMap;

import fi.jawsy.jawwa.zk.highcharts.Animation;
import fi.jawsy.jawwa.zk.highcharts.Color;
import fi.jawsy.jawwa.zk.highcharts.Highcharts;
import fi.jawsy.jawwa.zk.highcharts.Highcharts.Value;

class ChartImpl extends OptionBase implements Highcharts.Options.Chart {

    private Value<Boolean> alignTicks;
    private Value<Animation> animation;
    private Value<Color> backgroundColor;
    private Value<Color> borderColor;
    private Value<Integer> borderRadius;
    private Value<Integer> borderWidth;
    private Value<Boolean> ignoreHiddenSeries;
    private Value<Boolean> inverted;
    private Value<Color> plotBackgroundColor;
    private Value<Color> plotBorderColor;
    private Value<Integer> plotBorderWidth;
    private Value<Color> selectionMarkerFill;
    private Value<Boolean> showAxes;
    private Value<Integer> spacingBottom;
    private Value<Integer> spacingLeft;
    private Value<Integer> spacingRight;
    private Value<Integer> spacingTop;
    private Value<ImmutableMap<String, String>> style;

    @Override
    public Value<Boolean> alignTicks() {
        if (alignTicks == null)
            alignTicks = new Value<Boolean>();
        return alignTicks;
    }

    @Override
    public Value<Animation> animation() {
        if (animation == null)
            animation = new Value<Animation>();
        return animation;
    }

    @Override
    public Value<Color> backgroundColor() {
        if (backgroundColor == null)
            backgroundColor = new Value<Color>();
        return backgroundColor;
    }

    @Override
    public Value<Color> borderColor() {
        if (borderColor == null)
            borderColor = new Value<Color>();
        return borderColor;
    }

    @Override
    public Value<Integer> borderRadius() {
        if (borderRadius == null)
            borderRadius = new Value<Integer>();
        return borderRadius;
    }

    @Override
    public Value<Integer> borderWidth() {
        if (borderWidth == null)
            borderWidth = new Value<Integer>();
        return borderWidth;
    }

    @Override
    public Value<Boolean> ignoreHiddenSeries() {
        if (ignoreHiddenSeries == null)
            ignoreHiddenSeries = new Value<Boolean>();
        return ignoreHiddenSeries;
    }

    @Override
    public Value<Boolean> inverted() {
        if (inverted == null)
            inverted = new Value<Boolean>();
        return inverted;
    }

    @Override
    public Value<Color> plotBackgroundColor() {
        if (plotBackgroundColor == null)
            plotBackgroundColor = new Value<Color>();
        return plotBackgroundColor;
    }

    @Override
    public Value<Color> plotBorderColor() {
        if (plotBorderColor == null)
            plotBorderColor = new Value<Color>();
        return plotBorderColor;
    }

    @Override
    public Value<Integer> plotBorderWidth() {
        if (plotBorderWidth == null)
            plotBorderWidth = new Value<Integer>();
        return plotBorderWidth;
    }

    @Override
    public Value<Color> selectionMarkerFill() {
        if (selectionMarkerFill == null)
            selectionMarkerFill = new Value<Color>();
        return selectionMarkerFill;
    }

    @Override
    public Value<Boolean> showAxes() {
        if (showAxes == null)
            showAxes = new Value<Boolean>();
        return showAxes;
    }

    @Override
    public Value<Integer> spacingBottom() {
        if (spacingBottom == null)
            spacingBottom = new Value<Integer>();
        return spacingBottom;
    }

    @Override
    public Value<Integer> spacingLeft() {
        if (spacingLeft == null)
            spacingLeft = new Value<Integer>();
        return spacingLeft;
    }

    @Override
    public Value<Integer> spacingRight() {
        if (spacingRight == null)
            spacingRight = new Value<Integer>();
        return spacingRight;
    }

    @Override
    public Value<Integer> spacingTop() {
        if (spacingTop == null)
            spacingTop = new Value<Integer>();
        return spacingTop;
    }

    @Override
    public Value<ImmutableMap<String, String>> style() {
        if (style == null)
            style = new Value<ImmutableMap<String, String>>();
        return style;
    }

    @Override
    protected void writeJsonOutput(JSONObject json) {
        writeValue(json, "alignTicks", alignTicks);
        writeValue(json, "animation", animation);
        writeValue(json, "backgroundColor", backgroundColor);
        writeValue(json, "borderColor", borderColor);
        writeValue(json, "borderRadius", borderRadius);
        writeValue(json, "borderWidth", borderWidth);
        writeValue(json, "ignoreHiddenSeries", ignoreHiddenSeries);
        writeValue(json, "inverted", inverted);
        writeValue(json, "plotBackgroundColor", plotBackgroundColor);
        writeValue(json, "plotBorderColor", plotBorderColor);
        writeValue(json, "plotBorderWidth", plotBorderWidth);
        writeValue(json, "selectionMarkerFill", selectionMarkerFill);
        writeValue(json, "showAxes", showAxes);
        writeValue(json, "spacingBottom", spacingBottom);
        writeValue(json, "spacingLeft", spacingLeft);
        writeValue(json, "spacingRight", spacingRight);
        writeValue(json, "spacingTop", spacingTop);
        writeValue(json, "style", style);
    }

}
