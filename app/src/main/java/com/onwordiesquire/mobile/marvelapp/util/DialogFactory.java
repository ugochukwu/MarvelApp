package com.onwordiesquire.mobile.marvelapp.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.onwordiesquire.mobile.marvelapp.R;

/**
 * Created by michelonwordi on 10/25/16.
 */

public final class DialogFactory {


    public static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        return progressDialog;
    }
}
