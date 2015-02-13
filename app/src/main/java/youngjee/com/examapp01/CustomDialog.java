package youngjee.com.examapp01;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

public class CustomDialog extends Dialog {

    private Button mButton;
    private IOnclickInterface mIOnclickInterface;

    protected CustomDialog(Context context, IOnclickInterface iOnclickInterface) {
        super(context);
        setLayout();
        this.mIOnclickInterface = iOnclickInterface;
        mButton.setOnClickListener(mOnClickListener);
    }

    private void setLayout(){
        setContentView(R.layout.customdialog);
        mButton = (Button) findViewById(R.id.btn_button);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mIOnclickInterface.onClick();
        }
    };

    public void show(){
        super.show();
        mIOnclickInterface.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mIOnclickInterface.dismiss();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        mIOnclickInterface.getTitle(title);
    }
}
