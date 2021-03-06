package com.frame.greendao;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private NoteClickListener clickListener;
    private List<Note> dataset;

    public interface NoteClickListener {
        void onNoteClick(int position);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public TextView comment;

        public NoteViewHolder(final View itemView, final NoteClickListener clickListener) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.textViewNoteText);
            comment = (TextView) itemView.findViewById(R.id.textViewNoteComment);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public NotesAdapter(NoteClickListener clickListener) {
        this.clickListener = clickListener;
        this.dataset = new ArrayList<>();
    }

    public void setNotes(@NonNull List<Note> notes) {
        dataset = notes;
        notifyDataSetChanged();
    }

    public Note getNote(int position) {
        return dataset.get(position);
    }

    @Override
    public NotesAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.greendao_item_note, parent, false);
        return new NoteViewHolder(view, clickListener);
    }
    private int selectedPosition = -1; //默认一个参数
    @Override
    public void onBindViewHolder(final NoteViewHolder holder,final int position) {
        holder.itemView.setSelected(selectedPosition == position);
        if (selectedPosition == position) {
            holder.text.setTextColor(Color.parseColor("#ff0000"));
            holder.comment.setTextColor(Color.parseColor("#ff0000"));
        } else {
            holder.text.setTextColor(Color.parseColor("#004081"));
            holder.comment.setTextColor(Color.parseColor("#004081"));
        }
        Note note = dataset.get(position);
        holder.text.setText(note.getText());
        holder.comment.setText(note.getComment());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onNoteClick(  holder.getAdapterPosition());
                selectedPosition = position; //选择的position赋值给参数，
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
