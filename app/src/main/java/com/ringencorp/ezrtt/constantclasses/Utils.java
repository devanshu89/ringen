package com.ringencorp.ezrtt.constantclasses;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ringencorp.ezrtt.R;
import com.ringencorp.ezrtt.interfaces.MyDialogCloseListener;


@SuppressLint("SimpleDateFormat")
public class Utils {
    static AlertDialog alertDialog;

    public static void hideKeyBoard(Context c, View v) {
        InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static boolean isInternetConnected(Context mContext) {
        try {
            ConnectivityManager connect = null;
            connect = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connect != null) {
                NetworkInfo resultMobile = connect.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                NetworkInfo resultWifi = connect.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if ((resultMobile != null && resultMobile.isConnectedOrConnecting()) || (resultWifi != null && resultWifi.isConnectedOrConnecting())) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getDeviceWidth(Context context) {
        try {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            return metrics.widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 480;
    }

    public static int getDeviceHeight(Context context) {
        try {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            return metrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 480;
    }

    /**
     * Check if this device has a camera
     */
    public static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }


    private static AlertDialog getAlertDialogObject(Context context) {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(context).create();
        }
        return alertDialog;


    }

    public static void showAlertDialog(Context context, String title, String message, Boolean status) {
//        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        AlertDialog alertDialog = getAlertDialogObject(context);
        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if (status != null)
            // Setting alert dialog icon
            // alertDialog.setIcon((status) ? R.mipmap.ic_launcher : R.mipmap.ic_launcher);

            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });

        // Showing Alert Message
        alertDialog.show();
    }

    @SuppressWarnings("deprecation")
    public static void showAlertDialog(Context context, String title, String message, Boolean status, final MyDialogCloseListener closeListener) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if (status != null) {
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.mipmap.ic_launcher : R.mipmap.ic_launcher);
        } else {
            // No icon set to preference
        }

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                if (closeListener != null) {
                    closeListener.handleDialogClose(null);
                }
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    public static void showToast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
