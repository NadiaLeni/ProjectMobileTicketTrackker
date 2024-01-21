package com.example.projectmobile3.ui.home;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectmobile3.R;
import com.example.projectmobile3.User;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookTicketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookTicketFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BookTicketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookTicketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookTicketFragment newInstance(String param1, String param2) {
        BookTicketFragment fragment = new BookTicketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_ticket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText etName = view.findViewById(R.id.etName);
        EditText etNumOfPerson = view.findViewById(R.id.etNumOfPerson);
        EditText etDate = view.findViewById(R.id.etDate);

        etNumOfPerson.setText("1");

        TextView tvTotalPrice = view.findViewById(R.id.tvTotalPrice);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BookTicketFragment.this.getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                etDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        Button btnBookNow = view.findViewById(R.id.btnBookNow);
        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String numOfPerson = etNumOfPerson.getText().toString();
                String date = etDate.getText().toString();

                //untuk total price blm

                if (isEditTextNotEmpty(etName) && isEditTextNotEmpty(etNumOfPerson) && isEditTextNotEmpty(etDate)){
                    saveMyTicketData(name, numOfPerson, date);

                    Toast.makeText(getContext(), "Booking berhasil", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_fragmentBookTicket_to_navigation_myticket);
                }
                else{
                    Toast.makeText(getContext(), "Harap isi semua field", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private boolean isEditTextNotEmpty(EditText editText) {
        return editText.getText().toString().trim().length() > 0;
    }

    private void saveMyTicketData(String name, String numOfPerson, String date){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("myticket_name", name);
        editor.putString("myticket_numOfPerson", numOfPerson);
        editor.putString("myticketuser_date", date);
        editor.putBoolean("is_buyTicket", true);
        editor.apply();
    }

}