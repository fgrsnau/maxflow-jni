package com.github.fgrsnau.maxflow;

/**
 * @author fgrsnau
 */
public class MaxFlow {

	static {
		NarSystem.loadLibrary();
	}

	public static int SOURCE = 0;
	public static int SINK = 1;

	private final long ptr;

	public MaxFlow(int nodeNumMax, int edgeNumMax) {
		this.ptr = constructor(nodeNumMax, edgeNumMax);
	}

	@Override
	public void finalize() {
		destructor(this.ptr);
	}

	private native long constructor(int nodeNumMax, int edgeNumMax);

	private native void destructor(long ptr);

	public native long addNode();

	public native int addNode(int num);

	public native void addEdge(int i, int j, double cap, double revCap);

	public native void addTweights(int i, double capSource, double capSink);

	public native double maxflow();

	public native int whatSegment(int i);

	public native int whatSegment(int i, int defaultSegment);

}
