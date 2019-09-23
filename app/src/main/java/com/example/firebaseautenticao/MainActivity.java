package com.example.firebaseautenticao;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences; // Responsavel por guardar informações
    // neste caso irá gravar o login do app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String resultado = sharedPreferences.getString("LOGIN", "");

        if(!Boolean.parseBoolean(resultado)) {
            criarLogin();
        }
        //mAuth = FirebaseAuth.getInstance();
    }

    private void criarLogin()
    {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.ic_vip)
                        .setTheme(R.style.loginTheme)
                        .build(),
                123
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 123 && resultCode == RESULT_OK){
            sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("LOGIN", "true");
            editor.apply();
        }
    }




}
