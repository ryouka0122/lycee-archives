package net.coolblossom.lycee.tools.iterators;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

public class StreamSuiteIteratorTest {

	@Test
	public void test() {

		StreamSuiteIterator<Integer> iter = StreamSuiteIterator.make(
				IntStream.range(0, 5).boxed(),
				IntStream.range(10, 15).boxed(),
				IntStream.range(100, 105).boxed()
				);

		List<Integer> list = new ArrayList<Integer>();

		while(iter.hasNext()) {
			list.add(iter.next());
		}

		assertEquals(15, list.size());

		for(int i=0 ; i<5 ; i++) {
			assertEquals(i, list.get(i).intValue());

			assertEquals(10+i, list.get(5+i).intValue());

			assertEquals(100+i, list.get(10+i).intValue());
		}

	}

}
