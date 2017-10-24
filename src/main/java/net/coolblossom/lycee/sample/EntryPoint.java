package net.coolblossom.lycee.sample;

import net.coolblossom.lycee.tools.iterators.ArrayIterator;

public class EntryPoint {

	public static void main(String[] args) {
/*
		StreamBlender.link(
				IntStream.range(0, 10),
				new Random().doubles().skip(10),
				Arrays.stream("ABCDEFGHIJ".split(""))
		)
		.forEach(System.out::println);
*/
		Integer[] data = {1,2,3,4,5};

		ArrayIterator<Integer> iter = ArrayIterator.make(data, 1, 3);

		Integer sum = 0;

		while(iter.hasNext()) {
			Integer val = iter.next();
			System.out.println(val);
			sum += val;
		}
		System.out.println("sum=" + sum);



	}


}
