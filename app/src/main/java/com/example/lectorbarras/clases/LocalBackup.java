package com.example.lectorbarras.clases;

import android.os.Environment;
import android.widget.Toast;

import com.example.lectorbarras.MainActivity;
import com.example.lectorbarras.R;
import com.example.lectorbarras.db.Modelo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalBackup {
    private MainActivity mainActivity;
    public boolean success = false;

    public LocalBackup(MainActivity mainActivity){
        this.mainActivity  = mainActivity;
    }

    public void performBackup(final Modelo db){
        Date today = new Date();
        SimpleDateFormat format_date = new SimpleDateFormat("ddMMyy");
        SimpleDateFormat format_time = new SimpleDateFormat("hhmmss");
        String date = format_date.format(today);
        String time = format_time.format(today);

        Permission.verifyStoragePermissions(mainActivity);
        File folder  = new File(Environment.getExternalStorageDirectory()+"/documents/"+mainActivity.getResources().getString(R.string.app_name));
        try {
            success = true;
            if (!folder.exists())
                success = folder.mkdirs();
            if (success){
                db.backup(folder+"/"+"backup_"+date+time+".db");
            } else {
                Toast.makeText(mainActivity, "Unable to create Directory. Retry", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){e.printStackTrace();success=false;}
    }
}
