package youngjee.com.examapp01.dto;


import android.graphics.drawable.Drawable;

public class Music {
    private String gasu;
    private String jemok;
    private String albumName;
    private Drawable drawimage;

    public Music(String _gasu, String _jemok, String _albumName, Drawable _image){
        this.gasu = _gasu;
        this.jemok = _jemok;
        this.albumName = _albumName;
        this.drawimage = _image;
    }

    public String getGasu(){
        return gasu;
    }

    public String getJemok(){
        return jemok;
    }

    public String getAlbumName(){
        return albumName;
    }

    public Drawable getImage(){
        return drawimage;
    }

}
