package AliAmrol.discord;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {


    Client client;


    public void goToOriginActivity(String name) {
        Intent intent = new Intent(SignUpActivity.this, originActivity.class);
        intent.putExtra("send" , name);
        startActivity(intent);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText username = findViewById(R.id.signup_username);
        EditText password = findViewById(R.id.signup_Password);
        EditText email_e = findViewById(R.id.signup_email);
        Button sign = findViewById(R.id.signup_signup);



//      ------------------------------------------------------------------
        new Thread(() -> client = new Client(this)).start();// ----
        // ---------------------------------------------------------------





        Toast.makeText(this , "salaaam" , Toast.LENGTH_LONG).show();
        sign.setOnClickListener(new View.OnClickListener() {
            User user = new User();
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass = password.getText().toString();
                String email = email_e.getText().toString();
                boolean flag = true;
                if(!user.setUsername(name)){
                    flag = false;
                    Toast.makeText(SignUpActivity.this , "the name is invalid" , Toast.LENGTH_LONG).show();
                }
                else if(!user.setPassword(pass)){
                    flag = false;
                    Toast.makeText(SignUpActivity.this , "the password is invalid" , Toast.LENGTH_LONG).show();
                }
                else if(!user.setEmail(email)){
                    flag = false;
                    Toast.makeText(SignUpActivity.this , "the e mail is invalid" , Toast.LENGTH_LONG).show();
                }
                if(flag){
                   if( client.getDataBase().signup(user)){
                       Toast.makeText(SignUpActivity.this , "SUCCESSFULLY SIGN UP " , Toast.LENGTH_LONG).show();
                       goToOriginActivity(user.getUsername());
                   }
                   else{
                       Toast.makeText(SignUpActivity.this , "!!!!!!!!!" , Toast.LENGTH_LONG).show();
                   }
                }
            }
        });
    }
}