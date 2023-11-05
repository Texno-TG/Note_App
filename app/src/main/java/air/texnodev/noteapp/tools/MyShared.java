package air.texnodev.noteapp.tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import air.texnodev.noteapp.Models.Node;


public class MyShared {
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public MyShared(Context context, Gson gson) {
        this.sharedPreferences = context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        this.gson = gson;
    }

    public <T> void putList(String key, List<T> list) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, gson.toJson(list));
        editor.apply();
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        String data = sharedPreferences.getString(key, null);
        if (data == null){
            List<T> contact = new ArrayList<>();
            return contact;
        }else {
            Type typeOfT = new TypeToken<List<Node>>() {
            }.getType();

            return gson.fromJson(data, typeOfT);
        }

    }
}
