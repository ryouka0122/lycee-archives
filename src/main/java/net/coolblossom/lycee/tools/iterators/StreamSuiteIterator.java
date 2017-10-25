package net.coolblossom.lycee.tools.iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.Stream;

public class StreamSuiteIterator<T> implements Iterator<T> {

	/**
	 * Generator
	 * @param streams 連結させたい複数のStream
	 * @return StreamSuiteIteratorオブジェクト
	 */
	@SafeVarargs
	public static <T1> StreamSuiteIterator<T1> make(Stream<T1> ...streams) {
		return new StreamSuiteIterator<T1>(streams);
	}


	/** 処理対象Streamのリスト */
	private List<Stream<T>> streamList;

	/** リストのIterator */
	private ListIterator<Stream<T>> listIter;

	/** 現在のIterator */
	private Iterator<T> curIter;

	/**
	 * コンストラクタ
	 * @param streams
	 */
	@SafeVarargs
	public StreamSuiteIterator(Stream<T> ...streams) {
		Objects.requireNonNull(streams);
		this.streamList = Arrays.asList(streams);
		this.listIter = this.streamList.listIterator();
		this.curIter = this.listIter.next().iterator();
	}


	@Override
	public boolean hasNext() {
		// FIXME 整理すると処理が簡潔になりそう
		if( curIter.hasNext() ) return true;
		if( !listIter.hasNext() ) return false;

		do {
			curIter = listIter.next().iterator();
			if(curIter.hasNext()) return true;
		}while(listIter.hasNext());
		return false;
	}

	@Override
	public T next() {
		return curIter.next();
	}

}
