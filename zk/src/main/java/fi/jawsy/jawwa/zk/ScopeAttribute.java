package fi.jawsy.jawwa.zk;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import org.zkoss.zk.ui.ext.Scope;

import com.google.common.base.Preconditions;

import fi.jawsy.jawwa.lang.Option;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ScopeAttribute<T> {

    public final String name;
    public final Class<T> clazz;

    public static <T> ScopeAttribute<T> create(String name, Class<T> clazz) {
        Preconditions.checkNotNull(name, "name cannot be null");
        Preconditions.checkNotNull(clazz, "class cannot be null");
        return new ScopeAttribute<T>(name, clazz);
    }

    public static <T> ScopeAttribute<T> create(Class<?> namespace, String name, Class<T> clazz) {
        Preconditions.checkNotNull(namespace, "namespace cannot be null");
        Preconditions.checkNotNull(name, "name cannot be null");
        return create(namespace.getName() + "." + name, clazz);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> ScopeAttribute<T> createUnchecked(Class<?> namespace, String name, Class clazz) {
        return create(namespace, name, clazz);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> ScopeAttribute<T> createUnchecked(String name, Class clazz) {
        return create(name, clazz);
    }

    public Option<T> getValue(Scope s) {
        Preconditions.checkNotNull(s, "scope cannot be null");
        return Option.option(clazz.cast(s.getAttribute(name)));
    }

    public void setValue(Scope s, T value) {
        Preconditions.checkNotNull(s, "scope cannot be null");
        s.setAttribute(name, value);
    }

    public void clear(Scope s) {
        Preconditions.checkNotNull(s, "scope cannot be null");
        s.removeAttribute(name);
    }

}