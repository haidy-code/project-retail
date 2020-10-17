package com.example.project_retail.webservices;

import com.example.project_retail.requests.SignupLogin_Requests;
import com.example.project_retail.responses.Category_Response;
import com.example.project_retail.responses.Products_Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServices {
    @GET("products")
    Call<Products_Response>getproducts();
    @GET("categories")
    Call<Category_Response>getcategories();
    @POST("register")
    Call<String>register(@Body SignupLogin_Requests signupLogin_requests);
    @POST("login")
    Call<String>login(@Body SignupLogin_Requests signupLogin_requests);

}
