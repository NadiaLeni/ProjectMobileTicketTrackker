package com.example.projectmobile3.ui.myticket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectmobile3.databinding.FragmentMyticketBinding;

public class MyTicketFragment extends Fragment {

    private FragmentMyticketBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyTicketViewModel myTicketViewModel =
                new ViewModelProvider(this).get(MyTicketViewModel.class);

        binding = FragmentMyticketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMyticket;
        myTicketViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}