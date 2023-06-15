package AliAmrol.discord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class originActivity extends AppCompatActivity {



    public void goToSetting(String username) {
        Intent intent = new Intent(originActivity.this, setting.class);
        intent.putExtra("send" , username);
        startActivity(intent);
    }

    public void goToPVS(String username) {
        Intent intent = new Intent(originActivity.this, privatehats_activity.class);
        intent.putExtra("send" , username);
        startActivity(intent);
    }




    Client client;

    String username = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origin);
        Bundle extrns=getIntent().getExtras();
        username = extrns.getString("send");
        Button chat = findViewById(R.id.chats);
        Button pv = findViewById(R.id.privateChats);
        Button setting = findViewById(R.id.btn_setting);

        new Thread(() -> client = new Client(this)).start();// ----


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSetting(username);
            }
        });

        pv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPVS(username);
            }
        });
    }

}