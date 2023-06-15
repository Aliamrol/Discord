package AliAmrol.discord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Locale;



public class MainActivity extends AppCompatActivity {


    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



    public void goToSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "welcome to discord :)".toUpperCase(Locale.ROOT), Toast.LENGTH_SHORT).show();
        Button sgn = findViewById(R.id.btn_sign_up);
        Button lgn = findViewById(R.id.btn_login);


        sgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "SIGN UP ...".toUpperCase(Locale.ROOT), Toast.LENGTH_SHORT).show();
                goToSignUp();
            }
        });

        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "LOG IN ...".toUpperCase(Locale.ROOT), Toast.LENGTH_SHORT).show();
                goToLogin();
            }
        });

    }
}