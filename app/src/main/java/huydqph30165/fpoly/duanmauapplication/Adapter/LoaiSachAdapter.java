package huydqph30165.fpoly.duanmauapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huydqph30165.fpoly.duanmauapplication.R;
import huydqph30165.fpoly.duanmauapplication.dao.LoaiSachDao;
import huydqph30165.fpoly.duanmauapplication.model.ItemClick;
import huydqph30165.fpoly.duanmauapplication.model.Loaisach;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Loaisach>list;
    private ItemClick itemClick;
    public LoaiSachAdapter(Context context, ArrayList<Loaisach> list, ItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_loaisach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaLoai.setText("Mã Loai: " +String.valueOf(list.get(position).getId()));
        holder.txtTenLoai.setText("Tên Loại: " +list.get(position).getTenloai());
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSachDao loaiSachDao = new LoaiSachDao(context);
                int check = loaiSachDao.xoaLoaiSach(list.get(holder.getAdapterPosition()).getId());
                switch (check){
                    case 1:
                        list.clear();
                        list = loaiSachDao.getDanhSachLoaiSach();
                        notifyDataSetChanged();
                        break;
                    case -1:
                        Toast.makeText(context, "Không Thể Xóa Loại Sách Vì Có Sách Thuộc Thể Loại Này", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa Loại Sách Không Thành Công", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loaisach loaisach = list.get(holder.getAdapterPosition());
                itemClick.onClickLoaiSach(loaisach);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaLoai, txtTenLoai;
        ImageView ivDel, ivEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivDel = itemView.findViewById(R.id.ivDel);
            ivEdit = itemView.findViewById(R.id.ivEdit);
        }
    }


}
