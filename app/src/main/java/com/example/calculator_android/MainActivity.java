package com.example.calculator_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {

    private TextView resultCalculation;
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.Theme_DarkTheme);
        } else {
            setTheme(R.style.Theme_LightTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultCalculation = findViewById(R.id.resultCalculation);
        display = findViewById(R.id.displayEditText);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch themeSwitch = findViewById(R.id.switchTheme);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            themeSwitch.setChecked(true);
        }
        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            reset();
        });
    }

    private void reset() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        display.setSelection(cursorPos + strToAdd.length());
    }

    public void zeroBTNPush(View view){
        updateText(getResources().getString(R.string.nullText));
    }

    public void oneBTNPush(View view){
        updateText(getResources().getString(R.string.oneText));
    }

    public void twoBTNPush(View view){
        updateText(getResources().getString(R.string.twoText));
    }

    public void threeBTNPush(View view){
        updateText(getResources().getString(R.string.threeText));
    }

    public void fourBTNPush(View view){
        updateText(getResources().getString(R.string.fourText));
    }

    public void fiveBTNPush(View view){
        updateText(getResources().getString(R.string.fiveText));
    }

    public void sixBTNPush(View view){
        updateText(getResources().getString(R.string.sixText));
    }

    public void sevenBTNPush(View view){
        updateText(getResources().getString(R.string.sevenText));
    }

    public void eightBTNPush(View view){
        updateText(getResources().getString(R.string.eightText));
    }

    public void nineBTNPush(View view){
        updateText(getResources().getString(R.string.nineText));
    }

    public void multiplyBTNPush(View view){
        updateText(getResources().getString(R.string.multiplyText));
    }

    public void divideBTNPush(View view){
        updateText(getResources().getString(R.string.divideText));
    }

    public void subtractBTNPush(View view){
        updateText(getResources().getString(R.string.subtractText));
    }

    public void sumBTNPush(View view){
        updateText(getResources().getString(R.string.sumText));
    }

    public void clearBTNPush(View view){
        display.setText("");
        resultCalculation.setText("");
    }

    public void openBTNPush(View view){
        updateText(getResources().getString(R.string.parenthesesOpenText));
    }

    public void closeBTNPush(View view){
        updateText(getResources().getString(R.string.parenthesesCloseText));
    }

    public void decimalBTNPush(View view){
        updateText(getResources().getString(R.string.decimalText));
    }

    public void equalBTNPush(View view){
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        Expression exp = new Expression(userExp);
        String resultExp = String.valueOf(exp.calculate());
        resultCalculation.setText(resultExp);
        display.setSelection(userExp.length());
    }
    

    public void backspaceBTNPush(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }
}