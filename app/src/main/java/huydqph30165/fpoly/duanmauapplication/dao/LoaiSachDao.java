package huydqph30165.fpoly.duanmauapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import huydqph30165.fpoly.duanmauapplication.database.DbHelper;
import huydqph30165.fpoly.duanmauapplication.model.Loaisach;

public class LoaiSachDao {
    DbHelper dbHelper;
    public LoaiSachDao(Context context){
        dbHelper = new DbHelper(context);

    }
    //lấy danh sách loại sách
    public ArrayList<Loaisach>getDanhSachLoaiSach(){
        ArrayList<Loaisach>list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT *FROM LOAISACH", null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                list.add(new Loaisach(cursor.getInt(0), cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    //thêm loại sách
    public boolean themLoaiSach(String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", tenLoai);
        long check = sqLiteDatabase.insert("LOAISACH", null, contentValues);
        if (check == -1){
            return false;
        }else {
            return true;
        }
    }
    //xóa loai sách
    public int xoaLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH WHERE maloai = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0){
            return -1;
        }
        long check = sqLiteDatabase.delete("LOAISACH", "maloai = ?", new String[]{String.valueOf(id)});
        if (check == -1)
            return 0;
        return 1;
    }
    public boolean thayDoiLoaiSach(Loaisach loaisach){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloia", loaisach.getTenloai());
        long check = sqLiteDatabase.update("LOAISACH", contentValues,"maloai = ?", new String[]{String.valueOf(loaisach.getId())});
        if (check == -1)
            return  false;
        return true;
    }
}
