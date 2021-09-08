package com.example.realmappsameclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    ConnectToRealmApp1 realmAppOne;
    ConnectToRealmApp2 realmAppTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        realmAppOne = new ConnectToRealmApp1();
        realmAppTwo = new ConnectToRealmApp2();

        Button checkButton = findViewById(R.id.Check_button);
        checkButton.setOnClickListener(v -> {
            realmAppOne.RealmApp1(this);
            realmAppTwo.RealmApp2(this);
        });
    }
}