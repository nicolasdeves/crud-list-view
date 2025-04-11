package com.nicolas.crud_list_view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;

    public ContactAdapter(List<Contact> contatos) {
        this.contacts = contatos;

    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact, parent, false);
        return new ContactViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.nameText.setText(contact.getName());
        holder.phoneText.setText(contact.getPhone());

        holder.deleteButton.setOnClickListener(v -> {
            contacts.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, contacts.size());
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView phoneText;
        Button deleteButton;
        DatabaseManager databaseManager;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textView);
            phoneText = itemView.findViewById(R.id.editTextPhone);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}

