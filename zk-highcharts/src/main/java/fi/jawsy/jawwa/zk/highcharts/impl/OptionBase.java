package fi.jawsy.jawwa.zk.highcharts.impl;

import java.util.Map;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONObject;

import fi.jawsy.jawwa.zk.highcharts.Highcharts.Value;
import fi.jawsy.jawwa.zk.highcharts.RawJson;
import fi.jawsy.jawwa.zk.highcharts.RawJsonSupport;

abstract class OptionBase implements RawJson, RawJsonSupport, JSONAware {

    protected final JSONObject rawJson = new JSONObject();

    @Override
    public Object get(String key) {
        return rawJson.get(key);
    }

    @Override
    public void put(String key, JSONAware value) {
        rawJson.put(key, value);
    }

    @Override
    public void put(String key, int value) {
        rawJson.put(key, value);
    }

    @Override
    public void put(String key, double value) {
        rawJson.put(key, value);
    }

    @Override
    public void put(String key, String value) {
        rawJson.put(key, value);
    }

    @Override
    public void put(String key, Map<String, ?> values) {
        JSONObject jsonMap = new JSONObject();
        jsonMap.putAll(values);
        rawJson.put(key, jsonMap);
    }

    @Override
    public void putAll(Map<String, ?> values) {
        rawJson.putAll(values);
    }

    @Override
    public RawJson raw() {
        return this;
    }

    protected void writeValue(JSONObject json, String name, Value<?> value) {
        if (value != null && value.exists())
            json.put(name, value.get());
    }

    protected abstract void writeJsonOutput(JSONObject json);

    @Override
    public String toJSONString() {
        JSONObject json = new JSONObject();
        writeJsonOutput(json);
        json.putAll(rawJson);
        return json.toJSONString();
    }

    @Override
    public String toString() {
        return toJSONString();
    }

}
