package youngjee.com.examapp01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "test";
    private static final int DB_VER = 1;

    public DataBaseDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " create table person(id integer primary key autoincrement, name text not null) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exist person";
        db.execSQL(sql);
        onCreate(db);

    }
}
