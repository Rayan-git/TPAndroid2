package dehant.rayan.tp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuContacts extends AppCompatActivity {
    private ContactAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("ok","ok");
        Fonctions funct=new Fonctions(this);
        setTitle("Menu principal");

        setContentView(R.layout.activity_menu_contacts);
        Toast toast = Toast.makeText(MenuContacts.this, "Bienvenue à vous "+funct.getName()+" !", Toast.LENGTH_LONG);
        toast.show();


        ContactAdapter contactAdapter=new ContactAdapter(this);
        listView=this.findViewById(R.id.list);
        listView.setAdapter(contactAdapter);
        adapter=contactAdapter;

        FloatingActionButton button=findViewById(R.id.fab_menu_contact);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuContacts.this,CreationContact.class);
                MenuContacts.this.startActivityForResult(intent,1);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selected=(Contact) parent.getItemAtPosition(position);
                Intent intent=new Intent(MenuContacts.this,Detail.class);
                intent.putExtra("indice",position);
                intent.putExtra("personne",selected);
                MenuContacts.this.startActivityForResult(intent,2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1 || requestCode==2){
            if(resultCode==RESULT_OK){
                adapter=new ContactAdapter(this);
                listView.setAdapter(adapter);
            }
        }
    }
}
