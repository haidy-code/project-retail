package com.example.project_retail.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_retail.R;
import com.example.project_retail.requests.SignupLogin_Requests;
import com.example.project_retail.webservices.Retrofit_Factory;
import com.example.project_retail.webservices.WebServices;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class login_Fragment extends Fragment {
    TextInputEditText email_et;
    TextInputEditText pass_et;
    MaterialButton login_btn;
    TextView gosignup_tv;
    ProgressDialog progressDialog;
    WebServices webServices;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login_, container, false);
        email_et=view.findViewById(R.id.login_email_et);
        pass_et=view.findViewById(R.id.login_pass_et);
        login_btn= view.findViewById(R.id.login_btn);
        gosignup_tv=view.findViewById(R.id.signup_tv);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupclicklisteners();
    }

    private void setupclicklisteners() {
  login_btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View view) {
          String email=email_et.getText().toString();
          String pass=pass_et.getText().toString();
          if (pass.isEmpty()||email.isEmpty()){
              Toast.makeText(requireContext(), "there is empty field", Toast.LENGTH_SHORT).show();
          }
          else {
              progressDialog=new ProgressDialog(requireContext());
              progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
              progressDialog.show();
              webServices= Retrofit_Factory.getRetrofit().create(WebServices.class);
              Call<String> login=webServices.login(new SignupLogin_Requests(new SignupLogin_Requests(email,pass)));
              login.enqueue(new Callback<String>() {
                  @Override
                  public void onResponse(Call<String> call, Response<String> response) {
                      progressDialog.dismiss();
                      String token=response.body();
                      Navigation.findNavController(view).navigate(R.id.action_login_Fragment_to_homeFragment);
                  }

                  @Override
                  public void onFailure(Call<String> call, Throwable t) {
                      progressDialog.dismiss();
                      Toast.makeText(requireContext(),"errorhappened",Toast.LENGTH_SHORT).show();

                  }
              });
          }
      }
  });
  gosignup_tv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          Navigation.findNavController(view).navigate(R.id.action_login_Fragment_to_signupFragment);
      }
  });


    }
}