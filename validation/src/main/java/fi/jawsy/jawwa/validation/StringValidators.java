package fi.jawsy.jawwa.validation;

import java.io.Serializable;
import java.util.regex.Pattern;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public final class StringValidators {

    /**
     * http://fightingforalostcause.net/misc/2006/compare-email-regex.php
     */
    private static final Pattern REGEX_EMAIL = Pattern
            .compile(
                    "^([\\w\\!\\#$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\`{\\|\\}\\~]+\\.)*[\\w\\!\\#$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\`{\\|\\}\\~]+@((((([a-z0-9]{1}[a-z0-9\\-]{0,62}[a-z0-9]{1})|[a-z])\\.)+[a-z]{2,6})|(\\d{1,3}\\.){3}\\d{1,3}(\\:\\d{1,5})?)$",
                    Pattern.CASE_INSENSITIVE);

    private StringValidators() {
    }

    public static <E> Validator<E, String> isNotEmpty(final E error) {
        class NotEmptyValidator extends ValidatorBase<E, String> implements Serializable {
            private static final long serialVersionUID = 7808848560023135197L;

            @Override
            public void validate(String object, ImmutableList.Builder<E> errors) {
                if (Strings.isNullOrEmpty(object))
                    errors.add(error);
            }

        }
        return new NotEmptyValidator();
    }

    public static <E> Validator<E, String> matchesRegex(final Pattern regex, final E error) {
        class RegexValidator extends ValidatorBase<E, String> implements Serializable {
            private static final long serialVersionUID = 1643367630540281218L;

            @Override
            public void validate(String object, Builder<E> errors) {
                if (!regex.matcher(object).matches())
                    errors.add(error);
            }
        }
        return new RegexValidator();
    }

    public static <E> Validator<E, String> isValidEmail(final E error) {
        return matchesRegex(REGEX_EMAIL, error);
    }

}
