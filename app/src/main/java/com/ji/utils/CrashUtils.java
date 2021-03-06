package com.ji.utils;

import android.os.Build;
import android.os.Process;

import com.ji.app.BuildConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CrashUtils  {
    private static final String TAG = "CrashUtils";

    public static void initUncaughtExceptionHandler() {
        final Thread.UncaughtExceptionHandler defaultCrashHandler =
                Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                dumpException(e);
                if (defaultCrashHandler != null) {
                    defaultCrashHandler.uncaughtException(t, e);
                } else {
                    Process.killProcess(Process.myPid());
                }
            }
        });
    }

    private static void dumpException(Throwable e) {
        String time = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss",
                Locale.getDefault()).format(new Date());
        File file = new File(DiskUtils.getCrashCacheDir() + File.separator + time);
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println("VERSION_NAME:" + BuildConfig.VERSION_NAME
                    + " RELEASE:" + Build.VERSION.RELEASE
                    + " SDK_INT:" + Build.VERSION.SDK_INT
                    + " MANUFACTURER:" + Build.MANUFACTURER
                    + " MODEL:" + Build.MODEL);
            e.printStackTrace(pw);
            pw.close();
        } catch (IOException ex) {
            LogUtils.e(TAG, "dumpException", ex);
        }
    }
}
