package com.startandroid.foxminded;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    //    private Button button;
    private char[] textOfLayout;
    private char[] anagram;
    private final String TAG = "MYLOG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
//        button = (Button) findViewById(R.id.button);
        anagram = null;

        Log.i(TAG, "onCreate finish");
    }

    public void getAnagrams(View view) {

        Log.i(TAG, "getAnagrams start");
        textOfLayout = (editText.getText().toString()).toCharArray();
        anagram = new char[textOfLayout.length];
        int startLetter = 0;
        int finishLetter = 0;

        for (int i = 0; i < textOfLayout.length; i++) {
            char temp = textOfLayout[i];

            if (checkLatinAlphabet(temp)) {
                finishLetter++;
                if (i == textOfLayout.length - 1) {
                    rearrangingLetter(startLetter, finishLetter);
                }
            } else {
                rearrangingLetter(startLetter, finishLetter);
                startLetter = finishLetter + 1;
                finishLetter = finishLetter + 1;
                anagram[i] = textOfLayout[i];
            }
        }

        String result = "";
        for (int i = 0; i < anagram.length; i++) {
            result += Character.toString(anagram[i]);
        }

        textView.setText(result);


    }

    private static boolean checkLatinAlphabet(char c) {

        boolean check;

        if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'))) {

            check = true;
            return check;
        } else {
            check = false;
            return check;

        }

    }

    private void rearrangingLetter(int start, int finish) {
        for (int i = 0; i < finish - start; i++) {
            anagram[start + i] = textOfLayout[finish - i - 1];
        }
    }


}