package com.chainaautoid.kaohe.engine;


import com.seuic.scanner.Scanner;
import com.seuic.scanner.ScannerKey;

public class ScannerEngine {

	public static void getScanner(final Scanner scanner) {
		scanner.open();
		new Thread() {
			@Override
			public void run() {
				int ret = ScannerKey.open();
				if (ret > -1) {
					while (true) {
						int ret2 = ScannerKey.getKeyEvent();
						if (ret2 > -1) {
							switch (ret2) {
							case 1:
								scanner.startScan();
								break;
							case 0:
								scanner.stopScan();
								break;
							}
						}
					}
				}
			}
		}.start();
	}

}
