package huydqph30165.fpoly.duanmauapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import huydqph30165.fpoly.duanmauapplication.dao.ThuThuDao;

public class DangNhapActivity extends AppCompatActivity {
    EditText edtUser, edtPass;
    Button dangnhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        dangnhap = findViewById(R.id.btnDangnhap);
        ThuThuDao thuThuDao = new ThuThuDao(this);
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                if (thuThuDao.checkDangNhap(user, pass)){
                    startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(DangNhapActivity.this, "Tài khoản và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}