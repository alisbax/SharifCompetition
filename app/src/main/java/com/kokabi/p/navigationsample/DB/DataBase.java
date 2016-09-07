package com.kokabi.p.navigationsample.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.kokabi.p.navigationsample.Components.CustomLinkedMap;
import com.kokabi.p.navigationsample.Help.AppController;
import com.kokabi.p.navigationsample.Help.Constants;

public class DataBase extends SQLiteOpenHelper {

    static final int version = 1;
    static final String name = "AzmonBaz";
    /*Favorite Test Table Info*/
    static final String tableFavoriteTests = "favoriteTests";
    static final String KEY_idFavoriteTest = "idFavoriteTest";
    static final String KEY_idTest = "idTest";
    static final String KEY_breadCrumb = "breadCrumb";
    static final String createFavoriteTests = "CREATE TABLE " +
            tableFavoriteTests + "(" + KEY_idFavoriteTest + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_idTest + " INTEGER," + KEY_breadCrumb + " TEXT );";
    /*History Table Info*/
    static final String tableHistory = "history";
    static final String KEY_idHistory = "idHistory";
    static final String KEY_idTestHistory = "idTestHistory";
    static final String KEY_testName = "testName";
    static final String KEY_testTime = "testTime";
    static final String KEY_percentage = "percentage";
    static final String KEY_answered = "answered";
    static final String KEY_incorrect = "incorrect";
    static final String KEY_unAnswered = "unAnswered";
    static final String KEY_updateTime = "updateTime";
    static final String KEY_answerList = "answerList";
    static final String KEY_historyBreadCrumb = "historyBreadCrumb";
    static final String createHistory = "CREATE TABLE " +
            tableHistory + "(" + KEY_idHistory + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_idTestHistory + " INTEGER," + KEY_testName + " TEXT," + KEY_testTime + " TEXT,"
            + KEY_percentage + " TEXT," + KEY_answered + " INTEGER," + KEY_incorrect + " INTEGER," + KEY_unAnswered + " INTEGER,"
            + KEY_updateTime + " TEXT," + KEY_answerList + " TEXT," + KEY_historyBreadCrumb + " TEXT );";
    /*Favored Question Table Info*/
    static final String tableFavoredQuestion = "favoredQuestion";
    static final String KEY_idFavoredQuestion = "idFavoredQuestion";
    static final String KEY_favoredTestName = "favoredTestName";
    static final String KEY_idQuestion = "idQuestion";
    static final String KEY_questionImage = "questionImage";
    static final String KEY_answerImage = "answerImage";
    static final String KEY_key = "key";
    static final String KEY_favoredBreadCrumb = "favoredBreadCrumb";
    static final String createFavoredQuestion = "CREATE TABLE " +
            tableFavoredQuestion + "(" + KEY_idFavoredQuestion + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_favoredTestName + " TEXT," + KEY_idQuestion + " INTEGER," + KEY_questionImage + " TEXT,"
            + KEY_answerImage + " TEXT," + KEY_key + " INTEGER," + KEY_favoredBreadCrumb + " TEXT );";
    /*Question State Table Info*/
    static final String tableQuestionState = "questionState";
    static final String KEY_idQuestionState = "idQuestionState";
    static final String KEY_question = "question";
    static final String KEY_name = "questionName";
    static final String KEY_state = "state";
    static final String KEY_testId = "idTest";
    static final String KEY_stateBreadCrumb = "stateBreadCrumb";
    static final String createQuestionState = "CREATE TABLE " +
            tableQuestionState + "(" + KEY_idQuestionState + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_question + " INTEGER," + KEY_name + " TEXT," + KEY_state + " INTEGER,"
            + KEY_testId + " INTEGER," + KEY_stateBreadCrumb + " TEXT );";
    /*Saved Test Table Info*/
    static final String tableSavedTest = "savedTest";
    static final String KEY_id = "idRow";
    static final String KEY_idSavedTest = "idSavedTest";
    static final String KEY_time = "time";
    static final String KEY_answers = "answers";
    static final String KEY_hasNegativePoint = "hasNegativePoint";
    static final String KEY_savedTestName = "savedTestName";
    static final String KEY_isDone = "isDone";
    static final String KEY_initTime = "initTime";
    static final String KEY_savedBreadCrumb = "savedBreadCrumb";
    static final String createSavedTest = "CREATE TABLE " +
            tableSavedTest + "(" + KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_idSavedTest + " INTEGER," + KEY_time + " INTEGER," + KEY_answers + " TEXT,"
            + KEY_hasNegativePoint + " INTEGER," + KEY_savedTestName + " TEXT," + KEY_isDone + " INTEGER,"
            + KEY_initTime + " INTEGER," + KEY_savedBreadCrumb + " TEXT );";

    public DataBase() {
        super(AppController.getCurrentContext(), name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createFavoriteTests);
        db.execSQL(createHistory);
        db.execSQL(createFavoredQuestion);
        db.execSQL(createQuestionState);
        db.execSQL(createSavedTest);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tableFavoriteTests);
        db.execSQL("DROP TABLE IF EXISTS " + tableHistory);
        db.execSQL("DROP TABLE IF EXISTS " + tableFavoredQuestion);
        db.execSQL("DROP TABLE IF EXISTS " + tableQuestionState);
        db.execSQL("DROP TABLE IF EXISTS " + tableSavedTest);
        onCreate(db);
    }

    /*Insert Methods*/
    public long favoriteTestInsert(String breadCrumb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues favoriteTestValue = new ContentValues();

        favoriteTestValue.put(KEY_breadCrumb, breadCrumb);
        long success = db.insert(tableFavoriteTests, null, favoriteTestValue);
        db.close();
        return success;
    }

    /*Update Methods*/
    public long savedTestUpdate(int id, int time, String answers, int hasNegativePoint, String name, int isDone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues savedTestValue = new ContentValues();

        savedTestValue.put(KEY_time, time);
        savedTestValue.put(KEY_answers, answers);
        savedTestValue.put(KEY_hasNegativePoint, hasNegativePoint);
        savedTestValue.put(KEY_savedTestName, name);
        savedTestValue.put(KEY_isDone, isDone);

        String whereClause = KEY_idSavedTest + " = " + id;
        long success = db.update(tableSavedTest, savedTestValue, whereClause, null);
        db.close();
        return success;
    }

    /*Delete Methods*/
    public long favoriteTestDelete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = KEY_idTest + " = " + id;
        long success = db.delete(tableFavoriteTests, whereClause, null);
        db.close();
        return success;
    }

    /*Select Methods*/
    public CustomLinkedMap<Integer, Integer> selectQuestionState(String testName, String breadCrumb) {
        CustomLinkedMap<Integer, Integer> state = new CustomLinkedMap<>();
        String query = "SELECT * FROM " + tableQuestionState
                + " WHERE " + KEY_name + " = '" + testName + "' AND "
                + KEY_stateBreadCrumb + " = '" + breadCrumb + "'";

        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    state.put(cursor.getInt(1), cursor.getInt(3));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.i(Constants.TAG, e.toString());
        }
        this.getReadableDatabase().close();
        return state;
    }

    public boolean isTestFavored(int id) {
        boolean isFavored = false;
        String query = "SELECT * FROM " + tableFavoriteTests +
                " WHERE " + KEY_idTest + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
//                    TestsTitleObj testsTitleObj = new TestsTitleObj();
//                    testsTitleObj.setIdTest(cursor.getInt(1));
//                    if (testsTitleObj.getIdTest() != 0) {
//                        isFavored = true;
//                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            Log.i(Constants.TAG, e.toString());
        }
        this.getReadableDatabase().close();
        return isFavored;
    }
}