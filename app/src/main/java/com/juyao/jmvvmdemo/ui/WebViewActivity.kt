package com.juyao.jmvvmdemo.ui

import android.graphics.PixelFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.juyao.jmvvm.mvvm.JActivity
import com.juyao.jmvvmdemo.R
import com.juyao.jmvvmdemo.databinding.ActivityWebViewBinding
import com.juyao.jmvvmdemo.viewmodel.WebViewViewModel
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient


class WebViewActivity : JActivity<WebViewViewModel>() {
    val bd by lazy {
        jBinding as ActivityWebViewBinding
    }
    override fun initData(savedInstanceState: Bundle?) {
        window.setFormat(PixelFormat.TRANSLUCENT)
        bd.toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        bd.toolbar.setNavigationIcon(R.drawable.ic_action_back)
        bd.toolbar.setNavigationOnClickListener {
            if(bd.webview.canGoBack()){
                bd.webview.goBack()
            }else{
                finish()
            }

        }
        val url=intent.getStringExtra("url")
        Log.i(TAG,"加载地址：$url")
        bd.webview.loadUrl(url)
        bd.webview.webViewClient=object: WebViewClient() {
            override fun shouldOverrideUrlLoading(p0: WebView, p1: String?): Boolean {
                p0.loadUrl(p1)
                return true
            }
        }
        bd.webview.webChromeClient=object:WebChromeClient(){
            override fun onProgressChanged(p0: WebView?, p1: Int) {
                super.onProgressChanged(p0, p1)
                if(p1<100){
                    bd.progressbar.visibility= View.VISIBLE
                    bd.progressbar.progress=p1
                }else {
                    bd.progressbar.visibility= View.GONE
                }

            }

            override fun onReceivedTitle(p0: WebView?, p1: String?) {
                super.onReceivedTitle(p0, p1)
                bd.toolbar.title=p1
            }
        }


    }

    override fun onResume() {
        super.onResume()
        bd.webview.settings.javaScriptEnabled=true
    }

    override fun getViewBinding(): ViewBinding? =ActivityWebViewBinding.inflate(layoutInflater)

    override fun getViewModel(): WebViewViewModel=ViewModelProvider(this).get(WebViewViewModel::class.java)

}
