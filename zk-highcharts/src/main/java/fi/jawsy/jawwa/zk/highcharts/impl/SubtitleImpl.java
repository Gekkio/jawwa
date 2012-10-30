package fi.jawsy.jawwa.zk.highcharts.impl;

import org.zkoss.json.JSONObject;

import com.google.common.collect.ImmutableMap;

import fi.jawsy.jawwa.zk.highcharts.Align;
import fi.jawsy.jawwa.zk.highcharts.Highcharts;
import fi.jawsy.jawwa.zk.highcharts.Highcharts.Value;
import fi.jawsy.jawwa.zk.highcharts.VerticalAlign;

class SubtitleImpl extends OptionBase implements Highcharts.Options.Subtitle {

    private Value<Align> align;
    private Value<Boolean> floating;
    private Value<ImmutableMap<String, String>> style;
    private Value<String> text;
    private Value<VerticalAlign> verticalAlign;
    private Value<Integer> x;
    private Value<Integer> y;

    @Override
    public Value<Align> align() {
        if (align == null)
            align = new Value<Align>();
        return align;
    }

    @Override
    public Value<Boolean> floating() {
        if (floating == null)
            floating = new Value<Boolean>();
        return floating;
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
    public Value<VerticalAlign> verticalAlign() {
        if (verticalAlign == null)
            verticalAlign = new Value<VerticalAlign>();
        return verticalAlign;
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
        writeValue(json, "floating", floating);
        writeValue(json, "style", style);
        writeValue(json, "text", text);
        writeValue(json, "verticalAlign", verticalAlign);
        writeValue(json, "x", x);
        writeValue(json, "y", y);
    }

}
