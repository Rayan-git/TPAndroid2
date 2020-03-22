package dehant.rayan.tp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Modifier extends Activity {
    private CheckBox check;
    private Fonctions funct;
    private Contact contact;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        setTitle("Menu modification");
        funct=new Fonctions(this);
        contact=getIntent().getParcelableExtra("personne");
        position=getIntent().getIntExtra("position",-1);

        Button button=findViewById(R.id.bouton_creation);
        final EditText etPrenom=findViewById(R.id.creation_prenom);
        etPrenom.setText(contact.getPrenom());
        final EditText etNom=findViewById(R.id.creation_nom);
        etNom.setText(contact.getNom());
        final EditText etVille=findViewById(R.id.creation_ville);
        etVille.setText(contact.getVille());
        final EditText etMail=findViewById(R.id.creation_mail);
        etMail.setText(contact.getMail());
        final EditText etTel=findViewById(R.id.creation_tel);
        etTel.setText(contact.getTel());
        check=findViewById(R.id.creation_favori);
        if(contact.isFavori()){
            check.setChecked(true);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPrenom.getText().toString().equals("") || etNom.getText().toString().equals("") || etVille.getText().toString().equals("")
                        || etMail.getText().toString().equals("") || etTel.getText().toString().equals("")){
                    Toast toast = Toast.makeText(Modifier.this, "Il manque des informations", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    if(!Patterns.EMAIL_ADDRESS.matcher(etMail.getText().toString()).matches()){
                        Toast toast = Toast.makeText(Modifier.this, "Adresse e-mail invalide", Toast.LENGTH_LONG);
                        toast.show();
                    }else if(etTel.getText().toString().length()!=10){
                        Toast toast = Toast.makeText(Modifier.this, "Numéro de téléphone invalide", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        Contact result=new Contact(etNom.getText().toString(),etPrenom.getText().toString(),etMail.getText().toString(),etVille.getText().toString(),
                                etTel.getText().toString(),R.drawable.personne, Modifier.this.check.isChecked());
                        funct.removeContact(position);
                        funct.addContact(result);
                        Intent intent=new Intent();
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                }
            }
        });
    }
}
