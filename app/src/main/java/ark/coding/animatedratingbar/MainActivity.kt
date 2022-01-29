package ark.coding.animatedratingbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ark.coding.animatedratingbar.ui.theme.AnimatedRatingBarTheme
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimatedRatingBarTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    Scaffold() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            var rating by remember { mutableStateOf(1f) }
            val emoji by derivedStateOf {
                when (rating) {
                    1f -> "\uD83D\uDE2D️"
                    2f -> "\uD83D\uDE22️"
                    3f -> "\uD83D\uDE1F️"
                    4f -> "\uD83D\uDE42️"
                    else -> "\uD83D\uDE01️"
                }
            }

            AnimatedContent(targetState = emoji) {
                Text(text = it, fontSize = 160.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            RatingBar(
                value = rating,
                onValueChange = {
                    if (it in 1f..5f)
                        rating = it
                },
                onRatingChanged = {},
                config = RatingBarConfig()
                    .size(48.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnimatedRatingBarTheme {
        MainScreen()
    }
}