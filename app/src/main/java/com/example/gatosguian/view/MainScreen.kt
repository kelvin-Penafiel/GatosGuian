package com.example.gatosguian.view
import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import coil.compose.rememberImagePainter
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gatosguian.R
import com.example.gatosguian.model.Product
import com.example.gatosguian.model.Cat
import com.example.gatosguian.model.Text
import com.example.gatosguian.model.Imagen
import com.example.gatosguian.navigation.ItemTabs
import com.example.gatosguian.navigation.TopBar
import kotlinx.coroutines.launch


@Composable
fun MainScreen(viewModel: GatosViewModel,viewModelText: TextViewModel,viewModelImage: ImagenViewModel, navHostController: NavHostController){
    Column() {

       // TopBar(texto = "BlackCat", navHostController = navHostController)
        Scaffold(
            topBar = {
                TopBar(texto = "BlackCat", navHostController = navHostController)

            },

            bottomBar = {
                // Agregar una BottomAppBar
                BottomAppBar(
                    containerColor = Color.DarkGray
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = { navHostController.navigate("main")  },
                            modifier = Modifier.weight(1f),
                        ) {
                            Icon( painter = painterResource(id = R.drawable.baseline_pets_24), contentDescription = "Home", tint = Color.White)
                        }
                        IconButton(
                            onClick = { /* Hacer algo */ },
                            modifier = Modifier.weight(1f),
                        ) {
                            Icon(Icons.Default.Favorite, contentDescription = "Favorite", tint = Color.White)
                        }
                        IconButton(
                            onClick = { navHostController.navigate("login") },
                            modifier = Modifier.weight(1f),
                        ) {
                            Icon(Icons.Default.AccountCircle, contentDescription = "Perfil", tint = Color.White)
                        }
                    }
                }
            }
        ) { innerPadding ->
            // Contenido del cuerpo del Scaffold
            Box(modifier = Modifier.weight(1f) .padding(innerPadding)) {
                MovimientosTab(viewModel = viewModel,viewModelText,viewModelImage, navHostController)
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovimientosTab(viewModel: GatosViewModel,viewModelText: TextViewModel,viewModelImage: ImagenViewModel, navHostController: NavHostController){
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
        TabsContent(tabs, pagerState,viewModel, viewModelText,viewModelImage, navHostController)
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
fun TabsContent(tabs: List<ItemTabs>, pagerState: PagerState, viewModel: GatosViewModel, viewModelText: TextViewModel, viewModelImage: ImagenViewModel, navHostController: NavHostController) {
    HorizontalPager(
        state = pagerState,
        //pageCount = tabs.size
    ) {page ->
        tabs[page].screen(viewModel, viewModelText, viewModelImage, navHostController)

    }
}

@Composable
fun Gatos(viewModel: GatosViewModel,viewModelText: TextViewModel,viewModelImage: ImagenViewModel, navHostController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        //Busqueda()
        ListGatos(viewModel = viewModel, navHostController)

    }
}
@Composable
fun Opiniones(viewModel: GatosViewModel,viewModelText: TextViewModel,viewModelImage: ImagenViewModel, navHostController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        //Busqueda()
        ListText(viewModelText = viewModelText, navHostController)

    }
}
@Composable
fun Fotos(viewModel: GatosViewModel,viewModelText: TextViewModel,viewModelImage: ImagenViewModel, navHostController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        //Busqueda()
        ListImagen(viewModelImage = viewModelImage, navHostController)

        //Text(text = "Fotos")//contenido de la pagina
   /*     IconButton(
            onClick = { navHostController.navigate("camera") },
            modifier = Modifier
                .padding(16.dp)
               // .align(Alignment.TopEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_camera_alt_24), // Aquí debes reemplazar ic_camera con el nombre de tu icono de cámara
                contentDescription = "Botón de cámara"
            )
        }*/
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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListText(viewModelText: TextViewModel, navHostController: NavHostController) {

    val text by viewModelText.filteredText.collectAsState()

    LaunchedEffect(Unit) {
        viewModelText.fetchText()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(text) { currentProduct ->
            Text(text = currentProduct)
        }

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListImagen(viewModelImage: ImagenViewModel, navHostController: NavHostController) {

    val imagen by viewModelImage.filteredImagen.collectAsState()

    LaunchedEffect(Unit) {
        viewModelImage.fetchImagen()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(imagen) { currentProduct ->
            Imagen(imagen = currentProduct)
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
                painter = painterResource(id = R.drawable.baseline_pets_24),
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
        Column( horizontalAlignment = Alignment.End, modifier = Modifier.align(Alignment.Top)) {
        Text(
            text ="Caracteristicas",
            fontSize = 14.sp,
            //color = Color.Green.copy(alpha = 0.9f),
            color = Color.Gray,
        )
        Text(
            text =cat.temperament,
            fontSize = 14.sp,
            //color = Color.Green.copy(alpha = 0.9f),
            color = Color(0xFF3F51B5),
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
        }
    }
    Divider()
}


@Composable
fun Text (text: Text){

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
                text = text.text,
                fontSize = 16.sp,
                color = Color.Black
            )
         /*   Text(
                text =text.typer,
                fontSize =14.sp,
                color = Color.Gray
            )*/
        }
       /* Spacer(modifier = Modifier.weight(1f))
        Text(
            text =text.updatedAt,
            fontSize = 14.sp,
            //color = Color.Green.copy(alpha = 0.9f),
            color = Color(0xFF3F51B5),
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )*/
        /*Text(
            text =text.createdAt,
            fontSize = 14.sp,
            //color = Color.Green.copy(alpha = 0.9f),
            color = Color(0xFF3F51B5),
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )*/
    }
    Divider()
}

@Composable
fun Imagen (imagen: Imagen){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        ) {
        //https://assets.coincap.io/assets/icons/btc@2x.png
        Box(modifier = Modifier.padding(horizontal = 8.dp)) {

           /* Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .padding(8.dp)
                    .size(30.dp)
            )*/

        }
        Image(
            //imageVector = Icons.Filled.AccountBox,
            painter = rememberImagePainter(imagen.url),
            contentDescription = null,
            modifier = Modifier.size(140.dp)
        )
        Column() {
         /*   Text(
                text = imagen.width,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text =imagen.height,
                fontSize =14.sp,
                color = Color.Gray
            )*/
        }
        Spacer(modifier = Modifier.weight(1f))
        /*Text(
            text =imagen.temperament,
            fontSize = 14.sp,
            //color = Color.Green.copy(alpha = 0.9f),
            color = Color(0xFF3F51B5),
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )*/
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
    //* MainScreen(viewModel = GatosViewModel(), navHostController)
}
