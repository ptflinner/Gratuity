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

        //Various views that need to be changed or give information
        final SeekBar tipPercent= (SeekBar)findViewById(R.id.tipBar);
        final TextView tipTitle=(TextView) findViewById(R.id.tipValue);
        final TextView totalTV=(TextView) findViewById(R.id.total);
        final EditText subtotalET=(EditText) findViewById(R.id.subtotal);
        final TextView tipValue=(TextView) findViewById(R.id.tip);

        //Listens for any changes made to the seek bar
        //Updates the displayed tip value when it is changed
        tipPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tipTitle.setText(String.valueOf(tipPercent.getProgress()*5)+"%");
                updateValues((CharSequence) subtotalET.getText(),tipPercent,subtotalET,tipValue,totalTV);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Listens for any changes to the subtotal field
        //Auto updates when the text is changed
        subtotalET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               updateValues(charSequence,tipPercent,subtotalET,tipValue,totalTV);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    //Used for the listeners to update the Total TextView
    //Does the calculations required
    private void updateValues(CharSequence charSequence,SeekBar tipPercent,EditText subtotalET,TextView tipValue,TextView totalTV){
        double subtotal=0;
        double tip=0;
        String subtotalString="";
        int tipPercentage=0;
        double total=0;

        //Checks if there is an entered value
        //If yes it figures out what it is
        //Else sets all values to 0
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

        //Sets the text for the Tip and Total TextViews
        tipValue.setText("$"+String.format("%.2f",tip));
        totalTV.setText("$"+String.format("%.2f",total));
    }

}
