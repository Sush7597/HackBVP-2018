package hackbvp.hackbvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    String Info = null;
    JSONObject pdetails = null;
    protected BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            check();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Activate(View view) {
        Button button = findViewById(R.id.button);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        BounceInterpolator interpolator;
        interpolator = new BounceInterpolator();
        myAnim.setInterpolator(interpolator);
        button.startAnimation(myAnim);
    }


    public void check() throws JSONException {
        SharedPreferences sp = getSharedPreferences("HackBVP",MODE_PRIVATE);
        Info = sp.getString("Info", "null");
        Log.d("Stored Info:", Info);
        pdetails = new JSONObject(Info);
        if(Info.equals("null"))
        {
            Intent act=new Intent(this,MainActivity.class);
            startActivity(act);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        }
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
        finish();
        return true;
    }
}
