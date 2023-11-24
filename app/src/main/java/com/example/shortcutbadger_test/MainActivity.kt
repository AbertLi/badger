package com.example.shortcutbadger_test

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.NotificationUtils
import com.blankj.utilcode.util.Utils
import com.example.shortcutbadger_test.ui.theme.ShortcutBadger_TESTTheme
import me.leolin.shortcutbadger.ShortcutBadger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShortcutBadger_TESTTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.background(color = Color.White).fillMaxWidth()
    ) {
        val context = LocalContext.current
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick = {
            val isSuc = ShortcutBadger.isBadgeCounterSupported(context)
            val areAble = NotificationUtils.areNotificationsEnabled()
            Toast.makeText(context, "isSuc = $isSuc areAble = $areAble", Toast.LENGTH_SHORT).show()
        }) {
            Text("Click Is Suc")
        }

        Button(onClick = {
            NotificationUtils.notify(1) {builder->
                builder.setSmallIcon(R.mipmap.img,10000)
                val drawable = context.getDrawable(R.mipmap.img)
                builder.setLargeIcon(ConvertUtils.drawable2Bitmap(drawable))
                //builder.setBadgeIconType()
                builder.setContentTitle("Title")
                builder.setContentText("Content  ContentContent  ContentContent  ContentContent  ContentContent  ContentContent  ContentContent  ContentContent  ContentContent  Content")
            }

            ShortcutBadger.applyCount(
                context,
                10
            )
        }) {
            Text("applyCount 10")
        }

        Button(onClick = {
            ShortcutBadger.removeCount(context)
        }) {
            Text("removeCount 10")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShortcutBadger_TESTTheme {
        Greeting("Android")
    }
}