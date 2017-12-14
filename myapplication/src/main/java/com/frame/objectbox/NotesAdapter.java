package com.frame.objectbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends BaseAdapter {

    private List<Note> dataset;

    private static class NoteViewHolder {

        public TextView text;
        public TextView comment;

        public NoteViewHolder(View itemView) {
            text = (TextView) itemView.findViewById(R.id.textViewNoteText);
            comment = (TextView) itemView.findViewById(R.id.textViewNoteComment);
        }
    }

    public NotesAdapter() {
        this.dataset = new ArrayList<>();
    }

    public void setNotes(List<Note> notes) {
        dataset = notes;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NoteViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.objectbox_item_note , parent, false);
            holder = new NoteViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (NoteViewHolder) convertView.getTag();
        }

        Note note = getItem(position);
        holder.text.setText(note.getText());
        holder.comment.setText(note.getComment());

        return convertView;
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public Note getItem(int position) {
        return dataset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
