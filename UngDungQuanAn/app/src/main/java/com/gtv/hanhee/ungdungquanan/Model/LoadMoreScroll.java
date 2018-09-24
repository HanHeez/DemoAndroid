package com.gtv.hanhee.ungdungquanan.Model;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class LoadMoreScroll extends RecyclerView.OnScrollListener {

    int itemHienThiDauTien = 0;
    int tongItem = 0;
    int itemLoadTrenManHinh = 2;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;


    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore) {
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        tongItem = layoutManager.getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            itemHienThiDauTien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            itemHienThiDauTien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        //vị trí itemHienThiDauTien cũng = tổng item đang ẩn

        if (tongItem <= (itemHienThiDauTien + itemLoadTrenManHinh)) {
            iLoadMore.LoadMore(tongItem);
        }

        Log.d("checkscroll", tongItem + " ");

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}


