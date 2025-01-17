package com.vector.appupdatedemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.vanniktech.rxpermission.Permission;
import com.vanniktech.rxpermission.RealRxPermission;
import com.vector.appupdatedemo.R;
import com.vector.update_app.utils.AppUpdateUtils;
import com.vector.update_app.utils.DrawableUtil;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import rx.Observable;
import rx.functions.Action1;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawableUtil.setTextSolidTheme((Button) findViewById(R.id.btn_java), 2, 60, 0xffedd0be);
        DrawableUtil.setTextSolidTheme((Button) findViewById(R.id.btn_kotlin), 2, 60, 0xffff534d);

        ImageView im = findViewById(R.id.iv);

        im.setImageBitmap(AppUpdateUtils.drawableToBitmap(AppUpdateUtils.getAppIcon(this)));

        getPermission();

    }


    public void getPermission() {
        RealRxPermission.getInstance(this).request(WRITE_EXTERNAL_STORAGE)
                .subscribe(new SingleObserver<Permission>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Permission permission) {
                        if (permission.state() == Permission.State.GRANTED) {
                            Toast.makeText(MainActivity.this, "已授权", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "未授权", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }

    public void updateJava(View view) {
        startActivity(new Intent(this, JavaActivity.class));
    }

    public void updateKotlin(View view) {
        startActivity(new Intent(this, KotlinActivity.class));
    }
}
