package dehant.rayan.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Fonctions funct;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        funct=new Fonctions(this);
        setTitle("Accueil");
        setContentView(R.layout.activity_main);
        Button but = findViewById(R.id.bouton_valider);
        et = findViewById(R.id.user_edittext);
        TextView tv =findViewById(R.id.label_user);
        Button entrer=findViewById(R.id.buttonEntrer);
        if(funct.loadNom().equals("")) {
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et.getText().toString().equals("") || et.getText().toString().equals("Nom d'utilisateur")) {
                        Toast toast = Toast.makeText(MainActivity.this, "Erreur dans la saisie du nom !", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        funct.saveNom(et.getText().toString());
                        Intent intent = new Intent(MainActivity.this, MenuContacts.class);
                        MainActivity.this.startActivity(intent);
                    }
                }
            });
        }else{
            tv.setVisibility(View.INVISIBLE);
            et.setVisibility(View.INVISIBLE);
            but.setVisibility(View.INVISIBLE);
            entrer.setVisibility(View.VISIBLE);
            entrer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,MenuContacts.class);
                    MainActivity.this.startActivity(intent);
                }
            });
        }
    }
}
