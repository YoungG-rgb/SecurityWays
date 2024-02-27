package kg.hagivagi.keycloaktype.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import lombok.experimental.UtilityClass;

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
