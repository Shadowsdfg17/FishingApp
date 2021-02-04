package com.example.fishingapp.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class FishModel {


    public boolean insert(EntityFish fish) {

        fish.setId(UUID.randomUUID().toString());
        Realm realm = Realm.getDefaultInstance();
        Log.d("Realm", "path: " + realm.getPath());

        try {
            realm.beginTransaction();
            realm.copyToRealm(fish);
            realm.commitTransaction();
        } catch (Exception e) {
            return false;
        }

        realm.close();

        return true;
    }

    public ArrayList<EntityFish> getAllSummarize() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<EntityFish> results = realm.where(EntityFish.class).findAll();

        Log.d("Realm find items:", " " + results.size());

        ArrayList<EntityFish> fish = new ArrayList<>();
        fish.addAll(realm.copyFromRealm(results));

        realm.close();

        ArrayList<EntityFish> result = new ArrayList<>();

        for (EntityFish a : fish) {
            EntityFish entityFish= new EntityFish();
            entityFish.setId(a.getId());
            entityFish.setFisher(a.getFisher());
            entityFish.setFish(a.getFish());
            entityFish.setImage(a.getImage());
            result.add(entityFish);
        }

        return result;
    }

    public boolean deleteFish(EntityFish fish) {
        boolean flag;
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            EntityFish entityFish = realm.where(EntityFish.class).equalTo("id", fish.getId()).findFirst();
            entityFish.deleteFromRealm();
            realm.commitTransaction();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        realm.close();

        return flag;
    }

    public boolean updateFish(EntityFish fish) {
        boolean flag = false;
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            EntityFish entityFish = realm.where(EntityFish.class).equalTo("id", fish.getId()).findFirst();
            entityFish.setImage(fish.getImage());
            entityFish.setDate(fish.getDate());
            entityFish.setFish(fish.getFish());
            entityFish.setWeight(fish.getWeight());
            entityFish.setCaptures(fish.getCaptures());
            entityFish.setFisher(fish.getFisher());
            entityFish.setInformation(fish.getInformation());
            entityFish.setSex(fish.getSex());
            entityFish.setLoose(fish.isLoose());
            realm.insertOrUpdate(entityFish);
            realm.commitTransaction();
            flag = true;
        } catch (Exception e) {
        }

        return flag;
    }

    public ArrayList<String> getAllSexs(){
        ArrayList<String> result = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<EntityFish> results = realm.where(EntityFish.class).distinct("sex").findAll();
        if(results!=null){
            ArrayList<EntityFish> fishs = (ArrayList<EntityFish>) realm.copyFromRealm(results);
            for(EntityFish a : fishs){
                if(a.getSex()!=null){
                    result.add(a.getSex());
                }
            }

        }
        realm.close();
        return result;
    }

    public static ArrayList<EntityFish> getItemsByAllCriterion(String sex, String date, String fish) {
        Realm realm = Realm.getDefaultInstance();
        ArrayList<EntityFish> list = new ArrayList<>();
        RealmResults<EntityFish> result = realm.where(EntityFish.class).contains("fish", fish).and().equalTo("date", date).and().equalTo("sex", sex).findAll();
        list.addAll(realm.copyFromRealm(result));
        realm.close();
        return list;
    }
    public static ArrayList<EntityFish> getItemsByFish(String fish) {
        Realm realm = Realm.getDefaultInstance();
        ArrayList<EntityFish> list = new ArrayList<>();
        RealmResults<EntityFish> result = realm.where(EntityFish.class).contains("fish", fish).findAll();
        list.addAll(realm.copyFromRealm(result));
        realm.close();
        return list;
    }

    public static ArrayList<EntityFish> getItemsBySex(String sex) {
        Realm realm = Realm.getDefaultInstance();
        ArrayList<EntityFish> list = new ArrayList<>();
        RealmResults<EntityFish> result = realm.where(EntityFish.class).equalTo("sex", sex).findAll();
        list.addAll(realm.copyFromRealm(result));
        realm.close();
        return list;
    }

    public static ArrayList<EntityFish> getItemsByDate(String date) {
        Realm realm = Realm.getDefaultInstance();
        ArrayList<EntityFish> list = new ArrayList<>();
        RealmResults<EntityFish> result = realm.where(EntityFish.class).equalTo("date", date).findAll();
        list.addAll(realm.copyFromRealm(result));
        realm.close();
        return list;
    }

    public EntityFish getById(String id){
        Realm realm = Realm.getDefaultInstance();
        EntityFish result = realm.copyFromRealm(Objects.requireNonNull(realm.where(EntityFish.class).equalTo("id", id).findFirst()));
        realm.close();
        return result;
    }

}
