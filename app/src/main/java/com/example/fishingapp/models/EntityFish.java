package com.example.fishingapp.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class EntityFish extends RealmObject {

    @PrimaryKey
    private String id;
    private String date;
    private String fish;
    private String weight;
    private String captures;
    private String fisher;
    private String information;
    private String image;
    private String sex;
    private boolean loose;



    public EntityFish() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public int setDate(String date) {
        int error = 0;
        if(date != null && !date.isEmpty()){
            Pattern pat = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)" +
                    "(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)" +
                    "0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|" +
                    "(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)" +
                    "(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
            Matcher mat = pat.matcher(date);
            if (!mat.find()) {
                error= 2;
            } else {
                this.date=date;
            }
        }else {
            error = 1;
        }
        return error;


    }

    public boolean isLoose() {
        return loose;
    }

    public void setLoose(boolean loose) {
        this.loose = loose;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFish() {
        return fish;
    }

    public int setFish(String fish) {
        int error = 0;
        if (fish !=null && !fish.isEmpty()){
            Pattern pat = Pattern.compile("[a-zA-ZñÑ\\s]");
            Matcher mat = pat.matcher(fish);
            if(!mat.find()){
                error = 2;
            }else {
                this.fish=fish.toLowerCase();
            }

        }else{
            error = 1;
        }
        return error;
    }

    public String getWeight() {
        return weight;
    }

    public int setWeight(String weight) {
        int error = 0;
        if (weight!=null && !weight.isEmpty()){
            Pattern pat = Pattern.compile("^[0-9]+([,][0-9]+)?$");
            Matcher mat = pat.matcher(weight);
            if(!mat.find()){
                error = 2;
            }else {
                this.weight=weight.toLowerCase();
            }

        }else{
            error = 1;
        }
        return error;
    }


    public String getCaptures() {
        return captures;
    }

    public int setCaptures(String captures) {
        int error = 0;
        if (captures != null && !captures.isEmpty()){
            Pattern pat = Pattern.compile("[a-zA-ZñÑ\\s]");
            Matcher mat = pat.matcher(captures);
            if(!mat.find()){
                error = 2;
            }else {
                this.captures=captures;
            }

        }else{
            error = 1;
        }
        return error;
    }

    public String getFisher() {
        return fisher;
    }

    public int setFisher(String fisher) {
        int error = 0;
        if (fisher != null && !fisher.isEmpty()){
            Pattern pat = Pattern.compile("[a-zA-ZñÑ\\s]");
            Matcher mat = pat.matcher(fisher);
            if(!mat.find()){
                error = 2;
            }else {
                this.fisher=fisher.toLowerCase();
            }

        }else{
            error = 1;
        }
        return error;
    }

    public String getInformation() {
        return information;
    }

    public int setInformation(String information) {
        int error = 0;
        if (information != null && !information.isEmpty()){
            Pattern pat = Pattern.compile("[a-zA-ZñÑ\\s]");
            Matcher mat = pat.matcher(information);
            if(!mat.find()){
                error = 2;
            }else {
                this.information=information.toLowerCase();
            }

        }else{
            error = 1;
        }
        return error;
    }
}
