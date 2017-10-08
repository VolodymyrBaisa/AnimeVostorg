package usa.bios.animevostorg.ui.handler;

import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.ui.base.BaseActivity;
import usa.bios.animevostorg.ui.description.DescriptionActivity;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 10/8/2017.
 */

public class ItemPreviewScreenHandler {
    private static final String PAGE_ID = "page_id";

    public void onClickCardItem(View view, Integer id) {
        BaseActivity baseActivity = (BaseActivity) view.getContext();
        switchActivityWithAnimation(id, baseActivity, DescriptionActivity.class);
    }

    public View.OnTouchListener onTouchCardItem(final Integer id) {
        return new View.OnTouchListener() {
            private int CLICK_ACTION_THRESHOLD = 20;
            private float startX;
            private float startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        float endX = event.getX();
                        float endY = event.getY();
                        if (isAClick(startX, endX, startY, endY)) {
                            switchActivityWithAnimation(id, v.getContext(), DescriptionActivity.class);
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

    private void switchActivityWithAnimation(int id, Context context, Class aClass) {
        intentActivity(id, context, aClass);
        callAnimation((BaseActivity) context);

    }

    private void intentActivity(int id, Context context, Class aClass) {
        if (NullUtils.isNotNull(context) && NullUtils.isNotNull(aClass)) {
            Intent intent = new Intent(context, aClass);
            intent.putExtra(PAGE_ID, id);
            context.startActivity(intent);
        }
    }

    private void callAnimation(BaseActivity baseActivity) {
        if (NullUtils.isNotNull(baseActivity)) {
            baseActivity.overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        }

    }
}
