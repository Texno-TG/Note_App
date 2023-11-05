package air.texnodev.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import air.texnodev.noteapp.Fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFragment())
                .commit();
    }
}