package cursofirebase.com;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.ViewHolder> {

    List<Notes> notes;
    Context context;

    public NotasAdapter(Context context, List<Notes> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notas,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.count.setText(String.valueOf(notes.get(i).count));
        viewHolder.name.setText(String.valueOf(notes.get(i).name));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView name,count;
        ImageView trash;

        public ViewHolder(View view) {
            super(view);
             cardView = (CardView) view.findViewById(R.id.cardview);
             count = (TextView) view.findViewById(R.id.count);
             name = (TextView) view.findViewById(R.id.name);
             trash = (ImageView) view.findViewById(R.id.trash);

        }
    }
}
