package fi.jawsy.jawwa.zk.highcharts;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Value;

import org.zkoss.json.JSONAware;
import org.zkoss.json.JSONObject;

public abstract class SeriesDelta<P> implements Serializable, JSONAware {

    private static final long serialVersionUID = -8424417227016428706L;

    SeriesDelta() {
    }

    @Value(staticConstructor = "create")
    @EqualsAndHashCode(callSuper = false)
    @ToString(callSuper = false)
    public static class Clear<P> extends SeriesDelta<P> {
        private static final long serialVersionUID = -1674363300672199483L;

        public final Animation animation;

        @Override
        public String toJSONString() {
            JSONObject json = new JSONObject();
            json.put("type", "clear");
            if (animation != null)
                json.put("animation", animation);
            return json.toJSONString();
        }
    }

    @Value(staticConstructor = "create")
    @EqualsAndHashCode(callSuper = false)
    @ToString(callSuper = false)
    public static class Append<P> extends SeriesDelta<P> {
        private static final long serialVersionUID = 1860281019912317401L;

        public final P point;
        public final Animation animation;

        @Override
        public String toJSONString() {
            JSONObject json = new JSONObject();
            json.put("type", "append");
            json.put("point", point);
            if (animation != null)
                json.put("animation", animation);
            return json.toJSONString();
        }
    }

    @Value(staticConstructor = "create")
    @EqualsAndHashCode(callSuper = false)
    @ToString(callSuper = false)
    public static class Add<P> extends SeriesDelta<P> {
        private static final long serialVersionUID = 10788211827953789L;

        public final int index;
        public final P point;
        public final Animation animation;

        @Override
        public String toJSONString() {
            JSONObject json = new JSONObject();
            json.put("type", "add");
            json.put("point", point);
            if (animation != null)
                json.put("animation", animation);
            return json.toJSONString();
        }
    }

    @Value(staticConstructor = "create")
    @EqualsAndHashCode(callSuper = false)
    @ToString(callSuper = false)
    public static class Replace<P> extends SeriesDelta<P> {
        private static final long serialVersionUID = 146108817213500308L;

        public final int index;
        public final P point;
        public final Animation animation;

        @Override
        public String toJSONString() {
            JSONObject json = new JSONObject();
            json.put("type", "replace");
            json.put("index", index);
            json.put("point", point);
            if (animation != null)
                json.put("animation", animation);
            return json.toJSONString();
        }
    }

    @Value(staticConstructor = "create")
    @EqualsAndHashCode(callSuper = false)
    @ToString(callSuper = false)
    public static class Remove<P> extends SeriesDelta<P> {
        private static final long serialVersionUID = 146108817213500308L;

        public final int index;
        public final Animation animation;

        @Override
        public String toJSONString() {
            JSONObject json = new JSONObject();
            json.put("type", "remove");
            json.put("index", index);
            if (animation != null)
                json.put("animation", animation);
            return json.toJSONString();
        }
    }

}
