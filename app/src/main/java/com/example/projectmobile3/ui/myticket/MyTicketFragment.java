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
            if(!getAlreadyBuyStatus()){
                myTicketBox.setVisibility(View.GONE);
            }
            else{
                myTicketBox.setVisibility(View.VISIBLE);

                TextView tvDestination = view.findViewById(R.id.tvDestination);
                TextView tvNumOfPerson = view.findViewById(R.id.tvNumOfPerson);
                TextView tvDate = view.findViewById(R.id.tvDate);

                String destinationName = getDestinationName();
                String numOfPerson = getNumOfPerson();
                String date = getDate();

                tvDestination.setText(destinationName);
                tvNumOfPerson.setText(numOfPerson);
                tvDate.setText(date);

                myTicketBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showTicketDetailsPopUp();
                    }
                });
            }
        }
    }

    private void showTicketDetailsPopUp(){
        View popUpView = getLayoutInflater().inflate(R.layout.myticket_information, null);

        TextView tvDestination = popUpView.findViewById(R.id.tvDestination);
        TextView tvName = popUpView.findViewById(R.id.tvName);
        TextView tvNumOfPerson = popUpView.findViewById(R.id.tvNumOfPerson);
        TextView tvDate = popUpView.findViewById(R.id.tvDate);
        TextView tvTotalPrice = popUpView.findViewById(R.id.tvTotalPrice);

        String destinationName = getDestinationName();
        String name = getCustomerName();
        String numOfPerson = getNumOfPerson();
        String date = getDate();
        String totalPrice = getTotalPrice();

        tvDestination.setText(destinationName);
        tvName.setText(name);
        tvNumOfPerson.setText(numOfPerson);
        tvDate.setText(date);
        tvTotalPrice.setText(totalPrice);

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

    private boolean getLoginStatus() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("is_logged_in", false);
    }

    private String getCustomerName(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString("myticket_name", "");
    }

    private String getNumOfPerson(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString("myticket_numOfPerson", "");
    }

    private String getDate(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString("myticket_date", "");
    }

    private boolean getAlreadyBuyStatus() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("is_buyTicket", false);
    }

    private String getDestinationName(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString("destination_name", "");
    }

    private String getTotalPrice(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString("myticket_totalprice", "");
    }

}