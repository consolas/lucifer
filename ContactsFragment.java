package com.csipsimple.ui.contacts;
/*by Zhou*/
import java.io.InputStream;
import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
//import android.provider.ContactsContract.Contacts;
import android.support.v4.app.ListFragment; 
import android.support.v4.view.Menu;
import android.support.v4.view.MenuItem;
import android.support.v4.view.MenuItem.OnMenuItemClickListener;
import android.text.TextUtils;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
//import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;  
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;

import com.csipsimple.R;
import com.csipsimple.utils.Compatibility;

public class ContactsFragment extends ListFragment {
	
	//private ContactsAdapter mContactsAdapter;
	Context mContext = null;
	ListView mListView = null;
	private static final String[] PHONES_PROJECTION = new String[] {  
	        Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID,Phone.CONTACT_ID };
	private static final int PHONES_DISPLAY_NAME_INDEX = 0;  
     
	    /**电话号码**/  
	private static final int PHONES_NUMBER_INDEX = 1;  
	      
	    /**头像ID**/  
	private static final int PHONES_PHOTO_ID_INDEX = 2;  
	     
	    /**联系人的ID**/  
	private static final int PHONES_CONTACT_ID_INDEX = 3;  
	
	
	private ArrayList<String> mContactsName = new ArrayList<String>();  
    
    /**联系人头像**/  
    private ArrayList<String> mContactsNumber = new ArrayList<String>();  
 
    /**联系人头像**/  
    private ArrayList<Bitmap> mContactsPhonto = new ArrayList<Bitmap>();
    
    
	    
    MyListAdapter myAdapter = null;    
	    
	      
     
	@Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setHasOptionsMenu(true);
    }
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText("No phone numbers");

       
    }
	
	 
	
	 @Override
	    public void onViewCreated(View view, Bundle savedInstanceState) {
	        super.onViewCreated(view, savedInstanceState);
	       	        // Modify list view
	        mListView = this.getListView();
	        if(mContext == null ){
	        mContext = this.getActivity();  
		    //mListView = this.getListView();
		    getContacts();
		    getSIMContacts();
		    myAdapter = new MyListAdapter(mContext);  
		    setListAdapter(myAdapter); 
		    }
		    mListView.setOnItemClickListener(new OnItemClickListener() {

			    @Override
			    public void onItemClick(AdapterView<?> adapterView, View view,
				    int position, long id) {
				//调用系统方法拨打电话
				Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri
					.parse("tel:" + mContactsNumber.get(position)));
				startActivity(dialIntent);
			    }
			});
		   
	         
	 }
 

	@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        boolean showInActionBar = Compatibility.isCompatible(14)
                || Compatibility.isTabletScreen(getActivity());
        int ifRoomIfSplit = showInActionBar ? MenuItem.SHOW_AS_ACTION_IF_ROOM
                : MenuItem.SHOW_AS_ACTION_NEVER;
         
        MenuItem addMenu = menu.add("通讯录");
        addMenu.setIcon(R.drawable.ic_button_contacts).setShowAsAction(ifRoomIfSplit);
        addMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
            	 addContacts();
            	//sysContacts();
                return true;
            }
        }); 
    }
        
	 
	 /*从手机上获取通讯录*/
	public void getContacts(){	
		ContentResolver resolver = mContext.getContentResolver();

		// 获取手机联系人
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,PHONES_PROJECTION, null, null, null);


		if (phoneCursor != null) {
		    while (phoneCursor.moveToNext()) {

			//得到手机号码
			String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
			//当手机号码为空的或者为空字段 跳过当前循环
			if (TextUtils.isEmpty(phoneNumber))
			    continue;
			//得到联系人名称
			String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);			
			//得到联系人ID
			Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);
			//得到联系人头像ID
			Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);			
			//得到联系人头像Bitamp
			Bitmap contactPhoto = null;
			//photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的
			if(photoid > 0 ) {
			    Uri uri =ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,contactid);
			    InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri);
			    contactPhoto = BitmapFactory.decodeStream(input);
			}else {
			    contactPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.ic_button_contacts);
			}
			
			mContactsName.add(contactName);
			mContactsNumber.add(phoneNumber);
			mContactsPhonto.add(contactPhoto);
		    }

		    phoneCursor.close();
		}
	}
	private void getSIMContacts() {
		ContentResolver resolver = mContext.getContentResolver();
		// 获取Sims卡联系人
		Uri uri = Uri.parse("content://icc/adn");
		Cursor phoneCursor = resolver.query(uri, PHONES_PROJECTION, null, null,
			null);
		if (phoneCursor != null) {
		    while (phoneCursor.moveToNext()) {

			// 得到手机号码
			String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
			// 当手机号码为空的或者为空字段 跳过当前循环
			if (TextUtils.isEmpty(phoneNumber))
			    continue;
			// 得到联系人名称
			String contactName = phoneCursor
				.getString(PHONES_DISPLAY_NAME_INDEX);

			//Sim卡中没有联系人头像
			mContactsName.add(contactName);
			mContactsNumber.add(phoneNumber);
		    }

		    phoneCursor.close();
		}
	}
	/*private void sysContacts(){
		Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(ContactsContract.Contacts.CONTENT_URI);
        startActivity(intent);
		
	} */
	private void selContacts(){
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("vnd.android.cursor.item/phone");
		startActivity(intent);
	}
	private void addContacts(){
		Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.dir/person");
        intent.setType("vnd.android.cursor.dir/contact");
        intent.setType("vnd.android.cursor.dir/raw_contact");
        startActivity(intent);
	}
	class MyListAdapter extends BaseAdapter {
		public MyListAdapter(Context context) {
		    mContext = context;
		}

		public int getCount() {
		    //设置绘制数量
		    return mContactsName.size();
		}

		public boolean areAllItemsEnabled() {
		    return false;
		}

		public Object getItem(int position) {
		    return position;
		}

		public long getItemId(int position) {
		    return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
		    ImageView image = null;
		    TextView title = null;
		    TextView text = null;
		    //ImageButton	imageBtn = null;
		    if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
				R.layout.contacts_list_item, null);
			image = (ImageView) convertView.findViewById(R.id.contacts_image);
			title = (TextView) convertView.findViewById(R.id.contacts_name);
			text = (TextView) convertView.findViewById(R.id.contacts_number);
			//imageBtn = (ImageButton) convertView.findViewById(R.id.call);
			
		    }
		    //绘制联系人名称
		    title.setText(mContactsName.get(position));
		    //绘制联系人号码
		    text.setText(mContactsNumber.get(position));
		    //绘制联系人头像
		    image.setImageBitmap(mContactsPhonto.get(position));
		   
		    return convertView;
		}

	}
	
	
}
 
 
