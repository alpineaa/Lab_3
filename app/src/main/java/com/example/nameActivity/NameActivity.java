package com.example.nameActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nameactivity.R;

public class NameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        Button buttonThankYou = findViewById(R.id.buttonThankYou);
        Button buttonDontCallMeThat = findViewById(R.id.buttonDontCallMeThat);

        // Get the user's name passed from the previous activity
        String name = getIntent().getStringExtra("name");
        textViewWelcome.setText("Welcome " + name + "!");

        buttonThankYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the result to 1 and return to the previous activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", 1);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        buttonDontCallMeThat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the result to 0 and return to the previous activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", 0);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
