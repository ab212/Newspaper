package com.github.ayltai.newspaper.app.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.github.ayltai.newspaper.R;
import com.github.ayltai.newspaper.app.data.model.Item;
import com.github.ayltai.newspaper.app.view.ItemListAdapter;
import com.github.ayltai.newspaper.app.view.binding.CompactItemBinderFactory;
import com.github.ayltai.newspaper.app.view.binding.FeaturedBinderFactory;
import com.github.ayltai.newspaper.view.UniversalAdapter;

public class CompactItemListView extends ItemListView {
    public CompactItemListView(@NonNull final Context context) {
        super(context);
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return R.layout.view_list_compact;
    }

    @NonNull
    @Override
    protected UniversalAdapter<Item, ?, ?> createAdapter() {
        final ItemListAdapter adapter = new ItemListAdapter.Builder(this.getContext())
            .addBinderFactory(new FeaturedBinderFactory())
            .addBinderFactory(new CompactItemBinderFactory())
            .build();

        adapter.setAnimationInterpolator(new AccelerateDecelerateInterpolator());

        return adapter;
    }
}
