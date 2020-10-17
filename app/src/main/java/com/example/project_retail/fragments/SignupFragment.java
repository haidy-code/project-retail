package com.example.project_retail.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_retail.R;
import com.example.project_retail.requests.SignupLogin_Requests;
import com.example.project_retail.webservices.Retrofit_Factory;
import com.example.project_retail.webservices.WebServices;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class SignupFragment extends Fragment {
    TextInputEditText name_et;
    TextInputEditText email_et;
    TextInputEditText pass_et;
    MaterialButton signup_btn;
    TextView gologin_tv;
    ImageView logo_iv;
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
        View view=inflater.inflate(R.layout.fragment_signup, container, false);
        name_et=view.findViewById(R.id.name_et);
        email_et=view.findViewById(R.id.email_et);
        pass_et=view.findViewById(R.id.pass_et);
        signup_btn=view.findViewById(R.id.signup_btn);
        gologin_tv=view.findViewById(R.id.go_login_tv);
        logo_iv=view.findViewById(R.id.killua_iv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupclicklisteners();
    }

    private void setupclicklisteners() {
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String name=name_et.getText().toString();
                String pass=pass_et.getText().toString();
                String email=email_et.getText().toString();
                if (name.isEmpty()||pass.isEmpty()||email.isEmpty()) {
                    Toast.makeText(requireContext(),"there is empty field",Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog=new ProgressDialog(requireContext());
                    progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
                    progressDialog.show();
                    webServices= Retrofit_Factory.getRetrofit().create(WebServices.class);
                    Call<String>register=webServices.register(new SignupLogin_Requests(name,email,pass));
                    register.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            progressDialog.dismiss();
                            String token=response.body();
                            Toast.makeText(requireContext(),token,Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_homeFragment);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            progressDialog.dismiss();
                            Log.d("haidy_error",t.toString());
                            Toast.makeText(requireContext(),"error occured",Toast.LENGTH_SHORT).show();

                        }
                    });



                }

            }
        });
        gologin_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_login_Fragment);
            }
        });
    }
}