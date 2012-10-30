package fi.jawsy.jawwa.zk.highcharts;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONValue;

@RequiredArgsConstructor
@ToString
public enum VerticalAlign implements JSONAware {
    TOP("top"), MIDDLE("middle"), BOTTOM("bottom");

    private final String value;

    @Override
    public String toJSONString() {
        return JSONValue.toJSONString(value);
    }
}