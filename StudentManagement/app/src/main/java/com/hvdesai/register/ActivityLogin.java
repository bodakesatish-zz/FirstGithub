package com.hvdesai.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    String email = "", password = "",
            entered_email="",entered_password="",
            country = "", name = "", phone = "";
    EditText edit_email=null,edit_password=null;
    Button btn_login=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_login);

        Toast.makeText(ActivityLogin.this,
                "SessionVariables->\n"+
        "Name->"+((MyApplication) getApplication()).get_session("name")+"\n"+
        "Email->"+((MyApplication) getApplication()).get_session("email")+"\n"+
        "Password->"+((MyApplication) getApplication()).get_session("password")                , Toast.LENGTH_LONG).show();
        //To get the intent data
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        name = intent.getStringExtra("name");
        country = intent.getStringExtra("country");
        phone = intent.getStringExtra("phone");


        edit_email = (EditText)findViewById(R.id.edit_username);
        edit_password = (EditText)findViewById(R.id.edit_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            entered_email = edit_email.getText().toString();
            entered_password = edit_password.getText().toString();
            if(entered_email.length() == 0)
            {
                Toast.makeText(ActivityLogin.this,
                        "Please enter email",
                        Toast.LENGTH_SHORT).show();
            }
            else if(!android.util.Patterns.EMAIL_ADDRESS.
                    matcher(entered_email).matches())
            {
                Toast.makeText(ActivityLogin.this,
                        "Please enter valid email address",
                        Toast.LENGTH_SHORT).show();
                edit_email.requestFocus();
            }
            else if(entered_password.length() == 0)
            {
                Toast.makeText(ActivityLogin.this,
                        "Please enter password",
                        Toast.LENGTH_SHORT).show();
                edit_password.requestFocus();
            }
            else if(!entered_email.equals(
                    ((MyApplication) getApplication()).str_email))
            {// If entered email is not matched with
                // registered email
                Toast.makeText(ActivityLogin.this,
                        "Please enter valid email",
                        Toast.LENGTH_SHORT).show();
            }
            else if(!entered_password.equals(
                    ((MyApplication) getApplication()).str_password))
            {// If entered password is not matched with
                // registered password
                Toast.makeText(ActivityLogin.this,
                        "Please enter valid password",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(ActivityLogin.this,
                        "Succussfully Logged In",
                        Toast.LENGTH_SHORT).show();
                Intent intentLogin =
                        new Intent(ActivityLogin.this,
                                ActivityProfile.class);
                intentLogin.putExtra("name",name);
                intentLogin.putExtra("email",email);
                intentLogin.putExtra("password",password);
                intentLogin.putExtra("country",country);
                intentLogin.putExtra("phone",phone);
                startActivity(intentLogin);
                finish();

            }
            }
        });
        Toast.makeText(ActivityLogin.this,
                "Recieved Data\n"+
                "Email->"+email+"\n"+
                "Password->"+password,
                Toast.LENGTH_SHORT
                ).show();
    }
}
