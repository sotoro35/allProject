package com.hsr2024.ex74admob

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // admob sdk 초기화
        thread {
            MobileAds.initialize(this){
                Toast.makeText(this, "애드몹 광고 초기화 완료", Toast.LENGTH_SHORT).show()
            }
        }

        // admob 광고 형식
        // 1. 배너광고
        val adView: AdView = AdView(this)
        adView.setAdSize(AdSize.BANNER)
        adView.adUnitId= "ca-app-pub-2714340394673707/9718818479"
        // admob 웹 사이트에 등록한 앱에서 발급한 배너광고단위 ID 값을 설정

        // 실제 광고를 불러오는 요청객체
        val adRequest: AdRequest = AdRequest.Builder().build()

        adView.loadAd(adRequest) // 광고요청

        // 레이아웃에 AdView 추가하기
        findViewById<FrameLayout>(R.id.adview_container).addView(adView)

        // 2. 전면광고 보여주기
        findViewById<Button>(R.id.btn).setOnClickListener {
            InterstitialAd.load(this,"ca-app-pub-2714340394673707/8545337605",adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(p0: InterstitialAd) {
                        super.onAdLoaded(p0)
                        Toast.makeText(this@MainActivity, "광고로드 성공", Toast.LENGTH_SHORT).show()

                        p0.show(this@MainActivity)
                    }

                    override fun onAdFailedToLoad(p0: LoadAdError) {
                        super.onAdFailedToLoad(p0)
                        Toast.makeText(this@MainActivity, "광고로드 실패", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        // 3. 보상형 광고 보여주기
        findViewById<Button>(R.id.btn2).setOnClickListener {
            RewardedAd.load(this,"ca-app-pub-2714340394673707/9742869205",adRequest,
                object : RewardedAdLoadCallback() {
                    override fun onAdLoaded(p0: RewardedAd) {
                        super.onAdLoaded(p0)
                        Toast.makeText(this@MainActivity, "보상형 로드 성공", Toast.LENGTH_SHORT).show()

                        // 보상형 광고 보이기
                        p0.show(this@MainActivity, object : OnUserEarnedRewardListener{
                            override fun onUserEarnedReward(p0: RewardItem) {
                                var type:String = p0.type // 보상 아이템 종류
                                var count: Int = p0.amount // 보상 개수
                                AlertDialog.Builder(this@MainActivity).setMessage(type+"."+count).create().show()
                            }

                        })
                    }

                    override fun onAdFailedToLoad(p0: LoadAdError) {
                        super.onAdFailedToLoad(p0)
                        Toast.makeText(this@MainActivity, "보상형 로드 실패", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}