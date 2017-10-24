package net.coolblossom.lycee.tools.commons;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import net.coolblossom.lycee.tools.iterators.ArrayIterator;


/**
 * Tuple型
 * @author ryouka0122@github
 *
 */
public class Tuple implements Iterable<Object> {
	public static final Collector<CharSequence, ?, String> TUPLE_COLLECTOR = Collectors.joining(", ", "(", ")");

	/**
	 * Generator
	 * @param objects Tupleで管理するデータ
	 * @return Tupleオブジェクト
	 */
	public static Tuple make(Object ...objects) {
		return new Tuple(objects);
	}

	/** Tupleデータ */
	private Object[] data;

	/**
	 * コンストラクタ
	 * @param objects
	 */
	public Tuple(Object ...objects) {
		this.data = Objects.requireNonNull(objects);
	}

	/**
	 * データの取得
	 * @param index インデックス
	 * @return 型変換後のオブジェクト
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(int index) {
		return (T) this.data[index];
	}

	/**
	 * データの取得（型不一致時のデフォルト値付き）
	 * @param index インデックス
	 * @param defValue デフォルト値
	 * @return 型変換後のオブジェクト
	 */
	@SuppressWarnings("unchecked")
	public <T> T getIfBadCast(int index, T defValue) {
		return check(index, defValue.getClass()) ? (T) this.data[index] : defValue;
	}

	/**
	 * 型チェック
	 * @param index インデックス
	 * @param clazz 型
	 * @return 変換可能な場合trueを返す
	 */
	public <T> boolean check(int index, Class<T> clazz) {
		return clazz.isInstance(this.data[index]);
	}

	/**
	 * データ数
	 * @return サイズ
	 */
	public int size() {
		return this.data.length;
	}

	/**
	 * データの型のリストを取得
	 * @return 型のリスト
	 */
	public List<Class<?>> getTypeList() {
		return Arrays.stream(this.data).map(o->o.getClass()).collect(Collectors.toList());
	}

	/**
	 * Iteratorの取得
	 * @return
	 */
	@Override
	public Iterator<Object> iterator() {
		return ArrayIterator.make(this.data, 0, this.data.length);
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj) return true;
		if( !(obj instanceof Tuple) ) return false;
		Tuple rhs = (Tuple) obj;
		if(this.data.length!=rhs.data.length) return false;
		return IntStream.range(0, this.data.length).boxed()
				.map(i -> this.data[i].equals(rhs.data[i]))
				.reduce(true, (l, r)-> l & r);
	}

	@Override
	public String toString() {
		return Arrays.stream(this.data).map(o->o.toString()).collect(TUPLE_COLLECTOR);
	}

}
