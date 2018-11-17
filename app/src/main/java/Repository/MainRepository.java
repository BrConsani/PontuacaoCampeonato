package Repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import Models.Championship;
import Models.RaceSteps;

public abstract class MainRepository {

    private static String CHAMPIONSHIPS_DATA = "Championships";

    public static void salvarObjeto(Context context, List<Championship> championships){
        SharedPreferences settings = context.getSharedPreferences(CHAMPIONSHIPS_DATA, 0);
        SharedPreferences.Editor editor = settings.edit();
        Gson gson = new Gson();
        String json = gson.toJson(championships);
        editor.putString(CHAMPIONSHIPS_DATA, json);
        editor.apply();
    }

    public static void salvarObjeto(Context context, Championship championship, int index){
        SharedPreferences settings = context.getSharedPreferences(CHAMPIONSHIPS_DATA, 0);
        SharedPreferences.Editor editor = settings.edit();
        Gson gson = new Gson();
        List<Championship> lst = lerObjetoCompleto(context);
        lst.set(index, championship);
        String json = gson.toJson(lst);
        editor.putString(CHAMPIONSHIPS_DATA, json);
        editor.apply();
    }

    public static List<Championship> lerObjetoCompleto(Context context){
        try{
            SharedPreferences settings = context.getSharedPreferences(CHAMPIONSHIPS_DATA, 0);
            Gson gson = new Gson();
            String json = settings.getString(CHAMPIONSHIPS_DATA, "");
            Type listType = new TypeToken<List<Championship>>(){}.getType();
            return gson.fromJson(json, listType);
        }catch (Exception ex){
            return null;
        }
    }
}
