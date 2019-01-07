package com.example.d400demo.fragments;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.d400demo.AcceptThread;
import com.example.d400demo.CRCVerify;
import com.example.d400demo.D400Helper;
import com.example.d400demo.R;
import com.example.d400demo.utils.LogUtil;
import com.example.d400demo.utils.ToastUtil;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FirstFragment extends Fragment implements View.OnClickListener{
	
	private Context mContext;
	private BluetoothAdapter mBluetoothAdapter;
	private TextView mTvAcceptDatas;
	private TextView mTvCurrentState;
	private BluetoothSocket clientSocket;
	private ConnectThread connectThread;
	
	private int protocol = D400Helper.SPP;
	private View view;
	
	private AcceptThread acceptThread;
	private ArrayList<String> list = new ArrayList<String>();
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
            case D400Helper.ACCEPTDATAS:  // 收到蓝牙数据
                byte[] news = (byte[]) msg.obj;
                HashMap<String, Object> map = CRCVerify.bytesToMap(news);
                String newstr = null;
                String order = null;
                ToastUtil.showToast(mContext, "65收到数据：" + new String(news));
                LogUtil.e("65收到数据:\n"+new String(news));
                if (map != null) {
                    newstr = (String) map.get("datas");
                    order = (String) map.get("order");
                    ToastUtil.showToast(mContext, "79收到数据集：" + map.toString());
                    LogUtil.e("79:收到数据集:\n"+map.toString());
                } else {
                	ToastUtil.showToast(mContext, "长度或校验位错误");
                    mTvAcceptDatas.setText("erro");
                    break;
                }

                if (order.equals("02")) { // 收到条码数据
                    if (protocol == D400Helper.SPP) {
                        String string = newstr;
                        if (string.contains("0")) {
                        	String[] split = string.split("0");
                        	SpannableString spannableString = new SpannableString(split[split.length-1]);
                        	spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 0,spannableString.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        	mTvAcceptDatas.setText(spannableString);
                        	list.add(""+spannableString);
                        	LogUtil.v("split:"+split[split.length-1]);
						}else {
							mTvAcceptDatas.append("条码中没有0");
						}
                        int offset = mTvAcceptDatas.getLineCount()* mTvAcceptDatas.getLineHeight();
                        if (offset > mTvAcceptDatas.getHeight()) {
                            mTvAcceptDatas.scrollTo(0,offset - mTvAcceptDatas.getHeight());
                        }
                    } else {
                        mTvAcceptDatas.setText(newstr);
                    }
                    ToastUtil.showToast(mContext, "收到数据");
                    LogUtil.e("103:收到数据");
                } 
                break;

            case D400Helper.CONNECTFAIL:
                mTvCurrentState.setText("连接失败！");
                mTvCurrentState.setTextColor(Color.RED);
                break;
                
            case D400Helper.CONNECTSUCCESS:
            	mTvCurrentState.setTextColor(Color.GREEN);
                mTvCurrentState.setText("连接成功");
                Drawable drawable = res.getDrawable(R.drawable.boned);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                mTvGongwei.setCompoundDrawables(null, null, drawable, null);
                BluetoothDevice device = (BluetoothDevice)msg.obj;
                mTvMAC.setText("指环MAC地址：   " +device.getAddress());
                acceptThread = new AcceptThread(clientSocket, mHandler);
                acceptThread.start();
                break;
			}
		}
	};
	
	/**
     * 系统蓝牙打开、关闭都会发送广播;
     * 接收广播，蓝牙打开搜索到一个蓝牙设备就发送一次广播，直到搜索到全部蓝牙设备为止
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
    	
    	@Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {// 发现设备
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {// 搜索完毕！
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {// 搜索中...
            } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {// 状态改变
            	BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            	if (clientSocket != null && clientSocket.isConnected()) {
            		mTvCurrentState.setText("保持连接");
            		return ;
				}
                // 从intent 中获取蓝牙设备
                switch (device.getBondState()) {
                    case BluetoothDevice.BOND_BONDING:
                        mTvCurrentState.setText("正在配对...");// 正在配对...
                        mTvCurrentState.setTextColor(Color.BLACK);
                        break;
                        
                    case BluetoothDevice.BOND_BONDED:
                        mTvCurrentState.setText("完成配对"); // 完成配对
                        // 若socket打开，先关闭再连接
                        if (clientSocket != null && clientSocket.isConnected()) {
                            connectThread.cancel();
                        }
                        connectThread = new ConnectThread(device);// 连接设备
                        connectThread.start();
                        break;
                        
                    case BluetoothDevice.BOND_NONE:
                        mTvCurrentState.setText("取消配对");// 取消配对
                        break;
                }
                
            } else if (action.equals(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)) {
                int state = (Integer) intent.getExtras().get(BluetoothAdapter.EXTRA_CONNECTION_STATE);
                mTvCurrentState.setText("链接状态改变");// 链接状态改变
                switch (state) {
                    case BluetoothAdapter.STATE_CONNECTED:
                        mTvCurrentState.setText("已连接");// 已连接
                        break;
                        
                    case BluetoothAdapter.STATE_CONNECTING:
                        mTvCurrentState.setText("正在连接");// 正在连接
                        break;
                        
                    case BluetoothAdapter.STATE_DISCONNECTED:
                        mTvCurrentState.setText("已断开");
                        break;
                        
                    case BluetoothAdapter.STATE_DISCONNECTING:
                        mTvCurrentState.setText("正在断开...");
                        break;
                }
                
            } else if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                mTvCurrentState.setText("蓝牙开关状态改变");
                int state = (Integer) intent.getExtras().get(BluetoothAdapter.EXTRA_STATE);
                switch (state) {
                    case BluetoothAdapter.STATE_ON:
                        mTvCurrentState.setText("本机蓝牙已打开");
                        break;
                        
                    case BluetoothAdapter.STATE_TURNING_ON:
                        mTvCurrentState.setText("正在打开蓝牙...");
                        break;
                        
                    case BluetoothAdapter.STATE_OFF:
                        mTvCurrentState.setText("本机蓝牙已关闭");
                        break;
                        
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        mTvCurrentState.setText("正在关闭蓝牙...");
                        break;
                }
            }
        }
    };
    
	private Button mBtnLook;
	private TextView mTvMAC;
	private TextView mTvGongwei;
	private Resources res;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view = inflater.inflate(R.layout.fragment_first, container, false);
		mContext = getActivity();
        res = mContext.getResources();
     // 注册BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        mContext.registerReceiver(mReceiver, filter); // 不要忘了之后解除绑定

		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		initView();
        initBluetooth();
        initData();
	}
	
	/**
	 * 控件初始化
	 */
	private void initView() {
        mTvAcceptDatas = (TextView)view.findViewById(R.id.et_main_scanner);
        mTvAcceptDatas.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                 AlertDialog dialog = new AlertDialog.Builder(mContext)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mTvAcceptDatas.scrollTo(0,0);
                                mTvAcceptDatas.setText("");
                            }
                        })
                        .setMessage("是否清空数据？").create();
                dialog.show();
                return true;
            }
        });
        mTvCurrentState = (TextView)view.findViewById(R.id.tv_main_state2); // 连接状态
        mTvMAC = (TextView)view.findViewById(R.id.tv_main_mac); 
        mTvGongwei = (TextView)view.findViewById(R.id.tv_main_gonwei); 

        mBtnLook = (Button)view.findViewById(R.id.bt_main_look);
        mBtnLook.setOnClickListener(this);

    }
	
	/**
	 * 蓝牙开启还是关闭对状态TextView的初始化
	 */
	private void initData() {
        if (mBluetoothAdapter == null) {// 系统有蓝牙，此都不会为空
            return;
        }
        if (!mBluetoothAdapter.isEnabled()) {// 蓝牙未开启
            mTvCurrentState.setText("蓝牙已关闭");
        } else {
            mTvCurrentState.setText("蓝牙已打开");
            mBluetoothAdapter.startDiscovery();
        }
    }
	
	/**
	 * 蓝牙初始化
	 */
	private void initBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
        	mBluetoothAdapter.enable();
		}
        if (mBluetoothAdapter == null) {
            ToastUtil.showToast(mContext, "本设备不支持蓝牙");
        }
    }
	
	 /**
     * 建立连接线程类
     */
    public class ConnectThread extends Thread {
        OutputStream os = null;
        private BluetoothDevice device;

        private ConnectThread(BluetoothDevice device) { // device：蓝牙指环
        	this.device = device;
            try {
                // 获取 BluetoothSocket
                clientSocket = device.createRfcommSocketToServiceRecord(D400Helper.uuid);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        @Override
        public void run() {
            // 先取消继续搜索，以便建立链接
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
            try {
                clientSocket.connect();
                Message msg = mHandler.obtainMessage();
                msg.obj = device;
                msg.what = D400Helper.CONNECTSUCCESS;
                mHandler.sendMessage(msg); // 连接成功
            } catch (IOException e) {
                mHandler.sendEmptyMessage(D400Helper.CONNECTFAIL); // 连接失败
                try {
                    clientSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        /**
         * 关闭连接
         */
        public void cancel() {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
    public void onClick(View v) {
        switch (v.getId()) {
                
            case R.id.bt_main_look: // 查看上一条
            	String remenber = "";
            	if (list.size()<2) {
            		mTvAcceptDatas.setText(remenber);
            		return;
				}
            	for (int i = 0; i < list.size(); i++) {
					 remenber = list.get(list.size()-2);
				}
            	mTvAcceptDatas.setText(remenber);
            	break;
        }
    }
	
	 @Override
    public void onDestroy() {
    	super.onDestroy();
        mContext.unregisterReceiver(mReceiver);
        if (null != acceptThread && acceptThread.isAlive()) {
            acceptThread.cancel();
        }
        if (mBluetoothAdapter.isEnabled()) {
            // 关闭操作
        /*
         * 如果线程已经存在且正在阻塞，取消线程
		 */
            if (clientSocket != null && clientSocket.isConnected()) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mBluetoothAdapter.disable(); // 关闭蓝牙
        } 
    }
}
