package com.example.vkinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    private static final String USER_ID = "userId";
    private static final String USER_FIRST_NAME = "userFirstName";
    private static final String USER_LAST_NAME = "userLastName";
    private static final String USER_AVATAR = "userAvatar";

    TextView tvUserId;
    TextView tvUserFullName;
    ImageView ivUserAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUserId = findViewById(R.id.user_id);
        tvUserFullName = findViewById(R.id.user_fullname);
        ivUserAvatar = findViewById(R.id.user_avatar);

        String userId = (String) getIntent().getExtras().get(USER_ID);
        String userFirstName = (String) getIntent().getExtras().get(USER_FIRST_NAME);
        String userLastName = (String) getIntent().getExtras().get(USER_LAST_NAME);
        String userAvatar = (String) getIntent().getExtras().get(USER_AVATAR);

        tvUserFullName.setText(String.format("%s %s", userFirstName, userLastName));
        tvUserId.setText(String.format("ID: %s", userId));
        Picasso.get().load(userAvatar).into(ivUserAvatar);
    }
}