package youngjee.com.examapp01;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/*

interface GestureDetector.OnDoubleTapListener 두번 터치했을 경우의 관련 리스너
interface GestureDetector.OnGestureListener  일반적인 제스처들, 한 번 터치나 스크롤 관련 리스너 인터페이스의 모든 메서드를 반드시 구현해야 한다.
class GestureDetector.SimpleOnGestureListener  클래스를 확장하여 모든 제스처를 사용할 수 있다.
모든 메소드가 기본적인 방식으로 구현.

 */


public class ListViewIndexActivity extends ActionBarActivity  {

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_index);

        Arrays.sort(COUNTRIES);

        final ListView listview1 = (ListView) findViewById(R.id.listview01);
        listview1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,COUNTRIES));

        gestureDetector = new GestureDetector(this, gestureListener);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Toast.makeText(getBaseContext(), "onTouchEvent", Toast.LENGTH_SHORT).show();
//        return true;

//      기본적인 터치이벤트를 GestureDetector로 위임을 해야 GestureEvent가 가능하다.
        return gestureDetector.onTouchEvent(event);
    }

    GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener(){
//      터치하고 손을 대기만 해도 호출되는 메소드
        @Override
        public boolean onDown(MotionEvent e) {
//            Toast.makeText(getBaseContext(), "onDown", Toast.LENGTH_SHORT).show();
            return true;
        }
//      한 번 터치 제스처 중에 UP 이벤트가 발생했을 경우, 호출되는 메소드
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
//            Toast.makeText(getBaseContext(), "onSingleTapUp", Toast.LENGTH_SHORT).show();
            return true;
        }

//      한 번 터치했을 경우, 호출되는 메소드
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
//            Toast.makeText(getBaseContext(), "onSingleTapConfirmed", Toast.LENGTH_SHORT).show();
            return true;
        }

//      두 번 터치했을 경우 호출되는 메소드
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Toast.makeText(getBaseContext(), "onDoubleTap", Toast.LENGTH_SHORT).show();
            return true;
        }

//      두 번 터치 제스처 중에 DOWN/MOVE/UP 이벤트가 발생했을 경우, 호출되는 메소드
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
//            Toast.makeText(getBaseContext(), "onDoubleTapEvent", Toast.LENGTH_SHORT).show();
            return true;
        }

//      길게 눌렀을 경우 호출되는 메소드
        @Override
        public void onLongPress(MotionEvent e) {
//            Toast.makeText(getBaseContext(), "onLongPress", Toast.LENGTH_SHORT).show();
        }

//      터치 화면을 처음 눌렀을 경우 그러나 손가락을 떼거나 다른 곳으로 이동하기 전에 호출되는 메소드
        @Override
        public void onShowPress(MotionEvent e) {
//            Toast.makeText(getBaseContext(), "onShowPress", Toast.LENGTH_SHORT).show();
        }

//      손가락으로 화면을 누른채 손가락을 일정한 속도와 방향으로 움직인 후 떼었을 경우
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            Toast.makeText(getBaseContext(), "onScroll", Toast.LENGTH_SHORT).show();
            return true;
        }

//      손가락으로 화면을 누른 채 가속도를 붙여서 손가락을 움직인 후에 떼었을 경우
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            Toast.makeText(getBaseContext(), "onFling", Toast.LENGTH_SHORT).show();
            return true;
        }
    };

    static String[] COUNTRIES = new String[]
            {
                    "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
                    "Estonia", "Ethiopia", "Faeroe Islands", "Falkland Islands", "Fiji", "Finland",
                    "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra",
                    "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina",
                    "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan",
                    "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
                    "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia",
                    "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand",
                    "Guyana", "Haiti", "Heard Island and McDonald Islands", "Honduras", "Hong Kong", "Hungary",
                    "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica",
                    "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos",
                    "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
                    "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "North Korea", "Northern Marianas",
                    "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru",
                    "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar",
                    "French Southern Territories", "Gabon", "Georgia", "Germany", "Ghana", "Gibraltar",
                    "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau",
                    "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova",
                    "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory",
                    "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Saudi Arabia", "Senegal",
                    "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands",
                    "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Korea",
                    "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen", "Swaziland", "Sweden",
                    "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "The Bahamas",
                    "The Gambia", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey",
                    "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
                    "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan",
                    "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Virgin Islands", "Wallis and Futuna",
                    "Western Sahara", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi",
                    "Cote d'Ivoire", "Cambodia", "Cameroon", "Canada", "Cape Verde",
                    "Cayman Islands", "Central African Republic", "Chad", "Chile", "China",
                    "Reunion", "Romania", "Russia", "Rwanda", "Sqo Tome and Principe", "Saint Helena",
                    "Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon",
                    "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
                    "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
                    "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic",
                    "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
                    "Former Yugoslav Republic of Macedonia", "France", "French Guiana", "French Polynesia",
                    "Macau", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
                    "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};
}
