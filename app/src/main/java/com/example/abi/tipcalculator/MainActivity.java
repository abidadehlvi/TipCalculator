package com.example.abi.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String BILL_AMOUNT = "total bill amount in dollars";
    private static final String TIP_PERCENT = "tip in percentage";
    private static final String TOTAL_TIP = "tip in dollars";
    private static final String TOTAL_AMOUNT_WITH_TIP = "total including tip";
    public TextView bill;
    public EditText billAmount;
    public TextView tip;
    public Button plus;
    public Button minus;
    public TextView tipPercent;
    public TextView percent;
    public TextView tipAmount;
    public TextView tipTotal;
    public TextView total;
    public TextView totalBill;
    public double billInDollars;
    public int tipInPercent;
    public double totalTip;
    public double totalBillInDollars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            savedInstanceState.getDouble(BILL_AMOUNT);
            savedInstanceState.getInt(TIP_PERCENT);
            savedInstanceState.getDouble(TOTAL_TIP);
            savedInstanceState.getDouble(TOTAL_AMOUNT_WITH_TIP);
        }
        bill = findViewById(R.id.tv_bill);
        billAmount = findViewById(R.id.et_billAmount);
        tip = findViewById(R.id.tv_tip);
        plus = findViewById(R.id.btn_increase);
        minus = findViewById(R.id.btn_decrease);
        tipPercent = findViewById(R.id.tv_tip_percent);
        percent = findViewById(R.id.tv_percent);
        tipAmount = findViewById(R.id.tv_tipAmount);
        tipTotal = findViewById(R.id.tv_totalTipAmount);
        total = findViewById(R.id.tv_total);
        totalBill = findViewById(R.id.tv_totalBill);

        billAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {update();}
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tipPercent.getText().toString();
                int increase_percent = Integer.parseInt(text);
                increase_percent++;
                tipPercent.setText(String.valueOf(increase_percent));
                update();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = tipPercent.getText().toString();
                int decrease_percent = Integer.parseInt(text);
                if(decrease_percent == 0){
                    decrease_percent = 0;
                }
                else {
                    decrease_percent--;
                }
                tipPercent.setText(String.valueOf(decrease_percent));
                update();
            }
        });
    }

    //Calculating tip amount and set it to textview
    public void update(){
        try {
            String bill = billAmount.getText().toString();

            String tip = tipPercent.getText().toString();
            billInDollars = Double.parseDouble(bill); //100
            tipInPercent = Integer.parseInt(tip);
            totalTip = billInDollars * tipInPercent / 100;
            tipTotal.setText(String.valueOf(Math.round(totalTip*100.0)/100.0));
            totalBillInDollars = billInDollars + totalTip;
            totalBill.setText(String.valueOf(Math.round(totalBillInDollars*100.0)/100.0));

        }catch (NumberFormatException ex){
            System.out.println(ex);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.putDouble(BILL_AMOUNT, billInDollars);
        savedInstanceState.putInt(TIP_PERCENT, tipInPercent);
        savedInstanceState.putDouble(TOTAL_TIP, totalTip);
        savedInstanceState.putDouble(TOTAL_AMOUNT_WITH_TIP, totalBillInDollars);
    }
}
