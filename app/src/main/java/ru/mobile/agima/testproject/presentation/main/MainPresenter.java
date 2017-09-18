package ru.mobile.agima.testproject.presentation.main;

import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import ru.mobile.agima.testproject.App;
import ru.mobile.agima.testproject.R;
import ru.mobile.agima.testproject.domain.GalleryItem;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
    private static final int DEFAULT_RES = -1;
    private static final double SCREEN_CENTRE = 0.5;

    private GalleryAdapter adapter;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadGallery();
    }

    private void loadGallery() {
        final List<GalleryItem> items = getGalleryItemsFromResources();
        if (adapter == null) {
            adapter = new GalleryAdapter(items, App.getContext());
        }
        getViewState().updateGallery(adapter, getOnPageChangeListener());
    }

    private ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                getViewState().setTitleAlpha(calculateAlpha(positionOffset));
                getViewState().setTitle(calculateTitle(positionOffset, position));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    private float calculateAlpha(float positionOffset) {
        return (float) (2 * Math.abs(positionOffset - SCREEN_CENTRE));
    }

    private String calculateTitle(float positionOffset, int position) {
        return adapter.getItemTitle(positionOffset > SCREEN_CENTRE
                ? position + 1 : position);
    }

    private List<GalleryItem> getGalleryItemsFromResources() {
        final List<GalleryItem> items = new ArrayList<>();
        final TypedArray images = App.getContext().getResources().obtainTypedArray(R.array.gallery_res);
        final TypedArray titles = App.getContext().getResources().obtainTypedArray(R.array.gallery_titles);
        for (int i = 0; i < images.length(); i++) {
            items.add(new GalleryItem(images.getResourceId(i, DEFAULT_RES), titles.getString(i)));
        }
        return items;
    }
}
