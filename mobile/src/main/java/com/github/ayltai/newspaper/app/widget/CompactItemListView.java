package com.github.ayltai.newspaper.app.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.github.ayltai.newspaper.R;
import com.github.ayltai.newspaper.app.data.model.Item;
import com.github.ayltai.newspaper.app.view.ItemListAdapter;
import com.github.ayltai.newspaper.app.view.binding.CompactBinderFactory;
import com.github.ayltai.newspaper.app.view.binding.FeaturedBinderFactory;
import com.github.ayltai.newspaper.view.UniversalAdapter;

public class CompactItemListView extends ItemListView {
    public CompactItemListView(@NonNull final Context context, @NonNull final String category, final boolean isHistorical, final boolean isBookmarked) {
        super(context, category, isHistorical, isBookmarked);
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return R.layout.view_list_compact;
    }

    @NonNull
    @Override
    protected UniversalAdapter<Item, ?, ?> createAdapter() {
        final ItemListAdapter adapter = new ItemListAdapter.Builder(this.getContext(), this.category)
            .setIsHistorical(this.isHistorical)
            .setIsBookmarked(this.isBookmarked)
            .addBinderFactory(new FeaturedBinderFactory())
            .addBinderFactory(new CompactBinderFactory())
            .build();

        adapter.setAnimationInterpolator(new AccelerateDecelerateInterpolator());

        return adapter;
    }
}
