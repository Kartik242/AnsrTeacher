package ansore.app.ansrkidsteacher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {


    private TextInputEditText t_email,t_password;
    Button loginn;
    SharedPreferences sharedPreferences;
    CheckBox loginState;

    private String URL = "http://ansrkids.in/sign_in.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        t_email = findViewById(R.id.t_email);
        t_password = findViewById(R.id.t_password);
        loginn = findViewById(R.id.login_btn);
        loginState = findViewById(R.id.checkbox);

        
        
        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = t_email.getText().toString();
                String password = t_password.getText().toString();
                login(email,password);
                
            }
        });

        String loginStatus = sharedPreferences.getString(getResources().getString(R.string.preLoginStatus),"");
        if (loginStatus.equals("loggedin")){
            startActivity(new Intent(LoginActivity.this,homeActivity.class));
            finish();

        }





    }

    private void login(final String t_email, final String  t_password) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Signing In");
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Login Success")){
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (loginState.isChecked()){
                        editor.putString(getResources().getString(R.string.preLoginStatus),"loggedin");

                    }else{
                        editor.putString(getResources().getString(R.string.preLoginStatus),"loggedout");


                    }
                    editor.apply();

                    startActivity(new Intent(LoginActivity.this,homeActivity.class));
                    finish();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                    data.put("email",t_email);
                    data.put("password",t_password);
                    return data;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
    }


//    public void login_Funs(View view) {
//
//        email = t_email.getText().toString().trim();
//        password = t_password.getText().toString().trim();
//
//        if (!email.equals("")&&!password.equals("")){
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    if (response.equals("1")) {
//                        makeToast("success");
//                        Intent intent = new Intent(LoginActivity.this, homeActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else  {
//                        Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                        Log.e("tag",response);
//
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    makeToast(error.toString().trim());
//                }
//            }){
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> data = new HashMap<>();
//                    data.put("email",email);
//                    data.put("password",password);
//                    return data;
//                }
//            };
//            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//            requestQueue.add(stringRequest);
//        }else{
//            makeToast("Fields can not be empty.");
//        }
//    }

   
}