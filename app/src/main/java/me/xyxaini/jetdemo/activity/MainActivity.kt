package me.xyxaini.jetdemo.activity

import kotlinx.android.synthetic.main.activity_main.*
import me.xyxaini.jetdemo.R
import me.xyxaini.jetdemo.base.BaseActivity
import me.xyxaini.jetdemo.fragment.`fun`.MainFragment
import me.xyxaini.jetdemo.fragment.search.SearchFragment


/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-19
 */
class MainActivity : BaseActivity() {

    override fun initView() {
        val pagerAdapter = RootPagerAdapter(supportFragmentManager)
        root_pager.adapter = pagerAdapter
        pagerAdapter.addPage(MainFragment())
        pagerAdapter.addPage(SearchFragment())
//        replaceFragment(R.id.root_view, MainFragment(), "main")
    }

    override fun initAction() {
    }

    override fun getLayoutResId(): Int = R.layout.activity_main


}
