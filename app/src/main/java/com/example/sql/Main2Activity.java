package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    EditText editText1, editText2;
    Button button1, button2;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        editText1 = findViewById( R.id.emaill );
        editText2 = findViewById( R.id.passwordl );
        button1 = findViewById( R.id.register );
        button2 = findViewById( R.id.login );
        
        requestQueue = Volley.newRequestQueue( this );

        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String s3 = editText1.getText().toString();
                final String s4 = editText2.getText().toString();

                if (s3.isEmpty() || s4.isEmpty()) {
                    Toast.makeText( Main2Activity.this,
                                    "Enter Email Or Password", Toast.LENGTH_SHORT ).show();
                } else {
                    StringRequest stringRequest = new StringRequest
                            ( Request.Method.POST, link.url1, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText( Main2Activity.this, response,
                                                    Toast.LENGTH_SHORT ).show();
                                    
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
                            map.put( "email", s3 );
                            map.put( "password", s4 );

                            return map;
                        }
                    };

                    requestQueue.add( stringRequest );

                }

            }
        } );

    }
}
