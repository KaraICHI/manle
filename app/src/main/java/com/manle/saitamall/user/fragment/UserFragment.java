package com.manle.saitamall.user.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.manle.saitamall.R;
import com.manle.saitamall.app.LoginActivity;
import com.manle.saitamall.app.MyAppliction;
import com.manle.saitamall.base.BaseFragment;
import com.hankkin.gradationscroll.GradationScrollView;
import com.manle.saitamall.bean.User;
import com.manle.saitamall.user.activity.CollectorMangerActivity;
import com.manle.saitamall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

// 个人中心页面
public class UserFragment extends BaseFragment  implements View.OnClickListener{

    @Bind(R.id.ib_user_avator)
    ImageButton ibUserAvator;
    @Bind(R.id.rl_person_header)
    RelativeLayout rlPersonHeader;
    @Bind(R.id.sv_person)
    GradationScrollView svPerson;
    @Bind(R.id.tv_usercenter)
    TextView tvUsercenter;
    @Bind(R.id.tv_my_collector)
    TextView tvMyCollector;
    @Bind(R.id.tv_login_out)
    TextView tvLoginOut;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.tv_point)
    TextView tvPoint;
    @Bind(R.id.ll_not_login)
    LinearLayout llNotLogin;
    @Bind(R.id.tv_to_login)
    TextView tvToLogin;

    User user;

    protected static final int TAKE_PHOTO_PERMISSION_REQUEST_CODE = 0; // 拍照的权限处理返回码
    protected static final int WRITE_SDCARD_PERMISSION_REQUEST_CODE = 1; // 读储存卡内容的权限处理返回码

    protected static final int TAKE_PHOTO_REQUEST_CODE = 3; // 拍照返回的 requestCode
    protected static final int CHOICE_FROM_ALBUM_REQUEST_CODE = 4; // 相册选取返回的 requestCode
    protected static final int CROP_PHOTO_REQUEST_CODE = 5; // 裁剪图片返回的 requestCode

    protected Uri photoUri = null;
    protected Uri photoOutputUri = null; // 图片最终的输出文件的 Uri

    ProgressDialog progressDialog;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        ButterKnife.bind(this, view);
        tvMyCollector.setOnClickListener(this);
        tvLoginOut.setOnClickListener(this);
        tvToLogin.setClickable(true);
        tvToLogin.setOnClickListener(this);
        ibUserAvator.setOnClickListener(this);
        user = MyAppliction.getUser();
        if (user==null){
            llNotLogin.setVisibility(View.VISIBLE);
        }else {
            Log.d(TAG, "initView: "+user);
            tvUsername.setText(user.getUserName());
            if (user.getPoint()<100){
                tvPoint.setText("LV1");
            }else if (user.getPoint()<1000){
                tvPoint.setText("LV2");
            }
        }

        return view;
    }

    @Override
    public void initData() {
        super.initData();

        ViewTreeObserver vto = rlPersonHeader.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private int height;

            @Override
            public void onGlobalLayout() {
                // 移除监听
                tvUsercenter.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                // 获取顶部图片的高度
                height = rlPersonHeader.getHeight();

                // 监听滑动，改变透明度
                svPerson.setScrollViewListener(new GradationScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {

                        if (y <= 0) {   //设置标题的背景颜色
                            tvUsercenter.setBackgroundColor(Color.argb((int) 0, 255, 0, 0));
                        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                            float scale = (float) y / height;
                            float alpha = (255 * scale);
                            tvUsercenter.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                            tvUsercenter.setBackgroundColor(Color.argb((int) alpha, 255, 0, 0));
                        } else {    //滑动到banner下面设置普通颜色
                            tvUsercenter.setBackgroundColor(Color.argb((int) 255, 255, 0, 0));
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_my_collector:
                Intent intent = new Intent(mContext, CollectorMangerActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_out:
                Log.d(TAG, "onClick: loginout");
                MyAppliction.setUser(null);
                llNotLogin.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_to_login:
                Intent intent1 = new Intent(mContext,LoginActivity.class);
                startActivityForResult(intent1,1);
                break;
            case R.id.ib_user_avator:
                choiceFromAlbum();
                break;
        }
    }


    /**
     * 从相册选取
     */
    protected void choiceFromAlbum() {
        // 打开系统图库的 Action，等同于: "android.intent.action.GET_CONTENT"
        Intent choiceFromAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
        // 设置数据类型为图片类型
        choiceFromAlbumIntent.setType("image/*");
       startActivityForResult(choiceFromAlbumIntent, CHOICE_FROM_ALBUM_REQUEST_CODE);
    }

    /**
     * 裁剪图片
     */
    protected void cropPhoto(Uri inputUri) {
        // 调用系统裁剪图片的 Action
        Intent cropPhotoIntent = new Intent("com.android.camera.action.CROP");
        // 设置数据Uri 和类型
        cropPhotoIntent.setDataAndType(inputUri, "image/*");

        cropPhotoIntent.putExtra("aspectX", 60);
        cropPhotoIntent.putExtra("aspectY",60);
        // 授权应用读取 Uri，这一步要有，不然裁剪程序会崩溃
        cropPhotoIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        // 设置图片的最终输出目录
        cropPhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoOutputUri = Uri.parse("file:////sdcard/image_output/"+System.currentTimeMillis()+".jpg"));
        startActivityForResult(cropPhotoIntent, CROP_PHOTO_REQUEST_CODE);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            // 打开相册选取：
            case WRITE_SDCARD_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(mContext, "读写内存卡内容权限被拒绝", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 通过这个 activity 启动的其他 Activity 返回的结果在这个方法进行处理
     * 我们在这里对拍照、相册选择图片、裁剪图片的返回结果进行处理
     *
     * @param requestCode 返回码，用于确定是哪个 Activity 返回的数据
     * @param resultCode  返回结果，一般如果操作成功返回的是 RESULT_OK
     * @param data        返回对应 activity 返回的数据
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: resultcode"+resultCode);
        if (resultCode== 0){
            llNotLogin.setVisibility(View.GONE);
            Log.d(TAG, "onActivityResult: "+MyAppliction.getUser());
            User user = (User) data.getSerializableExtra("user");
            Log.d(TAG, "onActivityResult: "+user);
            tvUsername.setText(user.getUserName());
            if (user.getPoint()<100){
                tvPoint.setText("LV1");
            }else if (user.getPoint()<1000){
                tvPoint.setText("LV2");
            }
        }else if (resultCode == RESULT_OK) {
            // 通过返回码判断是哪个应用返回的数据
            switch (requestCode) {
                // 相册选择
                case CHOICE_FROM_ALBUM_REQUEST_CODE:
                    cropPhoto(data.getData());
                    break;
                // 裁剪图片
                case CROP_PHOTO_REQUEST_CODE:
                    File file = new File(photoOutputUri.getPath());
                    if (file.exists()) {
 //                       Bitmap bitmap = createCircleImage(BitmapFactory.decodeFile(photoOutputUri.getPath()));
                        uploadImg(file);
                      //  ibUserAvator.setBackground(new BitmapDrawable(getResources(),bitmap));
//                      file.delete(); // 选取完后删除照片
                    } else {
                        Toast.makeText(mContext, "找不到照片", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }


    }

    private void uploadImg(File file) {
        OkHttpUtils.postFile().file(file).url(Constants.USER_FIGURE).tag(this).id(100).build().execute(new StringCallback() {

            @Override
            public void onBefore(Request request, int id)
            {
                progressDialog = new ProgressDialog(mContext,ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("头像上传...");
                progressDialog.show();
            }

            @Override
            public void onAfter(int id)
            {
                progressDialog.dismiss();
                Log.d(TAG, "onAfter:============== "+file.getPath()+"/"+file.getName());
            }

            @Override
            public void onError(Call call, Exception e, int id){
                Log.e("TAG", "联网失败" + e.getMessage());

            }

            @Override
            public void onResponse(String response, int id)
            {
                Log.e(TAG, "onResponse：response="+response);
                switch (id)
                {
                    case 100://http
                        try {
                            if (response != null && !"".equals(response)){
                                //
                                JSONObject object = new JSONObject(response);
                                if(object.has("result") && object.getString("result").equals("上传成功")){
                                    Bitmap bitmap = createCircleImage(BitmapFactory.decodeFile(photoOutputUri.getPath()));
                                    ibUserAvator.setBackground(new BitmapDrawable(getResources(),bitmap));
                                    Toast.makeText(mContext,"上传成功",Toast.LENGTH_SHORT);
                                }else{
                                    Toast.makeText(mContext,"上传失败，请重试！",Toast.LENGTH_SHORT);
                                }
                            } else {
                                Toast.makeText(mContext,"上传失败，请重试！",Toast.LENGTH_SHORT);
                            }
                        }catch (Exception e) {
                            Toast.makeText(mContext,"上传异常，请重试！",Toast.LENGTH_SHORT);
                            Log.e(TAG, "onResponse: "+e.getMessage() );
                        }
                        break;
                    case 101://https
                        break;
                }
            }
            @Override
            public void inProgress(float progress, long total, int id)
            {
                Log.e(TAG, "inProgress:" + progress);//inProgress:0.99884826
                progressDialog.setProgress((int) progress);
                //ProgressWheelDialogUtil.setDialogMsg((int)(progress * 100)+"%");//更改上传进度提示框的进度值
            }
        });
    }

    public static Bitmap createCircleImage(Bitmap source) {
        int length = source.getWidth() < source.getHeight() ? source.getWidth() : source.getHeight();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(length, length, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(length / 2, length / 2, length / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }


}
