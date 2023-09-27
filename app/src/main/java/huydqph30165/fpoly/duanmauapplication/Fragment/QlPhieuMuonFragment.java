package huydqph30165.fpoly.duanmauapplication.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

import huydqph30165.fpoly.duanmauapplication.Adapter.PhieuMuonAdapter;
import huydqph30165.fpoly.duanmauapplication.R;
import huydqph30165.fpoly.duanmauapplication.dao.PhieuMuonDao;
import huydqph30165.fpoly.duanmauapplication.dao.SachDao;
import huydqph30165.fpoly.duanmauapplication.dao.ThanhVienDao;
import huydqph30165.fpoly.duanmauapplication.model.PhieuMuon;
import huydqph30165.fpoly.duanmauapplication.model.Sach;
import huydqph30165.fpoly.duanmauapplication.model.ThanhVien;

public class QlPhieuMuonFragment extends Fragment {
    PhieuMuonDao phieuMuonDao;
     RecyclerView recyclerView;
     FloatingActionButton floatingActionButton;
    ArrayList<PhieuMuon> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlphieumuon, container, false);
        recyclerView = view.findViewById(R.id.recyclerQlPhieumuon);
        floatingActionButton = view.findViewById(R.id.floatAdd);




        //adapter
       loadData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }
    private void loadData(){
        phieuMuonDao = new PhieuMuonDao(getContext());
        list = phieuMuonDao.getDSPhieuMuon();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        PhieuMuonAdapter phieuMuonAdapter = new PhieuMuonAdapter(list, getContext());
        recyclerView.setAdapter(phieuMuonAdapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themphieumuon, null);
        Spinner spinnerThanhVien = view.findViewById(R.id.spnThanhVien);
        Spinner spinnerSach =  view.findViewById(R.id.spnSach);

        getDataThanhVien(spinnerThanhVien);
        getDataSach(spinnerSach);
        builder.setView(view);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //lấy mã thành viên
                HashMap<String , Object>hsTV = (HashMap<String, Object>) spinnerThanhVien.getSelectedItem();
                int matv = (int) hsTV.get("matv");
                //lấy mã sách
                HashMap<String, Object>hsSach = (HashMap<String, Object>) spinnerSach.getSelectedItem();
                int masach = (int) hsSach.get("masach");
                //lấy tiền
                int tien = (int) hsSach.get("giathue");
                themPhieuMuon(matv, masach, tien);
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void getDataThanhVien(Spinner spinner){
        ThanhVienDao thanhVienDao = new ThanhVienDao(getContext());
        ArrayList<ThanhVien> list = thanhVienDao.getDSThanhVien();
        ArrayList<HashMap<String, Object>>listHM = new ArrayList<>();
        for (ThanhVien tv: list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("matv", tv.getMatv());
            hs.put("hoten", tv.getHoten());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listHM, android.R.layout.simple_list_item_1, new String[]{"hoten"}, new int[]{android.R.id.text1});
        spinner.setAdapter(simpleAdapter);
    }
    public void getDataSach(Spinner spinnerSach){
        SachDao sachDao = new SachDao(getContext());
        ArrayList<Sach> list = sachDao.getDSDauSach();
        ArrayList<HashMap<String, Object>>listHM = new ArrayList<>();
        for (Sach sach: list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("masach", sach.getMasach());
            hs.put("tensach", sach.getTensach());
            hs.put("giathue", sach.getGiathue());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listHM, android.R.layout.simple_list_item_1, new String[]{"tensach"}, new int[]{android.R.id.text1});
        spinnerSach.setAdapter(simpleAdapter);
    }
    private void themPhieuMuon(int matv, int masach, int tien){
        //lấy mã thủ thư
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt", "");
        //lấy ngày hiện tại
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);

        PhieuMuon phieuMuon = new PhieuMuon(matv, matt, masach, ngay, 0, tien);
        boolean kiemtra = phieuMuonDao.themPhieuMuon(phieuMuon);
        if (kiemtra == true){
            Toast.makeText(getContext(), "Thêm phiếu mượn thành công", Toast.LENGTH_SHORT).show();
            loadData();
        }else {
            Toast.makeText(getContext(), "Thêm phiếu mượn thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}
