package ansore.app.ansrkidsteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText t_email,t_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        t_email = findViewById(R.id.t_email);
        t_password = findViewById(R.id.t_password);

        userLogin( t_email.getText().toString(), t_password.getText().toString());

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

        Editable email = t_email.getText();
        Editable pass = t_password.getText();

        Toast.makeText(this,email+"\n"+pass,Toast.LENGTH_LONG).show();

    }

}