package com.hvdesai.register;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityRegister extends AppCompatActivity {

    private Spinner spinn_country1 = null;
    String arr_country[] = {"America","Canada","India"};
    EditText edit_name=null,edit_email=null,edit_password=null,
             edit_confirm_password=null,edit_phone=null;
    String str_name="",str_email="",str_country="",
            str_password="",str_confirm_password="",str_phone="";
    Button btn_submit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinn_country1 = (Spinner) findViewById(R.id.spinn_country1);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.select_dialog_item,
                        arr_country);
        spinn_country1.setAdapter(arrayAdapter);

        edit_name = (EditText)findViewById(R.id.edt_namee);
        edit_email = (EditText)findViewById(R.id.edt_email);
        edit_password = (EditText)findViewById(R.id.edt_pass1);
        edit_confirm_password = (EditText)findViewById(R.id.edt_pass2);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        edit_phone = (EditText)findViewById(R.id.edit_phone);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        str_name = edit_name.getText().toString();
                        str_email = edit_email.getText().toString();
                        str_password = edit_password.getText().toString();
                        str_confirm_password = edit_confirm_password.getText().toString();
                        str_country = spinn_country1.getSelectedItem().toString();
                        str_phone = edit_phone.getText().toString();
                String str_error_message = "";
                if(str_name.length()==0)
                {
                    /*Toast.makeText(ActivityRegister.this,
                            "Name is empty please enter name",
                            Toast.LENGTH_LONG).show();*/
                    str_error_message = "Name is empty please enter name";
                    show_error_message(str_error_message);
                    edit_name.requestFocus();
                }
                else if(str_email.length() == 0)
                {
                    /*Toast.makeText(ActivityRegister.this,
                            "Email is empty please enter email",
                            Toast.LENGTH_LONG).show();*/
                    str_error_message = "Email is empty please enter email";
                    show_error_message(str_error_message);
                    edit_email.requestFocus();
                }
                else if(!android.util.Patterns.EMAIL_ADDRESS.
                        matcher(str_email).matches())
                {
                    //Toast.makeText(ActivityRegister.this,"Please enter valid email address",Toast.LENGTH_SHORT).show();
                    str_error_message = "Please enter valid email address";
                    show_error_message(str_error_message);
                    edit_email.requestFocus();
                }
                else if(str_phone.length()<7  ||  str_phone.length()>13)
                {
                    /*Toast.makeText(ActivityRegister.this,
                            "Invalid phone number",
                            Toast.LENGTH_LONG).show();*/
                    str_error_message = "Invalid phone number";
                    show_error_message(str_error_message);
                    edit_phone.requestFocus();
                }
                else if(str_password.length() == 0)
                {
                    /*Toast.makeText(ActivityRegister.this,
                            "Password is empty please enter password",
                            Toast.LENGTH_LONG).show();*/
                    str_error_message = "Password is empty please enter password";
                    show_error_message(str_error_message);
                    edit_password.requestFocus();
                }
                else if(str_confirm_password.length() == 0)
                {
                    /*Toast.makeText(ActivityRegister.this,
                            "Confirm Password is empty" +
                            " please enter confirm password",
                            Toast.LENGTH_LONG).show();*/
                    str_error_message = "please enter confirm password";
                    show_error_message(str_error_message);
                    edit_confirm_password.requestFocus();
                }
                else if (!str_password.equals(str_confirm_password))
                {//if str_password do not match with str_confirm_passwp
                    Toast.makeText(ActivityRegister.this,
                            "Your password and confirm password do not match",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(ActivityRegister.this,
                            "Registration Successful\n" +
                                    "Name->" + str_name + "\n" +
                                    "Email->" + str_email + "\n" +
                                    "Password->" + str_password + "\n" +
                                    "ConfirmPassword->" + str_confirm_password + "\n" +
                                    "Country->" + str_country,
                            Toast.LENGTH_LONG).show();

                    //Save variables in session
                    ((MyApplication) getApplication()).
                            set_session("name", str_name);
                    ((MyApplication) getApplication()).
                            set_session("email", str_email);
                    ((MyApplication) getApplication()).
                            set_session("password", str_password);
                    ((MyApplication) getApplication()).
                            set_session("phone", str_phone);
                    ((MyApplication) getApplication()).
                            set_session("country", str_country);

                    //Assign values to application class variables
                    ((MyApplication) getApplication()).str_name = str_name;
                    ((MyApplication) getApplication()).str_email = str_email;
                    ((MyApplication) getApplication()).str_password = str_password;
                    ((MyApplication) getApplication()).str_country = str_country;
                    ((MyApplication) getApplication()).str_phone_number = str_phone;

                    //Store student data in database
                    int result = ((MyApplication) getApplication())
                            .dbObj.insert_student_data(
                                    str_name,
                                    str_email,
                                    str_country,
                                    str_phone,
                                    str_password);
                    if (result == -1)
                    {       Toast.makeText(ActivityRegister.this,
                                "Failed to insert in database",
                                Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ActivityRegister.this,
                            "Record inserted successfully.\n"+
                            "Result->"+result,
                            Toast.LENGTH_SHORT).show();
                    }

                    //Go to Login Activity
                    Intent intent = new Intent(ActivityRegister.this,
                                               ActivityLogin.class);
                    intent.putExtra("name",str_name);
                    intent.putExtra("email",str_email);
                    intent.putExtra("password",str_password);
                    intent.putExtra("country",str_country);
                    intent.putExtra("phone",str_phone);
                    startActivity(intent);
                    finish();

                }


            }
        });


    }

    public boolean is_valid_email(String email){
        boolean flag = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        return flag;
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder objBuilder =
                new AlertDialog.Builder(ActivityRegister.this);
        objBuilder.setTitle("HVDesai");
        objBuilder.setMessage("Do you really want to exit ?");
        objBuilder.setPositiveButton("Yes" ,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                            finish();
                    }
                });
        objBuilder.setNegativeButton("No" ,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = objBuilder.create();
        alertDialog.show();
    }

    public void show_error_message(String str_error_message)
    {
        AlertDialog.Builder objBuilder =
                new AlertDialog.Builder(ActivityRegister.this);
        objBuilder.setTitle("Alert");
        objBuilder.setMessage(str_error_message);
        objBuilder.setPositiveButton("Ok" ,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = objBuilder.create();
        alertDialog.show();
    }
}
