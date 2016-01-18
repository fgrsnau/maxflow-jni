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

	private long ptr;

	public MaxFlow(int nodeNumMax, int edgeNumMax) {
		this.ptr = constructor(nodeNumMax, edgeNumMax);
	}

	/**
	 * Frees the native memory associated with this object.
	 *
	 * The memory is also freed if the the garbage collector collects this object. The problem is that
	 * the garbage collector is not aware that this small wrapper object holds a relative large amount of
	 * native memory. If many instances are created in a tight loop the machine can easily run out of memory,
	 * even if the garbage collector could reclaim all the wasted space.
	 */
	public void dispose() {
		if (ptr != 0) {
			destructor(ptr);
			ptr = 0;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (ptr != 0) {
			destructor(ptr);
			ptr = 0;
		}
		super.finalize();
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

	public native void reset();

}
