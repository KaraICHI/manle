package com.manle.saitamall.community.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.manle.saitamall.R;
import com.manle.saitamall.base.CameraBaseActivity;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/3.
 */

public class EditArticleActivity extends CameraBaseActivity implements View.OnClickListener{
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
    @Bind(R.id.ll_add_menu)
    LinearLayout llAddMenu;
    @Bind(R.id.btn_take_photo)
    Button btnTakePhoto;
    @Bind(R.id.btn_choose_album)
    Button btnChooseAlbum;


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_edit_article);
        ButterKnife.bind(this);
        ibBack.setOnClickListener(this);
        ibEditDone.setOnClickListener(this);
        imgAdd.setOnClickListener(this);
        btnTakePhoto.setOnClickListener(this);
        btnChooseAlbum.setOnClickListener(this);
        aspectX = 400;
        aspectY = 250;
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
            case R.id.ib_article_image_add:
                llAddMenu.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_take_photo:
                startCamera();
                break;
            case R.id.btn_choose_album:
                choiceFromAlbum();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            // 通过返回码判断是哪个应用返回的数据
            switch (requestCode) {
                // 拍照
                case TAKE_PHOTO_REQUEST_CODE:
                    cropPhoto(photoUri);
                    break;
                // 相册选择
                case CHOICE_FROM_ALBUM_REQUEST_CODE:
                    cropPhoto(data.getData());
                    break;
                // 裁剪图片
                case CROP_PHOTO_REQUEST_CODE:
                    llAddMenu.setVisibility(View.INVISIBLE);
                    File file = new File(photoOutputUri.getPath());
                    if (file.exists()) {
                        Bitmap bitmap = BitmapFactory.decodeFile(photoOutputUri.getPath());
                        if (ivShareImg != null) {
                            ivShareImg.setImageBitmap(bitmap);
                        }
//                      file.delete(); // 选取完后删除照片
                    } else {
                        Toast.makeText(this, "找不到照片", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

    }





}
