package huydqph30165.fpoly.duanmauapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import huydqph30165.fpoly.duanmauapplication.Fragment.QLThanhVienFragment;
import huydqph30165.fpoly.duanmauapplication.Fragment.QlLoaiSachFragment;
import huydqph30165.fpoly.duanmauapplication.Fragment.QlPhieuMuonFragment;
import huydqph30165.fpoly.duanmauapplication.Fragment.QlSachFragment;
import huydqph30165.fpoly.duanmauapplication.Fragment.ThongKeDanhThuFragment;
import huydqph30165.fpoly.duanmauapplication.Fragment.ThongKeTop10Fragment;
import huydqph30165.fpoly.duanmauapplication.dao.SachDao;
import huydqph30165.fpoly.duanmauapplication.dao.ThuThuDao;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
        FrameLayout frameLayout = findViewById(R.id.farmLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        View headerLayout = navigationView.getHeaderView(0);
        TextView txtTen = headerLayout.findViewById(R.id.txtTen);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               Fragment fragment = null;
               if (item.getItemId() == R.id.mQLPhieuMuon){
                fragment = new QlPhieuMuonFragment();
               } else if (item.getItemId() == R.id.mQLLoaiSach) {
                   fragment = new QlLoaiSachFragment();
               } else if (item.getItemId()== R.id.mDangXuat) {
                   Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                   startActivity(intent);
               } else if (item.getItemId()==R.id.mDoiMatKhau) {
                   showDialogDoiMatKhau();
               } else if (item.getItemId()==R.id.mTop10) {
                   fragment = new ThongKeTop10Fragment();
               } else if (item.getItemId()==R.id.mDoanhThu) {
                   fragment = new ThongKeDanhThuFragment();
               } else if (item.getItemId() == R.id.mQLThanhVien) {
                   fragment = new QLThanhVienFragment();
               } else if (item.getItemId()==R.id.mQLSach) {
                   fragment = new QlSachFragment();
               }
               if (fragment!= null){
                   FragmentManager fragmentManager = getSupportFragmentManager();
                   fragmentManager.beginTransaction().replace(R.id.farmLayout, fragment).commit();
                   toolbar.setTitle(item.getTitle());
               }



                drawerLayout.closeDrawer(GravityCompat.START);

               return false;
           }
       });
       SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
       String loaiTk = sharedPreferences.getString("loaitaikhoan", "");
       if (!loaiTk.equals("Admin")){
           Menu menu = navigationView.getMenu();
           menu.findItem(R.id.mDoanhThu).setVisible(false);
           menu.findItem(R.id.mTop10).setVisible(false);
       }
        String hoten = sharedPreferences.getString("hoten", "");
        txtTen.setText("Xin chào" + hoten);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }
    private void showDialogDoiMatKhau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setNegativeButton("Cập Nhập", null).setPositiveButton("Hủy",null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau, null);
        EditText edtOldPass = view.findViewById(R.id.edtMkCu);
        EditText edtNewPass = view.findViewById(R.id.edtMkMoi);
        EditText edtReNewpass = view.findViewById(R.id.edtMkNhapLai);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String reNewPass = edtReNewpass.getText().toString();
                if (oldPass.equals("")|| newPass.equals("")||reNewPass.equals("")){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
                }else {
                    if (newPass.equals(reNewPass)){
                        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                        String matt = sharedPreferences.getString("matt", "");
                        ThuThuDao thuThuDao = new ThuThuDao(MainActivity.this);
                        int check = thuThuDao.capnhapMatKhau(matt, oldPass, newPass);
                        if (check == 1){
                            Toast.makeText(MainActivity.this, "Cập nhập mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else if (check == 0){
                            Toast.makeText(MainActivity.this, "Mật Khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Cập nhập mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}