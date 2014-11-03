package com.garciaericn.goodeats.data;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/16/14.
 */
public class DataManager {
    private static final String TAG = "com.garciaericn.photolocal.data.DataManager.TAG";
    private static final String FILENAME = "Pins";
    private static DataManager mgr = new DataManager();
    private static Context mContext;
    private ArrayList<Pin> pins;

    public static DataManager getInstance(Context context) {
        mContext = context;
        if (mgr == null) {
            mgr = new DataManager();
        }
        return mgr;
    }

    private DataManager() {
    }

    public Boolean checkFile(Context context) {
        Log.i(TAG, "checkFile entered");
        // Store data in "protected" directory
        File external = context.getExternalFilesDir(null);
        File file = new File(external, FILENAME);
        return file.exists();
    }

    public void writeToDisk(ArrayList<Pin> reviews) {
        Log.i(TAG, "writeToDisk entered");

        File external = mContext.getExternalFilesDir(null);
        File file = new File(external, FILENAME);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(reviews);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pin> readFromDisk() {
        Log.i(TAG, "readFromFile entered");

        if (checkFile(mContext)) {
            File external = mContext.getExternalFilesDir(null);
            File file = new File(external, FILENAME);

            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                pins = (ArrayList<Pin >) objectInputStream.readObject();
                objectInputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (OptionalDataException e) {
                e.printStackTrace();
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pins;
    }
}
