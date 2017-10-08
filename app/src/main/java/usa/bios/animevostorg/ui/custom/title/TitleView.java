package usa.bios.animevostorg.ui.custom.title;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.utils.TypefaceUtils;

/**
 * Created by Bios on 10/7/2017.
 */

public class TitleView extends FrameLayout {
    private static final String FONT = "fonts/roomfer.ttf";
    private Context context;

    private TextView textPart1;
    private TextView textPart2;
    private TextView textPart3;
    private TextView textVersion;

    public TitleView(@NonNull Context context) {
        this(context, null);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        initiateView();
        initStyle(attrs, defStyleAttr);
    }

    private void initStyle(AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleView, defStyleAttr, 0);
        if (a != null) {

            if (a.hasValue(R.styleable.TitleView_textPart1)) {
                setTextPart1(a.getString(R.styleable.TitleView_textPart1));
            }

            if (a.hasValue(R.styleable.TitleView_textPart2)) {
                setTextPart2(a.getString(R.styleable.TitleView_textPart2));
            }

            if (a.hasValue(R.styleable.TitleView_textPart3)) {
                setTextPart3(a.getString(R.styleable.TitleView_textPart3));
            }

            if (a.hasValue(R.styleable.TitleView_textVersion)) {
                setTextVersion(a.getString(R.styleable.TitleView_textVersion));
            }

            if (a.hasValue(R.styleable.TitleView_textColorPart1)) {
                setTextColorPart1(a.getColor(R.styleable.TitleView_textColorPart1, 0));
            }

            if (a.hasValue(R.styleable.TitleView_textColorPart2)) {
                setTextColorPart2(a.getColor(R.styleable.TitleView_textColorPart2, 0));
            }

            if (a.hasValue(R.styleable.TitleView_textColorPart3)) {
                setTextColorPart3(a.getColor(R.styleable.TitleView_textColorPart3, 0));
            }

            if (a.hasValue(R.styleable.TitleView_textColorVersion)) {
                setTextColorVersion(a.getColor(R.styleable.TitleView_textColorVersion, 0));
            }
            a.recycle();
        }
        setFontStile();
    }

    private void setFontStile() {
        textPart1.setTypeface(TypefaceUtils.font(context, FONT));
        textPart2.setTypeface(TypefaceUtils.font(context, FONT));
        textPart3.setTypeface(TypefaceUtils.font(context, FONT));
        textVersion.setTypeface(TypefaceUtils.font(context, FONT));
    }

    private void initiateView() {
        LayoutInflater.from(context).inflate(R.layout.title_view, this, true);

        LinearLayout titleLayout = findViewById(R.id.titleLayout);
        textPart1 = titleLayout.findViewById(R.id.textPart1);
        textPart2 = titleLayout.findViewById(R.id.textPart2);
        textPart3 = titleLayout.findViewById(R.id.textPart3);
        textVersion = titleLayout.findViewById(R.id.textVersion);
    }

    public void setTextPart1(String textPart1) {
        this.textPart1.setText(textPart1);
    }

    public void setTextPart2(String textPart2) {
        this.textPart2.setText(textPart2);
    }

    public void setTextPart3(String textPart3) {
        this.textPart3.setText(textPart3);
    }

    public void setTextVersion(String textVersion) {
        this.textVersion.setText(textVersion);
    }

    public void setTextColorPart1(int textColorPart1) {
        this.textPart1.setTextColor(textColorPart1);
    }

    public void setTextColorPart2(int textColorPart2) {
        this.textPart2.setTextColor(textColorPart2);
    }

    public void setTextColorPart3(int textColorPart3) {
        this.textPart3.setTextColor(textColorPart3);
    }

    public void setTextColorVersion(int textColorVersion) {
        this.textVersion.setTextColor(textColorVersion);
    }
}
