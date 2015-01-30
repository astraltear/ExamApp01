package youngjee.com.examapp01;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class TakePictureActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
    }

//    카메라로 사진을 찍으면 일정 비율로 축소된 이미지가 리턴된다.
    public void CameraOnClick(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);
    }

    public void CameraOnClick2(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            FileOutputStream fos = openFileOutput("test_picture.jpg", Context.MODE_WORLD_WRITEABLE);
            fos.close();

            File path = getFilesDir();
            File file = new File(path, "test_picture.jpg");

            Uri uri = Uri.fromFile(file);
            Log.d("TakePictureActivity", uri.getPath());

            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent,2);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 현재 상태에서는 아래와 같이 하면 오류발생함.
    public void CameraOnClick3(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File file = new File(path+"/external_sd", "test_img.jpg");

        if (!path.exists()){
            path.mkdirs();
        }

        Uri uri = Uri.fromFile(file);
        Log.d("TakePictureActivity", uri.getPath());

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent,3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TakePictureActivity","requestCode["+requestCode+"]resultCode["+resultCode+"]");

        if (resultCode == RESULT_OK) {
            if (requestCode ==1) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ImageView iv = (ImageView) findViewById(R.id.iv_take_picture);
                iv.setImageBitmap(bitmap);
                Log.d("TakePictureActivity",bitmap.toString());

            } else if (requestCode ==2) {

                File path = getFilesDir();
                Log.d("TakePictureActivity", path.getPath());

                File file = new File(path, "test_picture.jpg");
                try {
                    FileInputStream fis = new FileInputStream(file);
                    ImageView imgView = (ImageView) findViewById(R.id.iv_take_picture);
                    imgView.setImageDrawable(null); // 기존에 이미지가 설정되어 있었다면 설정된 이미지를 해제한다.
                    imgView.setImageDrawable(Drawable.createFromStream(fis, "images.jpg"));

                    fis.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode ==3) {
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File file = new File(path, "test_img.jpg");

                try {
                    FileInputStream fis = new FileInputStream(file);
                    ImageView img_view = (ImageView) findViewById(R.id.iv_take_picture);
                    img_view.setImageDrawable(null);
                    img_view.setImageDrawable(Drawable.createFromStream(fis,"image.jpg"));
                    fis.close();

                 System.gc();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }



        }
    }

}
