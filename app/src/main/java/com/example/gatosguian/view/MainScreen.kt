package com.example.gatosguian.view
import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gatosguian.model.Product
import com.example.gatosguian.model.Cat
import com.example.gatosguian.navigation.ItemTabs
import kotlinx.coroutines.launch


@Composable
fun MainScreen(viewModel: GatosViewModel, navHostController: NavHostController){
    Column() {

        Box(modifier = Modifier.weight(1f)) {
            MovimientosTab(viewModel = viewModel, navHostController)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovimientosTab(viewModel: GatosViewModel, navHostController: NavHostController){
    val tabs = listOf(
        ItemTabs.tab_gatos,
        ItemTabs.tab_juguetes,
        ItemTabs.tab_veterinarios

    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { tabs.size } )
    Column {
        Tabs(tabs, pagerState)
        TabsContent(tabs, pagerState,viewModel, navHostController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(tabs: List<ItemTabs>, pagerState: PagerState) {
    var selectedTab = pagerState.currentPage
    var scope = rememberCoroutineScope()
    TabRow(selectedTabIndex = selectedTab) {
        tabs.forEachIndexed { index, itemsTab ->
            Tab(
                selected = selectedTab == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(text = itemsTab.title) },
                icon = {
                    Icon(
                        if (index == selectedTab)
                            itemsTab.iconSelected
                        else
                            itemsTab.iconUnSelected, itemsTab.title
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(tabs: List<ItemTabs>, pagerState: PagerState, viewModel: GatosViewModel, navHostController: NavHostController) {
    HorizontalPager(
        state = pagerState,
        //pageCount = tabs.size
    ) {page ->
        tabs[page].screen(viewModel, navHostController)

    }
}

@Composable
fun Gatos(viewModel: GatosViewModel, navHostController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        //Busqueda()
        ListGatos(viewModel = viewModel, navHostController)
        //Icon(Icons.Outlined.Build, "Amiibos")
        //Text(text = "Gatos")//contenido de la pagina
    }
}
@Composable
fun Opiniones(navHostController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Busqueda()
        //ListProduct(viewModel = viewModel, navHostController)
        //Icon(Icons.Outlined.Build, "Amiibos")
        Text(text = "Juguetes")//contenido de la pagina
    }
}
@Composable
fun Fotos(navHostController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Busqueda()
        //ListProduct(viewModel = viewModel, navHostController)
        //Icon(Icons.Outlined.Build, "Amiibos")
        Text(text = "Veterinarios")//contenido de la pagina
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListGatos(viewModel: GatosViewModel, navHostController: NavHostController) {

    val gatos by viewModel.filteredGatos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchGatos()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(gatos) { currentProduct ->
            Cat(cat = currentProduct)
        }

    }
}


@Composable
fun Busqueda(){
    val busqueda = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = busqueda.value,
            onValueChange = { newText -> busqueda.value = newText },
            /*label = {
                Text("Busqueda", color = Color.Gray)
            },*/
            placeholder = {
                Text(text = "Busqueda")
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp)
                )
            },
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            modifier = Modifier.padding(8.dp)

        )
    }
}

@Composable
fun Cat (cat: Cat){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        ) {
        //https://assets.coincap.io/assets/icons/btc@2x.png
        Box(modifier = Modifier.padding(horizontal = 8.dp)) {

            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .padding(8.dp)
                    .size(30.dp)
            )

        }
        Column() {
            Text(
                text = cat.name,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text =cat.origin,
                fontSize =14.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text =cat.temperament,
            fontSize = 14.sp,
            //color = Color.Green.copy(alpha = 0.9f),
            color = Color(0xFF3F51B5),
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
    }
    Divider()
}




@Preview(
    showBackground = true,
    showSystemUi = true
)

@Composable
fun MainScreenPreview(){
    var navHostController = rememberNavController()
    MainScreen(viewModel = GatosViewModel(), navHostController)
}


