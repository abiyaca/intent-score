package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView varResult;
    TextView varWinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        varResult = findViewById(R.id.resultview);
        varWinner = findViewById(R.id.winnerView);

        Bundle extras = getIntent().getExtras();
        // get nilai dari match
        int homeResult = extras.getInt("homeScore");
        int awayResult = extras.getInt("awayScore");
        String txtHomeName = extras.getString("homeText");
        String txtAwayName = extras.getString("awayText");

        if(extras != null ){
            varResult.setText("Final Score : "+String.valueOf(homeResult) + " - " + String.valueOf(awayResult));
            if(homeResult > awayResult){
                varWinner.setText("pemenangnya adalah "+ txtHomeName + "!");
            }else if(awayResult > homeResult){
                varWinner.setText("pemenangnya adalah "+ txtAwayName + "!");
            }else{
                varWinner.setText("Skor Seimbang!");
            }
        }
    }
}
