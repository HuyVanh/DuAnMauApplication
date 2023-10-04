package huydqph30165.fpoly.duanmauapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import huydqph30165.fpoly.duanmauapplication.Adapter.Top10Adapter;
import huydqph30165.fpoly.duanmauapplication.R;
import huydqph30165.fpoly.duanmauapplication.dao.ThongKeDao;
import huydqph30165.fpoly.duanmauapplication.model.Sach;

public class ThongKeTop10Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongketop10, container,false);
        RecyclerView recyclerTop10 = view.findViewById(R.id.recyclerTop10);
        ThongKeDao thongKeDao = new ThongKeDao(getContext());
        ArrayList<Sach>list = thongKeDao.getTop10();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTop10.setLayoutManager(linearLayoutManager);
        Top10Adapter top10Adapter = new Top10Adapter(getContext(), list);
        recyclerTop10.setAdapter(top10Adapter);
        return view;
    }
}
