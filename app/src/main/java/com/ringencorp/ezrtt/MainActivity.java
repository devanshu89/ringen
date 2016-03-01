package com.ringencorp.ezrtt;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ringencorp.ezrtt.broadcastrecivers.LocationUpdateReciver;
import com.ringencorp.ezrtt.provider.Locationprovider;
import com.ringencorp.ezrtt.services.LocationService;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver receiver;
    Double Latitude, Longitude;
    String Adress;
    Context context;
    TextView txtlocation, txtadress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtlocation = (TextView) findViewById(R.id.lblLocation);
        txtadress = (TextView) findViewById(R.id.lblAdress);
        context = getApplicationContext();


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, LocationUpdateReciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1800000,
                pendingIntent);
        //alarmManager.cancel(pendingIntent);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle = intent.getExtras();
                Latitude = bundle.getDouble("Latitude");
                Longitude = bundle.getDouble("Longitude");

                Adress = bundle.getString("address");

                txtlocation.setText(new DecimalFormat("##.####").format(Latitude) + " , " + new DecimalFormat("##.####").format(Longitude));
                // txtlocation.setText(Double.toString(Latitude)+","+Double.toString(Longitude));
                txtadress.setText(Adress);
                long unixTime = System.currentTimeMillis() / 1000L;
                createentry(Latitude, Longitude, Adress, 1, unixTime);


            }

            private void createentry(Double latitude, Double longitude, String adress, int i, long unixTime) {

               /* LocationDb lb =new LocationDb(getApplicationContext());
                 lb.addentry(latitude,longitude,adress,i);*/


                ContentValues values = new ContentValues();

                values.put(Locationprovider.KEY_Latitute, latitude);

                values.put(Locationprovider.KEY_Longtute, longitude);
                values.put(Locationprovider.KEY_Adress, adress);
                values.put(Locationprovider.KEY_userid, i);
                values.put(Locationprovider.KEY_timestamp, unixTime);
                Uri uri = getContentResolver().insert(
                        Locationprovider.CONTENT_URI, values);

                //Toast.makeText(getBaseContext(),"Javacodegeeks: " + uri.toString() + " inserted!", Toast.LENGTH_LONG).show();


            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(
                LocationService.BROADCAST_ACTION));
        startService(new Intent(getApplicationContext(), LocationService.class));


    }
}
