package youngjee.com.examapp01;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class TakePictureActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
    }

    public void CameraOnClick(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            FileOutputStream fos = openFileOutput("test_picture.jpg", Context.MODE_WORLD_WRITEABLE);
            fos.close();

            File path = getFilesDir();
            File file = new File(path, "test_picture.jpg");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*

    카메라로 사진을 찍으면 일정 비율로 축소된 이미지가 리턴된다.
    public void CameraOnClick(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TakePictureActivity","requestCode["+requestCode+"]resultCode["+resultCode+"]");

        if (requestCode ==0 && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ImageView iv = (ImageView) findViewById(R.id.iv_take_picture);
            iv.setImageBitmap(bitmap);
            Log.d("TakePictureActivity",bitmap.toString());

        }
    }
    */
}
