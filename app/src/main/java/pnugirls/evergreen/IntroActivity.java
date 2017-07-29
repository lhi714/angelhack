package pnugirls.evergreen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {
    private ScreenManager screenManager;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btn_skip, btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        screenManager = new ScreenManager(this);
        if (!screenManager.check()) {
            goMain();
            finish();
        }


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_intro);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btn_next= (Button)findViewById(R.id.btn_next);
        btn_skip = (Button)findViewById(R.id.btn_skip);

        layouts = new int[]{
                R.layout.view_intro1,
                R.layout.view_intro2,
                R.layout.view_intro3
        };

        //dots..
        addDots(0);

        //notification bar use with upper version code..
        changeBarcolor();


        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerListener);

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMain();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current_p = getItem(+1);
                if(current_p < layouts.length) {
                    viewPager.setCurrentItem(current_p);
                } else {
                    goMain();
                }
            }
        });
    }

    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            if(position == layouts.length-1) {
                btn_next.setText("start");
                btn_skip.setVisibility(View.GONE);
            } else {
                btn_next.setText("next");
                btn_skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private void changeBarcolor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private  void goMain() {
        screenManager.setFirst(false);
        startActivity(new Intent(IntroActivity.this, MainActivity.class));
        finish();
    }

    private void addDots(int page) {
        dots = new TextView[layouts.length];
        String bullet = "&#8226;";

        int active_d = ContextCompat.getColor(this, R.color.dot_active);
        int inactive_d = ContextCompat.getColor(this, R.color.dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            if (Build.VERSION.SDK_INT >= 24) {
                dots[i].setText(Html.fromHtml(bullet,Html.FROM_HTML_MODE_LEGACY));
                // for 24 api and more
            } else {
                dots[i].setText(Html.fromHtml(bullet));
                // or for older api
            }
            dots[i].setTextSize(35);
            dots[i].setTextColor(inactive_d);
            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0) {
            dots[page].setTextColor(active_d);
        }
    }

    private int getItem(int i ) {
        return viewPager.getCurrentItem() + i;
    }

    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public ViewPagerAdapter() {
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}