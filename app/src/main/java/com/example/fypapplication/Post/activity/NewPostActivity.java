package com.example.fypapplication.Post.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.AddToFirebase;
import com.example.fypapplication.Post.utils.Constants;
import com.example.fypapplication.Post.utils.Utils;
import com.example.fypapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Calendar;


public class NewPostActivity extends AppCompatActivity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {

    private EditText companayName,packageType,packageName, packagePrice, securityType, overNightRate, extraHoursRate, perHourRate, startDate, expireDate, description;
    private Button btnStartDate, btnExpireDate, btnAddPost,btnUploadPic;
    private ImageView postImageView;
    private ProgressBar progressBar1;

private RadioGroup guardType,radio_pkgType,radio_guardNo,radio_wType,radio_dutyHours,radio_duration;
private RadioButton armed,unarmed,corporate,ssg,premium,standard,low,num2,num5,num7,num10,riffles,bayonets,pistols,non_lethal;
private RadioButton one_year,two_year,five_year,ten_hour,fifteen_hour,eighteen_hour;
private FirebaseAuth mAuth;
    private StorageReference storageReference;

    private DatePickerDialog.OnDateSetListener mStartDateSetListener;
    private DatePickerDialog.OnDateSetListener mExpireDateSetListener;

    private String guard_type;
    private String pkgType,no_ofGuards,weapon_types,duty_durationn,duty_hours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);
        init();
        setListners();
        guardType=findViewById(R.id.radio1);
        radio_pkgType=findViewById(R.id.radio_pkgtype);
        radio_guardNo=findViewById(R.id.radio2);
        radio_wType=findViewById(R.id.radio_wType);
        radio_dutyHours=findViewById(R.id.radio_dutyHours);
        radio_duration=findViewById(R.id.radio_duration);

        radio_wType.setOnCheckedChangeListener(this);
        radio_pkgType.setOnCheckedChangeListener(this);
        guardType.setOnCheckedChangeListener(this);
        radio_guardNo.setOnCheckedChangeListener(this);
        radio_duration.setOnCheckedChangeListener(this);
        radio_dutyHours.setOnCheckedChangeListener(this);

        //Guard Type RAdion Buttons
        armed=findViewById(R.id.armed);
        unarmed=findViewById(R.id.un_armed);
        corporate=findViewById(R.id.corporate);
        ssg=findViewById(R.id.ssg);

        //package type RADIO BUTTONS
        premium=findViewById(R.id.premium);
        standard=findViewById(R.id.standard1);
        low=findViewById(R.id.discount);

        //Number of Guards
        num2=findViewById(R.id.num2);
        num5=findViewById(R.id.num5);
        num7=findViewById(R.id.num7);
        num10=findViewById(R.id.num10);

        // weapon types
        riffles=findViewById(R.id.riffle);
        bayonets=findViewById(R.id.bayonet);
        pistols=findViewById(R.id.pistol);
        non_lethal=findViewById(R.id.nonlethal);

        // duty hours and duration

        one_year=findViewById(R.id.one_year);
        two_year=findViewById(R.id.two_year);
        five_year=findViewById(R.id.five_year);
        ten_hour=findViewById(R.id.ten_hours);
        fifteen_hour=findViewById(R.id.fifteen_hours);
        eighteen_hour=findViewById(R.id.eighteen_hours);








    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri=data.getData();
                postImageView.setImageURI(imageUri);
                uploadImagetoFirebase(imageUri);
            }
        }
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference();
        packageName = (EditText) (findViewById(R.id.company));
        companayName = (EditText) (findViewById(R.id.package1));
        packagePrice = (EditText) (findViewById(R.id.charges));

        overNightRate = (EditText) (findViewById(R.id.overnight));
        extraHoursRate = (EditText) (findViewById(R.id.extrahour));
        perHourRate = (EditText) (findViewById(R.id.out_city));
        description = (EditText) (findViewById(R.id.description));
        startDate = (EditText) (findViewById(R.id.starttime));
        expireDate = (EditText) (findViewById(R.id.endtime));
        btnStartDate = (Button) (findViewById(R.id.select_start_time));
        btnExpireDate = (Button) (findViewById(R.id.select_end_time));
        btnAddPost = (Button) (findViewById(R.id.postadd));

    //    btnUploadPic = (Button) (findViewById(R.id.btnUploadPic));
      //  postImageView= findViewById(R.id.postImageView);
        progressBar1 = (ProgressBar) (findViewById(R.id.progress1));
    }

    private void setListners() {
        btnStartDate.setOnClickListener(this);
        btnExpireDate.setOnClickListener(this);
        btnAddPost.setOnClickListener(this);

        mStartDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d("TAG", "onDateSet: date:" + month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                startDate.setText(date);
            }
        };
        mExpireDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d("TAG", "onDateSet: date:" + month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                expireDate.setText(date);
            }
        };

 /*       btnUploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
            }
        });*/
    }

    private void validateAndAddPost() {
        final String packageNameV = packageName.getText().toString().trim();
        final String companyNameV = companayName.getText().toString().trim();
        final String packagePriceV = packagePrice.getText().toString().trim();
      //  final String securityTypeV = securityType.getText().toString().trim();
        final String overNightRateV = overNightRate.getText().toString().trim();
        final String extraHoursRateV = extraHoursRate.getText().toString().trim();
        final String perHourRateV = perHourRate.getText().toString().trim();
        final String startDateV = startDate.getText().toString().trim();
        final String expireDateV = expireDate.getText().toString().trim();
        final String descriptionV = description.getText().toString().trim();

        if (packageNameV.isEmpty()) {
            packageName.setError("Again enter your package name!");
            packageName.requestFocus();
            return;
        }
        if (packagePriceV.isEmpty()) {
            packagePrice.setError("Again enter your package price!");
            packagePrice.requestFocus();
            return;
        }

        if (overNightRateV.isEmpty()) {
            overNightRate.setError("Again enter your over night rate!");
            overNightRate.requestFocus();
            return;
        }
        if (extraHoursRateV.isEmpty()) {
            extraHoursRate.setError("Again enter your extra hours rate");
            extraHoursRate.requestFocus();
            return;
        }
        if (perHourRateV.isEmpty()) {
            perHourRate.setError("Again enter your per hour rate!");
            perHourRate.requestFocus();
            return;
        }
        if (startDateV.isEmpty()) {
            startDate.setError("Again enter your start date!");
            startDate.requestFocus();
            return;
        }
        if (expireDateV.isEmpty()) {
            expireDate.setError("Again enter your expire date!");
            expireDate.requestFocus();
            return;
        }
        if (descriptionV.isEmpty()) {
            description.setError("Again enter your description!");
            description.requestFocus();
            return;
        }

        long timestamp = Utils.getCurrentTime();

/*        Post post = new Post(
                mAuth.getUid(),
                packageNameV,
                Double.parseDouble(packagePriceV),
                securityTypeV,
                Double.parseDouble(overNightRateV),
                Double.parseDouble(extraHoursRateV),
                Double.parseDouble(perHourRateV),
                startDateV,
                expireDateV,
                descriptionV,
                timestamp
        );*/
        AddToFirebase intoDatabase=new AddToFirebase(companyNameV,packageNameV,pkgType,packagePriceV,duty_durationn,guard_type,no_ofGuards
                ,weapon_types,duty_hours,startDateV,expireDateV,overNightRateV,extraHoursRateV,perHourRateV,descriptionV);

        addNewPostOnFirebase(intoDatabase);




    }

    private void addNewPostOnFirebase(AddToFirebase post) {
        String packagetype;
        if(pkgType.toLowerCase().contains("premium")){
            packagetype= Constants.premium;
        }
        else if (pkgType.toLowerCase().contains("on sale")){
            packagetype= Constants.onsale;
        }
        else if (pkgType.toLowerCase().contains("standard")){
            packagetype= Constants.standard;
        }
        else{
            packagetype="Posts";
        }
        FirebaseDatabase.getInstance().getReference(packagetype).push().setValue(post).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(NewPostActivity.this, "Post has been added successfully", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(NewPostActivity.this, "Post Failed" + task.getException(), Toast.LENGTH_LONG).show();
                }
                progressBar1.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_start_time: {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(NewPostActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                        mStartDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            }
            case R.id.select_end_time: {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(NewPostActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                        mExpireDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            }
            case R.id.postadd:
                validateAndAddPost();
                break;
        }
    }

    private void uploadImagetoFirebase(Uri imageUri) {
        // upload image to firebase storage
        final StorageReference fileRef= storageReference.child(pkgType+"/"+mAuth.getCurrentUser().getUid()+"/post.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(NewPostActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(postImageView);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NewPostActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){
            case R.id.ssg:
                guard_type=ssg.getText().toString();

                break;
            case R.id.corporate:
                guard_type=corporate.getText().toString();
                break;
            case R.id.un_armed:
                guard_type=unarmed.getText().toString();
                break;
            case R.id.armed:
                guard_type=armed.getText().toString();
                break;
            case R.id.premium:
                pkgType=premium.getText().toString();
                break;
            case R.id.standard1:
                pkgType=standard.getText().toString();
                break;
            case R.id.discount:
                pkgType=low.getText().toString();
                break;
            case R.id.num2:
                no_ofGuards=num2.getText().toString();
                break;
            case R.id.num5:
                no_ofGuards=num5.getText().toString();
                break;
            case R.id.num7:
                no_ofGuards=num7.getText().toString();
                break;
            case R.id.num10:
                no_ofGuards=num10.getText().toString();
                break;

            case R.id.riffle:
                weapon_types=riffles.getText().toString();
                break;
            case R.id.pistol:
                weapon_types=pistols.getText().toString();
                break;
            case R.id.bayonet:
                weapon_types=bayonets.getText().toString();
                break;
            case R.id.nonlethal:
                weapon_types=non_lethal.getText().toString();
                break;
            case R.id.one_year:
                duty_durationn=one_year.getText().toString();
                break;
            case R.id.two_year:
                duty_durationn=two_year.getText().toString();
                break;
            case R.id.five_year:
                duty_durationn=five_year.getText().toString();
                break;
            case R.id.ten_hours:
                duty_hours=ten_hour.getText().toString();
                break;
            case R.id.fifteen_hours:
                duty_hours=fifteen_hour.getText().toString();
                break;
            case R.id.eighteen_hours:
                duty_hours=eighteen_hour.getText().toString();
                break;
            default:
                guard_type="not_selected";
                pkgType="Not Specified";
                weapon_types="not Selected";

        }

    }
}