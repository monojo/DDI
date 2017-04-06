
#include <dlfcn.h>
#include <android/log.h>
#include "dexstuff.h"
#include "dalvik_hook.h"

static struct dexstuff_t d;
static struct dalvik_hook_t sb1;
static struct dalvik_hook_t sb2;
static struct dalvik_hook_t sb3;
static struct dalvik_hook_t sb4;
static struct dalvik_hook_t sb5;
static struct dalvik_hook_t sb6;
static struct dalvik_hook_t sb7;
static struct dalvik_hook_t sb8;
static struct dalvik_hook_t sb10;
static struct dalvik_hook_t sb9;
static struct dalvik_hook_t sb11;

void do_patch();

//void Java_com_example_moslab_ddi_Appstart_my_1init(void) {
    void Java_com_example_moslab_ddi_MainActivity_my_1init(void) {

        __android_log_print(ANDROID_LOG_DEBUG, "ZX", "my_init");

    dexstuff_resolv_dvm(&d);
    do_patch();
}

static void* sb1_tostring(JNIEnv *env, jobject obj) {
    dalvik_prepare(&d, &sb1, env);
    void *res = (*env)->CallObjectMethod(env, obj, sb1.mid);
    dalvik_postcall(&d, &sb1);

    const char *s = (*env)->GetStringUTFChars(env, res, 0);
    if (s) {
        (*env)->ReleaseStringUTFChars(env, res, s);
    }

    return res;
}

static void sb2_connect(JNIEnv *env, jobject obj) {
    //call get url here
    //(*env)->FindClass(env, "   ")

    __android_log_print(ANDROID_LOG_DEBUG, "ZX", "connect");

    dalvik_prepare(&d, &sb2, env);
    (*env)->CallVoidMethod(env, obj, sb2.mid);
    dalvik_postcall(&d, &sb2);


}

static void* sb3_getInputStream(JNIEnv  *env, jobject  obj) {
    //jvalue args[1];
    //args[0].l = stream;
    dalvik_prepare(&d, &sb3, env);
    void* ret = (*env)->CallObjectMethod(env, obj, sb3.mid);
    dalvik_postcall(&d, &sb3);

    return ret;
}

static void* sb4_getResponseCode(JNIEnv  *env, jobject  obj) {
    //jvalue args[1];
    //args[0].l = stream;
    __android_log_print(ANDROID_LOG_DEBUG, "ZX", "Code");

    dalvik_prepare(&d, &sb4, env);
    int ret = (*env)->CallIntMethod(env, obj, sb4.mid);
    dalvik_postcall(&d, &sb4);
    __android_log_print(ANDROID_LOG_DEBUG, "ZX", "Code");

    return (void*)ret;
}

static void* sb5_read(JNIEnv *env, jobject obj, jobjectArray C) {
    jvalue args[1];
    args[0].l = C;
    dalvik_prepare(&d, &sb5, env);
    int ret = (*env)->CallIntMethodA(env, obj, sb5.mid, args);
    int len = (*env)->GetArrayLength(env, C);
    jchar *s = (*env)->GetCharArrayElements(env, C, NULL);
    char *ca = malloc(sizeof(char)*(ret+1));
    memset(ca, 0, sizeof(char)*(ret+1));
    for (int i = 0; i < ret; i++) {
        ca[i] = (char)s[i];
    }
    __android_log_print(ANDROID_LOG_DEBUG, "ZX",  "%d %s",ret ,ca);
    dalvik_postcall(&d, &sb5);
    __android_log_print(ANDROID_LOG_DEBUG, "ZX", "read");

    return (void*)ret;
}

static void* sb6_contains(JNIEnv *env, jobject obj, jobject str) {
    jvalue args[1];
    args[0].l = str;
    dalvik_prepare(&d, &sb6, env);
    int res = (*env)->CallBooleanMethodA(env, obj, sb6.mid, args);
    dalvik_postcall(&d, &sb6);
    return (void*)res;
}

static void* sb7_readline(JNIEnv *env, jobject obj) {
    dalvik_prepare(&d, &sb7, env);
    void *res = (*env)->CallObjectMethod(env, obj, sb7.mid);
    dalvik_postcall(&d, &sb7);
    if (res == NULL)
        return NULL;
    const char *s= (*env)->GetStringUTFChars(env, res, NULL);
    //(*env)->ReleaseStringUTFChars(env, res, s);
    if (s) {
    //    __android_log_print(ANDROID_LOG_DEBUG, "ZX","len: %s", s);
    //__android_log_print(ANDROID_LOG_DEBUG, "ZX", "%s",s);
        (*env)->ReleaseStringUTFChars(env, res, s);

    }

    return res;
}

static void sb8_setRequestMethod(JNIEnv *env, jobject obj, jstring str) {


    jvalue args[1];
    args[0].l = str;
    dalvik_prepare(&d, &sb8, env);
    (*env)->CallVoidMethodA(env, obj, sb8.mid, args);
    dalvik_postcall(&d, &sb8);
    __android_log_print(ANDROID_LOG_DEBUG, "ZX", "sb8");
    //return (void*)res;
}
static void sb9_A(JNIEnv *env, jobject obj) {
    dalvik_prepare(&d, &sb9, env);
    (*env)->CallVoidMethod(env, obj, sb9.mid);
    dalvik_postcall(&d, &sb9);
    __android_log_print(ANDROID_LOG_DEBUG, "ZX", "sb9");
}

static void sb10_B(JNIEnv *env, jobject obj) {
    dalvik_prepare(&d, &sb10, env);
    (*env)->CallVoidMethod(env, obj, sb10.mid);
   jclass j = (*env)->FindClass(env, "com/example/moslab/ddi/A");
    if (j == NULL) {
        __android_log_print(ANDROID_LOG_DEBUG, "ZX", "FAIL!");
    }
    jmethodID  mid = (*env)->GetMethodID(env, j, "p", "()V");
    (*env)->CallVoidMethod(env, obj, mid);
    dalvik_postcall(&d, &sb10);
    __android_log_print(ANDROID_LOG_DEBUG, "ZX", "sb10");
}

void do_patch() {
    __android_log_print(ANDROID_LOG_DEBUG, "ZX", "do_patch");

   // dalvik_hook_setup(&sb1, "Ljava/lang/StringBuffer;", "toString", "()Ljava/lang/String;", 1, sb1_tostring);
   // dalvik_hook(&d, &sb1);

   // dalvik_hook_setup(&sb6, "Ljava/lang/String;", "contains", "(Ljava/lang/CharSequence;)Z", 2, sb6_contains);
    //dalvik_hook(&d, &sb6);

    dalvik_hook_setup(&sb2, "Lcom/android/okhttp/internal/http/HttpsURLConnectionImpl;", "connect", "()V", 1, sb2_connect);
    dalvik_hook(&d, &sb2);

   // dalvik_hook_setup(&sb3, "Ljavax/net/ssl/HttpsURLConnection;", "getInputStream", "()Ljava/io/InputStream", 1, sb3_getInputStream);
   // dalvik_hook(&d, &sb3);

    //dalvik_hook_setup(&sb4, "Ljavax/net/ssl/HttpsURLConnection;", "getResponseCode", "()I", 1, sb4_getResponseCode);
    dalvik_hook_setup(&sb4, "Lcom/android/okhttp/internal/http/HttpsURLConnectionImpl;", "getResponseCode", "()I", 1, sb4_getResponseCode);

    dalvik_hook(&d, &sb4);

    dalvik_hook_setup(&sb5, "Ljava/io/Reader;", "read", "([C)I", 2, sb5_read);
    dalvik_hook(&d, &sb5);
    // dalvik_hook_setup(&sb7, "Ljava/io/Buffered"
     //        "Reader;", "readLine", "()Ljava/lang/String;", 1, sb7_readline);
   //  dalvik_hook(&d, &sb7);
    //dalvik_hook_setup(&sb8, "Ljavax/net/ssl/HttpsURLConnection;", "setRequestMethod", "(Ljava/lang/String;)V", 2, sb8_setRequestMethod);
    //dalvik_hook(&d, &sb8);
    //dalvik_hook_setup(&sb9, "Lcom/example/moslab/ddi/A;", "a", "()V", 1, sb9_A);
    //dalvik_hook(&d, &sb9);
    //dalvik_hook_setup(&sb10, "Lcom/example/moslab/ddi/B;", "a", "()V", 1, sb10_B);
    //dalvik_hook(&d, &sb10);
    dalvik_hook_setup(&sb10, "Lcom/example/moslab/ddi/A;", "a", "()V", 1, sb10_B);
    dalvik_hook(&d, &sb10);

//    dalvik_hook_setup(&sb11, "Ljava/net/URLConnection;", "getURL", "()Ljava/net/URL;", 1, sb11_getURL);
//    dalvik_hook(&d, &sb11);
}
