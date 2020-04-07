package com.us.srs.net.handle;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class HttpResultFunc<T,E> implements Function<T, E> {
    @Override
    public E apply(@NonNull T t) throws Exception {
        return null;
    }
}
