package fi.jawsy.jawwa.zk.highcharts;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONValue;

@RequiredArgsConstructor
@ToString
public enum Align implements JSONAware {
    LEFT("left"), CENTER("center"), RIGHT("right");

    private final String value;

    @Override
    public String toJSONString() {
        return JSONValue.toJSONString(value);
    }
}