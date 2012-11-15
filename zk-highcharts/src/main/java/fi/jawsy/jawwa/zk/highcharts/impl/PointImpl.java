package fi.jawsy.jawwa.zk.highcharts.impl;

import org.zkoss.json.JSONObject;

import fi.jawsy.jawwa.zk.highcharts.Highcharts.Value;
import fi.jawsy.jawwa.zk.highcharts.PointSeries.Point;

public class PointImpl extends OptionBase implements Point {

    private Value<String> name;
    private Value<Double> x;
    private Value<Double> y;

    @Override
    public Value<String> name() {
        if (name == null)
            name = Value.create();
        return name;
    }

    @Override
    public Value<Double> x() {
        if (x == null)
            x = Value.create();
        return x;
    }

    @Override
    public Value<Double> y() {
        if (y == null)
            y = Value.create();
        return y;
    }

    @Override
    protected void writeJsonOutput(JSONObject json) {
        writeValue(json, "name", name);
        writeValue(json, "x", x);
        writeValue(json, "y", y);
    }

}
