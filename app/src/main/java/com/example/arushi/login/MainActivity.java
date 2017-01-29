package com.example.arushi.login;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.lang.*;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static android.widget.Toast.LENGTH_LONG;
import static org.apache.http.protocol.HTTP.USER_AGENT;


public class MainActivity extends AppCompatActivity {
    EditText email, pass;
    String emailid, Pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        email=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.pw);

        //Register button
            final TextView signup =(TextView)findViewById(R.id.reg);
        signup.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }
        });
        //Submit Button for login
        Button saveme=(Button)findViewById(R.id.save);
        saveme.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v) {

                //to check if any one of them is NULL
                if(email.length()==0 || pass.length()==0){

                    Toast.makeText(getApplicationContext(), "Invalid Input!", Toast.LENGTH_LONG).show();

                }
                //to check if pw is less than 8 characters
                else if(pass.length() <= 8){

                    Toast.makeText(getApplicationContext(), "Invalid Input!", Toast.LENGTH_LONG).show();

                }
                //to check if email contains @
                else if(!email.getText().toString().contains("@")) {
                    Toast.makeText(getApplicationContext(), "Invalid Input!", Toast.LENGTH_LONG).show();
                }

                else {

                emailid = email.getText().toString();
                Pass = pass.getText().toString();

                RequestQueue MyRequestQueue = Volley.newRequestQueue(getBaseContext());

                String url= R.string.serverAddress + "/checkmatch";
                StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Context context = getApplicationContext();
                        Toast.makeText(context, R.string.popup, LENGTH_LONG)
                                .show();

                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Context context = getApplicationContext();
                        Toast.makeText(context, R.string.fail, LENGTH_LONG)
                                .show();
                        error.printStackTrace();
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("email", emailid);
                        MyData.put("pass", Pass);
                        return MyData;
                    };
                };
                MyRequestQueue.add(MyStringRequest);
                Intent i = new Intent(MainActivity.this, afterlogin.class);
                startActivity(i);


            }}

        });



    }

}