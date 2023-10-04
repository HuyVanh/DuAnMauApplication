package huydqph30165.fpoly.duanmauapplication.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import huydqph30165.fpoly.duanmauapplication.Adapter.ThanhVienAdapter;
import huydqph30165.fpoly.duanmauapplication.R;
import huydqph30165.fpoly.duanmauapplication.dao.ThanhVienDao;
import huydqph30165.fpoly.duanmauapplication.model.ThanhVien;

public class QLThanhVienFragment extends Fragment {
    ThanhVienDao thanhVienDao;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanlythanhvien, container, false);
        recyclerView = view.findViewById(R.id.recycleThanhVien);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);
        thanhVienDao = new ThanhVienDao(getContext());
        loadData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }
    public void loadData(){
        ArrayList<ThanhVien>list = thanhVienDao.getDSThanhVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ThanhVienAdapter adapter = new ThanhVienAdapter(getContext(), list, thanhVienDao);
        recyclerView.setAdapter(adapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_thanhvien, null);
        builder.setView(view);
        EditText edthoten = view.findViewById(R.id.edtHoTen);
        EditText edtnamsinh = view.findViewById(R.id.edtNamSinh);
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten = edthoten.getText().toString();
                String namsinh = edtnamsinh.getText().toString();
                boolean check  = thanhVienDao.themThanhVien(hoten, namsinh);
                if (check == true){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
