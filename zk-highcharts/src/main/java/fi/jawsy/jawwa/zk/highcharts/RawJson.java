package fi.jawsy.jawwa.zk.highcharts;

import java.util.Map;

import org.zkoss.json.JSONAware;

public interface RawJson {
    Object get(String key);

    void putAll(Map<String, ?> values);

    void put(String key, JSONAware value);

    void put(String key, int value);

    void put(String key, double value);

    void put(String key, String value);

    void put(String key, Map<String, ?> values);
}