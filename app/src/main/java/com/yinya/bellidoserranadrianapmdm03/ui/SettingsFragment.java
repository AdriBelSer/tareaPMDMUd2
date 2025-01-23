package com.yinya.bellidoserranadrianapmdm03.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.firebase.ui.auth.AuthUI;
import com.yinya.bellidoserranadrianapmdm03.MyApp;
import com.yinya.bellidoserranadrianapmdm03.R;
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
        startLanguageMenuListening();

        MyApp app = (MyApp) requireActivity().getApplication();
        return view;
    }

    public void startLogoutListening() {
        TextView logoutBtn = binding.tvLogoutBtn;
        logoutBtn.setOnClickListener(this::onLogoutClick);
    }

    public void startLanguageMenuListening() {
        TextView languageBtn = binding.tvLanguage;
        languageBtn.setOnClickListener(this::onLanguageClick);
    }

    private void onLanguageClick(View v) {
        PopupMenu popupMenu = inflateLanguageMenu(v);
        popupMenu.setOnMenuItemClickListener(menuItem -> onContextItemSelected(menuItem));
        popupMenu.show();
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

    public PopupMenu inflateLanguageMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.language_menu, popupMenu.getMenu());
        return popupMenu;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        MyApp app = (MyApp) requireActivity().getApplication();
        String selectedLanguage;

        if (item.getItemId() == R.id.menu_selectable_english) {
            selectedLanguage = "en";
        } else if (item.getItemId() == R.id.menu_selectable_spanish) {
            selectedLanguage = "es";
        } else {
            Log.e("Context menu", "Unknown item");
            return false;
        }

        app.saveLanguage(selectedLanguage);
        app.applyLanguage(selectedLanguage);
        Log.d("language menu", selectedLanguage);

        // TODO Restart activity

        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}