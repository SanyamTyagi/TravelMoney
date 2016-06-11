package com.ritikrishu.travelmoney.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.ritikrishu.travelmoney.Model.Employee;
import com.ritikrishu.travelmoney.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ritikrishu on 10/06/16.
 */
public class TicketAlertDialog extends AlertDialog {

    private ImageView mTicket1;
    private ImageView mTicket2;
    private ImageView mTicket3;
    private TextView mNameorTripCost;
    private TextView mRemainingBalance;
    private TextView mWelcomeorTripCostText;
    private TextView mPesoTop;
    private TextView mPesoBottom;
    private TextView mRemainingBalanceText;
    private Employee mEmployee;
    private Activity mActivity;
    private Typeface BOLD;


    public TicketAlertDialog(Context context, Activity activity, Employee employee) {
        super(context);
        mEmployee = employee;
        mActivity = activity;
        BOLD = Typeface.createFromAsset(context.getAssets(), "font/ProximaNova-Bold.otf");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_alert_dialogue);
        mNameorTripCost = (TextView) findViewById(R.id.name_or_tripcost);
        mRemainingBalance = (TextView) findViewById(R.id.remaining_balance);
        mWelcomeorTripCostText = (TextView) findViewById(R.id.welcome_or_tripcost_text);
        mPesoTop = (TextView) findViewById(R.id.peso_text_top);
        mPesoBottom = (TextView) findViewById(R.id.peso_text_bottom);
        mRemainingBalanceText = (TextView) findViewById(R.id.remaining_balance_text);
        mTicket1 = (ImageView) findViewById(R.id.ticket_image1);
        mTicket2 = (ImageView) findViewById(R.id.ticket_image2);
        mTicket3 = (ImageView) findViewById(R.id.ticket_image3);
        setFonts();
        if(mEmployee.isCheckedIn() && System.currentTimeMillis() > (mEmployee.getCheckInTime()+30000)){
            Employee.onCheckOut(mEmployee);
            setUpCheckOutUI();
            rollOutTickets();
        }
        else if(mEmployee.isCheckedIn() && System.currentTimeMillis() < (mEmployee.getCheckInTime() + 30000)){
            hideAllViews();
            mNameorTripCost.setText(R.string.already_checkedin);
            YoYo.with(Techniques.Shake).duration(2000).playOn(mNameorTripCost);
            mRemainingBalance.setText(R.string.wait_30_seconds);
        }
        else{
            hideTickets();
            Employee.onCheckIn(mEmployee);
            setUpCheckInUI();
            rollInTickets();
        }

    }


    private void setUpCheckInUI() {
        mWelcomeorTripCostText.setText(R.string.welcome);
        mNameorTripCost.setText(mEmployee.getName());
        mRemainingBalance.setText(mEmployee.getFormattedRemainingBalance());
    }

    private void setUpCheckOutUI() {
        mWelcomeorTripCostText.setText(R.string.trip_cost);
        mNameorTripCost.setText(Employee.getTripCost(mEmployee));
        mRemainingBalance.setText(mEmployee.getFormattedRemainingBalance());
    }

    private void rollOutTickets(){
        YoYo.with(Techniques.RollOut).duration(1000).playOn(mTicket1);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        YoYo.with(Techniques.RollOut).duration(1000).playOn(mTicket2);
                    }
                });
                this.cancel();
            }
        }, 1000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        YoYo.with(Techniques.RollOut).duration(1000).playOn(mTicket3);
                    }
                });
                this.cancel();
            }
        }, 2000);
    }

    private void rollInTickets(){
        mTicket1.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.RollIn).duration(1000).playOn(mTicket1);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTicket2.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.RollIn).duration(1000).playOn(mTicket2);
                    }
                });
                this.cancel();
            }
        }, 1000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTicket3.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.RollIn).duration(1000).playOn(mTicket3);
                    }
                });
                this.cancel();
            }
        }, 2000);
    }

    public void hideTickets(){
        mTicket1.setVisibility(View.GONE);
        mTicket2.setVisibility(View.GONE);
        mTicket3.setVisibility(View.GONE);
        findViewById(R.id.peso_text_top).setVisibility(View.GONE);
    }

    private void hideAllViews() {
        mWelcomeorTripCostText.setVisibility(View.GONE);
        mPesoBottom.setVisibility(View.GONE);
        mPesoTop.setVisibility(View.GONE);
        mRemainingBalanceText.setVisibility(View.GONE);
    }

    public void setFonts(){
        mNameorTripCost.setTypeface(BOLD);
        mRemainingBalance.setTypeface(BOLD);
    }
}
