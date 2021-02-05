package com.example.vkinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.vkinfo.utils.NetworkUtils.generateURL;
import static com.example.vkinfo.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {

    private EditText searchField;
    private Button createButton;
    private TextView errorMessage;
    private ProgressBar loadingIndicator;
    private JSONArray vkUsersArray;
    ArrayList<VKUser> contacts;
    private RecyclerView recyclerView;

    private void showResultTextView() {
        errorMessage.setVisibility(View.INVISIBLE);
        loadingIndicator.setVisibility(View.INVISIBLE);
    };

    private void showErrorTextView() {
        errorMessage.setVisibility(View.VISIBLE);
        loadingIndicator.setVisibility(View.INVISIBLE);
    };

    class VKQueryTask extends AsyncTask<URL, Void, ArrayList<VKUser>> {

        @Override
        protected void onPreExecute(){
            loadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<VKUser> doInBackground(URL... urls) {
            String id = null;
            String firstName = null;
            String lastName = null;
            String avatar = null;

            String response = null;
            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e){
                e.printStackTrace();
            }

            if (response != null && !response.isEmpty()) {
                try {
                    contacts.clear();
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("response");

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject userInfo = jsonArray.getJSONObject(i);

                        id = userInfo.getString("id");
                        avatar = userInfo.getString("photo_50");
                        firstName = userInfo.getString("first_name");
                        lastName = userInfo.getString("last_name");

                        contacts.add(new VKUser(id,firstName,lastName,avatar));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                showResultTextView();
//                return contacts;
            } else {
                showErrorTextView();
            }
            return contacts;
        }

        protected void onPostExecute() {
            loadingIndicator.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_results);
        searchField = findViewById(R.id.et_search_field);
        createButton = findViewById(R.id.b_create);

        errorMessage = findViewById(R.id.tv_error_message);
        loadingIndicator = findViewById(R.id.pd_loading_indicator);

        contacts = new ArrayList<VKUser>();

        View.OnClickListener clickCreateButton = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                URL generatedURL = generateURL(searchField.getText().toString());
                new VKQueryTask().execute(generatedURL);

                VkUsersAdapter vkUsersAdapter = new VkUsersAdapter(contacts);
                recyclerView.setAdapter(vkUsersAdapter);
//                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        };
        createButton.setOnClickListener(clickCreateButton);
    }
}