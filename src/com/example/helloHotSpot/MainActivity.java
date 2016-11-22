package com.example.helloHotSpot;

import java.lang.reflect.Method;

import com.example.hellowifi.R;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	//private Button startWifiButton;	
	//private WifiManager wfManager;
	//private final String TAG = "test_wifi_log";
	//private WifiConfiguration wifiConfiguration = new WifiConfiguration();
	private Button startWifiButton;
	private WifiManager wfManager;
	private final String TAG ="myLog";
	private WifiConfiguration wifiConfiguration =new WifiConfiguration();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startWifiButton = (Button)findViewById(R.id.startWifiButton);
		wfManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		startWifiButton.setText("Click to start Wifi");
		
		startWifiButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String name="mywifitest",pw="12345678";
				createAp(name,pw);
			}
		});
		
	}
	public void createAp(String ssid,String pw) {
		
		if (wfManager.isWifiEnabled()) { //如果开启了WIFI，则将它关闭。
			wfManager.setWifiEnabled(false);
			Log.d(TAG, "wifi closed");
		} 
		try {
			wifiConfiguration.SSID = ssid;
			wifiConfiguration.preSharedKey = pw;
			
			Method method1 = wfManager.getClass().getMethod("setWifiApEnabled",
					WifiConfiguration.class, boolean.class);
			
			wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
			wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
			wifiConfiguration.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
			wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
			wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
			wifiConfiguration.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
			wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
			wifiConfiguration.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
			
			Log.d(TAG, "Wifi created");
			
			Method method = wfManager.getClass().getMethod("isWifiApEnabled");
			method.setAccessible(true);
			
			method1.invoke(wfManager, wifiConfiguration,true);
		
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
