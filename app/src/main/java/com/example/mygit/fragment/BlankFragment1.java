package com.example.mygit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygit.Decoration.SpacesItemDecoration;
import com.example.mygit.Listener.OnItemClickListener;
import com.example.mygit.R;
import com.example.mygit.activity.ItemInfoActivity;
import com.example.mygit.adapter.RecyclerAdapter;
import com.example.mygit.bean.Item;

import java.util.ArrayList;

import java.util.List;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import java.util.concurrent.TimeUnit;

public class BlankFragment1 extends Fragment implements OnItemClickListener {
    private View mView;
    private String[] mStrs={"kk","KK","wsks","wksx"};
    private SearchView searchView;
    private ListView listView;
    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private ImageButton imgbtn;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d
    };
    //存放图片的标题
    private String[] titles = new String[]{
            "北京失恋博物馆",
            "西城区烧烤",
            "春天花园国际",
            "踏青"
    };
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;
    @Nullable
    //轮播下面网格布局
    private RecyclerView ry;

    private GridLayoutManager layoutManager;
    private RecyclerAdapter mAdapter;
    private static List<Item> mList;
    static{
        mList=new ArrayList();
        //Item item = new Item();
        for (int i = 0; i < 1; i++) {
            Item item = new Item();
            item.type = Item.TYPE.TYPE_TITLE;
            item.imageId = R.drawable.ic_cover;
            item.title = "五一要去浪";
            mList.add(item);
        }
        for (int i = 0; i < 4; i++) {
           Item item = new Item();
            item.type = Item.TYPE.TYPE_GRID_TWO;
            item.imageId = R.drawable.ic_cover;
            item.title = "Perfect Day";
            mList.add(item);
        }
        for (int i = 0; i < 1; i++) {
            Item item = new Item();
            item.type = Item.TYPE.TYPE_TITLE;
            item.imageId = R.drawable.ic_cover;
            item.title = "轻松交友";
            mList.add(item);
        }
        for (int i = 0; i < 4; i++) {
           Item item = new Item();
            item.type = Item.TYPE.TYPE_GRID_TWO;
            item.imageId = R.drawable.ic_cover;
            item.title = "Perfect Day";
            mList.add(item);
        }
        for (int i = 0; i < 1; i++) {
           Item item = new Item();
            item.type = Item.TYPE.TYPE_TITLE;
            item.imageId = R.drawable.ic_cover;
            item.title = "精选专栏";
            mList.add(item);
        }
        for (int i = 0; i < 6; i++) {
           Item item = new Item();
            item.type = Item.TYPE.TYPE_LIST;
            item.imageId = R.drawable.ic_cover;
            item.title = "去看《复仇者联盟四》前，你应该知道的三件事";
            mList.add(item);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_blank_fragment1, null);
        setView();
        return mView;
    }
    private void setView(){
        searchView=(SearchView) mView.findViewById(R.id.search);
        listView=(ListView)mView.findViewById(R.id.listView);
        mViewPaper = (ViewPager)mView.findViewById(R.id.vp);
        ry=(RecyclerView)mView.findViewById(R.id.ry);
        layoutManager=new GridLayoutManager(getContext(),6);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mList.get(position).type;
                if (type == Item.TYPE.TYPE_GRID_TWO) {
                    return 3;
                } else if (type == Item.TYPE.TYPE_LIST) {
                    return 6;
                } else if (type == Item.TYPE.TYPE_TITLE) {
                    return 6;
                }
                return 0;
            }
        });
        ry.setLayoutManager(layoutManager);
        ry.addItemDecoration(new SpacesItemDecoration(2));
        // 填充数据
         mAdapter = new RecyclerAdapter(getContext(), mList);
          mAdapter.setOnItemClickListener(this);
          ry.setAdapter(mAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!TextUtils.isEmpty(newText)){
                    listView.setFilterText(newText);
                }else
                    listView.clearTextFilter();
                return false;
            }
        });
        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(mView.findViewById(R.id.dot_0));
        dots.add(mView.findViewById(R.id.dot_1));
        dots.add(mView.findViewById(R.id.dot_2));
        dots.add(mView.findViewById(R.id.dot_3));
        dots.add(mView.findViewById(R.id.dot_4));
        title = (TextView) mView.findViewById(R.id.title);
        title.setText(titles[0]);
        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);
        mViewPaper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.dot_yes);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_no);

                oldPosition = position;
                currentItem = position;
            }
            @Override

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        imgbtn=(ImageButton)mView.findViewById(R.id.imgbtn);
    }
    /*定义的适配器*/
    public class ViewPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return images.size();
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView(images.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stu
            view.addView(images.get(position));
            return images.get(position);
        }
    }
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }
    private class ViewPageTask implements Runnable{
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }
    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler(){

        public void handleMessage(android.os.Message msg) {

            mViewPaper.setCurrentItem(currentItem);

        };

    };
    @Override

    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }
    //监听方法
    @Override
    public void onItemClick(int position){
        int imageId = mList.get(position).imageId;
     //   int lunboIT=imageIds.get(position);
        //Toast.makeText(getContext(), title, Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getActivity(), ItemInfoActivity.class);
        i.putExtra("tupian",imageId);
        startActivity(i);
    }
}