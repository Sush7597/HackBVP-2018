package hackbvp.hackbvp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;


public class Notifications extends bottomNav{

    LinearLayout bottonNavBar;
    LinearLayout dynamicContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            //dynamically include the  current activity layout into  baseActivity layout.now all the view of baseactivity is accessible in current activity.
            dynamicContent =  findViewById(R.id.dynamicContent);
            bottonNavBar= (LinearLayout) findViewById(R.id.bottonNavBar);
            View wizard = getLayoutInflater().inflate(R.layout.activity_notifications,null);
            dynamicContent.addView(wizard);


            //get the reference of RadioGroup.

            RadioButton rb=(RadioButton)findViewById(R.id.notifications);

            // Change the corresponding icon and text color on nav button click.

            rb.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.ic_menu_notifications, 0,0);
            rb.setTextColor(Color.parseColor("#00574B"));



        }


}
