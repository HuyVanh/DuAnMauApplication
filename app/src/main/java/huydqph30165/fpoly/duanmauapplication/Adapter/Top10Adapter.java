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

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHoleder> {
    private Context context;
    private ArrayList<Sach>list;

    public Top10Adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycle_top10, parent, false);
        return new ViewHoleder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoleder holder, int position) {
        holder.txtMasach.setText(String.valueOf(list.get(position).getMasach()));
        holder.txtTenSach.setText(list.get(position).getTensach());
        holder.txtSoLuongMuon.setText(String.valueOf(list.get(position).getSoluongdamuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoleder extends RecyclerView.ViewHolder{
        TextView txtMasach, txtTenSach, txtSoLuongMuon;

        public ViewHoleder(@NonNull View itemView) {
            super(itemView);
            txtMasach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtSoLuongMuon = itemView.findViewById(R.id.txtSoLuongMuon);
        }
    }
}
