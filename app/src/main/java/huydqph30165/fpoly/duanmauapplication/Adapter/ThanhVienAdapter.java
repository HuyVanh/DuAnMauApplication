package huydqph30165.fpoly.duanmauapplication.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huydqph30165.fpoly.duanmauapplication.R;
import huydqph30165.fpoly.duanmauapplication.dao.ThanhVienDao;
import huydqph30165.fpoly.duanmauapplication.model.ThanhVien;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ThanhVien>list;
    private ThanhVienDao thanhVienDao;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list, ThanhVienDao thanhVienDao) {
        this.context = context;
        this.list = list;
        this.thanhVienDao = thanhVienDao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_recycler_thanhvien, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtMa.setText("Mã TV: " + list.get(position).getMatv());
        holder.txtHoTen.setText("Họ Tên: " + list.get(position).getHoten());
        holder.txtNamSinh.setText("Năm Sinh: " + list.get(position).getNamsinh());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialgCapNhapTT(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = thanhVienDao.xoaThongTinTV(list.get(holder.getAdapterPosition()).getMatv());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành viên thành công", Toast.LENGTH_SHORT).show();
                        loadDaTa();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa thành viên thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Thành viên tồn tại phiếu mượn", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMa, txtHoTen, txtNamSinh;
        ImageView ivEdit, ivDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMa = itemView.findViewById(R.id.txtMaThanhVien);
            txtHoTen = itemView.findViewById(R.id.txtHoTen);
            txtNamSinh = itemView.findViewById(R.id.txtNamSinh);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDel = itemView.findViewById(R.id.ivDel);
        }
    }
    private void showdialgCapNhapTT(ThanhVien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_chinhsua_thanhvien, null);
        builder.setView(view);
        TextView txtMaTv = view.findViewById(R.id.txtMaTV);
        EditText edtHoTen = view.findViewById(R.id.edtHoTen);
        EditText edtNamSinh = view.findViewById(R.id.edtNamSinh);

        txtMaTv.setText("Mã TV: " + thanhVien.getMatv());
        edtHoTen.setText(thanhVien.getHoten());
        edtNamSinh.setText(thanhVien.getNamsinh());
        builder.setNegativeButton("Cập Nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten = edtHoTen.getText().toString();
                String namsinh = edtNamSinh.getText().toString();
                int id = thanhVien.getMatv();
                boolean check = thanhVienDao.capNhapThongTin(id, hoten, namsinh);
                if (check){
                    Toast.makeText(context, "Cập nhập thông tin thành công", Toast.LENGTH_SHORT).show();
                    loadDaTa();
                }else {
                    Toast.makeText(context, "Cập nhập thông tin không thành công", Toast.LENGTH_SHORT).show();
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
    private void loadDaTa(){
        list.clear();
        list = thanhVienDao.getDSThanhVien();
        notifyDataSetChanged();
    }

}
