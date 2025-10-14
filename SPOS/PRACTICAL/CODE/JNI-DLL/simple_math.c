#include <jni.h>
#include "SimpleMathCalculator.h"
#include <math.h>

// Simple addition function
JNIEXPORT jdouble JNICALL Java_SimpleMathCalculator_add
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    return a + b;
}

// Simple subtraction function  
JNIEXPORT jdouble JNICALL Java_SimpleMathCalculator_subtract
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    return a - b;
}

// Simple multiplication function
JNIEXPORT jdouble JNICALL Java_SimpleMathCalculator_multiply
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    return a * b;
}

// Simple division function with zero check
JNIEXPORT jdouble JNICALL Java_SimpleMathCalculator_divide
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    if (b == 0.0) {
        return NAN; // Return Not-A-Number for division by zero
    }
    return a / b;
}