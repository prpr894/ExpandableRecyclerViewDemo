package com.prpr894.recycler.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private View itemView;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void setText(int resId, String text) {
        try {
            ((TextView) itemView.findViewById(resId)).setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public View getViewById(int resId) {
        return itemView.findViewById(resId);
    }
}
