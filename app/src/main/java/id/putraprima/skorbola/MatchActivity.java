package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {
    int counterH = 0;
    int counterA = 0;

    TextView homeText;
    TextView awayText;

    String homeValue;
    String awayValue;

    private TextView hscoreText;
    private TextView ascoreText;

    ImageView homeIcon;
    ImageView awayIcon;

    Uri imageUriHome;
    Uri imageUriAway;

    Bitmap bitmapHome;
    Bitmap bitmapAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);

        hscoreText = findViewById(R.id.score_home);
        ascoreText = findViewById(R.id.score_away);

        homeIcon = findViewById(R.id.home_logo);
        awayIcon = findViewById(R.id.away_logo);

        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"

        Bundle extras = getIntent().getExtras();

        homeValue = extras.getString("home");
        awayValue = extras.getString("away");

        if (extras != null) {
            imageUriHome = Uri.parse(extras.getString("iconHome"));
            imageUriAway = Uri.parse(extras.getString("iconAway"));
            //Attribut image
            bitmapHome = null;
            bitmapAway = null;

            try{
                bitmapHome = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriHome);
                bitmapAway = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriAway);
            }catch(IOException e){
                e.printStackTrace();
            }

            // TODO: display value here
            homeText.setText(homeValue);
            awayText.setText(awayValue);

            homeIcon.setImageBitmap(bitmapHome);
            awayIcon.setImageBitmap(bitmapAway);
        }
    }

    public void handleResult(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        //Dari tambah button score
        intent.putExtra("homeScore", counterH);
        intent.putExtra("awayScore", counterA);
        //Dari output nama team
        intent.putExtra("homeText", homeValue);
        intent.putExtra("awayText", awayValue);

        startActivity(intent);
    }

    public void handleAddHome(View view) {
        counterH++;
        hscoreText.setText(Integer.toString(counterH));
    }

    public void handleAddAway(View view) {
        counterA++;
        ascoreText.setText(Integer.toString(counterA));
    }
}
