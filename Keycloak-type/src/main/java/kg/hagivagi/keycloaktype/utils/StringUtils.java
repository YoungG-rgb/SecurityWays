package kg.hagivagi.keycloaktype.utils;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.stream.Collectors;

@UtilityClass
public class StringUtils {

    public static boolean isEmpty(final CharSequence charSequence) {
        return charSequence == null || charSequence.isEmpty();
    }

    public static boolean isNotEmpty(CharSequence... charSequences) {
        return Arrays.stream(charSequences).noneMatch(StringUtils::isEmpty);
    }

    public static String joinNonEmptyStrings(CharSequence separator, String... stringsToJoin) {
        return Arrays.stream(stringsToJoin)
                .filter(val -> !isEmpty(val))
                .collect(Collectors.joining(separator));
    }
}
