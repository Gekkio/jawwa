package fi.jawsy.jawwa.zk.highcharts;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONValue;

@RequiredArgsConstructor
@ToString
public enum Stacking implements JSONAware {
    NORMAL("normal"), PERCENT("percent");

    private final String value;

    @Override
    public String toJSONString() {
        return JSONValue.toJSONString(value);
    }
}