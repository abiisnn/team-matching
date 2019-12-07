package mx.ipn.escom.team_matching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;

public class form_send extends AppCompatActivity {
    Button startAgain;
    Button tryAgain;
    String url;
    AsyncUrlAccess sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_send);

        Intent prev = getIntent();

        startAgain = findViewById(R.id.send_start_again_button);
        tryAgain = findViewById(R.id.send_try_again);

        url = new String();
        url += "http://10.100.66.182:8000/save?";
        url += prev.getStringExtra("groupid") + "-";
        url += prev.getStringExtra("username") + "-";
        url += prev.getStringExtra("name") + "-";
        url += prev.getStringExtra("skills") + "-";
        url += prev.getStringExtra("personality");

        Toast.makeText(form_send.this, url, Toast.LENGTH_LONG).show();
        //Log.d("sending", url);
        sendData = new AsyncUrlAccess(url);
        sendData.setListener(new AsyncUrlAccess.Listener() {
            @Override
            public void onResult(String result) {
                if(result == null){
                    Log.d("sending", "Error.");
                    Toast.makeText(form_send.this, "Error.\nPlease verify your internet connection.", Toast.LENGTH_LONG).show();
                }
                Log.d("result", result);
                if(result.equals("OK")){
                    Log.d("sending", "Successful");
                    Toast.makeText(form_send.this, "Successful Registered", Toast.LENGTH_LONG).show();
                }else{
                    Log.d("sending", "Error.");
                    Toast.makeText(form_send.this, "Error.\nPlease verify your internet connection.", Toast.LENGTH_LONG).show();
                }
            }
        });
        sendData.execute(url);

        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(form_send.this, form_basic_data.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncUrlAccess secondTry = new AsyncUrlAccess(url);
                secondTry.setListener(new AsyncUrlAccess.Listener() {
                    @Override
                    public void onResult(String result) {
                        if(result != null && result == "Ok"){
                            Log.d("sending", "Successful");
                            Toast.makeText(form_send.this, "Successful Registered,", Toast.LENGTH_LONG).show();
                        }else{
                            Log.d("sending", "Error.");
                            Toast.makeText(form_send.this, "Error.\nPlease verify your internet connection.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                secondTry.execute(url);
            }
        });
    }
}


