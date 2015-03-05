package youngjee.com.examapp01;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileManagerActivity extends ActionBarActivity {

    private static final String SAVEPATH = Environment.getExternalStorageDirectory() + "/testfolder";
    private static final String SAVEPATH2 = Environment.getExternalStorageDirectory()+"/testfolder2";
    private static final String SAVEFILEPATH = "Myfile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);

        TextView tv_dirpath = (TextView) findViewById(R.id.tv_dirpath);
        TextView tv_filepath = (TextView) findViewById(R.id.tv_filepath);

        File dir = makeDirectory(SAVEPATH);
        File file = makeFile(dir, (SAVEPATH +"/"+ SAVEFILEPATH));

        tv_dirpath.setText( getAbsolutePath(dir)  );
        tv_filepath.setText( getAbsolutePath(file)  );

        String content = "가나다라마바사아자";
        writeFile(file, content.getBytes());

        readFile(file);

        makeDirectory(SAVEPATH2);
        copyFile(file, (SAVEPATH2 + SAVEFILEPATH));

        String[] list = getList(dir);
        for(String s: list) {
            Log.d("FileManagerActivity:", s);
        }
    }

    private File makeDirectory(String dirPath) {
        File dir = new File(dirPath);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    private File makeFile(File dir, String file_path){
        File file = null;
        boolean isSuccess = false;
        Log.d("isDirectory",""+dir.isDirectory());
        if(dir.isDirectory()) {
            file = new File(file_path);
            Log.d("exists",""+file.exists());
            if( !file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    private String getAbsolutePath(File file) {
        return file.getAbsolutePath();
    }

    private boolean deleteFile(File file){
        boolean result;
        if(file != null && file.exists()){
            file.delete();
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    private boolean isFile(File file){
        boolean result;
        if(file != null && file.isFile()){
            result=true;
        } else {
            result=false;
        }
        return  result;
    }

    private boolean isDirectory(File dir){
        boolean result;
        if(dir != null && dir.isDirectory()){
            result=true;
        } else {
            result=false;
        }
        return  result;
    }

    private boolean isFileExist(File file){
        boolean result;
        if(file != null && file.exists()){
            result=true;
        } else {
            result=false;
        }
        return  result;
    }

    private boolean reNameFile(File file,File newName){
        boolean result;
        if(file != null && file.exists() && file.renameTo(newName)){
            result=true;
        } else {
            result=false;
        }
        return  result;
    }

    private String[] getList(File dir){
        if(dir != null && dir.exists()){
            return dir.list();
        }
        return  null;
    }

    private boolean writeFile(File file, byte[] file_content){
        boolean result;
        FileOutputStream fos;

        if(file != null && file.exists() && file_content != null){
            Log.d("writeFile exists","file.exists()["+file.exists()+"]file_content["+file_content+"]");
            try {
                fos = new FileOutputStream(file);
                fos.write(file_content);
                fos.flush();
                fos.close();
            } catch (Exception e){
                e.printStackTrace();
            }
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    private void readFile(File file){
        int readcount = 0;
        Log.d("readFile exists",""+file.exists());
        if(file != null && file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);

                readcount = (int) file.length();
                Log.d("readFile readcount",""+readcount);
                byte[] buffer = new byte[readcount];
                fis.read(buffer);

                for (int i =0 ; i<file.length(); i++) {
                    Log.d("readFile", buffer[i]+"");
                }

                fis.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean copyFile(File file, String saveFile){
        boolean result;
        if(file != null && file.exists()){
            try {

                FileInputStream fis = new FileInputStream(file);
                FileOutputStream fos = new FileOutputStream(saveFile);
                int readcount = 0;
                byte [] buffer = new byte[1024];
                while((readcount = fis.read(buffer,0,1024)) != -1 ){
                    fos.write(buffer,0,readcount);
                }
                fos.close();
                fis.close();

            } catch(Exception e){
                e.printStackTrace();
            }
            result= true;
        } else {
            result=false;
        }

        return result;
    }
}
