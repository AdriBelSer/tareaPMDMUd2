package com.yinya.bellidoserranadrianapmdm03.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.yinya.bellidoserranadrianapmdm03.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        startLogoutListening();
        return view;
    }

    public void startLogoutListening() {
        TextView logoutBtn = binding.tvLogoutBtn;
        logoutBtn.setOnClickListener(this::onLogoutClick);
    }

    private void onLogoutClick(View v) {
        AuthUI.getInstance()
                .signOut(requireContext())
                .addOnCompleteListener(task -> {
                    Log.d("Logout", "Logout successful");
                    goToLogin();
                });
    }

    private void goToLogin() {
        Intent i = new Intent(requireContext(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}