package com.example.bizcard

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bizcard.ui.theme.BizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BizCardTheme {
                CreateBizCard()
            }
        }
    }
}

data class PortfolioItem(
    val thumbnails: String, val title: String, val description: String
)

@Composable
fun CreateBizCard() {
    val item1 = PortfolioItem(
        thumbnails = "portrait_woman",
        title = "Money Tracker",
        description = "Successfully launched a global money tracker app."
    )
    val item2 = PortfolioItem(
        thumbnails = "portrait_woman",
        title = "Khmer Calendar",
        description = "Successfully launched a national calendar app."
    )
    val item3 = PortfolioItem(
        thumbnails = "portrait_woman",
        title = "Thai Buddhist Calendar",
        description = "Successfully launched a Thai national calendar app."
    )
    val portfolioItems = listOf(item1, item2, item3)

    var isShowingPortfolio = remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(12.dp))
        ) {
            Column(
                modifier = Modifier.padding(top = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateProfile()
                Divider()
                CreateInformation()
                Button(onClick = {
                    isShowingPortfolio.value = !isShowingPortfolio.value
                }) {
                    Text(text = "Portfolio")
                }
                Divider()
                AnimatedVisibility(visible = isShowingPortfolio.value) {
                    if (isShowingPortfolio.value) {
                        CreatePortfolio(portfolioItems)
                    }
                }
            }
        }
    }
}

@Composable
private fun CreateProfile() {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp)
            .shadow(elevation = 15.dp, shape = CircleShape),
        shape = CircleShape,
        border = BorderStroke(width = 0.35.dp, color = Color.LightGray),
    ) {
        Image(
            painter = painterResource(id = R.drawable.portrait_woman),
            contentDescription = "Portrait of a Woman",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun CreateInformation() {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Miles P.",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(text = "iOS and Android Compose Programmer")
        Text(text = "@MilesCompose")
    }
}

@Composable
private fun CreatePortfolio(elements: List<PortfolioItem>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(elements) { element ->
            CreatePortfolioItem(element)
        }
    }
}

@Composable
private fun CreatePortfolioItem(element: PortfolioItem) {
    Row(
        modifier = Modifier.padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val context = LocalContext.current
        val drawableId = remember(element.thumbnails) {
            context.resources.getIdentifier(
                element.thumbnails, "drawable", context.packageName
            )
        }
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "Element image",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column {
            Text(text = element.title, fontWeight = FontWeight.SemiBold, maxLines = 1)
            Text(
                text = element.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BizCardTheme {
        CreateBizCard()
    }
}