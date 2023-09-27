package huydqph30165.fpoly.duanmauapplication.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import huydqph30165.fpoly.duanmauapplication.database.DbHelper;
import huydqph30165.fpoly.duanmauapplication.model.ThanhVien;

public class ThanhVienDao {
    DbHelper dbHelper;
    public ThanhVienDao(Context context){
        dbHelper = new DbHelper(context);

    }
    public ArrayList<ThanhVien>getDSThanhVien(){
        ArrayList<ThanhVien>list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
