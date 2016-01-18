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

JNIEXPORT jlong JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_constructor
  (JNIEnv *, jobject, jint nodeMaxNum, jint edgeMaxNum)
{
	return reinterpret_cast<jlong>(new GraphType(nodeMaxNum, edgeMaxNum));
}

JNIEXPORT void JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_destructor
  (JNIEnv *, jobject, jlong ptr)
{
	delete reinterpret_cast<GraphType*>(ptr);
}

JNIEXPORT jlong JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_addNode__
  (JNIEnv *env, jobject obj)
{

	GraphType *ptr = getPtr(env, obj);
	return ptr->add_node();
}

JNIEXPORT jint JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_addNode__I
  (JNIEnv *env, jobject obj, jint num)
{
	GraphType *ptr = getPtr(env, obj);
	return ptr->add_node(num);
}

JNIEXPORT void JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_addEdge
  (JNIEnv *env, jobject obj, jint i, jint j, jdouble cap, jdouble revCap)
{
	GraphType *ptr = getPtr(env, obj);
	return ptr->add_edge(i, j, cap, revCap);
}

JNIEXPORT void JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_addTweights
  (JNIEnv *env, jobject obj, jint i, jdouble capSource, jdouble capSink)
{
	GraphType *ptr = getPtr(env, obj);
	return ptr->add_tweights(i, capSource, capSink);
}

JNIEXPORT jdouble JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_maxflow
  (JNIEnv *env, jobject obj)
{
	GraphType *ptr = getPtr(env, obj);
	return ptr->maxflow();
}

JNIEXPORT jint JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_whatSegment__I
  (JNIEnv *env, jobject obj, jint i)
{
	GraphType *ptr = getPtr(env, obj);
	return ptr->what_segment(i);
}

JNIEXPORT jint JNICALL Java_com_github_fgrsnau_maxflow_MaxFlow_whatSegment__II
  (JNIEnv *env, jobject obj, jint i, jint defaultType)
{
	GraphType *ptr = getPtr(env, obj);
	return ptr->what_segment(i, static_cast<GraphType::termtype>(defaultType));
}
