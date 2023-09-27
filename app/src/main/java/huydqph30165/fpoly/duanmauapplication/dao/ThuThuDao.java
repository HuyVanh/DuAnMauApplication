package huydqph30165.fpoly.duanmauapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import huydqph30165.fpoly.duanmauapplication.database.DbHelper;

public class ThuThuDao {
    DbHelper dbHelper;
    public ThuThuDao(Context context){
        dbHelper = new DbHelper(context);

    }
    //Đăng Nhập
    public boolean checkDangNhap(String matt, String matkhau){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }
    public int capnhapMatKhau(String username, String oldPass, String newPass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{username, oldPass});
        if (cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", newPass);
            long check = sqLiteDatabase.update("THUTHU", contentValues,"matt", new String[]{username});
            if (check == -1){
                return -1;
            }else {
                return 1;
            }
        }
        return 0;
    }
}
