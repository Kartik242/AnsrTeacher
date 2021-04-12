package ansore.app.ansrkidsteacher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
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
    private String email, password;

    private String URL = "http://ansrkids.in/sign_in.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        t_email = findViewById(R.id.t_email);
        t_password = findViewById(R.id.t_password);



//        userLogin( t_email.getText().toString(), t_password.getText().toString());
    }

    private void userLogin(String email, String password) {

        if(email.equals("") || password.equals("")){
            Toast.makeText(LoginActivity.this, "Username or password must be filled", Toast.LENGTH_LONG).show();
            return;
        }
        if(email.length() <= 1 || password.length() <= 1){
            Toast.makeText(LoginActivity.this, "Username or password length must be greater than one", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void login_Funs(View view) {

        email = t_email.getText().toString().trim();
        password = t_password.getText().toString().trim();

        if (!email.equals("")&&!password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        makeToast("success");
                        Intent intent = new Intent(LoginActivity.this, homeActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        makeToast("failure");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    makeToast(error.toString().trim());
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email",email);
                    data.put("password",password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            makeToast("Fields can not be empty.");
        }
    }

    private void makeToast(String string) {
        Toast.makeText(LoginActivity.this, string, Toast.LENGTH_LONG).show();
    }
}