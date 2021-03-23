package com.example.projet_android_equipe7.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.RequestQueue;
import com.example.projet_android_equipe7.MainActivity;
import com.example.projet_android_equipe7.R;
import com.example.projet_android_equipe7.VolleySingleton;
import com.example.projet_android_equipe7.modele.dao.MaRequest;
import com.example.projet_android_equipe7.modele.metier.Eleve;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private RequestQueue queue;
    private  MaRequest request;
    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        loadingProgressBar.setVisibility(View.INVISIBLE);


        //gere le singleton
        /*
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        request = new MaRequest(this,queue);
        handler = new Handler();

        request.getEleve("3",new MaRequest.getEleveCallBack() {
            @Override
            public void onSuccess(Eleve nouvelEleve) {
                Toast.makeText(getBaseContext(),nouvelEleve.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getBaseContext(),message,Toast.LENGTH_LONG).show();
            }
        });
         */

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
                    @Override
                    public void onChanged(@Nullable LoginResult loginResult) {
                        if (loginResult == null) {
                            return;
                        }
                        loadingProgressBar.setVisibility(View.GONE);
                        if (loginResult.getError() != null) {
                            showLoginFailed(loginResult.getError());
                        }
                        if (loginResult.getSuccess() != null) {
                            updateUiWithUser(loginResult.getSuccess());
                        }
                        setResult(Activity.RESULT_OK);


                        //Complete and destroy login activity once successful

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                request.connexion(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new MaRequest.LoginCallBack() {

                                    @Override
                                    public void onSuccess(String id, String nom) {
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onError(String message) {
                                        Toast.makeText(getBaseContext(),message,Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }, 1000);
                    }
                });




        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };

        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        //String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        //Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        //Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}