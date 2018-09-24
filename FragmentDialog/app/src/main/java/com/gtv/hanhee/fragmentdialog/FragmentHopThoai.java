package com.gtv.hanhee.fragmentdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class FragmentHopThoai extends DialogFragment {

    DeleteData deleteData;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        deleteData = (DeleteData) getActivity();


        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Xac nhan");
        dialog.setMessage("Ban co muon xoa san pham nay khong?");
        dialog.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteData.GiaTriXoa(true);

            }
        });

        dialog.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteData.GiaTriXoa(false);

            }
        });

        Dialog hopThoai = dialog.create();

        return hopThoai;
    }
}
