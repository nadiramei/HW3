package com.mobcom.homework3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobcom.homework3.MessageActivity;
import com.mobcom.homework3.Model.Chat;
import com.mobcom.homework3.Model.User;
import com.mobcom.homework3.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    private Context mcontext;
    private List<Chat> mchat;

    private String imageurl;

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    FirebaseUser fuser;

    public MessageAdapter(Context mcontext, List<Chat> mchat, String imageurl){
        this.mchat = mchat;
        this.mcontext = mcontext;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == MSG_TYPE_RIGHT){
            View view = LayoutInflater.from(mcontext).inflate(R.layout.chat_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.chat_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }

        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {

        Chat chat = mchat.get(position);
        holder.show_message.setText(chat.getMessage());
        if (imageurl.equals("default")){
            holder.profileimg.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(mcontext).load(imageurl).into(holder.profileimg);
        }

        /*
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
         */
    }

    @Override
    public int getItemCount() {
        return mchat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
        public ImageView profileimg;

        public ViewHolder (View itemView){
            super (itemView);
            show_message = itemView.findViewById(R.id.show_message);
            profileimg = itemView.findViewById(R.id.profile_img);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (mchat.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;
        }
        else{
            return MSG_TYPE_LEFT;
        }
    }
}
