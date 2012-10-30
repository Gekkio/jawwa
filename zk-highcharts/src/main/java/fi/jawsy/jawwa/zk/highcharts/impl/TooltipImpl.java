package fi.jawsy.jawwa.zk.highcharts.impl;

import org.zkoss.json.JSONObject;

import com.google.common.collect.ImmutableMap;

import fi.jawsy.jawwa.zk.highcharts.Color;
import fi.jawsy.jawwa.zk.highcharts.Highcharts;
import fi.jawsy.jawwa.zk.highcharts.Highcharts.Value;

class TooltipImpl extends OptionBase implements Highcharts.Options.Tooltip {

    private Value<Boolean> animation;
    private Value<Color> backgroundColor;
    private Value<Color> borderColor;
    private Value<Integer> borderRadius;
    private Value<Integer> borderWidth;
    private Value<Boolean> enabled;
    private Value<String> footerFormat;
    private Value<Boolean> shadow;
    private Value<Boolean> shared;
    private Value<Integer> snap;
    private Value<ImmutableMap<String, String>> style;
    private Value<Boolean> useHtml;
    private Value<Integer> valueDecimals;
    private Value<String> valuePrefix;
    private Value<String> valueSuffix;

    @Override
    public Value<Boolean> animation() {
        if (animation == null)
            animation = new Value<Boolean>();
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
    public Value<Boolean> enabled() {
        if (enabled == null)
            enabled = new Value<Boolean>();
        return enabled;
    }

    @Override
    public Value<String> footerFormat() {
        if (footerFormat == null)
            footerFormat = new Value<String>();
        return footerFormat;
    }

    @Override
    public Value<Boolean> shadow() {
        if (shadow == null)
            shadow = new Value<Boolean>();
        return shadow;
    }

    @Override
    public Value<Boolean> shared() {
        if (shared == null)
            shared = new Value<Boolean>();
        return shared;
    }

    @Override
    public Value<Integer> snap() {
        if (snap == null)
            snap = new Value<Integer>();
        return snap;
    }

    @Override
    public Value<ImmutableMap<String, String>> style() {
        if (style == null)
            style = new Value<ImmutableMap<String, String>>();
        return style;
    }

    @Override
    public Value<Boolean> useHtml() {
        if (useHtml == null)
            useHtml = new Value<Boolean>();
        return useHtml;
    }

    @Override
    public Value<Integer> valueDecimals() {
        if (valueDecimals == null)
            valueDecimals = new Value<Integer>();
        return valueDecimals;
    }

    @Override
    public Value<String> valuePrefix() {
        if (valuePrefix == null)
            valuePrefix = new Value<String>();
        return valuePrefix;
    }

    @Override
    public Value<String> valueSuffix() {
        if (valueSuffix == null)
            valueSuffix = new Value<String>();
        return valueSuffix;
    }

    @Override
    protected void writeJsonOutput(JSONObject json) {
        writeValue(json, "animation", animation);
        writeValue(json, "backgroundColor", backgroundColor);
        writeValue(json, "borderColor", borderColor);
        writeValue(json, "borderRadius", borderRadius);
        writeValue(json, "borderWidth", borderWidth);
        writeValue(json, "enabled", enabled);
        writeValue(json, "footerFormat", footerFormat);
        writeValue(json, "shadow", shadow);
        writeValue(json, "shared", shared);
        writeValue(json, "snap", snap);
        writeValue(json, "style", style);
        writeValue(json, "useHtml", useHtml);
        writeValue(json, "valueDecimals", valueDecimals);
        writeValue(json, "valuePrefix", valuePrefix);
        writeValue(json, "valueSuffix", valueSuffix);
    }

}
