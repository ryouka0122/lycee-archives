package net.coolblossom.lycee.tools.iterators;

import java.util.Iterator;
import java.util.Objects;

/**
 * 配列Iterator
 * @author ryouka0122@github
 *
 * @param <T>
 */
public class ArrayIterator<T> implements Iterator<T> {

	/**
	 * Generator
	 * @param ary 処理対象の配列
	 * @return ArryaIteratorオブジェクト
	 */
	public static <U> ArrayIterator<U> make(U [] ary) {
		return new ArrayIterator<U>(ary, 0, ary.length);
	}

	/**
	 * Generator
	 * @param ary 処理対象の配列
	 * @param begin 開始インデックス
	 * @param last 終了インデックス（含めない）
	 * @return ArryaIteratorオブジェクト
	 */
	public static <U> ArrayIterator<U> make(U [] ary, int begin, int last) {
		return new ArrayIterator<U>(ary, begin, last);
	}

	/** 対象データ */
	private T[] ary;

	/** 現在のインデックス */
	private int cur;

	/** 終端インデックス（含めない） */
	private int last;

	/**
	 * コンストラクタ
	 * @param ary
	 * @param begin
	 * @param last
	 */
	public ArrayIterator(T[] ary, int begin, int last) {
		this.ary= Objects.requireNonNull(ary);

		if(begin<0 || last<begin || ary.length<=begin) {
			throw new IllegalArgumentException();
		}

		this.cur = begin;
		this.last = last;
	}

	@Override
	public boolean hasNext() {
		return (cur<last);
	}

	@Override
	public T next() {
		return ary[cur++];
	}


}
