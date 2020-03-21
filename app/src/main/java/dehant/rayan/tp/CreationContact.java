package dehant.rayan.tp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class CreationContact extends AppCompatActivity {
    CheckBox check;
    Fonctions funct;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        setTitle("Menu d'ajout");
        funct=new Fonctions(this);

        Button button=findViewById(R.id.bouton_creation);
        final EditText etPrenom=findViewById(R.id.creation_prenom);
        final EditText etNom=findViewById(R.id.creation_nom);
        final EditText etVille=findViewById(R.id.creation_ville);
        final EditText etMail=findViewById(R.id.creation_mail);
        final EditText etTel=findViewById(R.id.creation_tel);
        check=findViewById(R.id.creation_favori);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPrenom.getText().toString().equals("") || etNom.getText().toString().equals("") || etVille.getText().toString().equals("")
                        || etMail.getText().toString().equals("") || etTel.getText().toString().equals("")){
                    Toast toast = Toast.makeText(CreationContact.this, "Il manque des informations", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    if(!Patterns.EMAIL_ADDRESS.matcher(etMail.getText().toString()).matches()){
                        Toast toast = Toast.makeText(CreationContact.this, "Adresse e-mail invalide", Toast.LENGTH_LONG);
                        toast.show();
                    }else if(etTel.getText().toString().length()!=10){
                        Toast toast = Toast.makeText(CreationContact.this, "Numéro de téléphone invalide", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        Contact result=new Contact(etNom.getText().toString(),etPrenom.getText().toString(),etMail.getText().toString(),etVille.getText().toString(),
                                etTel.getText().toString(),R.drawable.personne, CreationContact.this.check.isChecked());
                        funct.addContact(result);
                        Intent resultIntent=new Intent();
                        setResult(RESULT_OK,resultIntent);
                        finish();
                    }
                }
            }
        });
    }
}
