package com.example.moslab.ddi;

import android.app.Application;

/**
 * Created by moslab on 4/2/17.
 */

public class Appstart extends Application{
    static {
        System.loadLibrary("ddi-lib");
    }
   // public static native void my_init();
    @Override
    public void onCreate() {
        super.onCreate();
        //my_init();
    }
}
