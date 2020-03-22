package dehant.rayan.tp;

import android.os.Parcel;
import android.os.Parcelable;


public class Contact implements Parcelable {
    private String nom;
    private String prenom;
    private String mail;
    private String tel;
    private String ville;
    private int image;
    private String favori;

    public Contact(Parcel in){
        nom=in.readString();
        prenom=in.readString();
        mail=in.readString();
        tel=in.readString();
        ville=in.readString();
        favori=in.readString();
        image=in.readInt();
    }

    public Contact(String n,String p,String m,String v,String t,int im,boolean fav){
        nom=n;
        prenom=p;
        mail=m;
        ville=v;
        tel=t;
        image=im;
        if(fav){
            favori="true"; //ici on a transforme le booléen en string car la méthode writeBoolean d'un Parcel nécessite l'API 29 (pour plus de flexibilité fait ce choix)
        }else{
            favori="false";
        }

    }

    public static final Creator<Contact> CREATOR=new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest,int flags){
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(mail);
        dest.writeString(tel);
        dest.writeString(ville);
        dest.writeString(favori);
        dest.writeInt(image);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getVille() {
        return ville;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public static Creator<Contact> getCREATOR() {
        return CREATOR;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public boolean isFavori() {
        if(favori.equals("true")){
            return true;
        }else{
            return false;
        }
    }

    public void setFavori(String favori) {
        if(favori.equals("true")){
            this.favori=favori;
        }else{
            this.favori="false";
        }
    }
}
