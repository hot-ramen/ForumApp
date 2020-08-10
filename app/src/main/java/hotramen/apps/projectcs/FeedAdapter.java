package hotramen.apps.projectcs;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import javax.annotation.Nullable;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class FeedAdapter extends RealmRecyclerViewAdapter<Thread, FeedAdapter.ViewHolder> {

    Home activity;

    public FeedAdapter(Home activity, @Nullable OrderedRealmCollection<Thread> data, boolean autoUpdate){

        super(data, autoUpdate);

        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView author;
        TextView title;
        TextView content;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            author = itemView.findViewById(R.id.tvAuthor);
            title = itemView.findViewById(R.id.tvTitle);
            content = itemView.findViewById(R.id.tvContent);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = activity.getLayoutInflater().inflate(R.layout.row_layout_home, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Thread t = getItem(position);

        User author = t.getAuthor();

        String poster = author.getName();


        // copy all the values needed to the appropriate views
        holder.author.setText(poster);
        holder.title.setText(t.getTitle());
        holder.content.setText(t.getContent());

        // NOTE: MUST BE A STRING String.valueOf() converts most types to a string


        // do any other initializations here as well,  e.g. Button listeners
//        holder.delete.setTag(t);
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                activity.delete(view);
//            }
//        });

//        holder.edit.setTag(u);
//        holder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                activity.edit(view);
//            }
//        });
    }


}
