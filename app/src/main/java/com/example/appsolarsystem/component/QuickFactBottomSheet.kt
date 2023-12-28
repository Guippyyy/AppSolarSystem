package com.example.appsolarsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appsolarsystem.model.QuickFact
import com.microsoft.sqlserver.jdbc.SQLServerDataTable

@Composable
fun QuickFactBottomSheet(qf: QuickFact) {

    if (!qf.recordedBy.isNullOrEmpty()) {
        RowWithLabel("Recorded By:", qf.recordedBy)
        Divider()
    }

    if (!qf.firstRecorded.isNullOrEmpty()) {
        RowWithLabel("First Recorded:", qf.firstRecorded)
        Divider()
    }


    if (!qf.surfaceTempurature.isNullOrEmpty()) {
        RowWithLabel("Surface tempurature: ",qf.surfaceTempurature)
        Divider()
    }


    if (!qf.orbitPeriod.isNullOrEmpty()) {


             RowWithLabel( "Orbit Period: " , qf.orbitPeriod)

        Divider()
    }

    if (!qf.orbitDistance.isNullOrEmpty()) {

        RowWithLabel(label = "Orbit Distance", value = qf.orbitDistance )
        Divider()
    }

    if (qf.knownRings != null) {
        RowWithLabel(label = "Known Rings", value = qf.knownRings.toString()  )
        Divider()
    }

    if (!qf.notableMoons.isNullOrEmpty()) {
       RowWithLabel(label = "Notalble Moons: " , value = qf.notableMoons)
        Divider()
    }

    if (qf.knownMoons != null) {
        RowWithLabel(label = "Known Moons", value = qf.knownMoons.toString() )
              Divider()
    }



    if (!qf.equatorialCircumference.isNullOrEmpty()) {

        RowWithLabel(label = "Equatorial Circumference", value = qf.equatorialCircumference )
        Divider()
    }

    if (!qf.polarDiameter.isNullOrEmpty()) {
    RowWithLabel(label = "Polar Diameter",   value = qf.polarDiameter )
        Divider()
    }


    if (!qf.equatorialDiameter.isNullOrEmpty()) {

        RowWithLabel(label = "Equatorial Diameter:",   value = qf.equatorialDiameter )

        Divider()
    }


    if (!qf.mass.isNullOrEmpty()) {
        RowWithLabel(label = "Mass:",   value = qf.mass )

        Divider()
    }

    Spacer(Modifier.height(50.dp))
}


@Composable
fun RowWithLabel(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 15.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        // Label
        Text(
            text = label,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )


        Spacer(modifier = Modifier.width(38.dp).weight(1f))


        ShowText(value, fontSize = 16.sp, fontWeight = FontWeight.Normal)
    }
}

@Composable
fun ShowText(text: String, fontSize: TextUnit, fontWeight: FontWeight) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = MaterialTheme.colorScheme.tertiary,
        textAlign = TextAlign.End
    )
}