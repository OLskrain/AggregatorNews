package com.olskrain.aggregatornews.presentationn.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olskrain.aggregatornews.R;
import com.olskrain.aggregatornews.presentationn.presenter.list.INewsListPresenter;
import com.olskrain.aggregatornews.presentationn.ui.activity.NewDetailActivity;
import com.olskrain.aggregatornews.presentationn.ui.view.item.INewsListItemView;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public class NewsListRVAdapter extends RecyclerView.Adapter<NewsListRVAdapter.NewListViewHolder> {

    private INewsListPresenter presenter;

    public NewsListRVAdapter(INewsListPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public NewListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new NewListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_new, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewListViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        viewHolder.position = position;
        presenter.bindView(viewHolder);

        //TODO: доделать пассивную реализацию.
        //данная реализация временная
        viewHolder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), NewDetailActivity.class);
            String s = "https://news.yandex.ru/";
            intent.putExtra("url new", s);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getNewCount();
    }

    public class NewListViewHolder extends RecyclerView.ViewHolder implements INewsListItemView {

        private int position = 0;
        private final TextView newTitle;
        private final TextView pubDate;
        private final TextView description;



        NewListViewHolder(View itemView) {
            super(itemView);

            newTitle = itemView.findViewById(R.id.new_title);
            pubDate = itemView.findViewById(R.id.new_pubDate);
            description = itemView.findViewById(R.id.new_description);
        }


        @Override
        public int getPos() {
            return position;
        }

        @Override
        public void setTitle(String title) {
            newTitle.setText(title);
        }

        @Override
        public void setLastBuildDate(String lastBuildDate) {
            pubDate.setText(lastBuildDate);
        }

        @Override
        public void setDescription(String description) {
            this.description.setText(description);
        }
    }
}
