package com.arinal.made.ui.settingbahasa

import android.os.Bundle
import com.arinal.made.R
import com.arinal.made.ui.base.BaseActivity
import com.arinal.made.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_setting_bahasa.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import java.util.*

class SettingBahasaActivity : BaseActivity() {

    private var goHome = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_bahasa)
        title = getString(R.string.title_setting_bahasa)
        if (getLang() == "en") cbEnglish.isChecked = true else cbBahasa.isChecked = true
        if (intent.getBooleanExtra("afterSuccess", false)){
            showSnackbar(getString(R.string.msg_sukses_set_bahasa))
            goHome = true
        }
        cbBahasa.setOnCheckedChangeListener { _, b -> if (b) localize(Locale("in")) }
        cbEnglish.setOnCheckedChangeListener { _, b -> if (b) localize(Locale("en")) }
    }

    private fun localize(locale: Locale) {
        setLocale(locale)
        startActivity<SettingBahasaActivity>("afterSuccess" to true)
        finish()
    }

    override fun finish() {
        if (goHome) startActivity(intentFor<HomeActivity>().clearTop())
        super.finish()
    }
}
