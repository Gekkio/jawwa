package fi.jawsy.jawwa.zk.highcharts;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONValue;

@RequiredArgsConstructor
@ToString
public enum AxisTitleAlign implements JSONAware {
    LOW("low"), MIDDLE("middle"), HIGH("high");

    private final String value;

    @Override
    public String toJSONString() {
        return JSONValue.toJSONString(value);
    }

}