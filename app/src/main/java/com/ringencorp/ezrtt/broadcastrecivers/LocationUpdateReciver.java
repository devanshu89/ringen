package com.ringencorp.ezrtt.broadcastrecivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ringencorp.ezrtt.constantclasses.Constant;
import com.ringencorp.ezrtt.constantclasses.JSONParser;
import com.ringencorp.ezrtt.constantclasses.Utils;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faraz on 01/02/2016.
 */
public class LocationUpdateReciver extends BroadcastReceiver {

    public static Context cont;

    @Override
    public void onReceive(Context context, Intent intent) {
        //get and send location information
        cont = context;

        String URL = "content://com.example.faraz.provider.Location/users";
        Uri friends = Uri.parse(URL);

        Cursor c = context.getContentResolver().query(friends, null, null, null, null);
        String result = "";

        if (!c.moveToFirst()) {
            Toast.makeText(context, result + " no content yet!", Toast.LENGTH_LONG).show();
        } else {
           /* do{
                result = result + "\n" + c.getString(c.getColumnIndex(Locationprovider.KEY_Latitute)) +
                        " \n" +  c.getString(c.getColumnIndex(Locationprovider.KEY_Longtute)) +
                        "\n " + c.getString(c.getColumnIndex(Locationprovider.KEY_Adress));
            } while (c.moveToNext());
            //Toast.makeText(context, result, Toast.LENGTH_LONG).show();*/

            JSONObject jobj;
            JSONArray arr = new JSONArray();

            while (c.moveToNext()) {
                jobj = new JSONObject();

//                try {
//                    jobj.put("userid", c.getString(c.getColumnIndex(Locationprovider.KEY_userid)));
//                    jobj.put("user_latitute", c.getString(c.getColumnIndex(Locationprovider.KEY_Latitute)));
//                    jobj.put("user_longtitute", c.getString(c.getColumnIndex(Locationprovider.KEY_Longtute)));
//                    jobj.put("user_address", c.getString(c.getColumnIndex(Locationprovider.KEY_Adress)));
//                    jobj.put("user_timestamp", c.getString(c.getColumnIndex(Locationprovider.KEY_timestamp)));
//                    jobj.put("user_mobie", Constant.USER_MOBILE);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                arr.put(jobj);
            }

            jobj = new JSONObject();
            try {
                jobj.put("data", arr);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String st = jobj.toString();


            System.out.println("-->" + st);

            if (Utils.isInternetConnected(cont)) {

                //  new sendlocation().execute("sendlocation",st);

            } else {
                Utils.showAlertDialog(cont, "Alert", "Internet Connection Unavailable", null);

            }


        }
        //Toast.makeText(context,"hellllllllllllllllllllloo",Toast.LENGTH_LONG).show();
    }


    public class sendlocation extends AsyncTask<String, Void, JSONObject> {

        protected void onPreExecute() {

        }

        @Override
        protected JSONObject doInBackground(String... params) {
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("method", params[0]));
            nameValuePair.add(new BasicNameValuePair("data", params[1]));

            JSONObject jsonObjectFromUrl = new JSONParser().makeHttpRequest(Constant.BASE_URL, "POST", nameValuePair);
            return jsonObjectFromUrl;

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                if (result != null) {
                    String status = result.getString("success");
                    if (status.equalsIgnoreCase("true")) {


                    } else {

                        Utils.showToast(cont, result.getString("message"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onCancelled() {

        }
    }
}