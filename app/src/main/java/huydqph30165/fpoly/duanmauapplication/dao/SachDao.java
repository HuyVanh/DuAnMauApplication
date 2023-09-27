package huydqph30165.fpoly.duanmauapplication.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import huydqph30165.fpoly.duanmauapplication.database.DbHelper;
import huydqph30165.fpoly.duanmauapplication.model.Sach;

public class SachDao {
    DbHelper dbHelper;
    public SachDao(Context context){
        dbHelper = new DbHelper(context);

    }

    //Lấy toàn bộ đầu sách trong thư viện
    public ArrayList<Sach>getDSDauSach(){
        ArrayList<Sach>list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH", null);
        if (cursor.getCount()!= 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
