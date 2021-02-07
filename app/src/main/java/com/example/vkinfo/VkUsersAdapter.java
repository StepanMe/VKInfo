package com.example.vkinfo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VkUsersAdapter extends RecyclerView.Adapter<VkUsersAdapter.ViewHolder> {

    private ArrayList<VKUser> vkUserList;

    public VkUsersAdapter (ArrayList<VKUser> users) {
        vkUserList = users;
    }

    @NonNull
    @Override
    public VkUsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View vkUserList = inflater.inflate(R.layout.user_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(vkUserList);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VKUser vkUser = vkUserList.get(position);

        TextView fn = holder._firstName;
        fn.setText(vkUser.getFirstName());

        TextView ln = holder._lastName;
        ln.setText(vkUser.getLastName());

        TextView id = holder._id;
        id.setText(vkUser.getId());

        ImageView ava = holder._avatar;
        Picasso.get().load(vkUser.getAvatarLink()).into(ava);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(),ProfileActivity.class);
            intent.putExtra("userId", vkUser.getId());
            intent.putExtra("userFirstName",vkUser.getFirstName());
            intent.putExtra("userLastName",vkUser.getLastName());
            intent.putExtra("userAvatar",vkUser.getAvatarLink());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return vkUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView _firstName;
        public TextView _lastName;
        public TextView _id;
        public ImageView _avatar;

        public ViewHolder(View itemView) {
            super(itemView);

            _firstName = itemView.findViewById(R.id.tv_first_name);
            _lastName = itemView.findViewById(R.id.tv_last_name);
            _id = itemView.findViewById(R.id.tv_vkid);
            _avatar = itemView.findViewById(R.id.iv_avatar);
        }
    }
}
