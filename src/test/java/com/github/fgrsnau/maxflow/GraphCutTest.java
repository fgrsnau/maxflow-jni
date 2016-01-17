package com.github.fgrsnau.maxflow;

import org.junit.Assert;
import org.junit.Test;

public class GraphCutTest {

	@Test
	public void testUnary1() {
		boolean[] expected = { false, true, false, true };
		GraphCut graphcut = new GraphCut();

		graphcut.addNode(4);
		graphcut.addUnaryTerm(0, 0, 1);
		graphcut.addUnaryTerm(1, 1, 0);
		graphcut.addUnaryTerm(2, 0, 1);
		graphcut.addUnaryTerm(3, 1, 0);

		boolean[] solution = graphcut.solve();
		Assert.assertArrayEquals(expected, solution);
	}

	@Test
	public void testUnary2() {
		boolean[] expected = { true, true, true, true };
		GraphCut graphcut = new GraphCut();

		graphcut.addNode(4);
		graphcut.addUnaryTerm(0, 1, 0);
		graphcut.addUnaryTerm(1, 1, 0);
		graphcut.addUnaryTerm(2, 1, 0);
		graphcut.addUnaryTerm(3, 1, 0);

		boolean[] solution = graphcut.solve();
		Assert.assertArrayEquals(expected, solution);
	}

	@Test
	public void testPairwise1() {
		boolean[] expected = { true, true, true, true };
		GraphCut graphcut = new GraphCut();

		graphcut.addNode(4);
		graphcut.addPairwiseTerm(0, 1, 0, 1, 1, 0);
		graphcut.addPairwiseTerm(0, 2, 1, 1, 1, 0);
		graphcut.addPairwiseTerm(1, 2, -1, 1, 1, -1);
		graphcut.addPairwiseTerm(2, 3, -1, 0, 0, -1);

		boolean[] solution = graphcut.solve();
		Assert.assertArrayEquals(expected, solution);
	}

	@Test
	public void testPairwise2() {
		boolean[] expected = { false, false, false, false };
		GraphCut graphcut = new GraphCut();

		graphcut.addNode(4);
		graphcut.addPairwiseTerm(0, 1, -1, 0, 0, 0);
		graphcut.addPairwiseTerm(0, 2, 0, 1, 1, 0);
		graphcut.addPairwiseTerm(1, 2, -1, 1, 1, -1);
		graphcut.addPairwiseTerm(2, 3, -1, 0, 0, -1);

		boolean[] solution = graphcut.solve();
		Assert.assertArrayEquals(expected, solution);
	}

	@Test
	public void testSimple() {
		boolean[] expected = { false, true, false, true };
		GraphCut graphcut = new GraphCut();

		graphcut.addNode(4);

		graphcut.addUnaryTerm(0, 0, 1);
		graphcut.addUnaryTerm(1, 0, 10);
		graphcut.addUnaryTerm(2, 1, 0);
		graphcut.addUnaryTerm(3, 10, 0);

		graphcut.addPairwiseTerm(0, 1, 10, -5, 20, 0);
		graphcut.addPairwiseTerm(0, 2, 0, 1, 1, 0);
		graphcut.addPairwiseTerm(1, 3, 0, 1, 1, 0);
		graphcut.addPairwiseTerm(2, 3, 0, 0, 2, 1);

		boolean[] solution = graphcut.solve();
		Assert.assertArrayEquals(expected, solution);
	}

}
