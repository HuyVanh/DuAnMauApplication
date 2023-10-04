package huydqph30165.fpoly.duanmauapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huydqph30165.fpoly.duanmauapplication.Adapter.LoaiSachAdapter;
import huydqph30165.fpoly.duanmauapplication.R;
import huydqph30165.fpoly.duanmauapplication.dao.LoaiSachDao;
import huydqph30165.fpoly.duanmauapplication.model.ItemClick;
import huydqph30165.fpoly.duanmauapplication.model.Loaisach;

public class QlLoaiSachFragment extends Fragment {
    RecyclerView recyclerView;
    LoaiSachDao loaiSachDao;
    EditText edtLoaiSach;
    int maloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlloaisach, container, false);recyclerView = view.findViewById(R.id.recycleLoaiSach);
         edtLoaiSach = view.findViewById(R.id.edtLoaiSach);
        Button btnThem = view.findViewById(R.id.btnThem);
        Button btnSua = view.findViewById(R.id.btnSua);

        loaiSachDao = new LoaiSachDao(getContext());
        loadDaTa();
        btnThem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String tenloai = edtLoaiSach.getText().toString();
                if (loaiSachDao.themLoaiSach(tenloai)){
                    loadDaTa();
                    edtLoaiSach.setText("");
                }else {
                    Toast.makeText(getContext(), "Thêm Loại Sách Không Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoai = edtLoaiSach.getText().toString();
                Loaisach loaisach = new Loaisach(maloai, tenLoai);
                if (loaiSachDao.thayDoiLoaiSach(loaisach)){
                    loadDaTa();
                    edtLoaiSach.setText("");
                }else {
                    Toast.makeText(getContext(), "Thay Đổi Thông Tin Không Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private void loadDaTa(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        loaiSachDao = new LoaiSachDao(getContext());
        ArrayList<Loaisach>list = loaiSachDao.getDanhSachLoaiSach();
        LoaiSachAdapter loaiSachAdapter = new LoaiSachAdapter(getContext(), list, new ItemClick() {
            @Override
            public void onClickLoaiSach(Loaisach loaisach) {
                edtLoaiSach.setText(loaisach.getTenloai());
                maloai = loaisach.getId();
            }
        });
        recyclerView.setAdapter(loaiSachAdapter);
    }
}
