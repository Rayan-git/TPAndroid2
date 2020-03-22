package dehant.rayan.tp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Detail extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView nom;
    private TextView prenom;
    private TextView ville;
    private TextView mail;
    private TextView tel;
    private ImageView photo;
    private ImageView favori;
    private Button butSms;
    private Button butMail;
    private Contact personne;
    private int position;
    private Fonctions funct;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Détail contact");
        funct=new Fonctions(this);
        personne=getIntent().getParcelableExtra("personne");
        position=getIntent().getIntExtra("indice",-1);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nom=findViewById(R.id.detail_nom);
        nom.setText("Nom : "+personne.getNom());
        prenom=findViewById(R.id.detail_prenom);
        prenom.setText("Prénom : "+personne.getPrenom());
        ville=findViewById(R.id.detail_ville);
        ville.setText("Ville : "+personne.getVille());
        mail=findViewById(R.id.detail_mail);
        mail.setText("Mail : "+personne.getMail());
        tel=findViewById(R.id.detail_tel);
        tel.setText("Tél. : "+personne.getTel());
        photo=findViewById(R.id.detail_photo);
        photo.setImageResource(personne.getImage());
        favori=findViewById(R.id.detail_favori);
        if(!personne.isFavori()){
            favori.setVisibility(View.INVISIBLE);
        }
        FloatingActionButton button=findViewById(R.id.fab_detail);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Detail.this,Modifier.class);
                intent.putExtra("personne",personne);
                intent.putExtra("position",position);
                Detail.this.startActivityForResult(intent,1);
            }
        });
        butSms=findViewById(R.id.bouton_envoyer_sms);
        butSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("smsto:" + personne.getTel())); // This ensures only SMS apps respond
                intent.putExtra("sms_body", "");
                startActivity(intent);
            }
        });
        butMail=findViewById(R.id.bouton_envoyer_mail);
        butMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, personne.getMail());
                intent.putExtra(Intent.EXTRA_SUBJECT, "Sujet du mail");
                intent.putExtra(Intent.EXTRA_TEXT, "Ceci correspond au mail à envoyer");
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an email client"));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            funct.removeContact(position);
            Intent intent=new Intent();
            setResult(RESULT_OK,intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        }
    }
}
