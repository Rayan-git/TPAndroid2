package dehant.rayan.tp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreationContact extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST=1;
    private static final int RESULT_LOAD_IMAGE = 1;

    private CheckBox check;
    private Fonctions funct;
    private Button image;
    private EditText editImage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        funct=new Fonctions(this);

        Button button=findViewById(R.id.bouton_creation);
        final EditText etPrenom=findViewById(R.id.creation_prenom);
        final EditText etNom=findViewById(R.id.creation_nom);
        final EditText etVille=findViewById(R.id.creation_ville);
        final EditText etMail=findViewById(R.id.creation_mail);
        final EditText etTel=findViewById(R.id.creation_tel);
        editImage=findViewById(R.id.creation_photo);
        image=findViewById(R.id.bouton_photo_creation);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,RESULT_LOAD_IMAGE);
                openFileChooser();
            }
        });
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

    private void openFileChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData() !=null){
            editImage.setText(data.getData().toString());
        }
    }
}
