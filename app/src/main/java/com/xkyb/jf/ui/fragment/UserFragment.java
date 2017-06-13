package com.xkyb.jf.ui.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.xkyb.jf.R;
import com.xkyb.jf.config.Config;
import com.xkyb.jf.model.OrderAllDataModel;
import com.xkyb.jf.model.OrderDataModel;
import com.xkyb.jf.ui.activity.DetailQueryActivity;
import com.xkyb.jf.ui.activity.RealNameActivity;
import com.xkyb.jf.ui.activity.RechargeActivity;
import com.xkyb.jf.ui.activity.SecuritySetActivity;
import com.xkyb.jf.ui.activity.SetActivity;
import com.xkyb.jf.ui.activity.WithdrawalsActivity;
import com.xkyb.jf.utils.BaseUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * author：Anumbrella
 * Date：16/5/24 下午8:04
 */
public class UserFragment extends Fragment {

    private Context mContext;

    private int uid;

    @BindView(R.id.user_detail)
    TextView userName;

    @BindView(R.id.user_detail_icon)
    SimpleDraweeView userDetailIcon;

    @BindView(R.id.recharge)
    TextView recharge;

    @BindView(R.id.withdrawals)
    TextView withdrawals;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        uid = BaseUtils.readLocalUser(mContext).getUid();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        // getOrderData();
        if (!BaseUtils.readLocalUser(mContext).isLogin()) {
            userName.setVisibility(View.VISIBLE);
        } else {
            userName.setVisibility(View.VISIBLE);
        }

        return view;
    }

/*    private void getOrderData() {
        if (uid > 0) {
            OrderAllDataModel.getOrderDataFromNet(new Subscriber<List<OrderDataModel>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(List<OrderDataModel> orderDataModels) {
                    int paySum = 0, deliverSum = 0, accessSum = 0;
                    for (int i = 0; i < orderDataModels.size(); i++) {
                        String result = BaseUtils.transformState(orderDataModels.get(i).getIsPay(), orderDataModels.get(i).getIsDeliver(), orderDataModels.get(i).getIsComment());
                        if (result.equals("待付款")) {
                            paySum++;
                        } else if (result.equals("待发货")) {
                            deliverSum++;
                        } else if (result.equals("待评价")) {
                            accessSum++;
                        }

                    }
                    if (paySum == 0) {
                        pay_oder_num.setVisibility(View.GONE);
                        pay_layout.setClickable(false);
                    } else {
                        pay_layout.setClickable(true);
                        pay_oder_num.setVisibility(View.VISIBLE);
                        pay_oder_num.setText(Config.numberText[paySum - 1]);
                    }

                    if (deliverSum == 0) {
                        deliver_layout.setClickable(false);
                        deliver_order_num.setVisibility(View.GONE);
                    } else {
                        deliver_layout.setClickable(true);
                        deliver_order_num.setVisibility(View.VISIBLE);
                        deliver_order_num.setText(Config.numberText[deliverSum - 1]);
                    }

                    if (accessSum == 0) {
                        comment_layout.setClickable(false);
                        comment_order_num.setVisibility(View.GONE);
                    } else {
                        comment_layout.setClickable(true);
                        comment_order_num.setVisibility(View.VISIBLE);
                        comment_order_num.setText(Config.numberText[accessSum - 1]);
                    }
                }
            }, String.valueOf(uid));
        }

    }*/

    private void updateUserIcon() {
        String img = BaseUtils.readLocalUser(mContext).getUserImg();
        if (!img.equals("null")) {
            userDetailIcon.setImageURI(Uri.parse(img));
        }
    }

    private void updateUserName() {
        String name = BaseUtils.readLocalUser(mContext).getUserName();
        if (!name.equals("null")) {
            userName.setText(name);
        }
    }

    @Override
    public void onResume() {
/*        getOrderData();
        updateUserIcon();
        updateUserName();
        updateUserId();
        if (uid <= 0) {
            pay_oder_num.setVisibility(View.GONE);
            pay_layout.setClickable(false);
            deliver_layout.setClickable(false);
            deliver_order_num.setVisibility(View.GONE);
            comment_layout.setClickable(false);
            comment_order_num.setVisibility(View.GONE);
        }*/
        super.onResume();
    }

    private void updateUserId() {
        uid = BaseUtils.readLocalUser(mContext).getUid();
    }

    @OnClick({R.id.user_detail_icon, R.id.recharge, R.id.withdrawals,R.id.security_set,R.id.realName,R.id.consumer_inquiry})
    public void onClick(View view) {
        // BaseUtils.checkLogin(getActivity());
       // if (BaseUtils.checkLogin(getActivity())) {
            switch (view.getId()) {
                case R.id.user_detail_icon:
                    String img = BaseUtils.readLocalUser(mContext).getUserImg();
                    if (!img.equals("null")) {
                        showDialogUserIcon(img);
                    } else {
                        Toast.makeText(mContext, "请上传头像", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.recharge:
                    Intent rechargeIntent = new Intent();
                    rechargeIntent.setClass(getActivity(), RechargeActivity.class);
                    startActivity(rechargeIntent);
                    break;
                case R.id.withdrawals:
                    Intent setIntent = new Intent();
                    setIntent.setClass(getActivity(), WithdrawalsActivity.class);
                    startActivity(setIntent);
                    break;
                case R.id.realName:
                    Intent realNameIntent = new Intent();
                    realNameIntent.setClass(getActivity(), RealNameActivity.class);
                    startActivity(realNameIntent);
                    break;
                case R.id.security_set:
                    Intent securitySetIntent = new Intent();
                    securitySetIntent.setClass(getActivity(), SecuritySetActivity.class);
                    startActivity(securitySetIntent);
                    break;
                case R.id.consumer_inquiry:
                    Intent detailQueryIntent = new Intent();
                    detailQueryIntent.setClass(getActivity(), DetailQueryActivity.class);
                    startActivity(detailQueryIntent);
                    break;
            }
        //}
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showDialogUserIcon(String img) {
        View viewLayout = LayoutInflater.from(mContext).inflate(R.layout.show_dialog_user_icon, null);
        SimpleDraweeView userImg = (SimpleDraweeView) viewLayout.findViewById(R.id.show_dialog_user_icon);
        userImg.setImageURI(Uri.parse(img));
        Holder holder = new ViewHolder(viewLayout);
        OnClickListener clickListener = new OnClickListener() {

            @Override
            public void onClick(DialogPlus dialog, View view) {
                dialog.dismiss();

            }
        };
        DialogPlus dialogPlus = DialogPlus.newDialog(mContext)
                .setContentHolder(holder)
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .setOnClickListener(clickListener)
                .create();
        dialogPlus.show();
    }
}