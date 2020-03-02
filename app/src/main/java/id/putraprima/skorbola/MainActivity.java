package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //TODO : Creating Key for Bundle
    public static final String HOME_TEAM = "hometeam";
    public static final String AWAY_TEAM = "awayteam";
    public static final String IMAGE_KEY_HOME = "imagekeyhome";
    public static final String IMAGE_KEY_AWAY = "imagekeyaway";
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALERY_REQUEST_CODE = 1;
    private static final int GALERY_REQUEST_CODEX = 2;

    //TODO : Creating Varibale
    private ImageView flaghome,flagaway;
    private EditText namehome,nameaway;
    private Uri imageUri;
    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO binding
        flaghome = findViewById(R.id.home_logo);
        flagaway = findViewById(R.id.away_logo);

        namehome = findViewById(R.id.home_team);
        nameaway = findViewById(R.id.away_team);
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED)
        {
            return;
        }
        if(requestCode == GALERY_REQUEST_CODE)
        {
            if(data != null)
            {
                try
                {
                    imageUri = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                    flaghome.setImageBitmap(bitmap);
                }
                catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG,e.getMessage());
                }
            }
        }
        else if(requestCode == GALERY_REQUEST_CODEX)
        {
            if(data != null)
            {
                try
                {
                    imageUri = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                    flagaway.setImageBitmap(bitmap);
                }
                catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG,e.getMessage());
                }
            }
        }
    }

    public void HomeLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALERY_REQUEST_CODE);
    }

    public void AwayLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALERY_REQUEST_CODEX);
    }

    public void HandleMatchActivity(View view) {
        String NameHome = namehome.getText().toString();
        String NameAway = nameaway.getText().toString();

        flaghome.buildDrawingCache();
        flagaway.buildDrawingCache();
        Bitmap home = flaghome.getDrawingCache();
        Bitmap away = flagaway.getDrawingCache();

        Intent intent = new Intent(this,MatchActivity.class);
        if(bitmap == null)
        {
            Toast.makeText(this,"Silahkan Isi gambar terlebih dahulu", Toast.LENGTH_LONG).show();
        }else
        {
            if (!NameAway.equals("") && !NameHome.equals(""))
            {
                intent.putExtra(HOME_TEAM,NameHome);
                intent.putExtra(AWAY_TEAM,NameAway);
                intent.putExtra(IMAGE_KEY_HOME,home);
                intent.putExtra(IMAGE_KEY_AWAY,away);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Harus melengkapi Data Team yang bertanding", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
