package net.coolblossom.lycee.tools.iterators;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ConditionalIteratorTest {

	@Test
	public void test_array() {
		Integer[] ary = {0,1,2,3,4,5,6,7,8,9};
		List<Integer> list = new ArrayList<>();
		ConditionalIterator<Integer> iter = ConditionalIterator.make(
				ArrayIterator.make(ary), () -> list.size()<5);

		while(iter.hasNext()) {
			int n = iter.next();
			list.add(n);
		}

		assertEquals(5, list.size());
		for(Integer i=0 ; i<5; i++) {
			assertEquals(i, list.get(i));
		}

	}

}
