package com.kahuanbao.com.v.fragment;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import com.kahuanbao.com.R;
import com.kahuanbao.com.view.DialogPress;

public class BaseFragment extends Fragment {

    private ProgressDialog progressDialog;
    public void showProgress(String message, boolean cancelable) {
        try {
            if (progressDialog == null) {
                progressDialog =new DialogPress(getActivity(), R.style.CustomDialog);
                progressDialog.setMessage(message);
                progressDialog.setCancelable(cancelable);
                progressDialog.show();
            } else {
                progressDialog.setMessage(message);
                progressDialog.setCancelable(cancelable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideProgress() {
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
                progressDialog = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
