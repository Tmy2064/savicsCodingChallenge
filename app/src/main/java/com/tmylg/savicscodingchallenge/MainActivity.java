package com.tmylg.savicscodingchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText firstname, lastname, age;
    AutoCompleteTextView country, city;
    RadioGroup sexRG, diabetRG;
    Button saveBtn, viewSaved;
    DataBaseHelper myDb;
    public String sex = null;
    public String diabetStat = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        age = findViewById(R.id.age);
        country = findViewById(R.id.country);
        city = findViewById(R.id.city);
        sexRG = findViewById(R.id.rdSex);
        diabetRG = findViewById(R.id.rdDiabet);

        saveBtn = findViewById(R.id.save);
        viewSaved = findViewById(R.id.viewData);

        myDb = new DataBaseHelper(this);

        String [] countries = {"Dem.Rep.Con","Ghana","Tanzania","Angola","Zambia"};
        String [] cities = {"Kinshasa","Accra","Daresalam","Luanda","Lusaka"};

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);

        country.setAdapter(countryAdapter);
        city.setAdapter(citiesAdapter);

        sexRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.male:
                        sex = "male";
                        break;
                    case R.id.female:
                        sex = "female";
                        break;
                }
            }
        });
        diabetRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.yes:
                        diabetStat = "yes";
                        break;
                    case R.id.no:
                        diabetStat = "no";
                        break;
                    case R.id.unknow:
                        diabetStat = "unknown";
                        break;
                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names = firstname.getText().toString() + ", " + lastname.getText().toString();
                String mySex = sex;
                String myDiabetStatus = diabetStat;
                String myAge = age.getText().toString();
                String countries = country.getText().toString() + ", " + city.getText().toString();

                Boolean result = myDb.insertData(names, mySex, myAge, countries, myDiabetStatus);
                if (result==true){
                    Toast.makeText(MainActivity.this, "Medical data saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecordList.class);
                startActivity(intent);
            }
        });
    }
}