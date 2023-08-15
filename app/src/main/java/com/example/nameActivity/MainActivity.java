package com.example.nameActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nameActivity.NameActivity;
import com.example.nameactivity.R;

public class MainActivity extends AppCompatActivity {
    private static final int NAME_ACTIVITY_REQUEST_CODE = 1;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        // Load the user's name from SharedPreferences and put it in the EditText
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String savedName = preferences.getString("name", "");
        editText.setText(savedName);

        Button buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();

                // Save the current value inside the EditText to SharedPreferences
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", name);
                editor.apply();

                // Launch NameActivity and pass the name using startActivityForResult
                Intent intent = new Intent(MainActivity.this, NameActivity.class);
                intent.putExtra("name", name);
                startActivityForResult(intent, NAME_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save the current value inside the EditText to SharedPreferences
        String name = editText.getText().toString();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NAME_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("result", 0);

                if (result == 0) {
                    // User wants to change their name
                } else if (result == 1) {
                    // User is happy, close the app
                    finish();
                }
            }
        }
    }
}
