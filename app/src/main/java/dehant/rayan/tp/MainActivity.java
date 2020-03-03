package dehant.rayan.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText et=findViewById(R.id.user_edittext);
        et.setText("Nom d'utilisateur");
        Button but=findViewById(R.id.bouton_valider);
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText et=findViewById(R.id.user_edittext);
                if(!hasFocus && et.getText().toString().equals("")){
                    et.setText("Nom d'utilisateur");
                } else if (hasFocus && et.getText().toString().equals("Nom d'utilisateur")){
                    et.setText("");
                }
            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et=findViewById(R.id.user_edittext);
                if(et.getText().toString().equals("") || et.getText().toString().equals("Nom d'utilisateur")){
                    Toast toast=Toast.makeText(MainActivity.this,"Erreur dans la saisie du nom !",Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    Intent intent=new Intent(MainActivity.this,MenuContacts.class);
                    MainActivity.this.startActivity(intent);
                }
            }
        });
    }
}
