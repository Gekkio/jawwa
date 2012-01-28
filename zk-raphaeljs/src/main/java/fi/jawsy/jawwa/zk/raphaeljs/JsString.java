package fi.jawsy.jawwa.zk.raphaeljs;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public final class JsString implements JsExpression {

    private final String value;

    @Override
    public String print() {
        return JsExp.escape(value);
    }

}
