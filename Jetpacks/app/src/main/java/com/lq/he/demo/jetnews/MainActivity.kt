package com.lq.he.demo.jetnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.material.*
import androidx.ui.tooling.preview.Preview
import com.lq.he.demo.jetnews.data.getPostsWithImagesLoadedCN
import com.lq.he.demo.jetnews.data.postsCN
import com.lq.he.demo.jetnews.ui.JetnewsApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            MaterialTheme {
//                Greeting("Android")
//            }
//        }

        postsCN = getPostsWithImagesLoadedCN(
                postsCN,
                resources
        )
        setContent {
            JetnewsApp()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Greeting("HEHE")
    }
}
