package com.example.aplikacija07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonDohvatiFakultete;
    private TextView labelFakulteti;

    public static final  String FAKULTETI_URL = "http://199.188.100.46/singidunum/android/faculties.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents(){
        buttonDohvatiFakultete = findViewById(R.id.buttonDohvatiFakultete);
        labelFakulteti = findViewById(R.id.labelFakulteti);

        buttonDohvatiFakultete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Api.getJSON(FAKULTETI_URL, new ReadDataHandler(){

            @Override
            public void handleMessage(Message msg) {
                String odgovor = getJson();
                try {
                    JSONArray array = new JSONArray(odgovor);
                    LinkedList<Faculty> fakulteti =  Faculty.parseJSONArray(array);
                    labelFakulteti.setText("Fakulteti: \n");
                    for (Faculty faculty : fakulteti){
                        String prikaz = "[" + faculty.getAcronym() + "]" + faculty.getName() + "\n" + faculty.getWebsite() + "\n\n";
                        labelFakulteti.append(prikaz);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        labelFakulteti.setText("Ucitava se... ");
    }
}