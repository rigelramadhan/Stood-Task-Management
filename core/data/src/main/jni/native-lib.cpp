#include <jni.h>

extern "C" {
JNIEXPORT jstring
jstring
Java_one_reevdev_stood_core_data_utils_AppConstants_getApiUrl(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "https://gg-project.ramdani-kurnia-dev-programming.work/")
}

JNIEXPORT jstring
jstring
Java_one_reevdev_stood_core_data_utils_AppConstants_getEncryptionKey(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "Odh82ahd8H2odhH99DH892hd98DH9dh898h98dh928dh9128DH9d298hd2198")
}
}