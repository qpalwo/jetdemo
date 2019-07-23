package me.xyxaini.jetdemo.activity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import me.xyxaini.jetdemo.base.BaseFragment

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-23
 */
class RootPagerAdapter(private val mFm: FragmentManager) : FragmentPagerAdapter(mFm) {
    private val mFragment: MutableList<BaseFragment> = ArrayList()

    override fun getItem(position: Int): Fragment = mFragment[position]

    override fun getCount(): Int = mFragment.size

    fun addPage(fragment: BaseFragment) {
        mFragment.add(fragment)
        notifyDataSetChanged()
    }

}