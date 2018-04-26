package com.example.umer9.lbfvf1.user;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.umer9.lbfvf1.R;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import com.kosalgeek.genasync12.AsyncResponse;

import java.util.ArrayList;


public class Home extends AppCompatActivity implements AsyncResponse, View.OnClickListener, com.kosalgeek.asynctask.AsyncResponse {

    Bundle searchThis;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        listView = (ListView)findViewById(R.id.lvPost);

        searchThis= getIntent().getExtras();

        String myParam = searchThis.getString("searchThis");

        PostResponseAsyncTask task = new PostResponseAsyncTask(this);
        AsyncTask<String, Void, String> string = task.execute("http://192.168.2.64/lbf_fyp/api/search.php?string="+myParam);

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void processFinish(String result) {

    ArrayList<Post> postList = new JsonConverter<Post>().toArrayList(result,Post.class);

        ArrayList<String> booksList = new ArrayList<String>();
        for (Post value: postList)
            {
                booksList.add(value.b_title);
            }
        ArrayAdapter<String> adapter = new ArrayAdapter<String >(this, android.R.layout.simple_list_item_1, booksList);
        listView.setAdapter(adapter);

    }

}
