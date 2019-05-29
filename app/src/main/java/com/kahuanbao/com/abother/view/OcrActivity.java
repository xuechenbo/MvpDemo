package com.kahuanbao.com.abother.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.kahuanbao.com.R;
import com.kahuanbao.com.utils.LogUtil;
import com.kahuanbao.com.utils.ViewUtils;
import com.kahuanbao.com.v.BaseActivity;

import java.io.File;

/**
 * Created by Administrator on 2019/2/22.
 *
 */

public class OcrActivity extends BaseActivity {
    private static final int REQUEST_CODE_CAMERA = 102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shenfen);
        init();

        findViewById(R.id.iv).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent1 = new Intent(context, CameraActivity.class);
                intent1.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH, getSaveFile(1).getAbsolutePath());
                intent1.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                startActivityForResult(intent1, REQUEST_CODE_CAMERA);
            }
        });
    }

    //存储文件
    public File getSaveFile(int flag) {
        File file = new File(Environment.getExternalStorageDirectory(), flag + "pic.jpg");
        return file;
    }

    private void init() {
        OCR.getInstance(this).initAccessTokenWithAkSk(new OnResultListener<AccessToken>(){
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
                //hasGotToken = true;
                LogUtil.e("aaaa", "AK，SK方式获取token成功" );
            }
            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                LogUtil.e("aaaa", "AK，SK方式获取token失败" + error.getMessage());
            }
        }, context, "Gz6kqkVeFlhrX5eoaxhvHjmg", "LFudKhN1vKA7Ql39Df2hazwDmZ47ZOi8");
    }

    private void recIDCard(final String idCardSide, final String filePath) {
        IDCardParams param = new IDCardParams();
        param.setImageFile(new File(filePath));
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.setImageQuality(20);

        OCR.getInstance(this).recognizeIDCard(param, new OnResultListener<IDCardResult>() {
            @Override
            public void onResult(IDCardResult result) {
                if (result != null) {
                    if (TextUtils.equals(idCardSide, IDCardParams.ID_CARD_SIDE_FRONT)) {
                        ViewUtils.makeToast(context,result.getName().getWords(),1200);
                        //et_name.setText(result.getName().getWords());
                        ViewUtils.makeToast(context,result.getIdNumber().getWords(),1200);
                        //et_idCard.setText(result.getIdNumber().getWords());
                        //Glide.with(context).load(new File(filePath)).signature(new StringSignature(String.valueOf(System.currentTimeMillis()))).into(iv_idCard_front);
                    }
                    if (TextUtils.equals(idCardSide, IDCardParams.ID_CARD_SIDE_BACK)) {
                        //.with(context).load(new File(filePath)).signature(new StringSignature(String.valueOf(System.currentTimeMillis()))).into(iv_idCard_back);
                    }
                }
            }

            @Override
            public void onError(OCRError error) {
                ViewUtils.makeToast(context, "识别出错,请重新上传正确的证件照片", 0);
                LogUtil.i("ssss", error.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 999) {
                finish();
            }
            if (requestCode == REQUEST_CODE_CAMERA) {  //拍照返回标志
                if (data != null) {
                    String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                    if (!TextUtils.isEmpty(contentType)) {
                        if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {    //反面
                            String filePath = getSaveFile(1).getAbsolutePath();
                            recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                        } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {   //背面
                            String filePath = getSaveFile(2).getAbsolutePath();
                            recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                        }
                    }
                }
            }
        }
    }

}
