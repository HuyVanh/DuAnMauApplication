package huydqph30165.fpoly.duanmauapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huydqph30165.fpoly.duanmauapplication.R;
import huydqph30165.fpoly.duanmauapplication.dao.PhieuMuonDao;
import huydqph30165.fpoly.duanmauapplication.model.PhieuMuon;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {
    private ArrayList<PhieuMuon> list;
    private Context context;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_phieumuon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaPM.setText("Mã PM: " + list.get(position).getMapm());
        holder.txtMaTV.setText("Mã TV: " + list.get(position).getMatv());
        holder.txtTenTV.setText("Tên TV: " + list.get(position).getTentv());
        holder.txtMaTT.setText("Mã TT: " + list.get(position).getMatt());
        holder.txtTenTT.setText("Tên TT: " + list.get(position).getTentt());
        holder.txtMaSach.setText("Mã Sách: " + list.get(position).getMasach());
        holder.txtTenSach.setText("Tên Sách: " + list.get(position).getTensach());
        holder.txtNgay.setText("Ngày: " + list.get(position).getNgay());
        String trangthai;
        if (list.get(position).getTrasach() == 1){
            trangthai = "Đã trả sách";
            holder.btnTrasach.setVisibility(View.GONE);
        }else {
            trangthai = "Chưa trả sách";
            holder.btnTrasach.setVisibility(View.VISIBLE);
        }
        holder.txtTrangThai.setText("Trạng Thái: " + trangthai);
        holder.txtTien.setText("Mã PM: " + list.get(position).getTienthue());

        holder.btnTrasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuonDao phieuMuonDao = new PhieuMuonDao(context);
                boolean kiemtra = phieuMuonDao.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMapm());
                if (kiemtra == true){
                    list.clear();
                    list = phieuMuonDao.getDSPhieuMuon();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "Thay đổi trạng thái không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaPM, txtMaTV, txtTenTV, txtMaTT, txtTenTT, txtMaSach, txtTenSach, txtNgay, txtTrangThai, txtTien;
        Button btnTrasach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaPM = itemView.findViewById(R.id.txtMaPM);
            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtTenTV = itemView.findViewById(R.id.txtTenTV);
            txtMaTT = itemView.findViewById(R.id.txtMaTT);
            txtTenTT = itemView.findViewById(R.id.txtTenTT);
            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach= itemView.findViewById(R.id.txtTenSach);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            txtTien = itemView.findViewById(R.id.txtTienThue);
            btnTrasach = itemView.findViewById(R.id.btnTraSach);

        }
    }
}
