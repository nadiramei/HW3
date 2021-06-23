package com.mobcom.homework3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobcom.homework3.MessageActivity;
import com.mobcom.homework3.Model.User;
import com.mobcom.homework3.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mcontext;
    private List<User> musers;

    public UserAdapter(Context mcontext, List<User> musers){
        this.musers = musers;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.useritem, parent, false);
        return new UserAdapter.ViewHolder(view);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = musers.get(position);
        holder.username.setText(user.getUsername());

        if (user.getImageURL().equals("default")){
            holder.profileimg.setImageResource(R.mipmap.ic_launcher);
        }
        else {
            Glide.with(mcontext).load(user.getImageURL()).into(holder.profileimg);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Toast.makeText(mcontext, "User ID: " + user.getId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent (mcontext, MessageActivity.class);
                //Intent intent = getIntent(mcontext, MessageActivity.class);
                intent.putExtra("id", user.getId());
                //intent.getIntent("id", user.getId());
                mcontext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return musers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView profileimg;

        public ViewHolder (View itemView){
            super (itemView);
            username = itemView.findViewById(R.id.user_name);
            profileimg = itemView.findViewById(R.id.profile_image);
        }
    }

}
