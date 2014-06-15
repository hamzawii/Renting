package com.example.renting.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.renting.entity.LesTrajets;
import com.example.renting.entity.Trajet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SharedPref {

	Context context;
	private static String LESTRAJET_KEY = "lesTrajets";



	public static void SaveString(Context context, String key, String value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"prefs", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getString(Context context,String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"prefs", context.MODE_PRIVATE);
		return sharedPreferences.getString(key, "");
	}

	public static void SaveInt(Context context,String key, int value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"prefs", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static int getInt(Context context,String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"prefs", context.MODE_PRIVATE);
		return sharedPreferences.getInt(key, 0);
	}
	
	public static void SaveBoolean(Context context,String key, boolean value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"prefs", context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static boolean getBoolean(Context context,String key) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"prefs", context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(key, true);
	}

	public static void SavelesTrajects(Context context,LesTrajets value) {
		// cast to json
		Gson gson = new Gson();
		String json = gson.toJson(value);
		SaveString(context,LESTRAJET_KEY, json);
	}

	public static LesTrajets getlesTrajects(Context context) {
		Gson gson = new Gson();
		String json = getString(context,LESTRAJET_KEY);
		return gson.fromJson(json, LesTrajets.class);
	}

	public static void SaveListString(Context context,String key, List<String> value) {
		Gson gson = new Gson();
		String json = gson.toJson(value);
		System.out.println("json" + json);
		SaveString(context, key, json);
	}

	public static List<String> GetListString(Context context,String key) {
		String old = getString(context,key);

		Gson gson = new Gson();

		Type listType = new TypeToken<List<String>>() {
		}.getType();
		List<String> res = (List<String>) gson.fromJson(old, listType);

		return res;
	}

}
