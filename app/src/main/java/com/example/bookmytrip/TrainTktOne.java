package com.example.bookmytrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TrainTktOne extends AppCompatActivity {

    public static AutoCompleteTextView autoCompleteTextViewSrc;
    public static AutoCompleteTextView autoCompleteTextViewDest;
    public ImageView clickDropdown;
    public ImageView clickDropdown1;
    TextView textViewDate;

    public static Spinner spinner;
    public Button continuetoNext;
    public static String[] spinnerValueHoldValue = {"0", "1", "2", "3", "4", "5"};
    public static String valueOfSpinner = "";
    private static RadioGroup radioGroup;
    public static RadioButton radioButton;
    public int selectedId;
    public boolean checkSpinner = true;
    public static String srcDemo = "";
    public static String destDemo = "";
    public static String holdRadioValue = "";
    public static String currentDate = "";
    
    String[] stations ={"CSMT", "Byculla", "Chichpokli", "Parel", "Dadar", "Matunga", "Kurla", "Ghatkopar", "Vikhroli",
            "Kanjur Marg","Bhandup", "Mulund", "Thane", "Diva Jn", "Dombivli", "Kalyan", "Ambernath", "Badlapur", "Neral", "Bhivpuri Road",
            "Karjat", "Khopoli", "Titwala", "Asangaon", "Kasara", "Churchgate", "Marine Lines", "Charni Road", "Grant Road", "Mumbai Central",
            "Mahalakshmi", "Lower Parel", "Prabhadevi", "Matunga Road", "Bandra", "Khar Road", "Andheri", "Jogeshwari", "Malad", "Borivali",
            "Mira Road", "Bhayander", "Vasai Road", "Virar", "Masjid", "Sandhurst Road", "Sewri", "Vadala Road", "Mahin Jn", "Vile Parle",
            "Goregaon", "Tilaknagar", "Chembur", "Mankhurd", "Vashi", "Sanpada", "Juinagar", "Nerul", "Belapur CBD", "Kharghar", "Panvel",
            "Airoli", "Rabale", "Ghansoli", "Koperkhairne", "Turbhe"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_tkt_one);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> stationAdapter = new ArrayAdapter<String>(this,R.layout.auto_dropdown,stations);

        autoCompleteTextViewSrc =  (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewSrc);
        autoCompleteTextViewDest =  (AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewDest);
        clickDropdown = (ImageView) findViewById(R.id.clickDropdown);
        clickDropdown1 = (ImageView) findViewById(R.id.clickDropdown1);
        continuetoNext = (Button) findViewById(R.id.continuetoNext);

        autoCompleteTextViewSrc.setThreshold(1);//will start working from first character
        autoCompleteTextViewSrc.setAdapter(stationAdapter);//setting the adapter data into the AutoCompleteTextView

        autoCompleteTextViewDest.setThreshold(1);//will start working from first character
        autoCompleteTextViewDest.setAdapter(stationAdapter);//setting the adapter data into the AutoCompleteTextView

        clickDropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextViewSrc.showDropDown();
            }
        });

        clickDropdown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteTextViewDest.showDropDown();
            }
        });

        //auto complete code ends

        //calender code
        Calendar calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textViewDate = (TextView) findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);
        //calender ends

        //radio button code start
        radioGroup = (RadioGroup)findViewById(R.id.groupradio);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // Get the selected Radio Button
                radioButton = (RadioButton) group.findViewById(checkedId);
            }
        });
        //radio code ends

        /*spinner code start*/
        spinner =(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(TrainTktOne.this, android.R.layout.simple_list_item_1, spinnerValueHoldValue);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                valueOfSpinner = spinner.getSelectedItem().toString();
                //Toast.makeText(TrainTktOne.this, valueOfSpinner, Toast.LENGTH_SHORT).show();
                if (valueOfSpinner.equals(spinnerValueHoldValue[0])){
                    checkSpinner = false;
                }
                else {
                    valueOfSpinner = spinner.getSelectedItem().toString();
                    checkSpinner = true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        //spinner code ends

        continuetoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                srcDemo = autoCompleteTextViewSrc.getText().toString();
                destDemo = autoCompleteTextViewDest.getText().toString();
                selectedId = radioGroup.getCheckedRadioButtonId();

                if (srcDemo.isEmpty() || destDemo.isEmpty() || selectedId == -1 || checkSpinner == false){

                    Toast.makeText(TrainTktOne.this, "Please fill the required fields!!!", Toast.LENGTH_SHORT).show();
                }
                else if (srcDemo.equals(destDemo)){
                    Toast.makeText(TrainTktOne.this, "Source and Destination can't be same", Toast.LENGTH_SHORT).show();
                }
                else {
                    radioButton = (RadioButton)radioGroup.findViewById(selectedId); //it holds the radioButton value
                    holdRadioValue = radioButton.getText().toString();
                    Intent intent = new Intent(TrainTktOne.this, LocalPaymentOne.class);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
