package dehant.rayan.tp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ContactAdapter extends BaseAdapter {
    private ArrayList<Contact> list;
    private LayoutInflater inflater;

    public ContactAdapter(Context con){
        super();
        Fonctions funct=new Fonctions(con);
        inflater=LayoutInflater.from(con);
        list=funct.loadListContact();
        ArrayList<Contact> favoris=new ArrayList<>();
        Contact courant;
        ArrayList<Contact> nonFav=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            courant=list.get(i);
            if(courant.isFavori()){
                favoris.add(courant);
            }else{
                nonFav.add(courant);
            }
        }
        sort(favoris);
        sort(nonFav);
        list=new ArrayList<>();
        list.addAll(favoris);
        list.addAll(nonFav);
        funct.saveList(list);
    }

    private void sort(ArrayList<Contact> lst){
        Collections.sort(lst, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getNom().toLowerCase().compareTo(o2.getNom().toLowerCase());
            }
        });
    }


    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Contact getItem(int arg0){
        return list.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;

        if(convertView==null){
            layoutItem=(LinearLayout) inflater.inflate(R.layout.layout_list,parent,false);
        }else{
            layoutItem=(LinearLayout) convertView;
        }

        TextView prenom= layoutItem.findViewById(R.id.prenom);
        prenom.setText(this.list.get(position).getPrenom());
        TextView nom= layoutItem.findViewById(R.id.nom);
        nom.setText(this.list.get(position).getNom());
        ImageView image= layoutItem.findViewById(R.id.photo);
        image.setImageResource(this.list.get(position).getImage());
        ImageView fav= layoutItem.findViewById(R.id.favori);
        if(list.get(position).isFavori()){
            fav.setVisibility(View.VISIBLE);
        }else{
            fav.setVisibility(View.INVISIBLE);
        }
        return layoutItem;
    }
}

