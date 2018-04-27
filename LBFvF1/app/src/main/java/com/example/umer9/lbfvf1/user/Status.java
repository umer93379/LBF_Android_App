package com.example.umer9.lbfvf1.user;

import com.google.gson.annotations.*;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("bb_id")
    public String get_id;

    @SerializedName("b_isbn")
    public String get_isbn;

    @SerializedName("s_cnic")
    public String get_cnic;

    @SerializedName("bb_date_issued")
    public String get_IssueDate;

    @SerializedName("bb_date_returned")
    public String get_returnDate;

    @SerializedName("bb_fine_status")
    public String get_fine_status;

    @SerializedName("fine")
    public String get_fine;

    @SerializedName("status")
    public String get_status;

    @SerializedName("difference")
    public String get_difference;

}
