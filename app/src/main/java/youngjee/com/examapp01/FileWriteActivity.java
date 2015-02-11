package youngjee.com.examapp01;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileWriteActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_write);

        Log.d("FileWriteActivity", "getFilesDir().getAbsolutePath():"+getFilesDir().getAbsolutePath());
        Log.d("FileWriteActivity", "getCacheDir().getAbsolutePath():"+getCacheDir().getAbsolutePath());
        Log.d("FileWriteActivity", "Environment.getExternalStorageState():"+Environment.getExternalStorageState());
        Log.d("FileWriteActivity", "Environment.MEDIA_MOUNTED:"+Environment.MEDIA_MOUNTED);
        Log.d("FileWriteActivity", "Environment.MEDIA_MOUNTED_READ_ONLY:"+Environment.MEDIA_MOUNTED_READ_ONLY);
        Log.d("FileWriteActivity", "Environment.DIRECTORY_PICTURES:"+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsoluteFile());

        File dirs = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (dirs.isDirectory()){
            File files[] =dirs.listFiles();

            for (File file  : files){
                Log.d("FileWriteActivity", "file.getName():"+file.getName());

            }
        }

        String fileName = "myfiles";
        String str = "Version Control";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(str.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
