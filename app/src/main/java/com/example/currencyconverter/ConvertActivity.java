package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertActivity extends AppCompatActivity {

    TextView textView,textView2;
    EditText editText;
    Spinner spinner,spinner2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        editText=(EditText)findViewById (R.id.editText3_1);
        button=(Button)findViewById (R.id.button3_1);
        spinner=(Spinner)findViewById(R.id.spinner3_1);
        spinner2=(Spinner)findViewById(R.id.spinner3_2);
        editText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8, 2)});

        List<String> list2=new ArrayList<String>();
        list2.add("US Dollar");
        list2.add("Canadian Dollar");
        list2.add("Indian Rupees");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list2);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner2.setAdapter(arrayAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double result;
                Double amount=Double.parseDouble (editText.getText ().toString ());

                if(spinner.getSelectedItem ().toString ()=="Indian Rupees" && spinner2.getSelectedItem().toString()=="US Dollar" ){

                    result=amount/76.61800;
                    TextView textview2 = (TextView) findViewById(R.id.textView3_5);
                   textview2.setText(result.toString());
                }
                else if(spinner.getSelectedItem ().toString ()=="Indian Rupees" && spinner2.getSelectedItem ().toString ()=="Canadian Dollar"){

                    result=amount/54.33500;
                    TextView textview2 = (TextView) findViewById(R.id.textView3_5);
                    textview2.setText(result.toString());
                }
                else if(spinner.getSelectedItem ().toString ()=="US Dollar" && spinner2.getSelectedItem().toString()=="Indian Rupees" ){

                    result=amount*76.61800;
                    TextView textview2 = (TextView) findViewById(R.id.textView3_5);
                    textview2.setText(result.toString());
                }
                else if(spinner.getSelectedItem ().toString ()=="US Dollar" && spinner2.getSelectedItem ().toString ()=="Canadian Dollar"){

                    result=amount/1.41;
                    TextView textview2 = (TextView) findViewById(R.id.textView3_5);
                    textview2.setText(result.toString());
                }
                else if(spinner.getSelectedItem ().toString ()=="Canadian Dollar" && spinner2.getSelectedItem().toString()=="Indian Rupees" ){

                    result=amount*54.33500;
                    TextView textview2 = (TextView) findViewById(R.id.textView3_5);
                    textview2.setText(result.toString());
                }
                else if(spinner.getSelectedItem ().toString ()=="Canadian Dollar" && spinner2.getSelectedItem ().toString ()=="US Dollar"){

                    result=amount*1.41;
                    TextView textview2 = (TextView) findViewById(R.id.textView3_5);
                    textview2.setText(result.toString());
                }

            }
        });
    }

    private class DecimalDigitsInputFilter implements InputFilter {
        private Pattern mPattern;
        public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero ) {
            mPattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
        }
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher matcher = mPattern.matcher(dest);
            if (!matcher.matches())
                return "";
            return null;
        }
    }
}
