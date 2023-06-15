package AliAmrol.discord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class privatehats_activity extends AppCompatActivity {

    String username;
    Client client;

    public void goToPV(String name , int id) {
        Intent intent = new Intent(privatehats_activity.this, pv.class);
        intent.putExtra("send" , name);
        intent.putExtra("id" , id);
        startActivity(intent);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privatehats);

        Bundle extrns = getIntent().getExtras();
        username = extrns.getString("send");

        ListView pvs = findViewById(R.id.pvs);

        // ------------------------------------------------------------------------
        new Thread(() -> client = new Client(this)).start();// -------------
        // ------------------------------------------------------------------------

        ImageButton createpv = findViewById(R.id.createpv);
        EditText target = findViewById(R.id.edti_user);


        ArrayList<PrivateChat> pv = client.getDataBase().showPrivateChats(client.getDataBase().determineId(username));

        ArrayAdapter arrayAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , pv);
        pvs.setAdapter(arrayAdapter);


        createpv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = target.getText().toString();
                Toast.makeText(privatehats_activity.this, client.getDataBase().creatPrivateChat(client.getDataBase().determineId(username), client.getDataBase().determineId(name)), Toast.LENGTH_LONG).show();
            }
        });

        pvs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idpv = pv.get(position).getId();
                goToPV(username , idpv);
            }
        });

    }
}