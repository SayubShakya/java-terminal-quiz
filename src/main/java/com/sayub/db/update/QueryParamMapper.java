package com.sayub.db.update;

@FunctionalInterface
public interface QueryParamMapper<I,O> {

    O map(I param);
}
