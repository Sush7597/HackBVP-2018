package hackbvp.hackbvp;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class PersonalDetails extends AppCompatActivity {

    EditText etname, etage, etadd, etmob;
    Button btnsave;
    String name, age, add, mob;
    String Info="null";//if we want to display the contents of shared preferences
    String jsonstring="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        etname = findViewById(R.id.etname);
        etage = findViewById(R.id.etage);
        etadd = findViewById(R.id.etadd);
        etmob = findViewById(R.id.etmob);
        btnsave = findViewById(R.id.btnsave);


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                name = etname.getText().toString();
                age = etage.getText().toString();
                add = etadd.getText().toString();
                mob = etmob.getText().toString();
                if(TextUtils.isEmpty(name)||TextUtils.isEmpty(age)||TextUtils.isEmpty(add)||TextUtils.isEmpty(mob))
                {
                    Toast.makeText(PersonalDetails.this, "Fill all deatils please ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    save(name,age,add,mob);
                }


            }
        });

    }
    public void editSharedPreferences(String jsonstring)
    {

        SharedPreferences sp = getSharedPreferences("HackBVP",MODE_PRIVATE);
//        token1 = sp.getString("token", "null");
        sp.edit().putString("details",jsonstring).apply();//editing the shared preferneces

//        Log.d("Stored Token:", token1);

    }

    private void save(String name,String age,String add, String mob) {
        Save st = new Save(name,age,add,mob);
        st.doInBackground();

    }

    public class Save extends AsyncTask<Void, Void, Boolean> {


        final String mName;
        final String mAge;
        final String mAdd;
        final String mMob;
        //        final JSONArray mArray = new JSONArray();
//
//        final JSONObject name = new JSONObject();
//        final JSONObject age = new JSONObject();
//        final JSONObject add = new JSONObject();
//        final JSONObject mob = new JSONObject();
        final JSONObject log=new JSONObject();



        Save(String mName, String mAge, String mAdd, String mMob) {
            this.mName=mName;
            this.mAge=mAge;
            this.mAdd=mAdd;
            this.mMob=mMob;


            try {

//                log.put("password", mPassword);
//                name.put("name",mName);
//                age.put("age",mAge);
//                add.put("add",mAdd);
//                mob.put("mob",mMob);
//
//             mArray.put(name);
//             mArray.put(age);
//             mArray.put(add);
//             mArray.put(mob);
                log.put("name",mName);
                log.put("age",mAge);
                log.put("add",mAdd);
                log.put("mob",mMob);
                jsonstring=log.toString();
                editSharedPreferences(jsonstring);
                if (log.length() > 0) {
                    doInBackground();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        @Override
        protected Boolean doInBackground(Void... voids) {


            try {
                // Simulate network access.
                Thread.sleep(2000);
                getServerResponse(log);
            } catch (InterruptedException e) {
                Log.d("Exception from :", "doInBackground");
                e.printStackTrace();
            }
            return null;
        }

        private void getServerResponse(final JSONObject log) {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    JSONObject networkResp = null;
                    {
                        try {
                            try {

                                URL url = new URL("https://socioplay.in/reset/password");//change this acc to new server
                                #MediaType JSON = MediaType.parse("application/json; charset=utf-8");

                                #okhttp3.RequestBody body = RequestBody.create(JSON, log.toString());

                                #OkHttpClient client = new OkHttpClient();
                                #okhttp3.Request request = new okhttp3.Request.Builder()
                                        .url(url)
                                        .header("Authorization", "Bearer " + token1)
                                        .header("Content-Type", "application/json")
                                        .post(body)
                                        .build();


                                #okhttp3.Response response = client.newCall(request).execute();
                                Log.d("Response", "achieved");
                                #String networkResp1 = response.body().string();
                                networkResp = new JSONObject(networkResp1);
                            } catch (Exception ex) {
                                String err = String.format("{\"result\":\"false\",\"error\":\"%s\"}", ex.getMessage());
                                Log.d("Exception ex:", err);
                            }
                            String result = (networkResp.getString("status"));
                            Log.d("Status Code is :", result);
                            String S = "EVENT STATUS";
                            if (result != null) {
                                if (result.equals("200")) {//change this acc to new server
                                    Log.d("Success", S);
                                    onPostExecute();

                                } else {
                                    Log.d("Failed", S);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(PersonalDetails.this, "Invalid Details!", Toast.LENGTH_LONG).show();
                                        }
                                    });

                                }
                            }
                        } catch (Exception e) {
                            Log.d("InputStream", e.getLocalizedMessage());
                        }
                    }

                }
            });
            thread.start();
        }

        private void onPostExecute() {

            String S = "STATUS";
            Log.d("Starting!", S);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(PersonalDetails.this, "Details Set", Toast.LENGTH_LONG).show();

                }
            });

        }

        @Override
        protected void onCancelled() {

        }


    }
}
