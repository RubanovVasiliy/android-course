package com.example.dbasesjava;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbhelper;
    private SQLiteDatabase database;
    private Button btnShowDB;
    private Button btnShowButterflyDB;
    private ListView listDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnShowDB = findViewById(R.id.btnShowDB);
        btnShowButterflyDB =  findViewById(R.id.btnShowButterflyDB);
        listDB = findViewById(R.id.listDB);
        dbhelper = new DBHelper(this);

        btnShowDB.setOnClickListener(this::onClickShowDB);
        btnShowButterflyDB.setOnClickListener(this::onClickShowButterflyDB);

        try {
            database = dbhelper.getWritableDatabase();
        } catch (SQLException e) {
            throw e;
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickShowDB(View view) {
        ArrayList<HashMap<String, String>> persons = new ArrayList<>();
        HashMap<String, String> person;

        Cursor cursor = database.rawQuery("SELECT * FROM persons", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            person = new HashMap<>();
            person.put("name", cursor.getString(1));
            person.put("achievement", cursor.getString(2));
            persons.add(person);
            cursor.moveToNext();
        }

        SimpleAdapter adapter = new SimpleAdapter(this, persons, R.layout.listview_item,
                new String[]{"name", "achievement"},
                new int[]{R.id.textPerson, R.id.textAchievement});
        listDB.setAdapter(adapter);
    }

    public void onClickShowButterflyDB(View view) {
        ArrayList<HashMap<String, String>> persons = new ArrayList<>();
        HashMap<String, String> person;

        Cursor cursor = database.rawQuery("SELECT * FROM but", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            person = new HashMap<>();
            person.put("name", cursor.getString(1));
            person.put("description", cursor.getString(2));
            persons.add(person);
            cursor.moveToNext();
        }

        SimpleAdapter adapter = new SimpleAdapter(this, persons, R.layout.listview_item,
                new String[]{"name", "description"},
                new int[]{R.id.textPerson, R.id.textAchievement});
        listDB.setAdapter(adapter);
    }
}