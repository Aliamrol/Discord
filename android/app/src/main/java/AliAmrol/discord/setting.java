package AliAmrol.discord;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class setting extends AppCompatActivity {
    Client client ;

//    DataBase dataBase = new DataBase();
    private ObjectOutputStream outputStream;

    private ObjectInputStream inputStream;

    /**
     * Output client data base.
     */
//    public void outputClientDataBase() {
//        try {
//            outputStream.writeUnshared(client.getDataBase());
//        } catch (IOException e) {
//            System.exit(0);
//        }
//    }

    /**
     * Input client data base.
     */
//    public void inputClientDataBase() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    dataBase = (DataBase) inputStream.readObject();
//                } catch (IOException e) {
//                    System.exit(0);
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

    /**
     * Connect.
     *
     * @throws Exception the exception
     */


    public void goToOriginActivity(String username) {
        Intent intent = new Intent(setting.this, originActivity.class);
        intent.putExtra("send", username);
        startActivity(intent);
    }

    String username = null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        new Thread(() -> client = new Client(this)).start();// ----
        Bundle extrns = getIntent().getExtras();
        username = extrns.getString("send");
        EditText username_txt = findViewById(R.id.change_username);
        EditText pass_txt = findViewById(R.id.change_password);
        EditText email_txt = findViewById(R.id.change_email);
        Button back = findViewById(R.id.back_od_setting);
        Button username_btn = findViewById(R.id.btn_change_username);
        Button pass = findViewById(R.id.btn_change_password);
        Button email = findViewById(R.id.btn_change_email);
        Button get_id = findViewById(R.id.get_id);
        TextView username_show = findViewById(R.id.username_show);
        username_show.setText("USER NAME : " + username);





//        TextView status = findViewById(R.id.status);
//        status.setText(dataBase.getUser(dataBase.determineId(username)).getState());
//        Button online = findViewById(R.id.status_online);
//        Button idle = findViewById(R.id.status_idle);
//        Button dnt = findViewById(R.id.status_dnt_disturb);
//        Button invisible = findViewById(R.id.status_Invisible);
//        User user = dataBase.getUser(dataBase.determineId(username));
//        online.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user.setState("ONLINE");
//                dataBase.updateUser(user);
//                outputClientDataBase();
//                inputClientDataBase();
//            }
//        });
//
//        idle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user.setState("IDLE");
//                dataBase.updateUser(user);
//                outputClientDataBase();
//                inputClientDataBase();
//            }
//        });
//
//        dnt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user.setState("DNT DISTURB");
//                dataBase.updateUser(user);
//                outputClientDataBase();
//                inputClientDataBase();
//            }
//        });
//
//        invisible.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user.setState("INVISIBLE");
//                dataBase.updateUser(user);
//                outputClientDataBase();
//                inputClientDataBase();
//            }
//        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOriginActivity(setting.this.username);
            }
        });
        get_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(setting.this, "ID : " + String.valueOf(client.getDataBase().determineId(username)), Toast.LENGTH_LONG).show();
            }
        });
        username_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = client.getDataBase().getUser(client.getDataBase().determineId(username));
                String neww = username_txt.getText().toString();
                boolean flag = user.setUsername(neww);
                if (flag) {
                    Toast.makeText(setting.this, "SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    username = neww;
                    client.getDataBase().updateUser(user);
                    client.outputClientDataBase();
                    client.inputClientDataBase();
                } else {
                    Toast.makeText(setting.this, "TRY AGAIN", Toast.LENGTH_LONG).show();
                }

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = client.getDataBase().getUser(client.getDataBase().determineId(username));
                String neww = email_txt.getText().toString();
                boolean flag = user.setEmail(neww);
                if (flag) {
                    Toast.makeText(setting.this, "SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    client.getDataBase().updateUser(user);
                    client.outputClientDataBase();
                    client.inputClientDataBase();
                } else {
                    Toast.makeText(setting.this, "TRY AGAIN", Toast.LENGTH_LONG).show();
                }
            }
        });

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = client.getDataBase().getUser(client.getDataBase().determineId(username));
                String neww = pass_txt.getText().toString();
                boolean flag = user.setPassword(neww);
                if (flag) {
                    Toast.makeText(setting.this, "SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    client.getDataBase().updateUser(user);
                    client.outputClientDataBase();
                    client.inputClientDataBase();
                } else {
                    Toast.makeText(setting.this, "TRY AGAIN", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}