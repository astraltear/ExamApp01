package youngjee.com.examapp01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

public class IntentExamActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_exam);
    }

    public void goWeb(View v) {
        Uri uri = Uri.parse("http://www.google.com");
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    public void goGmap(View v) {
        Uri uri = Uri.parse("geo:38.899512,-77.035682");
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    public void goGmap2(View v) {
        Uri uri = Uri.parse("http://maps.google.com/maps?f=d&saddr=서울시성북구정릉4동&daddr=서울시강동구암사동&hl=ko");
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    public void goTel(View v) {
        Uri uri = Uri.parse("tel:024825224");
//        startActivity(new Intent(Intent.ACTION_VIEW, uri));
         startActivity(new Intent(Intent.ACTION_DIAL, uri));
         // 위의 두 개의 메소드는 전화걸기 창까지만 띄우고 아래는 직접 전화를 건다.
//        startActivity(new Intent(Intent.ACTION_CALL, uri)); // android.pemission.CALL_PHONE
    }

    public void goSms(View v) {
        /*
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.putExtra("sms_body", "The SMS text");
        intent.setType("vnd.android-dir/mms-sms");
        startActivity(intent);
        */

        Uri uri = Uri.parse("smsto:01032905220");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", "The SMS text");
        startActivity(intent);

        /*
        Uri uri = Uri.parse("content://media/external/images/media/23");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra("sms_body", "The SMS text");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/png");
        startActivity(intent);
         */

    }

    /*
// 이메일 발송
Uri uri = Uri.parse("mailto:xxx@abc.com");
Intent it = new Intent(Intent.ACTION_SENDTO, uri);
startActivity(it);


Intent it = new Intent(Intent.ACTION_SEND);
it.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
it.putExtra(Intent.EXTRA_TEXT, "The email body text");
it.setType("text/plain");
startActivity(Intent.createChooser(it, "Choose Email Client"));


Intent it = new Intent(Intent.ACTION_SEND);
String[] tos = {"me@abc.com"};
String[] ccs = {"you@abc.com"};
it.putExtra(Intent.EXTRA_EMAIL, tos);
it.putExtra(Intent.EXTRA_CC, ccs);
it.putExtra(Intent.EXTRA_TEXT, "The email body text");
it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
it.setType("message/rfc822");
startActivity(Intent.createChooser(it, "Choose Email Client"));


// extra 추가하기
Intent it = new Intent(Intent.ACTION_SEND);
it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
it.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/mysong.mp3");
sendIntent.setType("audio/mp3");
startActivity(Intent.createChooser(it, "Choose Email Client"));


// 미디어파일 플레이 하기
Intent it = new Intent(Intent.ACTION_VIEW);
Uri uri = Uri.parse("file:///sdcard/song.mp3");
it.setDataAndType(uri, "audio/mp3");
startActivity(it);


Uri uri = Uri.withAppendedPath(
  MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
Intent it = new Intent(Intent.ACTION_VIEW, uri);
startActivity(it);


// 설치 어플 제거
Uri uri = Uri.fromParts("package", strPackageName, null);
Intent it = new Intent(Intent.ACTION_DELETE, uri);
startActivity(it);


// APK파일을 통해 제거하기
Uri uninstallUri = Uri.fromParts("package", "xxx", null);
returnIt = new Intent(Intent.ACTION_DELETE, uninstallUri);


// APK파일 설치
Uri installUri = Uri.fromParts("package", "xxx", null);
returnIt = new Intent(Intent.ACTION_PACKAGE_ADDED, installUri);


// 음악 파일 재생
Uri playUri = Uri.parse("file:///sdcard/download/everything.mp3");
returnIt = new Intent(Intent.ACTION_VIEW, playUri);


// 첨부파일을 추가하여 메일 보내기
Intent it = new Intent(Intent.ACTION_SEND);
it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
it.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/eoe.mp3");
sendIntent.setType("audio/mp3");
startActivity(Intent.createChooser(it, "Choose Email Client"));


// 마켓에서 어플리케이션 검색
Uri uri = Uri.parse("market://search?q=pname:pkg_name");
Intent it = new Intent(Intent.ACTION_VIEW, uri);
startActivity(it);
// 패키지명은 어플리케이션의 전체 패키지명을 입력해야 합니다.


// 마켓 어플리케이션 상세 화면
Uri uri = Uri.parse("market://details?id=어플리케이션아이디");
Intent it = new Intent(Intent.ACTION_VIEW, uri);
startActivity(it);
// 아이디의 경우 마켓 퍼블리싱사이트의 어플을 선택후에 URL을 확인해보면 알 수 있습니다.


// 구글 검색
Intent intent = new Intent();
intent.setAction(Intent.ACTION_WEB_SEARCH);
intent.putExtra(SearchManager.QUERY,"searchString")
startActivity(intent);
    * */




}
