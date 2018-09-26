package hackbvp.hackbvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends bottomNav {

    String Info = null;
    JSONObject pdetails = null;
    Button button;
    LinearLayout bottonNavBar;
    LinearLayout dynamicContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        check();
        dynamicContent =  findViewById(R.id.dynamicContent);
        bottonNavBar=  findViewById(R.id.bottonNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_main,null);
        dynamicContent.addView(wizard);

        RadioButton rb=findViewById(R.id.home);

        rb.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.ic_menu_home, 0,0);
        rb.setTextColor(Color.parseColor("#3F51B5"));

    }

    public void Activate(View view) {
        button = findViewById(R.id.button);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        BounceInterpolator interpolator;
        interpolator = new BounceInterpolator();
        myAnim.setInterpolator(interpolator);
        button.startAnimation(myAnim);
    }


    public void check() {
        Toast.makeText(this, "Save Our Souls!", Toast.LENGTH_SHORT).show();
        SharedPreferences sp = getSharedPreferences("HackBVP", MODE_PRIVATE);
        Info = sp.getString("details", "null");
        Log.d("Stored Info:", Info);
        try {
            pdetails = new JSONObject(Info);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (Info.equals("null")) {
            Intent act = new Intent(this, PersonalDetails.class);
           startActivity(act);
           overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        }


    }

}