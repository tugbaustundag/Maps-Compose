package com.smality.mapscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Maps(LatLng(41.0391643,29.0004594))
        }
    }
}
@Composable
fun Maps(ubicacion: LatLng){
    //Harita açıldığında konumun büyütülerek gösterilmesi
    val cameraPositionState= rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(ubicacion,12f)
    }
    //Haritayı kullanma ve Konum bilgisine göre haritaya marker ekleme
    val lugar = rememberMarkerState(position = ubicacion)
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick={ lugar.position = it}
    ){ //Marker konumunu ve başlık değeri atama
        Marker(
            state = MarkerState(position = ubicacion),
            title = "Dolmabahçe Sarayı",
        )
    }
}
