package fi.jawsy.jawwa.zk.highcharts.impl;

import org.zkoss.json.JSONObject;

import com.google.common.collect.ImmutableMap;

import fi.jawsy.jawwa.zk.highcharts.Align;
import fi.jawsy.jawwa.zk.highcharts.Color;
import fi.jawsy.jawwa.zk.highcharts.Highcharts;
import fi.jawsy.jawwa.zk.highcharts.Highcharts.Value;
import fi.jawsy.jawwa.zk.highcharts.VerticalAlign;

class LegendImpl extends OptionBase implements Highcharts.Options.Legend {

    private Value<Align> align;
    private Value<Color> backgroundColor;
    private Value<Boolean> enabled;
    private Value<Boolean> floating;
    private Value<ImmutableMap<String, String>> itemStyle;
    private Value<VerticalAlign> verticalAlign;

    @Override
    public Value<Align> align() {
        if (align == null)
            align = new Value<Align>();
        return align;
    }

    @Override
    public Value<Color> backgroundColor() {
        if (backgroundColor == null)
            backgroundColor = new Value<Color>();
        return backgroundColor;
    }

    @Override
    public Value<Boolean> enabled() {
        if (enabled == null)
            enabled = new Value<Boolean>();
        return enabled;
    }

    @Override
    public Value<Boolean> floating() {
        if (floating == null)
            floating = new Value<Boolean>();
        return floating;
    }

    @Override
    public Value<ImmutableMap<String, String>> itemStyle() {
        if (itemStyle == null)
            itemStyle = new Value<ImmutableMap<String, String>>();
        return itemStyle;
    }

    @Override
    public Value<VerticalAlign> verticalAlign() {
        if (verticalAlign == null)
            verticalAlign = new Value<VerticalAlign>();
        return verticalAlign;
    }

    @Override
    public void writeJsonOutput(JSONObject json) {
        writeValue(json, "align", align);
        writeValue(json, "backgroundColor", backgroundColor);
        writeValue(json, "enabled", enabled);
        writeValue(json, "floating", floating);
        writeValue(json, "itemStyle", itemStyle);
        writeValue(json, "verticalAlign", verticalAlign);
    }

}
