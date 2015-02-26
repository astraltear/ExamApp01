package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PopupWindowActivity extends ActionBarActivity {

    private PopupWindow mPopupWindow;
    private Button btn_Popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);

        btn_Popup = (Button) findViewById(R.id.btn_click);
    }

    public void onPopupClick(View v){
        switch (v.getId()){
            case R.id.btn_click:
                View popupView = getLayoutInflater().inflate(R.layout.popup_window, null);
                /*
                mPopupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                mPopupWindow.setAnimationStyle(-1);
                mPopupWindow.showAsDropDown(btn_Popup, 50, 50);
                 */
                //or
                mPopupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                mPopupWindow.setAnimationStyle(-1);
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER,0,-100);


                break;

            case R.id.btn_close:
                if(mPopupWindow != null && mPopupWindow.isShowing()){
                    mPopupWindow.dismiss();
                }
                break;

            case R.id.btn_bottom:
                Toast.makeText(PopupWindowActivity.this,"Bottom UI입니다",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }


}
