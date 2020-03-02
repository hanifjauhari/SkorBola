package id.putraprima.skorbola;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ScorerActivity extends AppCompatActivity {

    EditText scorerName;
    EditText minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        scorerName = findViewById(R.id.editText);
        minute = findViewById(R.id.time);
    }

    public void handleFinishNamed(View view) {
        String time = minute.getText().toString();
        String scorer = scorerName.getText().toString();
        Intent resultIntent = new Intent();
        if(scorer.equals("") && time.equals(""))
        {
            setResult(RESULT_CANCELED,resultIntent);
            finish();
        }else
        {
            resultIntent.putExtra("time",time);
            resultIntent.putExtra("pencetak",scorer);
            setResult(RESULT_OK,resultIntent);
            finish();
        }
    }
}
