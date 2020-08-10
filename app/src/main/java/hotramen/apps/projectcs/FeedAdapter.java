package hotramen.apps.projectcs;

import android.view.View;
import android.widget.ImageButton;
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

        TextView uuid;
        TextView name;
        ImageButton delete;
        ImageButton edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            uuid = itemView.findViewById(R.id.tvUuid);
            name = itemView.findViewById(R.id.tvName);

            delete = itemView.findViewById(R.id.ibtnDelete);
            edit =  itemView.findViewById(R.id.ibtnEdit);
        }
    }
}
