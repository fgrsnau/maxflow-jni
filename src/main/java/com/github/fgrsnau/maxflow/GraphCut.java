package com.github.fgrsnau.maxflow;

public class GraphCut {

	protected final MaxFlow maxflow;
	protected int nodeNum;

	public GraphCut(int nodeNumMax, int edgeNumMax) {
		maxflow = new MaxFlow(nodeNumMax, edgeNumMax);
		nodeNum = 0;
	}

	public void dispose() {
		maxflow.dispose();
	}

	public int addNode(int num) {
		int index = maxflow.addNode(num);
		nodeNum = index + num;
		return index;
	}

	public void addUnaryTerm(int node, double e0, double e1) {
		assert(node >= 0);
		assert(node < nodeNum);
		maxflow.addTweights(node, e1, e0);
	}

	public void addPairwiseTerm(int i, int j, double e00, double e01, double e10, double e11) {
		assert(i >= 0);
		assert(j >= 0);
		assert(i < nodeNum);
		assert(j < nodeNum);
		assert(i < j);
		assert(e00 + e11 <= e01 + e10);

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
		maxflow.addTweights(i, i_e1, i_e0);
		maxflow.addTweights(j, j_e1, j_e0);
		maxflow.addEdge(i, j, 0, e10);
	}

	public boolean[] solve() {
		maxflow.maxflow();

		boolean solution[] = new boolean[nodeNum];
		for (int i = 0; i < nodeNum; ++i) {
			solution[i] = maxflow.whatSegment(i) == MaxFlow.SINK;
		}

		return solution;
	}

}
