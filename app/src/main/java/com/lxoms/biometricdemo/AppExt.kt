package com.lxoms.biometricdemo

import android.widget.Toast


/**
 * @date      2020/9/16
 * @author    Pengshuwen
 * @describe
 */

fun String.toast() {
    Toast.makeText(App.getContext(), this, Toast.LENGTH_SHORT).show()
}