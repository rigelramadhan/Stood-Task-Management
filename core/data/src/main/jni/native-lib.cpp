#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_one_reevdev_stood_core_data_utils_constants_AppConstants_getApiUrl(JNIEnv *env, jobject thiz) {
    return (*env).NewStringUTF("https://gg-project.ramdani-kurnia-dev-programming.work/");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_one_reevdev_stood_core_data_utils_constants_AppConstants_getEncryptionKey(JNIEnv *env, jobject thiz) {
    return (*env).NewStringUTF("Odh82ahd8H2odhH99DH892hd98DH9dh898h98dh928dh9128DH9d298hd2198");
}