package fi.jawsy.jawwa.zk.highcharts;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Value;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONObject;

public abstract class Animation implements Serializable, JSONAware {

    private static final long serialVersionUID = -211002701045132933L;

    public static final Animation TRUE = new True();
    public static final Animation FALSE = new False();

    Animation() {
    }

    @Value
    @EqualsAndHashCode(callSuper = false)
    @ToString(callSuper = false)
    static class True extends Animation {
        private static final long serialVersionUID = 1752973724974989156L;

        @Override
        public String toJSONString() {
            return "true";
        }
    }

    @Value
    @EqualsAndHashCode(callSuper = false)
    @ToString(callSuper = false)
    static class False extends Animation {
        private static final long serialVersionUID = 1909492551264742682L;

        @Override
        public String toJSONString() {
            return "false";
        }
    }

    @Value
    @EqualsAndHashCode(callSuper = false)
    @ToString(callSuper = false)
    static class Advanced extends Animation {
        private static final long serialVersionUID = -7433598820746913409L;

        public final int duration;
        public final String easing;

        @Override
        public String toJSONString() {
            JSONObject json = new JSONObject();
            json.put("duration", duration);
            json.put("easing", easing);
            return json.toJSONString();
        }
    }

    public static Animation advanced(int duration, String easing) {
        return new Advanced(duration, easing);
    }

}
