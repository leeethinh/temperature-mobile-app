package com.thinhle.homework1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText value;
    private TextView resultsText;
    private TextView topText;
    private TextView scrollTextView;
    private TextView bottomText;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = findViewById(R.id.firstValueEditText);
        resultsText = findViewById(R.id.resultTextView);
        topText = findViewById(R.id.topTextView);
        bottomText = findViewById(R.id.bottomTextView);
        radioGroup = findViewById(R.id.radioGroupID);
        scrollTextView = findViewById(R.id.scrollTextView);
        scrollTextView.setMovementMethod(new ScrollingMovementMethod());

    }

    public void doConvert(View v) {
        String value1 = value.getText().toString();
        if (value1.trim().isEmpty()) return;
        double num = Double.parseDouble(value1); //String => Double
        Log.d(TAG, "doConvert:" + num);

        int radioChecked = radioGroup.getCheckedRadioButtonId(); // if you want to get an ID, they are usually an int
        if (radioChecked == R.id.fahrenheitRadioButton) {
            Double temp = (num - 32) / 1.8;
            resultsText.setText(String.format("%.1f", temp)); //setText() uses a string not a value
            String oldText = scrollTextView.getText().toString();
            scrollTextView.setText(String.format("%.1f F" + "==>" + "%.1f C\n%s", num, temp, oldText));
        } else {
            Double temp = (num * 1.8) + 32;
            resultsText.setText(String.format("%.1f", temp));
            String oldText = scrollTextView.getText().toString();
            scrollTextView.setText(String.format("%.1f C" + "==>" + "%.1f F\n%s", num, temp, oldText));
        }
    }

    public void radioClicked(View v) {
        if (v.getId() == R.id.fahrenheitRadioButton) {
            topText.setText("Fahrenheit Degrees:");
            bottomText.setText("Celsius Degrees:");
        } else {
            topText.setText("Celsius Degrees:");
            bottomText.setText("Fahrenheit Degrees:");
        }

    }

    public void doClear(View v) {
        scrollTextView.setText("");
        value.setText("");
        resultsText.setText("");
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("HISTORY", scrollTextView.getText().toString());
        outState.putString("VALUE", value.getText().toString());
        outState.putString("RESULT", resultsText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
    super.onRestoreInstanceState(savedInstanceState);
    scrollTextView.setText(savedInstanceState.getString("HISTORY"));
    value.setText(savedInstanceState.getString("VALUE"));
    resultsText.setText(savedInstanceState.getString("RESULT"));
    }
}