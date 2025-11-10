#include <jni.h>
#include "MathCalculator.h"

JNIEXPORT jint JNICALL Java_MathCalculator_add
  (JNIEnv *env, jobject obj, jint a, jint b) {
    return a + b;
}

JNIEXPORT jint JNICALL Java_MathCalculator_subtract
  (JNIEnv *env, jobject obj, jint a, jint b) {
    return a - b;
}

JNIEXPORT jint JNICALL Java_MathCalculator_multiply
  (JNIEnv *env, jobject obj, jint a, jint b) {
    return a * b;
}

JNIEXPORT jdouble JNICALL Java_MathCalculator_divide
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    if (b != 0) {
        return a / b;
    }
    return 0.0;
}