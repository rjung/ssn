package org.rjung.ssn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.rjung.ssn.org.rjung.ssn.db.Attempt;
import org.rjung.ssn.org.rjung.ssn.db.AttemptDatabase;

public class MainActivity extends AppCompatActivity {

    private AttemptDatabase attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attempts = new AttemptDatabase(this);
        setContentView(R.layout.activity_main);
    }

}
