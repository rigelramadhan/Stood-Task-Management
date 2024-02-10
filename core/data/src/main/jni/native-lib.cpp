#include <jni.h>

extern "C" {
JNIEXPORT jstring
jstring
Java_one_reevdev_stood_core_data_utils_AppConstants_getApiUrl(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "https://gg-project.ramdani-kurnia-dev-programming.work/")
}
}