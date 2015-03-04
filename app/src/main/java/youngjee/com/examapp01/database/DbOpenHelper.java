package youngjee.com.examapp01.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.sql.SQLException;

public class DbOpenHelper {

    private Context mCtx;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;

    public DbOpenHelper(Context context) {
        this.mCtx = context;
    }

    public  DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, "addressbook.db", null, 2);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDB.close();
    }

    public long insertColumn(String name,String contact,String email) {
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("contact",contact);
        values.put("email",email);
        return mDB.insert("address", null, values);
    }

    public Cursor getAllColumns() {
        return mDB.query("address", null, null, null, null, null, null);
    }

    public  boolean deleteColumn(long id){
        return mDB.delete("address", "_id=" + id, null) > 0;
    }
    /*
    * not use only references
    * */
    public boolean updateColumn(long id, String name, String contact, String email) {
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("contact",contact);
        values.put("emaiil",email);
        return mDB.update("address", values, "_id=" + id, null) > 0;
    }



    public  boolean deleteColumn(String number){
        return mDB.delete("address", "contact=" + number, null) > 0;
    }

    public Cursor getColumn(long id) {
        Cursor c = mDB.query("address", null, "_id=" + id, null, null, null, null);

        if ( c != null && c.getCount() != 0 ) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getMatchName(String name) {
        Cursor c = mDB.rawQuery("select * from address where name ='" + name + "'", null);
        return c;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        /*
        * 최초 DB를 만들때 한번만 호출된다.
        * */
        @Override
        public void onCreate(SQLiteDatabase db) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("create table address ")
                    .append("(")
                    .append(BaseColumns._ID).append(" integer primary key autoincrement,")
                    .append("name text not null,")
                    .append("contact text not null,")
                    .append("email text not null")
                    .append(");");

            db.execSQL(buffer.toString());
        }

       /*
       * 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
       * */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS address");
            onCreate(db);
        }
    }
}
