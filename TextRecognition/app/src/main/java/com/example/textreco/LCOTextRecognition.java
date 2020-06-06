package com.example.textreco;

import android.app.Application;
import android.content.ContentProvider;
import android.os.Build;

import com.google.firebase.FirebaseApp;

public class LCOTextRecognition extends Application {

    public static final String RESULT_TEXT = "RESULT_TEXT";

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
