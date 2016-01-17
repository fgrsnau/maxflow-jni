package com.github.fgrsnau.maxflow;

import java.util.ArrayList;

public class GraphCut {

	private class UnaryTerm {

		public final int node;
		public final double e0;
		public final double e1;

		public UnaryTerm(int node, double e0, double e1) {
			this.node = node;
			this.e0 = e0;
			this.e1 = e1;
		}

	}

	private class PairwiseTerm {

		public final int i;
		public final int j;
		public final double e00;
		public final double e01;
		public final double e10;
		public final double e11;

		public PairwiseTerm(int i, int j, double e00, double e01, double e10, double e11) {
			this.i = i;
			this.j = j;
			this.e00 = e00;
			this.e01 = e01;
			this.e10 = e10;
			this.e11 = e11;
		}

	}

	protected MaxFlow maxflow;
	protected final ArrayList<UnaryTerm> unaryTerms;
	protected final ArrayList<PairwiseTerm> pairwiseTerms;

	public GraphCut() {
		unaryTerms = new ArrayList<UnaryTerm>();
		pairwiseTerms = new ArrayList<PairwiseTerm>();
	}

	public int addNode(int num) {
		int index = unaryTerms.size();
		for (int i = 0; i < num; ++i) {
			unaryTerms.add(new UnaryTerm(index + i, 0, 0));
		}
		return index;
	}

	public void addUnaryTerm(int node, double e0, double e1) {
		assert(node >= 0);
		assert(node < unaryTerms.size());
		unaryTerms.set(node, new UnaryTerm(node, e0, e1));
	}

	public void addPairwiseTerm(int i, int j, double e00, double e01, double e10, double e11) {
		assert(i >= 0);
		assert(j >= 0);
		assert(i < unaryTerms.size());
		assert(j < unaryTerms.size());
		assert(i < j);
		assert(e00 + e11 <= e01 + e10);
		pairwiseTerms.add(new PairwiseTerm(i, j, e00, e01, e10, e11));
	}

	public void buildGraph() {
		maxflow = new MaxFlow(unaryTerms.size(), pairwiseTerms.size());
		maxflow.addNode(unaryTerms.size());

		for (UnaryTerm u : unaryTerms) {
			maxflow.addTweights(u.node, u.e1, u.e0);
		}

		for (PairwiseTerm pw : pairwiseTerms) {
			double e00 = pw.e00;
			double e01 = pw.e01;
			double e10 = pw.e10;
			double e11 = pw.e11;

			double i_e0 = 0;
			double i_e1 = 0;
			double j_e0 = 0;
			double j_e1 = 0;

			i_e0 += e01;
			e00  -= e01;
			e01   =   0;

			j_e0 += e00;
			e10  -= e00;
			e00   =   0;

			i_e1 += e11;
			e10  -= e11;
			e11   =   0;

			assert(e10 >= 0);

			maxflow.addTweights(pw.i, i_e1, i_e0);
			maxflow.addTweights(pw.j, j_e1, j_e0);
			maxflow.addEdge(pw.i, pw.j, 0, e10);
		}
	}

	public boolean[] solve() {
		buildGraph();
		maxflow.maxflow();

		boolean solution[] = new boolean[unaryTerms.size()];
		for (int i = 0; i < unaryTerms.size(); ++i) {
			solution[i] = maxflow.whatSegment(i) == MaxFlow.SINK;
		}

		return solution;
	}

}
