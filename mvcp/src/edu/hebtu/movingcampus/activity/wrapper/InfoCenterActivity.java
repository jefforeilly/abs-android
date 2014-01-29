package edu.hebtu.movingcampus.activity.wrapper;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import edu.hebtu.movingcampus.R;
import edu.hebtu.movingcampus.activity.MainActivity;
import edu.hebtu.movingcampus.activity.SearchActivity;
import edu.hebtu.movingcampus.activity.base.Observer;
import edu.hebtu.movingcampus.activity.base.PageWraper;
import edu.hebtu.movingcampus.adapter.InfoSubjectAdapter;
import edu.hebtu.movingcampus.utils.IntentUtil;
import edu.hebtu.movingcampus.utils.NetWorkHelper;
/**
 * @author hippo
 * @version 1.0
 * @created 14-Nov-2013 9:13:32 AM
 */

/**
 * 
 * @author zhangjiantian
 *
 */
public class InfoCenterActivity implements Observer, PageWraper {

	private Activity mainActivity = MainActivity.instance;
	private View content;
	private ListView  list;

	public InfoCenterActivity(View view) {
		this.content = view;
		//API disabled 
//		try{
//		MyWeather.getWeather("石家庄");
//		content.findViewById(R.id.imgbtn_today_weather).setBackgroundResource(
//		MyWeather.iconToday[0]);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		list = (ListView) (content.findViewById(R.id.lv_infoitem));

		list.setAdapter( new InfoSubjectAdapter(IPreference.getInstance(mainActivity).getTopics(), MainActivity.instance,
				R.layout.infoitem));

		bindButton();

	}

	private void bindButton() {
//		content.findViewById(R.id.imgbtn_today_weather).setOnClickListener(
//				new View.OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						Intent intent = new Intent(mainActivity,
//								MyWeather.class);
//						mainActivity.startActivity(intent);
//						Toast.makeText(mainActivity.getApplicationContext(),
//								"some text", Toast.LENGTH_LONG).show();
//					}
//				});
		content.findViewById(R.id.imageview_above_query).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						if (NetWorkHelper.isNetworkAvailable(mainActivity)) {
							IntentUtil.start_activity(mainActivity,
									SearchActivity.class,
									new BasicNameValuePair("tag", "0"));
						} else {
							Toast.makeText(
									mainActivity.getApplicationContext(),
									"网络连接失败,请检查网络", Toast.LENGTH_LONG).show();
						}
					}
				});
		// TODO
		// content.findViewById(R.id.btn_info_class).setOnClickListener(
		// new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// Intent intent = new Intent(mainActivity,
		// MainTopRightDialog.class);
		// mainActivity.startActivity(intent);
		// Toast.makeText(mainActivity.getApplicationContext(),
		// "some text", Toast.LENGTH_LONG).show();
		// }
		// });
	}

	@Override
	public void update() {
	}

	@Override
	public void onResume() {
        InfoSubjectAdapter adapter = new InfoSubjectAdapter(IPreference.getInstance(mainActivity).getTopics(), MainActivity.instance,
				R.layout.infoitem);
		list.setAdapter(adapter) ;
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
	}

}