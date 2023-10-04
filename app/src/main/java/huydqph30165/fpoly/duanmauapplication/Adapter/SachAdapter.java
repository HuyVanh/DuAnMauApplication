package huydqph30165.fpoly.duanmauapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huydqph30165.fpoly.duanmauapplication.R;
import huydqph30165.fpoly.duanmauapplication.model.Sach;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Sach>list;

    public SachAdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_recycler_sach, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMasach.setText("Mã Sách: "+list.get(position).getMasach());
        holder.txtTenSach.setText("Tên Sách: "+list.get(position).getTensach());
        holder.txtGiaThue.setText("Giá thuê: "+list.get(position).getGiathue());
        holder.txtMaLoai.setText("Mã Loại: "+list.get(position).getMaloai());
        holder.txtTenLoai.setText("Tên Loại: "+list.get(position).getTenloai());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMasach, txtTenSach, txtGiaThue, txtMaLoai, txtTenLoai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMasach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtGiaThue = itemView.findViewById(R.id.txtGiaThue);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);

        }
    }
}
