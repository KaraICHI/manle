package com.manle.saitamall.community.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.manle.saitamall.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/3.
 */

public class EditArticleActivity extends Activity implements View.OnClickListener{
    @Bind(R.id.iv_article_image)
    ImageView ivShareImg;
    @Bind(R.id.ib_article_image_add)
     ImageButton imgAdd;
    @Bind(R.id.et_edit_content)
     EditText etShareContent;
    @Bind(R.id.ib_edit_done)
     ImageButton ibEditDone;
    @Bind(R.id.ib_community_back)
    ImageButton ibBack;

     PopupMenu popupMenu = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_edit_article);
        ButterKnife.bind(this);
        onPopupBottonClick(imgAdd);
        ibBack.setOnClickListener(this);
        ibEditDone.setOnClickListener(this);
    }

    public void onPopupBottonClick(View button){
        popupMenu =new PopupMenu(this,button);
        getMenuInflater().inflate(R.menu.menu_img_add,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.take_photo:

                        break;
                    case R.id.choose_album:

                        break;
                    default:

                }
             return true;
            }

        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_edit_done :
                finish();
                break;
            case R.id.ib_community_back:
                finish();
                break;
        }
    }
}
