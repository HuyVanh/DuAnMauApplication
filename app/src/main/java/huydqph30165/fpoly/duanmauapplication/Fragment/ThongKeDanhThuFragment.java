package huydqph30165.fpoly.duanmauapplication.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import huydqph30165.fpoly.duanmauapplication.R;
import huydqph30165.fpoly.duanmauapplication.dao.ThongKeDao;

public class ThongKeDanhThuFragment extends Fragment {
    EditText edtStart, edtEnd;
    Button btnThongKe;
    TextView txtKetQua;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongkedoanhthu, container, false);
        edtStart = view.findViewById(R.id.edtStart);
        edtEnd = view.findViewById(R.id.edtEnd);
        btnThongKe = view.findViewById(R.id.btnThongKe);
        txtKetQua = view.findViewById(R.id.txtKetQua);
        Calendar calendar = Calendar.getInstance();

        edtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String ngay = "";
                        String thang = "";
                        if (dayOfMonth < 10){
                                ngay = "0" + dayOfMonth;
                        }else {
                            ngay = String.valueOf(dayOfMonth);
                        }
                        if (month + 1 < 10){
                            thang = "0" + (month + 1);
                        }else {
                            thang = String.valueOf((month + 1));
                        }
                            edtStart.setText(year + "/" + thang + "/" + ngay);
                        }

                },calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();

            }
        });
        edtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";
                                if (dayOfMonth < 10){
                                    ngay = "0" + dayOfMonth;
                                }else {
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if (month + 1 < 10){
                                    thang = "0" + (month + 1);
                                }else {
                                    thang = String.valueOf((month + 1));
                                }
                                edtEnd.setText(year + "/" + thang + "/" + ngay);
                            }

                        },calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongKeDao thongKeDao = new ThongKeDao(getContext());
                String ngaybatdau = edtStart.getText().toString();
                String ngayketthuc = edtEnd.getText().toString();
                int doanhthu = thongKeDao.getDoanhThu(ngaybatdau, ngayketthuc);
                txtKetQua.setText(doanhthu + "VND");
            }
        });
        return view;
    }
}
