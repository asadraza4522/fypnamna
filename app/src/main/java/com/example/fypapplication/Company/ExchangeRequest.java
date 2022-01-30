package com.example.fypapplication.Company;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fypapplication.R;

public class ExchangeRequest extends AppCompatActivity {
TextView nameinput, inputguardsno, inputdays, inputstartingtime, inputendingtime,contactinfo,addressinfo, requestdescription;
Button approverequest,cancelrequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_request);
        nameinput=findViewById(R.id.inputname);
        inputguardsno=findViewById(R.id.inputnoofguards);
        inputdays=findViewById(R.id.inputnoofdays);
        inputstartingtime=findViewById(R.id.startingtime);
        inputendingtime=findViewById(R.id.endingtime);
        contactinfo=findViewById(R.id.contactinfo);
        addressinfo=findViewById(R.id.addressinfo);
        requestdescription=findViewById(R.id.description);
        approverequest=findViewById(R.id.approverequest);
        cancelrequest=findViewById(R.id.cancelrequest);
       // Bundle bundle = getIntent().getExtras();
        //if (bundle != null) {
            //String nameofcompany = bundle.getString("message2");
            //String requiredguards = bundle.getString("message6");
            //String requireddays = bundle.getString("message1");
           // String starttime = bundle.getString("message");
           // String endtime = bundle.getString("message0");
           // String companycontact = bundle.getString("message3");
           // String companyaddress = bundle.getString("message4");
            //String requestcomments = bundle.getString("message5");
        SharedPreferences sharedpreferences=getSharedPreferences("temp", getApplicationContext().MODE_PRIVATE);

                String nameofcompany   = sharedpreferences.getString("nameKey", null);
                String requiredguards = sharedpreferences.getString("guardKey", null);
                String requireddays     = sharedpreferences.getString("dayKey", null);
                String starttime    = sharedpreferences.getString("startKey", null);
                String endtime      = sharedpreferences.getString("endKey", null);
                String companycontact      = sharedpreferences.getString("phoneKey", null);
                String companyaddress      = sharedpreferences.getString("addressKey", null);
                String requestcomments      = sharedpreferences.getString("describeKey", null);

                nameinput.setText(nameofcompany);
                inputguardsno.setText(requiredguards);
                inputdays.setText(requireddays);
                inputstartingtime.setText(starttime);
                inputendingtime.setText(endtime);
                contactinfo.setText(companycontact);
                addressinfo.setText(companyaddress);
                requestdescription.setText(requestcomments);




       // String nameofcompany=intent.getStringExtra("message2");
        //nameinput.setText(nameofcompany);
        //String requiredguards=intent.getStringExtra("message6");
        //inputguardsno.setText(requiredguards);
        //String requireddays=intent.getStringExtra("message1");
        //inputdays.setText(requireddays);
        //String starttime=intent.getStringExtra("message");
        //inputstartingtime.setText(starttime);
        //String endtime=intent.getStringExtra("message0");
        //inputendingtime.setText(endtime);
        //String companycontact=intent.getStringExtra("message3");
        //contactinfo.setText(companycontact);
        //String companyaddress=intent.getStringExtra("message4");
        //addressinfo.setText(companyaddress);
        //String requestcomments=intent.getStringExtra("message5");
        //requestdescription.setText(requestcomments);

    }
}