package com.Permission.yeyu

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

//这里之所以要将PermissionX指定成单例类，是为了让PermissionX中的接口能够更加方便地被调用
object PermissionX {
    //在request()方法中需要使用的TAG标记
    private const val TAG="InvisibleFragment"

    //我们在PermissionX中定义了一个request()方法，这个方法接收一个FragmentActivity参数、一个可变长度的permissions参数列表，以及一个callback回调。其中，FragmentActivity是AppCompatActivity的父类。
    fun request(activity: FragmentActivity,vararg permissions:String,callback: PermissionCallback){
        //首先使用FragmentActivity.getSupportFragmentManager()方法获取FragmentManager的实例，用来管理fragment，注意，getFragmentManager()方法已经因为support.v4库升级Androidx的原因被废弃
        val fragmentManager=activity.supportFragmentManager
        //调用findFragmentByTag()方法来判断传入的Activity参数中是否已经包含了指定TAG的Fragment
        val existedFragment=fragmentManager.findFragmentByTag(TAG)
        val fragment=if(existedFragment!=null){//如果已经包含则直接使用该Fragment
            //注意，在添加结束后一定要调用commitNow()方法，而不能调用commit()方法，因为commit()方法并不会立即执行添加操作，因而无法保证下一行代码执行时InvisibleFragment已经被添加到Activity中了
            existedFragment as InvisibleFragment//if else语句的返回值
        }else{//否则就创建一个新的InvisibleFragment实例，并将它添加到Activity中，同时指定一个TAG
            val invisibleFragment=InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment,TAG).commitNow()
            invisibleFragment//if else语句的返回值
        }

        //有了InvisibleFragment的实例之后，接下来我们只需要调用它的requestNow()方法就能去申请运行时权限了，申请结果会自动回调到callback参数中。
        //需要注意的是，permissions参数在这里实际上是一个数组。对于数组，我们可以遍历它，可以通过下标访问，但是不可以直接将它传递给另外一个接收可变长度参数的方法。因此，这里在调用requestNow()方法时，在permissions参数的前面加上了一个*，这个符号并不是指针的意思，而是表示将一个数组转换成可变长度参数传递过去
        fragment.requestNow(callback,*permissions)
    }
}