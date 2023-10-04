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

import huydqph30165.fpoly.duanmauapplication.Adapter.SachAdapter;
import huydqph30165.fpoly.duanmauapplication.R;
import huydqph30165.fpoly.duanmauapplication.dao.SachDao;
import huydqph30165.fpoly.duanmauapplication.model.Sach;

public class QlSachFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlsach,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycleSach);
        SachDao sachDao = new SachDao(getContext());

        ArrayList<Sach> list = sachDao.getDSDauSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        SachAdapter sachAdapter = new SachAdapter(getContext(), list);
        recyclerView.setAdapter(sachAdapter);
        return view;
    }
}
