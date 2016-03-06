package com.ringencorp.ezrtt;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    Context appContext = null;
    String empNum;
    Button btVerify;
    EditText etNum;

    ProgressBar pgVerify;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_request_sms:
                empNum = etNum.getText().toString();

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.appContext = getApplicationContext();
        btVerify = (Button) findViewById(R.id.btn_request_sms);
        etNum = (EditText) findViewById(R.id.inputMobile);
        pgVerify = (ProgressBar) findViewById(R.id.pgverification);

    }
}
