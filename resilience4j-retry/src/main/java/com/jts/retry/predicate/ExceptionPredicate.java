package com.jts.retry.predicate;

import java.util.function.Predicate;

import com.jts.retry.OrderNotFoundException;

public class ExceptionPredicate implements Predicate<Throwable> {
    @Override
    public boolean test(Throwable throwable) {
        System.out.println("Exception predicate is called.");
        return throwable instanceof OrderNotFoundException;
    }
}