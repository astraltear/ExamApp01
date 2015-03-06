package youngjee.com.examapp01;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageSendSMSActivity extends ActionBarActivity {

    private Uri mImageCaptureUri;
    private AlertDialog mDialog;

    private static final int PICK_FROM_CAMERA=0;
    private static final int PICK_FROM_ALBUM=1;
    private static final int CROP_FROM_CAMERA=2;

    private ImageView mPhotoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_send_sms);

        mPhotoImageView = (ImageView) findViewById(R.id.img_crop);
    }

    public void onButtonClick2(View v){
        switch (v.getId()){
            case R.id.btn_sms:
                sendSMS("01032905220", "hi nice to meet you");
                break;
            case R.id.btn_image_crop:
                mDialog = createDialog();
                mDialog.show();
                break;
        }
    }

    private AlertDialog createDialog() {
        final View innerView = getLayoutInflater().inflate(R.layout.image_crop_row, null);

        Button camera = (Button) innerView.findViewById(R.id.btn_camera_crop);
        Button gallery = (Button) innerView.findViewById(R.id.btn_gallery_crop);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTakePhotoAction();
                setDismiss(mDialog);
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTakeAlbumAction();
                setDismiss(mDialog);
            }
        });

        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("이미지 크롭");
        ab.setView(innerView);

        return ab.create();
    }

    private void setDismiss(AlertDialog dialog){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    private void sendSMS(String phoneNumber, String content) {
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", content);
        startActivity(intent);
    }

    private void doTakePhotoAction() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mImageCaptureUri = createSaveCropFile();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        startActivityForResult(intent,PICK_FROM_CAMERA);
    }

    private void doTakeAlbumAction() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode !=RESULT_OK){ return ; }

        switch (requestCode){
            case PICK_FROM_ALBUM:{
                mImageCaptureUri  = data.getData();
                Log.d("ImageSendSMSActivity","mImageCaptureUri["+mImageCaptureUri+"]");
                // mImageCaptureUri[content://media/external/images/media/14546]

                File original_file = getImageFile(mImageCaptureUri);

                mImageCaptureUri = createSaveCropFile();
                File copy_file = new File(mImageCaptureUri.getPath());
                copyFile(original_file, copy_file);

                // break 를 사용하지 않는 이유는 이후의 처리가 카메라(PICK_FROM_CAMERA)와 같다.
            }

            case PICK_FROM_CAMERA: {
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");
                intent.putExtra("output", mImageCaptureUri);
                startActivityForResult(intent,CROP_FROM_CAMERA);
                break;
            }

            case CROP_FROM_CAMERA: {
                String full_path = mImageCaptureUri.getPath();

                Bitmap photo = BitmapFactory.decodeFile(full_path);
                mPhotoImageView.setImageBitmap(photo);
                break;
            }
        }
    }

    private File getImageFile(Uri uri) {
        String projection[] = {MediaStore.Images.Media.DATA};

        if(uri == null) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        Cursor mCursor = getContentResolver().query(uri, projection, null, null, MediaStore.Images.Media.DATE_MODIFIED + " desc");
        if(mCursor == null || mCursor.getCount() < 1){
            return  null;
        }

        int column_index = mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        mCursor.moveToFirst();

        String path = mCursor.getString(column_index);
        Log.d("ImageSendSMSActivity","path["+path+"]");
//      path[/storage/emulated/0/Download/fd64f3772bb4be46b7573e72aa5d52b1.jpeg]


        if(mCursor != null){
            mCursor.close();
            mCursor = null;
        }

        return new File(path);
    }

    public static boolean copyFile(File srcFile, File destFile){
        boolean result = false;
        try {
            InputStream in = new FileInputStream(srcFile);
            try {
                OutputStream out = new FileOutputStream(destFile);
                try {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) >=0 ){
                        out.write(buffer,0,bytesRead);
                    }

                    result = true;
                } finally {
                    out.close();
                }

            } finally {
                in.close();
            }
        } catch (Exception e){
            result=false;
        }

        return  result;
    }

    private Uri createSaveCropFile(){
        Uri uri;
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
        return uri;
    }
}
