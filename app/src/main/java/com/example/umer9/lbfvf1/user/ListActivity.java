package com.example.umer9.lbfvf1.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.example.umer9.lbfvf1.R;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.Serializable;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements AsyncResponse,
        AdapterView.OnItemClickListener{

    final String LOG = "ListActivity";
    private ArrayList<Post> bookList;
    private ListView lvBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle searchThis= getIntent().getExtras();
        String myParam = searchThis.getString("searchThis");

        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(ListActivity.this, this);
        taskRead.execute("http://192.168.2.64/lbf_fyp/api/search.php?string="+myParam);
    }

    @Override
    public void processFinish(String s) {

        if (s == null || s.isEmpty()) {

            Intent intent = new Intent(ListActivity.this, User_Main.class);
            Toast.makeText(ListActivity.this, "No Record Found", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        else {

            ArrayList<Post> bookList = new JsonConverter<Post>().toArrayList(s, Post.class);
            BindDictionary<Post> dict = new BindDictionary<Post>();

            dict.addStringField(R.id.title, new StringExtractor<Post>() {
                @Override
                public String getStringValue(Post item, int position) {
                    return item.b_title;
                }
            });
            dict.addStringField(R.id.author, new StringExtractor<Post>() {
                @Override
                public String getStringValue(Post item, int position) {
                    return item.b_author;
                }
            });
            dict.addStringField(R.id.isbn, new StringExtractor<Post>() {
                @Override
                public String getStringValue(Post item, int position) {
                    return item.b_isbn;
                }
            });
            dict.addStringField(R.id.avail_on, new StringExtractor<Post>() {
                @Override
                public String getStringValue(Post item, int position) {
                    return ""+item.last_availbleDate;
                }
            });
            dict.addStringField(R.id.rownum, new StringExtractor<Post>() {
                @Override
                public String getStringValue(Post item, int position) {
                    return "" + item.b_row;
                }
            });
            dict.addStringField(R.id.standnum, new StringExtractor<Post>() {
                @Override
                public String getStringValue(Post item, int position) {
                    return "" + item.b_stand;
                }
            });
            dict.addStringField(R.id.shelvenum, new StringExtractor<Post>() {
                @Override
                public String getStringValue(Post item, int position) {
                    return "" + item.b_shelf;
                }
            });
            
            FunDapter<Post> adapter = new FunDapter<>(ListActivity.this,
                    bookList, R.layout.books_list, dict);
            lvBook = (ListView) findViewById(R.id.lvBook);
            lvBook.setAdapter(adapter);
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Post selectedBook = bookList.get(position);
        Intent intent = new Intent(ListActivity.this, DetaileActivity.class);
        intent.putExtra("book", (Serializable) selectedBook);
        startActivity(intent);
    }

}
