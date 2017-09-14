package io.almayce.dev.icologs.view.fragment

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import io.almayce.dev.icologs.R
import io.almayce.dev.icologs.databinding.FragmentRecentBinding
import io.almayce.dev.icologs.presenter.RecentPresenter
import io.almayce.dev.icologs.view.ItemActivity
import io.almayce.dev.icologs.view.RecentView
import io.almayce.dev.icologs.view.adapter.RecentRecyclerViewAdapter

/**
 * Created by almayce on 28.08.17.
 */


class RecentFragment : MvpAppCompatFragment(), RecentView, RecentRecyclerViewAdapter.ItemClickListener {

    @InjectPresenter
    lateinit var pr: RecentPresenter
    private lateinit var bn: FragmentRecentBinding

    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        bn = DataBindingUtil.inflate<FragmentRecentBinding>(
                inflater, R.layout.fragment_recent, container, false)
        pr.init(context)
        var adapter = pr.adapter
        bn.rvContent.layoutManager = LinearLayoutManager(context)
        bn.rvContent.adapter = adapter
        adapter.setClickListener(this)

        return bn.getRoot()
    }

    override fun onItemClick(view: View, position: Int) {
        var target = pr.list.get(position)
        var intent = Intent(context, ItemActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("id", target.id)
        intent.putExtra("title", target.title)
        intent.putExtra("descript", target.descript)
        intent.putExtra("status", target.status)
        intent.putExtra("startDate", target.startDate)
        intent.putExtra("endDate", target.endDate)
        intent.putExtra("symbol", target.symbol)
        intent.putExtra("tokenPrice", target.tokenPrice)
        intent.putExtra("sum", target.sum)
        intent.putExtra("invest", target.invest)
        intent.putExtra("link", target.link)
        intent.putExtra("whitePaperLink", target.whitePaperLink)
        intent.putExtra("news", target.news)
        startActivity(intent)
    }
}