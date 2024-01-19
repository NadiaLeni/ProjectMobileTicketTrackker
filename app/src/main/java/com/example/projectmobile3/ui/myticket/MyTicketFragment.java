package com.example.projectmobile3.ui.myticket;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectmobile3.R;
import com.example.projectmobile3.databinding.FragmentMyticketBinding;

public class MyTicketFragment extends Fragment {

    private FragmentMyticketBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyTicketViewModel myTicketViewModel =
                new ViewModelProvider(this).get(MyTicketViewModel.class);

        binding = FragmentMyticketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LinearLayout myTicketBox = view.findViewById(R.id.ticketbox);
        if(!getLoginStatus()){
            myTicketBox.setVisibility(View.GONE);
        }
        else{
            myTicketBox.setVisibility(View.VISIBLE);
            myTicketBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showTicketDetailsPopUp();
                }
            });
        }
    }

    private boolean getLoginStatus() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("is_logged_in", false);
    }

    private void showTicketDetailsPopUp(){
        View popUpView = getLayoutInflater().inflate(R.layout.myticket_information, null);

        Button btnOk = popUpView.findViewById(R.id.btnOK);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(popUpView);

        final AlertDialog alertDialog = builder.create();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }
}