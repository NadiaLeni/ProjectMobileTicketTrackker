package com.example.projectmobile3.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.projectmobile3.ListAdapter;
import com.example.projectmobile3.ListDestination;
import com.example.projectmobile3.MainActivity;
import com.example.projectmobile3.R;
import com.example.projectmobile3.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    ListAdapter listAdapter;
    ArrayList<ListDestination> dataArrayList = new ArrayList<>();
    ListDestination listDestination;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
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
        TextView tvUserName = view.findViewById(R.id.tvUserName);
        if(!getLoginStatus()){
            tvUserName.setText("User");
        }
        else{
            String userName = getUserName();
            Log.d("UserDebug", "User: " + userName);
            tvUserName.setText(userName);
        }

        ListView listView = view.findViewById(R.id.listDestination);

        int[] imageList = {R.drawable.orchid_forest_picture, R.drawable.bali_zoo_picture, R.drawable.candi_prambanan_picture, R.drawable.jakarta_bird_land, R.drawable.little_venice_picture, R.drawable.labuan_bajo_picture};
        int[] priceList = {50000, 80000, 50000, 51600, 25000, 500000};
        String[] nameList = {"Orchid Forest Cikole", "Bali Zoo", "Candi Prambanan", "Jakarta Bird Land", "Little Venice", "Labuan Bajo"};

        for(int i=0; i<imageList.length; i++){
            listDestination = new ListDestination(nameList[i], priceList[i], imageList[i]);
            dataArrayList.add(listDestination);
        }
        listAdapter = new ListAdapter(HomeFragment.this.getActivity(), dataArrayList);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!getLoginStatus()){
                    Log.d("ToastDebug", "User not logged in");
                    Toast.makeText(getContext(), "Mohon login terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else{
                    bookTicket(position);
                    Navigation.findNavController(view).navigate(R.id.action_fragmentHome_to_fragmentBookTicket);
                }
            }
        });


    }

    private String getUserName(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getString("user_name", "");
    }

    private boolean getLoginStatus() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("is_logged_in", false);
    }

    private void bookTicket(int position){
        ListDestination selectedDestination = dataArrayList.get(position);
        String name = selectedDestination.getName();
        int price = selectedDestination.getPrice();
        String priceToString = String.valueOf(price);

        HomeFragment.this.saveDestinationData(name, priceToString);

    }

    private void saveDestinationData(String name, String price){
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("destination_name", name);
        editor.putString("destination_price", price);
        editor.apply();
    }


}