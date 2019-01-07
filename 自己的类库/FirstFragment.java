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
            case D400Helper.ACCEPTDATAS:  // �յ���������
                byte[] news = (byte[]) msg.obj;
                HashMap<String, Object> map = CRCVerify.bytesToMap(news);
                String newstr = null;
                String order = null;
                ToastUtil.showToast(mContext, "65�յ����ݣ�" + new String(news));
                LogUtil.e("65�յ�����:\n"+new String(news));
                if (map != null) {
                    newstr = (String) map.get("datas");
                    order = (String) map.get("order");
                    ToastUtil.showToast(mContext, "79�յ����ݼ���" + map.toString());
                    LogUtil.e("79:�յ����ݼ�:\n"+map.toString());
                } else {
                	ToastUtil.showToast(mContext, "���Ȼ�У��λ����");
                    mTvAcceptDatas.setText("erro");
                    break;
                }

                if (order.equals("02")) { // �յ���������
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
							mTvAcceptDatas.append("������û��0");
						}
                        int offset = mTvAcceptDatas.getLineCount()* mTvAcceptDatas.getLineHeight();
                        if (offset > mTvAcceptDatas.getHeight()) {
                            mTvAcceptDatas.scrollTo(0,offset - mTvAcceptDatas.getHeight());
                        }
                    } else {
                        mTvAcceptDatas.setText(newstr);
                    }
                    ToastUtil.showToast(mContext, "�յ�����");
                    LogUtil.e("103:�յ�����");
                } 
                break;

            case D400Helper.CONNECTFAIL:
                mTvCurrentState.setText("����ʧ�ܣ�");
                mTvCurrentState.setTextColor(Color.RED);
                break;
                
            case D400Helper.CONNECTSUCCESS:
            	mTvCurrentState.setTextColor(Color.GREEN);
                mTvCurrentState.setText("���ӳɹ�");
                Drawable drawable = res.getDrawable(R.drawable.boned);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                mTvGongwei.setCompoundDrawables(null, null, drawable, null);
                BluetoothDevice device = (BluetoothDevice)msg.obj;
                mTvMAC.setText("ָ��MAC��ַ��   " +device.getAddress());
                acceptThread = new AcceptThread(clientSocket, mHandler);
                acceptThread.start();
                break;
			}
		}
	};
	
	/**
     * ϵͳ�����򿪡��رն��ᷢ�͹㲥;
     * ���չ㲥��������������һ�������豸�ͷ���һ�ι㲥��ֱ��������ȫ�������豸Ϊֹ
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
    	
    	@Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothDevice.ACTION_FOUND)) {// �����豸
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {// ������ϣ�
            } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {// ������...
            } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {// ״̬�ı�
            	BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            	if (clientSocket != null && clientSocket.isConnected()) {
            		mTvCurrentState.setText("��������");
            		return ;
				}
                // ��intent �л�ȡ�����豸
                switch (device.getBondState()) {
                    case BluetoothDevice.BOND_BONDING:
                        mTvCurrentState.setText("�������...");// �������...
                        mTvCurrentState.setTextColor(Color.BLACK);
                        break;
                        
                    case BluetoothDevice.BOND_BONDED:
                        mTvCurrentState.setText("������"); // ������
                        // ��socket�򿪣��ȹر�������
                        if (clientSocket != null && clientSocket.isConnected()) {
                            connectThread.cancel();
                        }
                        connectThread = new ConnectThread(device);// �����豸
                        connectThread.start();
                        break;
                        
                    case BluetoothDevice.BOND_NONE:
                        mTvCurrentState.setText("ȡ�����");// ȡ�����
                        break;
                }
                
            } else if (action.equals(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)) {
                int state = (Integer) intent.getExtras().get(BluetoothAdapter.EXTRA_CONNECTION_STATE);
                mTvCurrentState.setText("����״̬�ı�");// ����״̬�ı�
                switch (state) {
                    case BluetoothAdapter.STATE_CONNECTED:
                        mTvCurrentState.setText("������");// ������
                        break;
                        
                    case BluetoothAdapter.STATE_CONNECTING:
                        mTvCurrentState.setText("��������");// ��������
                        break;
                        
                    case BluetoothAdapter.STATE_DISCONNECTED:
                        mTvCurrentState.setText("�ѶϿ�");
                        break;
                        
                    case BluetoothAdapter.STATE_DISCONNECTING:
                        mTvCurrentState.setText("���ڶϿ�...");
                        break;
                }
                
            } else if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                mTvCurrentState.setText("��������״̬�ı�");
                int state = (Integer) intent.getExtras().get(BluetoothAdapter.EXTRA_STATE);
                switch (state) {
                    case BluetoothAdapter.STATE_ON:
                        mTvCurrentState.setText("���������Ѵ�");
                        break;
                        
                    case BluetoothAdapter.STATE_TURNING_ON:
                        mTvCurrentState.setText("���ڴ�����...");
                        break;
                        
                    case BluetoothAdapter.STATE_OFF:
                        mTvCurrentState.setText("���������ѹر�");
                        break;
                        
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        mTvCurrentState.setText("���ڹر�����...");
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
     // ע��BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        mContext.registerReceiver(mReceiver, filter); // ��Ҫ����֮������

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
	 * �ؼ���ʼ��
	 */
	private void initView() {
        mTvAcceptDatas = (TextView)view.findViewById(R.id.et_main_scanner);
        mTvAcceptDatas.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                 AlertDialog dialog = new AlertDialog.Builder(mContext)
                        .setNegativeButton("ȡ��", null)
                        .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mTvAcceptDatas.scrollTo(0,0);
                                mTvAcceptDatas.setText("");
                            }
                        })
                        .setMessage("�Ƿ�������ݣ�").create();
                dialog.show();
                return true;
            }
        });
        mTvCurrentState = (TextView)view.findViewById(R.id.tv_main_state2); // ����״̬
        mTvMAC = (TextView)view.findViewById(R.id.tv_main_mac); 
        mTvGongwei = (TextView)view.findViewById(R.id.tv_main_gonwei); 

        mBtnLook = (Button)view.findViewById(R.id.bt_main_look);
        mBtnLook.setOnClickListener(this);

    }
	
	/**
	 * �����������ǹرն�״̬TextView�ĳ�ʼ��
	 */
	private void initData() {
        if (mBluetoothAdapter == null) {// ϵͳ���������˶�����Ϊ��
            return;
        }
        if (!mBluetoothAdapter.isEnabled()) {// ����δ����
            mTvCurrentState.setText("�����ѹر�");
        } else {
            mTvCurrentState.setText("�����Ѵ�");
            mBluetoothAdapter.startDiscovery();
        }
    }
	
	/**
	 * ������ʼ��
	 */
	private void initBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
        	mBluetoothAdapter.enable();
		}
        if (mBluetoothAdapter == null) {
            ToastUtil.showToast(mContext, "���豸��֧������");
        }
    }
	
	 /**
     * ���������߳���
     */
    public class ConnectThread extends Thread {
        OutputStream os = null;
        private BluetoothDevice device;

        private ConnectThread(BluetoothDevice device) { // device������ָ��
        	this.device = device;
            try {
                // ��ȡ BluetoothSocket
                clientSocket = device.createRfcommSocketToServiceRecord(D400Helper.uuid);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        @Override
        public void run() {
            // ��ȡ�������������Ա㽨������
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
            try {
                clientSocket.connect();
                Message msg = mHandler.obtainMessage();
                msg.obj = device;
                msg.what = D400Helper.CONNECTSUCCESS;
                mHandler.sendMessage(msg); // ���ӳɹ�
            } catch (IOException e) {
                mHandler.sendEmptyMessage(D400Helper.CONNECTFAIL); // ����ʧ��
                try {
                    clientSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        /**
         * �ر�����
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
                
            case R.id.bt_main_look: // �鿴��һ��
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
            // �رղ���
        /*
         * ����߳��Ѿ�����������������ȡ���߳�
		 */
            if (clientSocket != null && clientSocket.isConnected()) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mBluetoothAdapter.disable(); // �ر�����
        } 
    }
}
