package com.example.sampleapplication.POJO;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class StorageClass {

    private static final String file = "details.txt";

    public static void saveData(Context context, ArrayList<String> items) {

        try {

            FileOutputStream fileOutputStream = context.openFileOutput(file, 0);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            for (String item : items) {

                writer.write(item);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<String> loadData(Context context) {
        ArrayList<String> items = new ArrayList<>();

        try {
            FileInputStream fileInputStream = context.openFileInput(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line;

            while ((line = reader.readLine()) != null) {

                items.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}



