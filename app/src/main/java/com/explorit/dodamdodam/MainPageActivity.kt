package com.explorit.dodamdodam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.explorit.dodamdodam.databinding.ActivityMainPageBinding

private const val TAG_HOME = "HomeFragment"
private const val TAG_QUESTION = "RandomQuestionFragment"
private const val TAG_CALENDAR = "MissionCalendarFragment"
private const val TAG_MY_PAGE = "MyPageFragment"

class MainPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(TAG_HOME, HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.calendarFragment -> setFragment(TAG_CALENDAR, MissionCalendarFragment())
                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.myPageFragment-> setFragment(TAG_MY_PAGE, MyPageFragment())
            }
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if(manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout,fragment, tag)
        }

        val home = manager.findFragmentByTag(TAG_HOME)
        val question = manager.findFragmentByTag(TAG_QUESTION)
        val calendar = manager.findFragmentByTag(TAG_CALENDAR)
        val myPage = manager.findFragmentByTag(TAG_MY_PAGE)

        if (home != null){
            fragTransaction.hide(home)
        }

        if (question != null){
            fragTransaction.hide(question)
        }

        if (calendar != null) {
            fragTransaction.hide(calendar)
        }
        if (myPage != null) {
            fragTransaction.hide(myPage)
        }

        if (tag == TAG_HOME) {
            if (home!=null){
                fragTransaction.show(home)
            }
        }
        else if (tag == TAG_QUESTION) {
            if (question != null) {
                fragTransaction.show(question)
            }
        }

        else if (tag == TAG_CALENDAR) {
            if (calendar != null) {
                fragTransaction.show(calendar)
            }
        }

        else if (tag == TAG_MY_PAGE){
            if (myPage != null){
                fragTransaction.show(myPage)
            }
        }

        fragTransaction.commitAllowingStateLoss()

    }

}


