package youngjee.com.examapp01;

import android.content.Context;
import android.database.Cursor;
import android.provider.Contacts;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import youngjee.com.examapp01.dto.Contact;
import youngjee.com.examapp01.utils.HangulUtils;

public class TelNoSearchActivity extends ActionBarActivity {

    private String searchKeyword;
    private ListView mListView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel_no_search);

        EditText searchStr = (EditText) findViewById(R.id.EditText01);
        mListView = (ListView) findViewById(R.id.lvlist);

        try {
            searchStr.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void afterTextChanged(Editable s) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        searchKeyword = s.toString();
                        Log.d(getClass().getSimpleName(), searchKeyword);
                        displayList();
                    } catch (Exception e) {
                        Log.e(getClass().getSimpleName(), e.getMessage(), e);
                    }
                }
            });

//            displayList();

        } catch (Exception e){
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
        }

    }

    private void displayList() {
        List<Contact> contactList = new ArrayList<Contact>();

        String[] projection = new String[]{Contacts.People._ID, Contacts.People.NAME, Contacts.People.NUMBER};
        Cursor cursor = managedQuery(Contacts.People.CONTENT_URI, projection, null, null, null);

        while (cursor.moveToNext()) {
            addContact(contactList, cursor.getString(1), cursor.getString(2));
        }

        Log.d(getClass().getSimpleName(), "contactList.size()[" + contactList.size() + "]");

        ContactListAdapter<Contact> adapter = new ContactListAdapter<Contact>(this, R.layout.contact_list_row, contactList);
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    private void addContact(List<Contact> contactList, String name, String number) {
        if (contactList == null) {
            throw new NullPointerException("contactList 가 널");
        }

        boolean isAdd = false;

        if(searchKeyword != null && "".equals(searchKeyword.trim()) == false) {
            String iniName = HangulUtils.getHangulInitialSound(name, searchKeyword);

//            Log.d(getClass().getSimpleName(), "iniName[" + iniName + "]searchKeyword[" + searchKeyword + "]");

            if(iniName.indexOf(searchKeyword) >=0) {
                isAdd = true;
            }
        } else {
            isAdd = true;
        }

        if(isAdd){
            Log.d(getClass().getSimpleName(), "name[" + name + "]number[" + number + "]");
            contactList.add(new Contact(name,number));
        }
    }

    public class ContactListAdapter<T extends Contact> extends ArrayAdapter<T> {

        private List<T> contactList;

        public ContactListAdapter(Context context, int textViewResourceId, List<T> items) {
            super(context,textViewResourceId, items);
            contactList = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if (view == null){
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.contact_list_row, null);
            }

            T contact = contactList.get(position);

            if(contact != null) {
                TextView viewName = (TextView) view.findViewById(R.id.toptext);
                viewName.setText(contact.getName());

                TextView viewNumber = (TextView) view.findViewById(R.id.bottomtext);
                viewNumber.setText("전화번호:"+contact.getNumber());
            }

            return view;
        }
    }
}
