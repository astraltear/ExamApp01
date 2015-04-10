package youngjee.com.examapp01;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class DataBaseDBHandle {
    private Context context;
    private DataBaseDBHelper helper;
    private SQLiteDatabase db;

    public DataBaseDBHandle(Context context) {
        this.context = context;
        helper = new DataBaseDBHelper(context);
        db = helper.getWritableDatabase();
    }

    public static DataBaseDBHandle open(Context context) throws SQLException {
        DataBaseDBHandle handle = new DataBaseDBHandle(context);
        return handle;
    }

    public void close(){
        helper.close();
    }

    public long insert(String name) {
        ContentValues values = new ContentValues();
        values.put("name",name);

        long result = db.insert("person", null, values);
        return result;
    }

    public Cursor selectAll() {
        Cursor cursor = db.query(true, "person", new String[]{"id", "name"}, null, null, null, null, null, null);
        return cursor;
    }

    public Cursor selectId(String id) {
        Cursor cursor = db.query(true, "person", new String[]{"id", "name"}, "id=" + id, null, null, null, null, null);

        if(cursor !=null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Cursor selectName(String name) {
        String sql = "select * from person where name like ?";

        Cursor cursor = db.rawQuery(sql, new String[]{name+"%"});
        return cursor;
    }

    public long delete(String id) {
        long result = db.delete("person", "id=" + id, null);
        return result;
    }

    public long update(String id, String name) {
        ContentValues values = new ContentValues();
        values.put("name",name);

        long result = db.update("person", values, "id=" + id, null);
        return result;
    }

}


