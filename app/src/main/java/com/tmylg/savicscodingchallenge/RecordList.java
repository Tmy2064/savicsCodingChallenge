package com.tmylg.savicscodingchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RecordList extends AppCompatActivity {

    DataBaseHelper myDb;
    TextView savedDatas;
    EditText ageFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        myDb = new DataBaseHelper(this);
        savedDatas = findViewById(R.id.datas);
        ageFilter = findViewById(R.id.search);

        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();

        if (res!=null && res.getCount()>0){
            while (res.moveToNext()){
                stringBuffer.append("Id: "+res.getString(0)+"\n");
                stringBuffer.append("names: "+res.getString(1)+"\n");
                stringBuffer.append("sex: "+res.getString(2)+"\n");
                stringBuffer.append("age: "+res.getString(3)+"\n");
                stringBuffer.append("country: "+res.getString(4)+"\n");
                stringBuffer.append("diabetsatus: "+res.getString(5)+"\n");
            }
            savedDatas.setText(stringBuffer.toString());
            Toast.makeText(this, "Datas retrieved duccessfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }

        ageFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = myDb.getAllData();
                StringBuffer stringBuffer = new StringBuffer();

                if (res!=null && res.getCount()>0){
                    while (res.moveToNext()){
                        stringBuffer.append("Id: "+res.getString(0)+"\n");
                        stringBuffer.append("names: "+res.getString(1)+"\n");
                        stringBuffer.append("sex: "+res.getString(2)+"\n");
                        stringBuffer.append("age: "+res.getString(3)+"\n");
                        stringBuffer.append("country: "+res.getString(4)+"\n");
                        stringBuffer.append("diabetsatus: "+res.getString(5)+"\n");
                    }
                    savedDatas.setText(stringBuffer.toString());
                    Toast.makeText(getApplicationContext(), "Datas retrieved duccessfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ageFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}