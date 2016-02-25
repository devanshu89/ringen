package com.ringencorp.ezrtt;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ringencorp.ezrtt.broadcastrecivers.LocationUpdateReciver;
import com.ringencorp.ezrtt.constantclasses.Constant;
import com.ringencorp.ezrtt.constantclasses.JSONParser;
import com.ringencorp.ezrtt.constantclasses.PrefManager;
import com.ringencorp.ezrtt.constantclasses.Utils;
import com.ringencorp.ezrtt.services.HttpService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by faraz on 08/02/2016.
 */
public class VerificationScreen extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = VerificationScreen.class.getSimpleName();
    Context cont;
    //  inputName, inputEmail,
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private Button btnRequestSms, btnVerifyOtp;
    private EditText inputMobile, inputOtp;
    private ProgressBar progressBar;
    private PrefManager pref;
    private ImageButton btnEditMobile;
    private TextView txtEditMobile;
    private LinearLayout layoutEditMobile;

    /**
     * Regex to validate the mobile number
     * mobile number should be of 10 digits length
     *
     * @param mobile
     * @return
     */
    private static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verificationscreen);
        cont = getApplicationContext();
        viewPager = (ViewPager) findViewById(R.id.viewPagerVertical);
        //  inputName = (EditText) findViewById(R.id.inputName);
        // inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputMobile = (EditText) findViewById(R.id.inputMobile);
        inputOtp = (EditText) findViewById(R.id.inputOtp);
        btnRequestSms = (Button) findViewById(R.id.btn_request_sms);
        btnVerifyOtp = (Button) findViewById(R.id.btn_verify_otp);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnEditMobile = (ImageButton) findViewById(R.id.btn_edit_mobile);
        txtEditMobile = (TextView) findViewById(R.id.txt_edit_mobile);
        layoutEditMobile = (LinearLayout) findViewById(R.id.layout_edit_mobile);

        // view click listeners
        btnEditMobile.setOnClickListener(this);
        btnRequestSms.setOnClickListener(this);
        btnVerifyOtp.setOnClickListener(this);

        // hiding the edit mobile number
        layoutEditMobile.setVisibility(View.GONE);


        AlarmManager alarmManager = (AlarmManager) cont.getSystemService(Context.ALARM_SERVICE);
        Intent intent1 = new Intent(cont, LocationUpdateReciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(cont, 0, intent1, 0);
        // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1800000,pendingIntent);
        alarmManager.cancel(pendingIntent);

        pref = new PrefManager(this);


        // Checking for user session
        // if user is already logged in, take him to main activity
        if (pref.isLoggedIn()) {
            Intent intent = new Intent(VerificationScreen.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            finish();
        }

        adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        /**
         * Checking if the device is waiting for sms
         * showing the user OTP screen
         */
        if (pref.isWaitingForSms()) {
            viewPager.setCurrentItem(1);
            layoutEditMobile.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_request_sms:
                validateForm();
                break;

            case R.id.btn_verify_otp:
                verifyOtp();
                break;

            case R.id.btn_edit_mobile:
                viewPager.setCurrentItem(0);
                layoutEditMobile.setVisibility(View.GONE);
                pref.setIsWaitingForSms(false);
                break;
        }
    }

    /**
     * Validating user details form
     */
    private void validateForm() {
        // String name = inputName.getText().toString().trim();
        //String email = inputEmail.getText().toString().trim();
        String mobile = inputMobile.getText().toString().trim();

        // validating empty name and email
      /*  if (name.length() == 0 || email.length() == 0) {
            Toast.makeText(getApplicationContext(), "Please enter your details", Toast.LENGTH_SHORT).show();
            return;
        }
*/
        // validating mobile number
        // it should be of 10 digits length
        if (isValidPhoneNumber(mobile)) {

            // request for sms
            progressBar.setVisibility(View.VISIBLE);

            // saving the mobile number in shared preferences
            pref.setMobileNumber(mobile);


            new requestForSMS().execute(mobile);

        } else {
            Toast.makeText(getApplicationContext(), "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Method initiates the SMS request on the server
     *

     * @param mobile user valid mobile number
     */
/*    private void requestForSMS(final String mobile) {
        StringRequest strReq = new StringRequest(Request.Method.POST, Constant.URL_REQUEST_SMS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                try {
                    JSONObject responseObj = new JSONObject(response);

                    // Parsing json object response
                    // response will be a json object
                    boolean error = responseObj.getBoolean("error");
                    String message = responseObj.getString("message");
                 System.out.println("error-->"+" "+"msg-->"+message);
                    // checking for error, if not error SMS is initiated
                    // device should receive it shortly
                    if (!error) {
                        // boolean flag saying device is waiting for sms
                        pref.setIsWaitingForSms(true);

                        // moving the screen to next pager item i.e otp screen
                        viewPager.setCurrentItem(1);
                        txtEditMobile.setText(pref.getMobileNumber());
                        layoutEditMobile.setVisibility(View.VISIBLE);

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Error: " + message,
                                Toast.LENGTH_LONG).show();
                    }

                    // hiding the progress bar
                    progressBar.setVisibility(View.GONE);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();

                    progressBar.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }) {

            *//**
     * Passing user parameters to our server
     * @return
     *//*

            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
               // params.put("name", name);
               // params.put("email", email);
                params.put("mobile", mobile);

                Log.e(TAG, "Posting params: " + params.toString());

                return params;
            }

        };

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq);
    }*/

    /**
     * sending the OTP to server and activating the user
     */
    private void verifyOtp() {
        String otp = inputOtp.getText().toString().trim();

        if (!otp.isEmpty()) {
            Intent grapprIntent = new Intent(getApplicationContext(), HttpService.class);
            grapprIntent.putExtra("otp", otp);
            startService(grapprIntent);
        } else {
            Toast.makeText(getApplicationContext(), "Please enter the OTP", Toast.LENGTH_SHORT).show();
        }
    }

    public class requestForSMS extends AsyncTask<String, Void, JSONObject> {

        protected void onPreExecute() {

        }

        @Override
        protected JSONObject doInBackground(String... params) {
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("mobile", params[0]));

            JSONObject jsonObjectFromUrl = new JSONParser().makeHttpRequest(Constant.URL_REQUEST_SMS, "POST", nameValuePair);
            return jsonObjectFromUrl;

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            try {
                if (result != null) {
                    System.out.println("far--->" + result);
                    String status = result.getString("error");
                    if (status.equalsIgnoreCase("false")) {
                        String message = result.getString("message");
                        pref.setIsWaitingForSms(true);

                        // moving the screen to next pager item i.e otp screen
                        viewPager.setCurrentItem(1);
                        txtEditMobile.setText(pref.getMobileNumber());
                        layoutEditMobile.setVisibility(View.VISIBLE);

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();


                    } else {

                        Utils.showToast(VerificationScreen.this, result.getString("message"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();

                progressBar.setVisibility(View.GONE);
            }

        }

        @Override
        protected void onCancelled() {

        }
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }

        public Object instantiateItem(View collection, int position) {

            int resId = 0;
            switch (position) {
                case 0:
                    resId = R.id.layout_sms;
                    break;
                case 1:
                    resId = R.id.layout_otp;
                    break;
            }
            return findViewById(resId);
        }
    }

}