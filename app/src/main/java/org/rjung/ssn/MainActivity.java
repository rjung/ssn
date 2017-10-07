package org.rjung.ssn;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.rjung.ssn.db.Attempt;
import org.rjung.ssn.db.AttemptDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private AttemptDatabase attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attempts = new AttemptDatabase(this);
        setContentView(R.layout.activity_main);
        updateState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateState();
    }

    private void updateState() {
        Attempt attempt = attempts.getCurrentAttempt();
        TextView icon = (TextView) findViewById(R.id.attempt_icon);
        TextView notice = (TextView) findViewById(R.id.attempt_days);
        TextView highscore = (TextView) findViewById(R.id.highscore);
        TextView question = (TextView) findViewById(R.id.message_smoked_question);

        int days = attempt.isFinished() ? 0 : (int) attempt.getDays();
        int highscoreDays = (int) attempts.getHighscore();

        icon.setText(attempt.isFinished() ? R.string.character_no : R.string.character_yes);
        icon.setTextColor(ContextCompat.getColor(this, attempt.isFinished() ? R.color.colorAccent : R.color.colorPrimary));
        notice.setText(getResources().getQuantityString(R.plurals.message_days, days, days));
        highscore.setText(getResources().getQuantityString(R.plurals.highscore_days, highscoreDays, highscoreDays));
        question.setText(getResources().getString(Attempt.getMilisecondsInDays(new Date().getTime() - attempt.getUpdatedStore()) > 1 ? R.string.message_smoked_since_last_checking_question : R.string.message_smoked_question));
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
