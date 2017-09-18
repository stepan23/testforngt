package ru.mobile.agima.testproject.presentation.main;


import android.support.v4.view.ViewPager;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {
    void updateGallery(GalleryAdapter adapter, ViewPager.OnPageChangeListener pagerListener);

    void setTitleAlpha(float alpha);

    void setTitle(String text);
}
