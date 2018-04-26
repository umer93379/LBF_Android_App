package com.example.umer9.lbfvf1.user;
import com.google.gson.annotations.SerializedName;
/**
 * Created by umer9 on 11/01/2018.
 */

public class Post {

    @SerializedName("b_title")
    public String b_title;

    @SerializedName("b_author")
    public  String b_author;


    @SerializedName("b_isbn")
    public  String b_isbn;

    @SerializedName("last_availbleDate")
    public  String last_availbleDate;

    @SerializedName("b_row")
    public  int b_row;

    @SerializedName("b_stand")
    public  int b_stand;

    @SerializedName("b_shelf")
    public  int b_shelf;

}
