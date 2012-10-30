package fi.jawsy.jawwa.zk.highcharts;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.val;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONValue;

import com.google.common.base.Preconditions;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class Color implements JSONAware, Serializable {
    private static final long serialVersionUID = -2202301087881431129L;

    public final String value;

    public static Color html(String value) {
        Preconditions.checkNotNull(value, "value cannot be null");
        Preconditions.checkArgument(value.length() >= 4 && value.charAt(0) == '#', "'%s' is not a valid html color", value);
        return new Color(value);
    }

    public static Color rgb(int r, int g, int b) {
        Preconditions.checkArgument(0 <= r && r < 256, "r must be between 0-255");
        Preconditions.checkArgument(0 <= g && g < 256, "g must be between 0-255");
        Preconditions.checkArgument(0 <= b && b < 256, "b must be between 0-255");

        val sb = new StringBuilder();
        sb.append("rgb(");
        sb.append(r);
        sb.append(',');
        sb.append(g);
        sb.append(',');
        sb.append(b);
        sb.append(')');
        return new Color(sb.toString());
    }

    public static Color rgba(int r, int g, int b, double a) {
        Preconditions.checkArgument(0 <= r && r < 256, "r must be between 0-255");
        Preconditions.checkArgument(0 <= g && g < 256, "g must be between 0-255");
        Preconditions.checkArgument(0 <= b && b < 256, "b must be between 0-255");
        Preconditions.checkArgument(0 <= a && a <= 1.0, "a must be between 0.0-1.0");

        val sb = new StringBuilder();
        sb.append("rgba(");
        sb.append(r);
        sb.append(',');
        sb.append(g);
        sb.append(',');
        sb.append(b);
        sb.append(',');
        sb.append(a);
        sb.append(')');
        return new Color(sb.toString());
    }

    @Override
    public String toJSONString() {
        return JSONValue.toJSONString(value);
    }
}