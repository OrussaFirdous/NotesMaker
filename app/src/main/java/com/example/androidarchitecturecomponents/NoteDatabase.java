package com.example.androidarchitecturecomponents;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1 )
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance; //to make this singleton that is only one instance is allowed throughut the app

    public abstract NoteDao noteDao();
    //create database

    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null ){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database" )
                    .fallbackToDestructiveMigration()//in case we increment the version the app will have a new database
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

     //if we want to have an already populated table
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
         @Override
         public void onCreate(@NonNull SupportSQLiteDatabase db) {
             super.onCreate(db);
             new PopulateDbAsyncTask(instance).execute();
         }
     };
    private static class PopulateDbAsyncTask extends AsyncTask<Void , Void, Void>{
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db){
            noteDao=db.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1","Description 1",1));
            noteDao.insert(new Note("Title 2","Description 2",2));
            noteDao.insert(new Note("Title 3","Description 3",3));

            return null;
        }
    }
}
