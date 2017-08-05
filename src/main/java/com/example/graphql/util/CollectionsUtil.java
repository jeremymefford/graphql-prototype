package com.example.graphql.util;

import java.util.Collection;
import java.util.stream.Stream;

public abstract class CollectionsUtil {
    public static <R> Stream<R> nonNullStream(Collection<R> coll) {
        if (coll == null || coll.isEmpty()) {
            return Stream.empty();
        }
        return coll.stream();
    }
}
