package dehant.rayan.tp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Fonctions {
    private String name;
    private ArrayList<Contact> listContact;
    private Context context;

    public Fonctions(Context c){
        context=c;
        loadNom();
        loadListContact();
    }

    public String loadNom(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String nom = settings.getString("nom", "");
        name=nom;
        return nom;
    }

    public void saveNom(String nom){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("nom", nom);
        editor.commit();
    }

    public void addContact(Contact cont){
        listContact.add(cont);
        saveList(listContact);
    }

    public void saveList(ArrayList<Contact> list){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        Gson gson=new Gson();
        String json=gson.toJson(list);
        editor.putString("listContact", json);
        editor.apply();
        editor.commit();
    }


    public ArrayList<Contact> loadListContact(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson=new Gson();
        String json=settings.getString("listContact","");
        Type type=new TypeToken<ArrayList<Contact>>(){}.getType();
        listContact=gson.fromJson(json,type);
        if(listContact==null){
            listContact=new ArrayList<>();
        }
        return listContact;
    }

    public String getName(){
        return name;
    }
}
