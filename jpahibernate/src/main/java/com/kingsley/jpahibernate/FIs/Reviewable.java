package com.kingsley.jpahibernate.FIs;

import java.util.Objects;

@FunctionalInterface
public interface Reviewable<T,R> {
	
	public R reviewThis(T t);
	
	public default R findReviewType(T t) {
		String r = Objects.toString(t);
		System.out.println();
		return (R) r;
	}

}
