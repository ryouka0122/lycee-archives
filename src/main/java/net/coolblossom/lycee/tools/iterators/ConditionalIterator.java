package net.coolblossom.lycee.tools.iterators;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 条件付きIterator
 * @author ryouka0122@github
 *
 * @param <T>
 */
public class ConditionalIterator<T> implements Iterator<T> {

	/**
	 * Generator
	 * @param iter 対象Iterator
	 * @param supplier 継続条件定義
	 * @return ConditionalIteratorオブジェクト
	 */
	public static <U> ConditionalIterator<U> make(Iterator<U> iter, Supplier<Boolean> supplier) {
		return new ConditionalIterator<U>(iter, supplier);
	}

	/** 対象Iterator */
	Iterator<T> iterator;

	/** 継続条件定義 */
	Supplier<Boolean> supplier;

	/**
	 * コンストラクタ
	 * @param iter 対象Iterator
	 * @param supplier 継続条件定義
	 */
	public ConditionalIterator(Iterator<T> iter, Supplier<Boolean> supplier) {
		this.iterator = Objects.requireNonNull(iter);
		this.supplier = Objects.requireNonNull(supplier);
	}


	@Override
	public boolean hasNext() {
		return supplier.get() && this.iterator.hasNext();
	}

	@Override
	public T next() {
		return this.iterator.next();
	}

}
