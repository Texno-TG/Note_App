package air.texnodev.noteapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import air.texnodev.noteapp.Models.Node;
import air.texnodev.noteapp.R;
import air.texnodev.noteapp.tools.TimeFormater;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {
    private LayoutInflater inflater;
    private List<Node> roots;
    OnItemsClick onItemsClick;

    public AdapterRecycler(Context context, List<Node> roots, OnItemsClick onItemsClick1) {
        this.inflater = LayoutInflater.from(context);
        this.roots = roots;
        this.onItemsClick = onItemsClick1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_notes, parent, false);
        return new ViewHolder(view, onItemsClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(roots.get(position).getName());
        holder.desc.setText(TimeFormater.formater(roots.get(position).getTime()));

//        Log.d("MYLOG", roots.get(position).getSave());
        holder.button.setOnClickListener(view -> onItemsClick.onClick(view, position));
    }

    @Override
    public int getItemCount() {
        return roots.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView title;
        AppCompatTextView desc;
        AppCompatImageButton button;


        public ViewHolder(@NonNull View itemView, OnItemsClick onItemsClick) {
            super(itemView);
            title = itemView.findViewById(R.id.title_template);
            desc = itemView.findViewById(R.id.time);
            button = itemView.findViewById(R.id.remove_item);

            itemView.setOnClickListener(view -> {
                if (onItemsClick != null) {

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        onItemsClick.onClickItem(view, pos);
                    }
                }
            });
        }
    }
}

