package me.xyxaini.jetdemo.activity

import android.os.Bundle
import android.os.PersistableBundle
import me.xyxaini.jetdemo.R
import me.xyxaini.jetdemo.base.BaseActivity
import me.xyxaini.jetdemo.fragment.MainFragment


/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-19
 */
class MainActivity : BaseActivity() {

    override fun initView() {
        replaceFragment(R.id.root_view, MainFragment(), "main")
    }

    override fun initAction() {
    }

    override fun getLayoutResId(): Int = R.layout.activity_main


}
