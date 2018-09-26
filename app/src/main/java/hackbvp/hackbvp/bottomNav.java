package hackbvp.hackbvp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class bottomNav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);
        RadioGroup radioGroup1;
        radioGroup1= findViewById(R.id.radioGroup1);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch (checkedId) {
                    case R.id.home:
                        startActivity(new Intent(getBaseContext() , MainActivity.class));
                        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                        break;
                    case R.id.personal_details:

                        break;
                    case R.id.notifications:
                        startActivity(new Intent(getBaseContext() , Notifications.class));
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        break;
                }
            }
        });
    }

}
