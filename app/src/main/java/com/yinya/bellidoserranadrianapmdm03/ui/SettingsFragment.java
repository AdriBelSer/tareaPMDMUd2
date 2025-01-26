package com.yinya.bellidoserranadrianapmdm03.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.materialswitch.MaterialSwitch;
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
        initSwitchStatus();
        startLogoutListening();
        startLanguageMenuListening();
        startAboutMeMenuListening();
        startDeletePokemonSwitchListening();

        MyApp app = (MyApp) requireActivity().getApplication();
        return view;
    }


    public void startLogoutListening() {
        TextView logoutBtn = binding.tvLogoutBtn;
        logoutBtn.setOnClickListener(this::onLogOutAlertDialog);
    }

    public void startLanguageMenuListening() {
        TextView languageBtn = binding.tvLanguage;
        languageBtn.setOnClickListener(this::onLanguageClick);
    }

    private void startAboutMeMenuListening() {
        TextView aboutMeBtn = binding.tvAbout;
        aboutMeBtn.setOnClickListener(this::onAboutMeClick);
    }

    private void initSwitchStatus() {
        MaterialSwitch switchDeletePokemon = binding.switchDeletePokemon;
        MyApp app = (MyApp) requireActivity().getApplication();
        switchDeletePokemon.setChecked(app.getDeletePokemonOption().equals("true"));
    }

    private void startDeletePokemonSwitchListening() {
        MaterialSwitch switchDeletePokemon = binding.switchDeletePokemon;
        switchDeletePokemon.setOnCheckedChangeListener(this::onCheckedSwitchDeletePokemon);

    }

    private void onCheckedSwitchDeletePokemon(CompoundButton switchView, boolean isChecked) {
        String selectedDeleteOption = isChecked ? "true" : "false";
        MyApp app = (MyApp) requireActivity().getApplication();
        app.saveDeletePokemonOption(selectedDeleteOption);
    }

    private void onLogoutClick(View v) {
        AuthUI.getInstance()
                .signOut(requireContext())
                .addOnCompleteListener(task -> {
                    Log.d("Logout", "Logout successful");
                    ((MainActivity) requireActivity()).networkRepository.resetRepository();
                    goToLogin();
                });
    }

    private void goToLogin() {
        Intent i = new Intent(requireContext(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    private void onLanguageClick(View v) {
        PopupMenu popupMenu = inflateLanguageMenu(v);
        popupMenu.setOnMenuItemClickListener(menuItem -> onContextItemSelected(menuItem));
        popupMenu.show();
    }

    private void onAboutMeClick(View v) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle(getResources().getString(R.string.context_menu_about_me))
                .setMessage(getResources().getString(R.string.context_menu_alert_dialog_text))
                .setNeutralButton(R.string.context_menu_neutral_button, (dialog, which) -> dialog.dismiss())
                .show();
    }

    public PopupMenu inflateLanguageMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.language_menu, popupMenu.getMenu());
        return popupMenu;
    }

    private void onLogOutAlertDialog(View v) {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle(getResources().getString(R.string.settings_logout))
                .setMessage(getResources().getString(R.string.context_menu_log_out_text))
                .setNegativeButton(getResources().getString(R.string.context_menu_log_out_negative_button), (dialog, which) -> dialog.dismiss())
                .setPositiveButton(getResources().getString(R.string.context_menu_log_out_positive_button), (dialog, which) -> onLogoutClick(v))
                .show();
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
        app.applySavedLanguage(requireActivity());
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}