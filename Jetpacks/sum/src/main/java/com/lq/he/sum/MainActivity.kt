package com.lq.he.sum

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.startup.AppInitializer
import com.lq.he.sum.databinding.ActivityMainBinding
import com.lq.he.sum.startup.Sdk1
import com.lq.he.sum.startup.Sdk1Initializer
import com.lq.he.sum.startup.Sdk2Initializer


class MainActivity : AppCompatActivity() {

    /*
    1.lazy{} 只能用在val类型, lateinit 只能用在var类型 如 ：
        val name: String by lazy { "sherlbon" }
        lateinit var adapter: MyAdapter
    2.lateinit不能用在可空的属性上和java的基本类型上 如：
        lateinit var age: Int  //会报错
    3.lateinit可以在任何位置初始化并且可以初始化多次。
        而lazy在第一次被调用时就被初始化，想要被改变只能重新定义

    4.lateinit 有支持（反向）域（Backing Fields）
     */

    /** 这是一个普通的变量，默认都是private， 只是由lateinit修饰,对于非空变量延迟初始化 */
    lateinit var drawerLayout: DrawerLayout

    /**
     * 普通方法：
     * param 有1个参数 不为空
     * return 没有返回值
     * override 必须写
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        AppInitializer.getInstance(applicationContext)
            .initializeComponent(Sdk1Initializer::class.java)

        Sdk1.printApplicationName()
    }
}
