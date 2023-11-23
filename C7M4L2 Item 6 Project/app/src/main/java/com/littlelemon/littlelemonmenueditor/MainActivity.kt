package com.littlelemon.littlelemonmenueditor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign.Companion.Right
import androidx.compose.ui.unit.dp
import com.littlelemon.littlelemonmenueditor.ui.theme.LittleLemonMenuEditorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonMenuEditorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val menuItems by remember {
                        mutableStateOf(emptyList<MenuItem>())
                    }
                    Column {
                        var dishName by remember { mutableStateOf("") }
                        var priceInput by remember { mutableStateOf("") }
                        Row(modifier = Modifier.padding(16.dp)) {
                            TextField(
                                modifier = Modifier.weight(.6f),
                                value = dishName,
                                onValueChange = { value -> dishName = value },
                                label = { Text("Dish name") }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            TextField(
                                modifier = Modifier.weight(.4f),
                                value = priceInput,
                                onValueChange = { value -> priceInput = value },
                                label = { Text("Price") }
                            )
                        }
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            onClick = {
                                dishName = ""
                                priceInput = ""
                            }
                        ) {
                            Text("Add dish")
                        }
                        ItemsList(menuItems)
                    }
                }
            }
        }
    }

    @Composable
    private fun ItemsList(menuItems: List<MenuItem>) {
        if (menuItems.isEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                text = "The menu is empty"
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
            ) {
                items(
                    items = menuItems,
                    itemContent = { menuItem ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(menuItem.name)
                            Text(
                                modifier = Modifier.weight(1f),
                                textAlign = Right,
                                text = "%.2f".format(menuItem.price)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Button(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.delete_icon),
                                    contentDescription = "Delete"
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}
