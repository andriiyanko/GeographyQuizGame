package com.example.medve.flagsquizapp.DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.service.notification.NotificationListenerService;

import com.example.medve.flagsquizapp.Common.Common;
import com.example.medve.flagsquizapp.Model.QuestionCities;
import com.example.medve.flagsquizapp.Model.QuestionFamousPlaces;
import com.example.medve.flagsquizapp.Model.QuestionFlags;
import com.example.medve.flagsquizapp.Model.QuestionWaterFalls;
import com.example.medve.flagsquizapp.Model.RankingCities;
import com.example.medve.flagsquizapp.Model.RankingFamousPlaces;
import com.example.medve.flagsquizapp.Model.RankingFlags;
import com.example.medve.flagsquizapp.Model.RankingWaterFalls;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "MyDB.db";
    private static String DB_PATH = "";
    private SQLiteDatabase mDataBase;
    private Context mContext = null;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);

        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        File file = new File(DB_PATH+"MyDB.db");
        if(file.exists())
            openDataBase(); // Add this line to fix db.insert can't insert values
        this.mContext = context;
    }

    public void openDataBase() {
        String myPath = DB_PATH + DB_NAME;
        mDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void copyDataBase() throws IOException {
        try {
            InputStream myInput = mContext.getAssets().open(DB_NAME);
            String outputFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0)
                myOutput.write(buffer, 0, length);

            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase tempDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            tempDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        if (tempDB != null)
            tempDB.close();
        return tempDB != null ? true : false;
    }

    public void createDataBase() throws IOException {
        boolean isDBExists = checkDataBase();
        if (isDBExists) {

        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    //CRUD for All Tables
        public List<QuestionFlags> getAllQuestionFlags() {
            List<QuestionFlags> listQuestion = new ArrayList<>();
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c;
            try {
                //SELECT * FROM table WHERE id IN (SELECT id FROM table ORDER BY RANDOM() LIMIT x
                c = db.rawQuery("SELECT * FROM QuestionFlags WHERE ID IN (SELECT ID FROM QuestionFlags ORDER BY Random())", null);
               // c = db.rawQuery("SELECT * FROM QuestionFlags ORDER BY ID", null);
               // c = db.rawQuery("SELECT * FROM QuestionFlags ORDER BY Random()", null);

                if (c == null) return null;
                c.moveToFirst();
                do {
                    int Id = c.getInt(c.getColumnIndex("ID"));
                    String Image = c.getString(c.getColumnIndex("Image"));
                    String AnswerA = c.getString(c.getColumnIndex("AnswerA"));
                    String AnswerB = c.getString(c.getColumnIndex("AnswerB"));
                    String AnswerC = c.getString(c.getColumnIndex("AnswerC"));
                    String AnswerD = c.getString(c.getColumnIndex("AnswerD"));
                    String CorrectAnswer = c.getString(c.getColumnIndex("CorrectAnswer"));

                    QuestionFlags questionFlags = new QuestionFlags(Id, Image, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer);
                    listQuestion.add(questionFlags);
                }
                while (c.moveToNext());
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
            return listQuestion;
    }

        //CRUD for QuestionFamousPlaces
        public List<QuestionFamousPlaces> getAllQuestionFamousPlaces() {
            List<QuestionFamousPlaces> listQuestion = new ArrayList<>();
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c;
            try {
                //SELECT * FROM table WHERE id IN (SELECT id FROM table ORDER BY RANDOM() LIMIT x
                //c = db.rawQuery("SELECT * FROM QuestionFamousPlaces WHERE ID IN (SELECT ID FROM QuestionFamousPlaces ORDER BY Random())", null);
                // c = db.rawQuery("SELECT * FROM QuestionFlags ORDER BY ID", null);
                 c = db.rawQuery("SELECT * FROM QuestionFamousPlaces ORDER BY Random()", null);

                if (c == null) return null;
                c.moveToFirst();
                do {
                    int Id = c.getInt(c.getColumnIndex("ID"));
                    String Image = c.getString(c.getColumnIndex("Image"));
                    String AnswerA = c.getString(c.getColumnIndex("AnswerA"));
                    String AnswerB = c.getString(c.getColumnIndex("AnswerB"));
                    String AnswerC = c.getString(c.getColumnIndex("AnswerC"));
                    String AnswerD = c.getString(c.getColumnIndex("AnswerD"));
                    String CorrectAnswer = c.getString(c.getColumnIndex("CorrectAnswer"));

                    QuestionFamousPlaces questionFamousPlaces = new QuestionFamousPlaces(Id, Image, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer);
                    listQuestion.add(questionFamousPlaces);
                }
                while (c.moveToNext());
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
            return listQuestion;
        }

        //CRUD for Cities
        public List<QuestionCities> getAllQuestionCities() {
            List<QuestionCities> listQuestion = new ArrayList<>();
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c;
            try {
                //SELECT * FROM table WHERE id IN (SELECT id FROM table ORDER BY RANDOM() LIMIT x
                //c = db.rawQuery("SELECT * FROM QuestionFamousPlaces WHERE ID IN (SELECT ID FROM QuestionFamousPlaces ORDER BY Random())", null);
                // c = db.rawQuery("SELECT * FROM QuestionFlags ORDER BY ID", null);
                c = db.rawQuery("SELECT * FROM QuestionCities ORDER BY Random()", null);

                if (c == null) return null;
                c.moveToFirst();
                do {
                    int Id = c.getInt(c.getColumnIndex("ID"));
                    String Image = c.getString(c.getColumnIndex("Image"));
                    String AnswerA = c.getString(c.getColumnIndex("AnswerA"));
                    String AnswerB = c.getString(c.getColumnIndex("AnswerB"));
                    String AnswerC = c.getString(c.getColumnIndex("AnswerC"));
                    String AnswerD = c.getString(c.getColumnIndex("AnswerD"));
                    String CorrectAnswer = c.getString(c.getColumnIndex("CorrectAnswer"));

                    QuestionCities questionCities = new QuestionCities(Id, Image, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer);
                    listQuestion.add(questionCities);
                }
                while (c.moveToNext());
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
            return listQuestion;
        }

        //CRUD for WaterFalls
        public List<QuestionWaterFalls> getAllQuestionWaterFalls() {
            List<QuestionWaterFalls> listQuestion = new ArrayList<>();
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c;
            try {
                //SELECT * FROM table WHERE id IN (SELECT id FROM table ORDER BY RANDOM() LIMIT x
                //c = db.rawQuery("SELECT * FROM QuestionFamousPlaces WHERE ID IN (SELECT ID FROM QuestionFamousPlaces ORDER BY Random())", null);
                // c = db.rawQuery("SELECT * FROM QuestionFlags ORDER BY ID", null);
                c = db.rawQuery("SELECT * FROM QuestionWaterFalls ORDER BY Random()", null);

                if (c == null) return null;
                c.moveToFirst();
                do {
                    int Id = c.getInt(c.getColumnIndex("ID"));
                    String Image = c.getString(c.getColumnIndex("Image"));
                    String AnswerA = c.getString(c.getColumnIndex("AnswerA"));
                    String AnswerB = c.getString(c.getColumnIndex("AnswerB"));
                    String AnswerC = c.getString(c.getColumnIndex("AnswerC"));
                    String AnswerD = c.getString(c.getColumnIndex("AnswerD"));
                    String CorrectAnswer = c.getString(c.getColumnIndex("CorrectAnswer"));

                    QuestionWaterFalls questionWaterFalls = new QuestionWaterFalls(Id, Image, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer);
                    listQuestion.add(questionWaterFalls);
                }
                while (c.moveToNext());
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
            return listQuestion;
        }


    //We need to improve this function to optimize for PlayingFlags
         public List<QuestionFlags> getQuestionMode(String mode) {
        List<QuestionFlags> listQuestion = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        int limit = 0;
        if (mode.equals(Common.MODE.EASY.toString()))
            limit = 10;
       else if (mode.equals(Common.MODE.MEDIUM.toString()))
            limit = 15;
        else if (mode.equals(Common.MODE.HARD.toString()))
            limit = 20;
        else if (mode.equals(Common.MODE.HARDEST.toString()))
            limit = 30;

        try {
            //SELECT * FROM table WHERE id IN (SELECT id FROM table ORDER BY RANDOM() LIMIT x)
            c = db.rawQuery(String.format("SELECT * FROM QuestionFlags WHERE ID IN (SELECT ID FROM QuestionFlags ORDER BY Random() LIMIT %d)",limit), null);
            //c = db.rawQuery(String.format("SELECT * FROM QuestionFlags ORDER BY ID LIMIT %d",limit), null);
            //c = db.rawQuery(String.format("SELECT * FROM QuestionFlags ORDER BY Random() LIMIT %d", limit), null);
            if (c == null) return null;
            c.moveToFirst();
            do {
                int Id = c.getInt(c.getColumnIndex("ID"));
                String Image = c.getString(c.getColumnIndex("Image"));
                String AnswerA = c.getString(c.getColumnIndex("AnswerA"));
                String AnswerB = c.getString(c.getColumnIndex("AnswerB"));
                String AnswerC = c.getString(c.getColumnIndex("AnswerC"));
                String AnswerD = c.getString(c.getColumnIndex("AnswerD"));
                String CorrectAnswer = c.getString(c.getColumnIndex("CorrectAnswer"));

                QuestionFlags questionFlags = new QuestionFlags(Id, Image, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer);
                listQuestion.add(questionFlags);
            }
            while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        return listQuestion;
    }

        //improve FamousPlaces
         public List<QuestionFamousPlaces> getQuestionFamousPlacesMode(String mode) {
        List<QuestionFamousPlaces> listQuestion = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        int limit = 0;
        if (mode.equals(Common.MODE.EASY.toString()))
            limit = 10;
        else if (mode.equals(Common.MODE.MEDIUM.toString()))
            limit = 15;
        else if (mode.equals(Common.MODE.HARD.toString()))
            limit = 20;
        else if (mode.equals(Common.MODE.HARDEST.toString()))
            limit = 30;

        try {
            //SELECT * FROM table WHERE id IN (SELECT id FROM table ORDER BY RANDOM() LIMIT x)
           ///// c = db.rawQuery(String.format("SELECT * FROM QuestionFamousPlaces WHERE ID IN (SELECT ID FROM QuestionFamousPlaces ORDER BY Random() LIMIT %d)",limit), null);
            //c = db.rawQuery(String.format("SELECT * FROM QuestionFlags ORDER BY ID LIMIT %d",limit), null);
            c = db.rawQuery(String.format("SELECT * FROM QuestionFamousPlaces ORDER BY Random() LIMIT %d", limit), null);
            if (c == null) return null;
            c.moveToFirst();
            do {
                int Id = c.getInt(c.getColumnIndex("ID"));
                String Image = c.getString(c.getColumnIndex("Image"));
                String AnswerA = c.getString(c.getColumnIndex("AnswerA"));
                String AnswerB = c.getString(c.getColumnIndex("AnswerB"));
                String AnswerC = c.getString(c.getColumnIndex("AnswerC"));
                String AnswerD = c.getString(c.getColumnIndex("AnswerD"));
                String CorrectAnswer = c.getString(c.getColumnIndex("CorrectAnswer"));

                QuestionFamousPlaces questionFamousPlaces = new QuestionFamousPlaces(Id, Image, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer);
                listQuestion.add(questionFamousPlaces);
            }
            while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        return listQuestion;
    }

        //Improve Cities
        public List<QuestionCities> getQuestionCitiesMode(String mode) {
            List<QuestionCities> listQuestion = new ArrayList<>();
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c;
            int limit = 0;
            if (mode.equals(Common.MODE.EASY.toString()))
                limit = 10;
            else if (mode.equals(Common.MODE.MEDIUM.toString()))
                limit = 15;
            else if (mode.equals(Common.MODE.HARD.toString()))
                limit = 20;
            else if (mode.equals(Common.MODE.HARDEST.toString()))
                limit = 30;

            try {
                //SELECT * FROM table WHERE id IN (SELECT id FROM table ORDER BY RANDOM() LIMIT x)
                ///// c = db.rawQuery(String.format("SELECT * FROM QuestionFamousPlaces WHERE ID IN (SELECT ID FROM QuestionFamousPlaces ORDER BY Random() LIMIT %d)",limit), null);
                //c = db.rawQuery(String.format("SELECT * FROM QuestionFlags ORDER BY ID LIMIT %d",limit), null);
                c = db.rawQuery(String.format("SELECT * FROM QuestionCities ORDER BY Random() LIMIT %d", limit), null);
                if (c == null) return null;
                c.moveToFirst();
                do {
                    int Id = c.getInt(c.getColumnIndex("ID"));
                    String Image = c.getString(c.getColumnIndex("Image"));
                    String AnswerA = c.getString(c.getColumnIndex("AnswerA"));
                    String AnswerB = c.getString(c.getColumnIndex("AnswerB"));
                    String AnswerC = c.getString(c.getColumnIndex("AnswerC"));
                    String AnswerD = c.getString(c.getColumnIndex("AnswerD"));
                    String CorrectAnswer = c.getString(c.getColumnIndex("CorrectAnswer"));

                    QuestionCities questionCities = new QuestionCities(Id, Image, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer);
                    listQuestion.add(questionCities);
                }
                while (c.moveToNext());
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            db.close();
            return listQuestion;
        }

    //Improve WaterFalls
    public List<QuestionWaterFalls> getQuestionWaterFallsMode(String mode) {
        List<QuestionWaterFalls> listQuestion = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        int limit = 0;
        if (mode.equals(Common.MODE.EASY.toString()))
            limit = 10;
        else if (mode.equals(Common.MODE.MEDIUM.toString()))
            limit = 15;
        else if (mode.equals(Common.MODE.HARD.toString()))
            limit = 20;
        else if (mode.equals(Common.MODE.HARDEST.toString()))
            limit = 30;

        try {
            //SELECT * FROM table WHERE id IN (SELECT id FROM table ORDER BY RANDOM() LIMIT x)
            ///// c = db.rawQuery(String.format("SELECT * FROM QuestionFamousPlaces WHERE ID IN (SELECT ID FROM QuestionFamousPlaces ORDER BY Random() LIMIT %d)",limit), null);
            //c = db.rawQuery(String.format("SELECT * FROM QuestionFlags ORDER BY ID LIMIT %d",limit), null);
            c = db.rawQuery(String.format("SELECT * FROM QuestionWaterFalls ORDER BY Random() LIMIT %d", limit), null);
            if (c == null) return null;
            c.moveToFirst();
            do {
                int Id = c.getInt(c.getColumnIndex("ID"));
                String Image = c.getString(c.getColumnIndex("Image"));
                String AnswerA = c.getString(c.getColumnIndex("AnswerA"));
                String AnswerB = c.getString(c.getColumnIndex("AnswerB"));
                String AnswerC = c.getString(c.getColumnIndex("AnswerC"));
                String AnswerD = c.getString(c.getColumnIndex("AnswerD"));
                String CorrectAnswer = c.getString(c.getColumnIndex("CorrectAnswer"));

                QuestionWaterFalls questionWaterFalls = new QuestionWaterFalls(Id, Image, AnswerA, AnswerB, AnswerC, AnswerD, CorrectAnswer);
                listQuestion.add(questionWaterFalls);
            }
            while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        return listQuestion;
    }


        //insert Score to RankingFlags
        public void insertScoreFlags(double score){
            /*SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Score", Score);
            db.insert("RankingFlags",null,contentValues);*/
            String query = "INSERT INTO RankingFlags(Score) VALUES("+score+")";
            mDataBase.execSQL(query);
        }

        public List<RankingFlags> getRankingFlags(){
            List<RankingFlags> listRanking = new ArrayList<>();
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c;
            try {
                c = db.rawQuery("SELECT * FROM RankingFlags ORDER BY Score DESC",null);
                if (c == null) return  null;
                c.moveToNext();
                do {
                    int Id = c.getInt(c.getColumnIndex("Id"));
                    double Score = c.getDouble(c.getColumnIndex("Score"));

                    RankingFlags rankingFlags = new RankingFlags(Id,Score);
                    listRanking.add(rankingFlags);
                }
                while (c.moveToNext());
                c.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            db.close();
            return listRanking;
        }

        //insert Score to RankingFamousPlaces
        public void insertScoreFamousPlaces(double score){
            /*SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Score", Score);
            db.insert("RankingFlags",null,contentValues);*/
        String query = "INSERT INTO RankingFamousPlaces(Score) VALUES("+score+")";
        mDataBase.execSQL(query);
    }

        public List<RankingFamousPlaces> getRankingFamousPlaces(){
        List<RankingFamousPlaces> listRanking = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM RankingFamousPlaces ORDER BY Score DESC",null);
            if (c == null) return  null;
            c.moveToNext();
            do {
                int Id = c.getInt(c.getColumnIndex("Id"));
                double Score = c.getDouble(c.getColumnIndex("Score"));

                RankingFamousPlaces rankingFamousPlaces = new RankingFamousPlaces(Id,Score);
                listRanking.add(rankingFamousPlaces);
            }
            while (c.moveToNext());
            c.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return listRanking;
    }

        //Insert to ScoreCities
        public void insertScoreCities(double score){
            /*SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Score", Score);
            db.insert("RankingFlags",null,contentValues);*/
            String query = "INSERT INTO RankingCities(Score) VALUES("+score+")";
            mDataBase.execSQL(query);
        }

        public List<RankingCities> getRankingCities(){
        List<RankingCities> listRanking = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM RankingCities ORDER BY Score DESC",null);
            if (c == null) return  null;
            c.moveToNext();
            do {
                int Id = c.getInt(c.getColumnIndex("Id"));
                double Score = c.getDouble(c.getColumnIndex("Score"));

                RankingCities rankingCities = new RankingCities(Id,Score);
                listRanking.add(rankingCities);
            }
            while (c.moveToNext());
            c.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return listRanking;
    }


    //Insert to ScoreWatterFalls
    public void insertScoreWaterFalls(double score){
            /*SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Score", Score);
            db.insert("RankingFlags",null,contentValues);*/
        String query = "INSERT INTO RankingWaterFalls(Score) VALUES("+score+")";
        mDataBase.execSQL(query);
    }

    public List<RankingWaterFalls> getRankingWaterFalls(){
        List<RankingWaterFalls> listRanking = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        try {
            c = db.rawQuery("SELECT * FROM RankingWaterFalls ORDER BY Score DESC",null);
            if (c == null) return  null;
            c.moveToNext();
            do {
                int Id = c.getInt(c.getColumnIndex("Id"));
                double Score = c.getDouble(c.getColumnIndex("Score"));

                RankingWaterFalls rankingWaterFalls = new RankingWaterFalls(Id,Score);
                listRanking.add(rankingWaterFalls);
            }
            while (c.moveToNext());
            c.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return listRanking;
    }

        //Update Version
        public int getPlayCount(int level)
        {
            int result = 0;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c;
            try {
                c = db.rawQuery("SELECT PlayCount FROM UserPlayCount WHERE Level="+level+";",null);
                if (c == null) return 0;
                c.moveToNext();
                do {
                    result = c.getInt(c.getColumnIndex("PlayCount"));
                }
                while (c.moveToNext());
                c.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return result;
        }

        public void updatePlayCount(int level, int playCount){
            String query = String.format("UPDATE UserPlayCount Set PlayCount = %d WHERE Level = %d",playCount,level);
            mDataBase.execSQL(query);
        }






}
