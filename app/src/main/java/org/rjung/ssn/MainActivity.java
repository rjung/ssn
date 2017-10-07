package org.rjung.ssn;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        updateState();
    }

    private void updateState() {
        Attempt attempt = attempts.getCurrentAttempt();
        TextView icon = (TextView) findViewById(R.id.attempt_icon);
        TextView notice = (TextView) findViewById(R.id.attempt_days);

        icon.setText(attempt.isFinished() ? R.string.character_no : R.string.character_yes);
        icon.setTextColor(ContextCompat.getColor(this, attempt.isFinished() ? R.color.colorAccent : R.color.colorPrimary));
        notice.setText(getResources().getString(R.string.message_days, attempt.isFinished() ? 0 : attempt.getDays()));
    }

    public void buttonSmoke(View view) {
        attempts.cigaretteTheLastDay();
        updateState();
    }
    public void buttonNoSmoke(View view) {
        attempts.noCigaretteTheLastDay();
        updateState();
    }
}
