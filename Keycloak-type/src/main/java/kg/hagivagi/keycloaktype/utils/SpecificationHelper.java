package kg.hagivagi.keycloaktype.utils;

import lombok.experimental.UtilityClass;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class SpecificationHelper {

    public static String getContainsLikePattern(String searchTerm) {
        return searchTerm == null || searchTerm.isEmpty() ? "%" : "%" + searchTerm.toLowerCase().trim() + "%";
    }

    public static Expression<String> concatFieldsAndLower(CriteriaBuilder criteriaBuilder, Root<?> root, Join<?, ?> join, String... fields) {
        List<Expression<String>> expressions = new ArrayList<>();
        Arrays.stream(fields).forEach(field -> expressions.add(criteriaBuilder.coalesce(join != null ? join.get(field) : root.get(field), " ")));

        return criteriaBuilder.lower(
                expressions.stream()
                        .reduce(criteriaBuilder.literal(" "), (a, b) ->
                                criteriaBuilder.concat(criteriaBuilder.concat(a, " "), b))
        );

    }

}
