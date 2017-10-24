package net.coolblossom.lycee.tools.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import net.coolblossom.lycee.tools.test.BaseTest;

/**
 * Tupleのテストケース
 * @author ryouka0122@github
 *
 */
@RunWith(JUnit4.class)
public class TupleTest extends BaseTest {

	@Test
	public void test_iterator() {
		Tuple tuple = Tuple.make(1,2,3,4,5);

		Iterator<Object> iter = tuple.iterator();
		Integer expected = 1;
		while(iter.hasNext()) {
			assertEquals(expected++, iter.next());
		}
		;
	}

	@Test
	public void test_integer_only() {
		Tuple tuple = Tuple.make(1,2,3,4,5);

		Integer expected=1;
		for(Object actual : tuple) {
			assertEquals(expected++, actual);
		}

	}

	@Test
	public void test_string_only() {
		Tuple tuple = Tuple.make("A", "B", "C");

		assertEquals(3, tuple.size());

		assertEquals("A", tuple.get(0));
		assertEquals("B", tuple.get(1));
		assertEquals("C", tuple.get(2));

	}

	@Test
	public void test_mix() {
		Tuple tuple = Tuple.make("A", 10000, Math.PI);

		/** actual type */
		assertTrue(tuple.check(0, String.class));
		assertTrue(tuple.check(1, Integer.class));
		assertTrue(tuple.check(2, Double.class));

		/** illegal type */
		assertFalse(tuple.check(0, Integer.class));
		assertFalse(tuple.check(1, Float.class));
		assertFalse(tuple.check(2, List.class));

		/** parent type */
		assertTrue(tuple.check(0, Object.class));
		assertTrue(tuple.check(1, Number.class));

	}

	@Test
	@SuppressWarnings("unused")
	public void test_cast() {
		Tuple tuple = Tuple.make("A", 10000, Math.PI);

		/** actual type */
		String str = tuple.get(0);
		Integer num = tuple.get(1);
		Double pi = tuple.get(2);

		/** illegal type */
		try {
			Integer illegal = tuple.get(0);
			fail();
		}catch(ClassCastException e) {
			succeed();
		}

		/** parent type */
		try{
			Number trual = tuple.get(2);
			succeed();
		}catch(ClassCastException e) {
			fail();
		}
	}


}
