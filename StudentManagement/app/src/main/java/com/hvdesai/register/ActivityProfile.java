package com.hvdesai.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityProfile extends AppCompatActivity {

    String str_name="",str_email = "", str_password = "",
    str_phone = "", str_country = "";

    TextView txt_name = null,txt_email = null,
            txt_phone = null,txt_password = null,
            txt_country = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Get data from Previous Activity
        Intent obj = getIntent();
        str_name = obj.getStringExtra("name");
        str_email = obj.getStringExtra("email");
        str_password = obj.getStringExtra("password");
        str_phone = obj.getStringExtra("phone");
        str_country = obj.getStringExtra("country");

        //Initialize the components
        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_email = (TextView) findViewById(R.id.txt_email);
        txt_phone = (TextView) findViewById(R.id.txt_phone);
        txt_password = (TextView) findViewById(R.id.txt_password);
        txt_country = (TextView) findViewById(R.id.txt_country);

        //Bind the data to components
        txt_name.setText(str_name);
        txt_email.setText(str_email);
        txt_password.setText(str_password);
        txt_phone.setText(str_phone);
        txt_country.setText(str_country);
    }
}
