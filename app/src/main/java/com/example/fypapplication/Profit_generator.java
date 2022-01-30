package com.example.fypapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Profit_generator extends AppCompatActivity {
    RadioButton daily,weekly,monthly;
    EditText packagecharges,noofguards,guardsalary,assignedweapons,weaponcost,fuelcost,foodcost;
    EditText monthlyprofit,yearlyprofit;
    Button submit;
    int pckage,noguard,gsalary,aweapons,wcost,fcost,focost,a1,totalresult,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profit_gen);

        daily=findViewById(R.id.daily);
        weekly=findViewById(R.id.weekly);
        monthly=findViewById(R.id.monthly);
        packagecharges=findViewById(R.id.package_charges);
        noofguards=findViewById(R.id.no_of_guards);
        guardsalary=findViewById(R.id.guard_salary);
        assignedweapons=findViewById(R.id.weapon_assigned);
        weaponcost=findViewById(R.id.weapon_cost);
        fuelcost=findViewById(R.id.fuel_cost);
        foodcost=findViewById(R.id.food_cost);
        monthlyprofit=findViewById(R.id.monthly_profit);
        yearlyprofit=findViewById(R.id.yearly_profit);
        submit=findViewById(R.id.submitButton);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 pckage=Integer.parseInt(packagecharges.getText().toString());
                 noguard= Integer.parseInt(noofguards.getText().toString());
                 gsalary=Integer.parseInt(guardsalary.getText().toString());
                 aweapons=Integer.parseInt(assignedweapons.getText().toString());
                 wcost=Integer.parseInt(weaponcost.getText().toString());
                 fcost=Integer.parseInt(fuelcost.getText().toString());
                 focost=Integer.parseInt(foodcost.getText().toString());



                if (daily.isChecked()) {
                     a1 = noguard * gsalary + aweapons * wcost + fcost + focost;
                     result = pckage * 30 - a1;

                    monthlyprofit.setText(""+result);
                    totalresult = result * 12;
                    yearlyprofit.setText(""+totalresult);

                    Toast.makeText(getApplicationContext(), "result"+result, Toast.LENGTH_SHORT).show();
                } else if (weekly.isChecked()) {

                    int b1 = noguard * gsalary + aweapons * wcost + fcost + focost;
                    int result = pckage * 4 - b1;
                    monthlyprofit.setText(""+result);
                    int totalresult = result * 12;
                    yearlyprofit.setText(""+totalresult);

                } else if (monthly.isChecked()) {
                    int c1 = noguard * gsalary + aweapons * wcost + fcost + focost;
                    int result = pckage - c1;
                    monthlyprofit.setText(""+result);
                    int totalresult = result * 12;
                    yearlyprofit.setText(""+totalresult);
                }

            }
        });
        }
}