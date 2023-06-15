package AliAmrol.discord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;


import java.util.ArrayList;

public class pv extends AppCompatActivity {

    int id_pv;
    String username;
    Client client;
    ArrayAdapter arrayAdapter;
    ArrayList<ChatMessage> messages;


    public void goToPV(String name , int id) {
        Intent intent = new Intent(pv.this, pv.class);
        intent.putExtra("send" , name);
        intent.putExtra("id" , id);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pv);
//      -----------------------------------------------------------------
        new Thread(() -> client = new Client(this)).start();// ---
//      -----------------------------------------------------------------

        ListView listView = findViewById(R.id.messages);
        ImageButton send = findViewById(R.id.send_message_btn);
        EditText msg = findViewById(R.id.send_message);



        Bundle extrns = getIntent().getExtras();
        username = extrns.getString("send");
        id_pv = extrns.getInt("id");

         messages = client.getDataBase().showMessagesPrivateChat( client.getDataBase().determineId(username), id_pv);
        arrayAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , messages);
        listView.setAdapter(arrayAdapter);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = msg.getText().toString();
//                client.getDataBase().sendMessageToPrivateChat(client.getDataBase().determineId(username) , id_pv , s);
//                 messages = client.getDataBase().showMessagesPrivateChat( client.getDataBase().determineId(username), id_pv);
//                arrayAdapter = new ArrayAdapter(this , simple_spinner_item , messages);
//                listView.setAdapter(arrayAdapter);
                goToPV(username , id_pv);
            }
        });

    }
}