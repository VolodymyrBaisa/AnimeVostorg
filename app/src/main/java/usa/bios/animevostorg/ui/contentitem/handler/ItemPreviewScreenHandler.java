package usa.bios.animevostorg.ui.contentitem.handler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import org.parceler.Parcel;
import org.parceler.Parcels;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.ui.base.BaseActivity;
import usa.bios.animevostorg.ui.contentitem.viewmodel.ItemPreviewScreenViewModel;
import usa.bios.animevostorg.ui.description.DescriptionActivity;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 10/8/2017.
 */

public class ItemPreviewScreenHandler {
    private static final String PAGE_ITEMS = "page_items";

    public ItemPreviewScreenHandler() {

    }

    public void onClickCardItem(View view, ItemPreviewScreenViewModel items) {
        intentActivity(view.getContext(), DescriptionActivity.class, items);
        callAnimation((BaseActivity) view.getContext());
    }

    public View.OnTouchListener onTouchCardItem() {
        return new View.OnTouchListener() {
            private int CLICK_ACTION_THRESHOLD = 20;
            private float startX;
            private float startY;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        float endX = event.getX();
                        float endY = event.getY();
                        if (isAClick(startX, endX, startY, endY)) {
                            //intentActivity(view.getContext(), DescriptionActivity.class);
                            callAnimation((BaseActivity) view.getContext());
                        }
                        break;
                }
                return false;
            }

            private boolean isAClick(float startX, float endX, float startY, float endY) {
                float differenceX = Math.abs(startX - endX);
                float differenceY = Math.abs(startY - endY);
                return !(differenceX > CLICK_ACTION_THRESHOLD || differenceY > CLICK_ACTION_THRESHOLD);
            }
        };
    }

    private void intentActivity(Context context, Class aClass, ItemPreviewScreenViewModel items) {
        if (NullUtils.isNotNull(context) && NullUtils.isNotNull(aClass)) {
            Intent intent = new Intent(context, aClass);
            intent.putExtras(getBundle(items));
            context.startActivity(intent);
        }
    }

    @NonNull
    private Bundle getBundle(ItemPreviewScreenViewModel items) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PAGE_ITEMS, Parcels.wrap(items));
        return bundle;
    }

    private void callAnimation(BaseActivity baseActivity) {
        if (NullUtils.isNotNull(baseActivity)) {
            baseActivity.overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        }
    }
}
