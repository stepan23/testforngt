package ru.mobile.agima.testproject.presentation.main;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ru.mobile.agima.testproject.R;
import ru.mobile.agima.testproject.domain.GalleryItem;

public class GalleryAdapter extends PagerAdapter {
    private List<GalleryItem> items = new ArrayList<>();
    private Context context;

    public GalleryAdapter(List<GalleryItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public String getItemTitle(int position) {
        return items.get(position).text;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.gallery_item, container, false);
        loadImage((ImageView)item, items.get(position));
        container.addView(item);
        return item;
    }

    private void loadImage(ImageView imageView, GalleryItem item) {
        Glide.with(context)
                .load(item.imageRes)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeViewInLayout((View)object);
    }
}
