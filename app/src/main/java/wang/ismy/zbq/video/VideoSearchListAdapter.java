package wang.ismy.zbq.video;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import wang.ismy.zbq.view.VideoItemView;

public class VideoSearchListAdapter extends RecyclerView.Adapter<VideoItemHolder> {
    @NonNull
    @Override
    public VideoItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class VideoItemHolder extends RecyclerView.ViewHolder{

    private VideoItemView videoItemView;

    public VideoItemHolder(@NonNull View itemView) {
        super(itemView);
    }


}
