package com.example.umer9.lbfvf1.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.example.umer9.lbfvf1.R;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class Profile extends AppCompatActivity implements AsyncResponse {

    final String LOG = "Profile";
    private ArrayList<Status> status;
    private ListView lvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(Profile.this, this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        //Toast.makeText(this, username, LENGTH_LONG).show();   //To Test username / verify

        taskRead.execute("http://192.168.2.64/lbf_fyp/api/profile_b.php?email="+username);


    }

    @Override
    public void processFinish(String s) {
         status = new JsonConverter<Status>().toArrayList(s, Status.class);

        BindDictionary<Status> statusBindDictionary = new BindDictionary<Status>();
        statusBindDictionary.addStringField(R.id.tvbID, new StringExtractor<Status>() {
            @Override
            public String getStringValue(Status item, int position) {
                return item.get_id;
            }
        });
        statusBindDictionary.addStringField(R.id.b_isbn, new StringExtractor<Status>() {
            @Override
            public String getStringValue(Status item, int position) {
                return item.get_isbn;
            }
        });
        statusBindDictionary.addStringField(R.id.u_id, new StringExtractor<Status>() {
            @Override
            public String getStringValue(Status item, int position) {
                return item.get_cnic;
            }
        });
        statusBindDictionary.addStringField(R.id.is_fine, new StringExtractor<Status>() {
            @Override
            public String getStringValue(Status item, int position) {
                return item.get_status;
            }
        });
        statusBindDictionary.addStringField(R.id.fine_status, new StringExtractor<Status>() {
            @Override
            public String getStringValue(Status item, int position) {
                return "" + item.get_fine;
            }
        });
        statusBindDictionary.addStringField(R.id.startDate, new StringExtractor<Status>() {
            @Override
            public String getStringValue(Status item, int position) {
                return item.get_IssueDate;
            }
        });
        statusBindDictionary.addStringField(R.id.endDate, new StringExtractor<Status>() {
            @Override
            public String getStringValue(Status item, int position) {
                return item.get_returnDate;
            }
        });



        FunDapter<Status> adapter = new FunDapter<>(
                Profile.this, status, R.layout.profile_lv, statusBindDictionary);

        lvStatus=(ListView)findViewById(R.id.lvStatus);
        lvStatus.setAdapter(adapter);

    }
}
