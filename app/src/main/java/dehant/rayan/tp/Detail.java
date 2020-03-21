package dehant.rayan.tp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Detail extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        TextView nom;
        TextView prenom;
        TextView ville;
        TextView mail;
        TextView tel;
        ImageView photo;
        ImageView favori;
        Button butSms;
        Button butMail;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Détail contact");
        final Contact personne=getIntent().getParcelableExtra("personne");

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
}
