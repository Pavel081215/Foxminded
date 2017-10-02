package com.startandroid.foxminded;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private char[] textOfLayout;
    private char[] anagram;
    private final String TAG = "MYLOG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
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


            if (!(temp == ' ')) {
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

        int temp = 0;

        int numberOfLetters = numberOfLetters(start, finish); // количестов не букв в слове
        int indexesChangePlace[] = new int[numberOfLetters];   // массив для индексов которые надоменять местами


        for (int i = 0; i < finish - start; i++) {
            if (checkLatinAlphabet(textOfLayout[start + i])) {
                indexesChangePlace[temp] = start + i;
                temp++;
            } else {
                anagram[start + i] = textOfLayout[start + i];
            }
        }

        for (int i = 0; i < indexesChangePlace.length; i++) {
            anagram[indexesChangePlace[i]] = textOfLayout[indexesChangePlace[indexesChangePlace.length - i - 1]];
        }
    }

    private int numberOfLetters(int start, int finish) {

        int number = 0;
        for (int i = 0; i < finish - start; i++) {
            if (checkLatinAlphabet(textOfLayout[start + i])) {
                number++;
            }
        }
        return number;
    }


}