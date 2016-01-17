#include "com_github_fgrsnau_maxflow_MaxFlow.h"
#include "maxflow/graph.h"

typedef Graph<jdouble, jdouble, jdouble> GraphType;

static GraphType* getPtr(JNIEnv *env, jobject obj)
{
	jclass clazz = env->GetObjectClass(obj);
	jfieldID field = env->GetFieldID(clazz, "ptr", "J");
	jlong ptr = env->GetLongField(obj, field);
	return reinterpret_cast<GraphType*>(ptr);
}

/*
 * Class:     com_github_fgrsnau_maxflow_MaxFlow
 * Method:    constructor
 * Signature: (II)J
 */
JNIEXPORT jlong JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_constructor
  (JNIEnv *, jobject, jint nodeMaxNum, jint edgeMaxNum)
{
	return reinterpret_cast<jlong>(new GraphType(nodeMaxNum, edgeMaxNum));
}

/*
 * Class:     com_github_fgrsnau_maxflow_MaxFlow
 * Method:    destructor
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_destructor
  (JNIEnv *, jobject, jlong ptr)
{
	delete reinterpret_cast<GraphType*>(ptr);
}

/*
 * Class:     com_github_fgrsnau_maxflow_MaxFlow
 * Method:    addNode
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_addNode__
  (JNIEnv *env, jobject obj)
{

	GraphType *ptr = getPtr(env, obj);
	return ptr->add_node();
}

/*
 * Class:     com_github_fgrsnau_maxflow_MaxFlow
 * Method:    addNode
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_addNode__I
  (JNIEnv *env, jobject obj, jint num)
{
	GraphType *ptr = getPtr(env, obj);
	return ptr->add_node(num);
}

/*
 * Class:     com_github_fgrsnau_maxflow_MaxFlow
 * Method:    addEdge
 * Signature: (IIDD)V
 */
JNIEXPORT void JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_addEdge
  (JNIEnv *env, jobject obj, jint i, jint j, jdouble cap, jdouble revCap)
{
	GraphType *ptr = getPtr(env, obj);
	return ptr->add_edge(i, j, cap, revCap);
}

/*
 * Class:     com_github_fgrsnau_maxflow_MaxFlow
 * Method:    addTweights
 * Signature: (IDD)V
 */
JNIEXPORT void JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_addTweights
  (JNIEnv *env, jobject obj, jint i, jdouble capSource, jdouble capSink)
{
	GraphType *ptr = getPtr(env, obj);
	return ptr->add_tweights(i, capSource, capSink);
}

/*
 * Class:     com_github_fgrsnau_maxflow_MaxFlow
 * Method:    maxflow
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_maxflow
  (JNIEnv *env, jobject obj)
{
	GraphType *ptr = getPtr(env, obj);
	return ptr->maxflow();
}

/*
 * Class:     com_github_fgrsnau_maxflow_MaxFlow
 * Method:    whatSegment
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_whatSegment__I
  (JNIEnv *env, jobject obj, jint i)
{
	GraphType *ptr = getPtr(env, obj);
	return ptr->what_segment(i);
}

/*
 * Class:     com_github_fgrsnau_maxflow_MaxFlow
 * Method:    whatSegment
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_whatSegment__II
  (JNIEnv *env, jobject obj, jint i, jint defaultType)
{
	GraphType *ptr = getPtr(env, obj);
	return ptr->what_segment(i, static_cast<GraphType::termtype>(defaultType));
}
