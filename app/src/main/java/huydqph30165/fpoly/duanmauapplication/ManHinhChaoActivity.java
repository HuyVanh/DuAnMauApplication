package huydqph30165.fpoly.duanmauapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ManHinhChaoActivity extends AppCompatActivity {
    ImageView ivlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        ivlogo = findViewById(R.id.ivLogo);
        Glide.with(this).load(R.mipmap.book).into(ivlogo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ManHinhChaoActivity.this, DangNhapActivity.class));
            }
        },3000);
    }
}