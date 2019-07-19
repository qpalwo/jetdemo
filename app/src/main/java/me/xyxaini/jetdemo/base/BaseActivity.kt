package me.xyxaini.jetdemo.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-19
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val a = getLayoutResId()
        setContentView(getLayoutResId())
        initView()
        initAction()
    }

    protected abstract fun initView()

    protected abstract fun initAction()

    protected abstract fun getLayoutResId(): Int

    protected fun toast(message: String) {
        toast(message, Toast.LENGTH_LONG)
    }

    protected fun toast(message: String, time: Int) {
        Toast.makeText(this, message, time).show()
    }

    protected fun replaceFragment(res: Int, fragment: BaseFragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(res, fragment, tag)
            .commit()
    }
}