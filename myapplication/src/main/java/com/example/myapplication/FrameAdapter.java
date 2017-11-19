package com.example.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FrameAdapter extends RecyclerView.Adapter<FrameAdapter.NoteViewHolder> {

    private ItemClickListener clickListener;
    private List<ActivityBean> dataset;

    public interface ItemClickListener {
        void onItemClick(int position );
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public TextView comment;

        public NoteViewHolder(View itemView, final ItemClickListener clickListener) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textViewNoteText);
            comment = (TextView) itemView.findViewById(R.id.textViewNoteComment);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public FrameAdapter(ItemClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataset = new ArrayList<ActivityBean>();
    }

    public void setNotes(@NonNull List<ActivityBean> notes) {
        dataset = notes;
        notifyDataSetChanged();
    }

    public ActivityBean getNote(int position) {
        return dataset.get(position);
    }

    @Override
    public FrameAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(FrameAdapter.NoteViewHolder holder, int position) {
        ActivityBean note = dataset.get(position);
        holder.text.setText(note.name );

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
