package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText emailr, passwordr;
    Button register, loginr;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        emailr = findViewById( R.id.emailr );
        passwordr = findViewById( R.id.passwordr );
        register = findViewById( R.id.register );
        loginr = findViewById( R.id.loginr );
        
        Cache cache = new DiskBasedCache( getCacheDir(), 1024 * 1024 );
        Network network = new BasicNetwork( new HurlStack() );
        requestQueue = new RequestQueue( cache, network );
        requestQueue.start();

        loginr.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, Main2Activity.class );
                startActivity( intent );
            }
        } );


        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=emailr.getText().toString();
                final String pass=passwordr.getText().toString();
                if(email.isEmpty()||pass.isEmpty()){
                    Toast.makeText( MainActivity.this, "Enter Email Or Password", Toast.LENGTH_SHORT ).show();
                }else {
                    StringRequest stringRequest = new StringRequest
                            ( Request.Method.POST, link.url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText( MainActivity.this, response, Toast.LENGTH_SHORT ).show();
                                    requestQueue.stop();

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            } 
                            ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<>();
                            map.put( "email", email );
                            map.put( "password", pass );

                            return map;
                        }
                    };

                    requestQueue.add( stringRequest );
                }
            }
        } );
    }
}
