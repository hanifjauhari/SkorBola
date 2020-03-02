package id.putraprima.skorbola;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView result,scorer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = findViewById(R.id.textView3);
        scorer = findViewById(R.id.texxView_Scorer);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            //TODO : Display Value
            String txt = result.getText().toString();
            String txtScorer = scorer.getText().toString();
            result.setText(txt +" "+extras.getString(String.valueOf(scorer)));
            scorer.setText(extras.getString(txtScorer+"\n"+scorer));
        }
    }
}
