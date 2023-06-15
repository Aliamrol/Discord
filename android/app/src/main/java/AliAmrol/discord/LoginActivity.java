package AliAmrol.discord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Client client;
    User user = new User();


    public void goToOriginActivity(String id) {
        Intent intent = new Intent(LoginActivity.this, originActivity.class);
        intent.putExtra("send" , id);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText name = findViewById(R.id.login_username);
        EditText pass = findViewById(R.id.login_Password);
        Button login = findViewById(R.id.login_login);

        new Thread(() -> client = new Client(this)).start();// ----
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String password = pass.getText().toString();
                if(client.getDataBase().checkLogin(username , password , client.getUser())){
                    Toast.makeText( LoginActivity.this, "WELCOME " + username , Toast.LENGTH_SHORT).show();
                    goToOriginActivity(username);
                }
                else {
                    Toast.makeText( LoginActivity.this, "INCORRECT " , Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText( LoginActivity.this, "WELCOME " + username , Toast.LENGTH_SHORT).show();
                //goToOriginActivity(username);
            }
        });
    }
}