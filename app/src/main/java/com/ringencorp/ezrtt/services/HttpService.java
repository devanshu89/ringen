package com.ringencorp.ezrtt.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ringencorp.ezrtt.MainActivity;
import com.ringencorp.ezrtt.constantclasses.Constant;
import com.ringencorp.ezrtt.constantclasses.JSONParser;
import com.ringencorp.ezrtt.constantclasses.PrefManager;
import com.ringencorp.ezrtt.constantclasses.Utils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HttpService extends IntentService {

    private static String TAG = HttpService.class.getSimpleName();

    public HttpService() {
        super(HttpService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String otp = intent.getStringExtra("otp");
            verifyOtp(otp);


        }
    }


    /**
     * Posting the OTP to server and activating the user
     *
     * @param otp otp received in the SMS
     */
    private void verifyOtp(final String otp) {
        new verifyOtp().execute(otp);

    }

    public class verifyOtp extends AsyncTask<String, Void, JSONObject> {

        protected void onPreExecute() {

        }

        @Override
        protected JSONObject doInBackground(String... params) {
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("otp", params[0]));


            JSONObject jsonObjectFromUrl = new JSONParser().makeHttpRequest(Constant.URL_VERIFY_OTP, "POST", nameValuePair);
            return jsonObjectFromUrl;

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                if (result != null) {
                    String status = result.getString("error");
                    if (status.equalsIgnoreCase("false")) {
                        String message = result.getString("message");
                        JSONObject profileObj = result.getJSONObject("profile");

                        //  String name = profileObj.getString("name");
                        //  String email = profileObj.getString("email");
                        String mobile = profileObj.getString("mobile");

                        PrefManager pref = new PrefManager(getApplicationContext());
                        pref.createLogin(mobile);

                        Intent intent = new Intent(HttpService.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();


                    } else {

                        Utils.showToast(getApplicationContext(), result.getString("message"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();

            }

        }

        @Override
        protected void onCancelled() {

        }
    }
}
