#include "MathOperations.h"
#include <math.h>
#include <string.h>
#include <stdlib.h>

// Library version for DMAS operations
#define MATHOPS_VERSION "DMAS-1.0"

/*
 * DMAS Operations Implementation
 * D - Division, M - Multiplication, A - Addition, S - Subtraction
 */

/*
 * Class:     MathOperations
 * Method:    add
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_MathOperations_add
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    return a + b;
}

/*
 * Class:     MathOperations
 * Method:    subtract
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_MathOperations_subtract
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    return a - b;
}

/*
 * Class:     MathOperations
 * Method:    multiply
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_MathOperations_multiply
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    return a * b;
}

/*
 * Class:     MathOperations
 * Method:    divide
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_MathOperations_divide
  (JNIEnv *env, jobject obj, jdouble a, jdouble b) {
    if (b == 0.0) {
        // Throw an exception for division by zero
        jclass exceptionClass = (*env)->FindClass(env, "java/lang/ArithmeticException");
        if (exceptionClass != NULL) {
            (*env)->ThrowNew(env, exceptionClass, "Division by zero");
        }
        return 0.0;
    }
    return a / b;
}

/*
 * Class:     MathOperations
 * Method:    getLibraryVersion
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_MathOperations_getLibraryVersion
  (JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, MATHOPS_VERSION);
}