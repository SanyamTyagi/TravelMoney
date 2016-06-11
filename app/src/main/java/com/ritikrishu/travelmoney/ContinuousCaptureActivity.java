package com.ritikrishu.travelmoney;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.ritikrishu.travelmoney.Model.Employee;
import com.ritikrishu.travelmoney.Model.EmployeeDataBase;
import com.ritikrishu.travelmoney.View.TicketAlertDialog;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This sample performs continuous scanning, displaying the barcode and source image whenever
 * a barcode is scanned.
 */
public class ContinuousCaptureActivity extends Activity {
    private static final String TAG = "TAG";
    private DecoratedBarcodeView barcodeView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continuous_scan);
        barcodeView = (DecoratedBarcodeView) findViewById(R.id.barcode_scanner);
        barcodeView.decodeContinuous(callback);
        barcodeView.setStatusText("");

    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }

    public void pause(View view) {
        barcodeView.pause();
    }

    public void resume(View view) {
        barcodeView.resume();
    }

    public void triggerScan(View view) {
        barcodeView.decodeSingle(callback);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }



    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null) {
                Log.d(TAG, "barcodeResult() returned: *********" );
                barcodeView.pause();
                Employee employee = Employee.getEmployeeByID(result.getText());
                if(employee != null) {
                    final TicketAlertDialog closedialog = new TicketAlertDialog(ContinuousCaptureActivity.this, ContinuousCaptureActivity.this,employee);
                    closedialog.show();
                    final Timer timer2 = new Timer();
                    timer2.schedule(new TimerTask() {
                        public void run() {
                            closedialog.dismiss();
                            timer2.cancel(); //this will cancel the timer of the system
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    barcodeView.resume();
                                }
                            });
                        }
                    }, 6000);
                }

            }

        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };
}