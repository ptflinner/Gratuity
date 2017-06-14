package com.example.patrick.gratuity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final SeekBar tipPercent= (SeekBar)findViewById(R.id.tipBar);
        final TextView tipTitle=(TextView) findViewById(R.id.tipValue);
        final TextView totalTV=(TextView) findViewById(R.id.total);
        final EditText subtotalET=(EditText) findViewById(R.id.subtotal);
        final TextView tipValue=(TextView) findViewById(R.id.tip);

        tipPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tipTitle.setText(String.valueOf(tipPercent.getProgress()*5)+"%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        subtotalET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double subtotal=0;
                double tip=0;
                String subtotalString="";
                int tipPercentage=0;
                double total=0;

                if(charSequence.length()!=0){

                    subtotalString=subtotalET.getText().toString();
                    subtotal=Double.parseDouble(subtotalString);

                    tipPercentage=Integer.parseInt(String.valueOf(tipPercent.getProgress()*5));

                    tip=Double.parseDouble(String.valueOf(tipPercent.getProgress()*5));
                    tip/=100;
                    tip*=subtotal;
                    total=subtotal+tip;


                }
                else{
                    subtotal=0;
                    tip=0;
                    total=0;
                }
                tipValue.setText("$"+String.format("%.2f",tip));
                totalTV.setText("$"+String.format("%.2f",total));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


}
