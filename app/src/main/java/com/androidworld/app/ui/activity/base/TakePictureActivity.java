package com.androidworld.app.ui.activity.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.List;

import me.fattycat.kun.library.ImageChooserDialog;

/**
 * <h3>通用的拍照Activity</h3>
 *
 * @author LQC
 *         当前时间：2016/12/31 12:46
 */
public abstract class TakePictureActivity extends BaseSwipeBackActivity {

    /**
     * 裁剪照片结果
     */
    private static final int REQUEST_CROP_IMAGE = 0x01;
    private ImageChooserDialog mImageChooserDialog;
    public View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showImageChooserDialog();
        }
    };

    /**
     * 获取拍照后将Bitmap设置到的View
     * @return view
     */
    protected abstract ImageView getImageView();

    public void showImageChooserDialog() {
        if (mImageChooserDialog == null) {
            mImageChooserDialog = new ImageChooserDialog();
            mImageChooserDialog.setListener(new ImageChooserDialog.OnImageChooserListener() {
                @Override
                public void onDoneClicked(List<String> imagePaths) {
                    if (imagePaths != null && imagePaths.size() > 0) {
                        toCropImage(Uri.fromFile(new File(imagePaths.get(0))));
                    }
                }

                @Override
                public void onCameraCaptureSuccess(Bitmap photoBitmap) {
                    toCropImage(Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), photoBitmap, null, null)));
                }
            });
            mImageChooserDialog.setChooserType(ImageChooserDialog.CHOOSER_TYPE_SINGLE);
            mImageChooserDialog.setTitleText("选择图片");
            mImageChooserDialog.setDoneText("完成");
        }
        mImageChooserDialog.show(getSupportFragmentManager(), mImageChooserDialog.getTag());
    }

    private void toCropImage(Uri uri) {
        final Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "avatar.jpg"));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, REQUEST_CROP_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CROP_IMAGE) {
                Glide.with(mContext)
                        .load(data.getData())
                        .asBitmap()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(getImageView());
                mImageChooserDialog.dismiss();
            }
        }
    }

}
