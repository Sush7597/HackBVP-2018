package hackbvp.hackbvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import static java.security.AccessController.getContext;

public class PersonalDetails extends bottomNav {

    EditText etname, etage, etadd, etmob;
    Button btnsave;
    String name, age, add, mob;
    String Info="null";
    String jsonstring="";
    LinearLayout bottonNavBar;
    LinearLayout dynamicContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        dynamicContent =  findViewById(R.id.dynamicContent);
        bottonNavBar= findViewById(R.id.bottonNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_personal_details,null);
        dynamicContent.addView(wizard);
        RadioButton rb=findViewById(R.id.Details);
        rb.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.ic_menu_settings_holo_light, 0,0);
        rb.setTextColor(Color.parseColor("#3F51B5"));

    }
    public void editSharedPreferences(String jsonstring)
    {

        SharedPreferences sp = getSharedPreferences("HackBVP",MODE_PRIVATE);
        sp.edit().putString("details",jsonstring).apply();

    }

    private void save(String name,String age,String add, String mob) {
        Save st = new Save(name,age,add,mob);

    }

    public void Activate(View view) {
        btnsave = findViewById(R.id.btn);
        etname = findViewById(R.id.etname);
        etage = findViewById(R.id.etage);
        etadd = findViewById(R.id.etadd);
        etmob = findViewById(R.id.etmob);
        name = etname.getText().toString();
        age = etage.getText().toString();
        add = etadd.getText().toString();
        mob = etmob.getText().toString();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(age)||TextUtils.isEmpty(add)||TextUtils.isEmpty(mob))
        {
            Toast.makeText(PersonalDetails.this, "Please Fill All The Details!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            save(name,age,add,mob);
        }

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        BounceInterpolator interpolator;
        interpolator = new BounceInterpolator();
        myAnim.setInterpolator(interpolator);
        btnsave.startAnimation(myAnim);
    }

    public class Save {


        final String mName;
        final String mAge;
        final String mAdd;
        final String mMob;
        final JSONObject log=new JSONObject();



        Save(String mName, String mAge, String mAdd, String mMob) {
            this.mName=mName;
            this.mAge=mAge;
            this.mAdd=mAdd;
            this.mMob=mMob;
            try {
                log.put("name",mName);
                log.put("age",mAge);
                log.put("add",mAdd);
                log.put("mob",mMob);
                jsonstring=log.toString();
                Log.d("Details : ", jsonstring);
                editSharedPreferences(jsonstring);
                startActivity(new Intent(getBaseContext() , MainActivity.class));
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
