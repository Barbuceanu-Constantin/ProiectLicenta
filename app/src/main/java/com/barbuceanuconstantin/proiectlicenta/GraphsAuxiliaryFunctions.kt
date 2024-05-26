package com.barbuceanuconstantin.proiectlicenta

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import java.util.Collections.max
import kotlin.math.atan2
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
internal fun PieDonutChartGraph(chartColors: List<androidx.compose.ui.graphics.Color>,
                                chartValues: List<Double>,
                                donut: Boolean) {
    PieDonutChart(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_extra)),
        colors = chartColors,
        inputValues = chartValues,
        textColor = colorResource(id = R.color.black),
        donut = donut
    )
}

@Composable
internal fun PieDonutChart(
    modifier: Modifier = Modifier,
    colors: List<androidx.compose.ui.graphics.Color>,
    inputValues: List<Double>,
    textColor: androidx.compose.ui.graphics.Color,
    donut: Boolean
) {
    val chartDegrees = 360f // circle shape

    // start drawing clockwise (top to right)
    var startAngle = 270f

    // calculate each input percentage
    val proportions = inputValues.map {
        it * 100 / inputValues.sum()
    }

    val transactionTypes =  listOf(
        stringResource(id = R.string.active),
        stringResource(id = R.string.pasive),
        stringResource(id = R.string.datorii)
    )

    // calculate each input slice degrees
    val angleProgress = proportions.map { prop ->
        chartDegrees * prop / 100
    }

    // for donut graph
    val sliceWidth = with(LocalDensity.current) { dimensionResource(id = R.dimen.margin_extra).toPx() }

    // clicked slice index
    var clickedItemIndex by remember {
        mutableStateOf(0)
    }

    // calculate each slice end point in degrees, for handling click position
    val progressSize = mutableListOf<Float>()

    LaunchedEffect(angleProgress){
        progressSize.add(angleProgress.first().toFloat())
        for (x in 1 until angleProgress.size) {
            progressSize.add((angleProgress[x] + progressSize[x - 1]).toFloat())
        }
    }

    // text style
    val density = LocalDensity.current
    val textFontSize = with(density) { dimensionResource(id = R.dimen.gap).toPx() }
    val textPaint = remember {
        Paint().apply {
            color = textColor.toArgb()
            textSize = textFontSize
            textAlign = Paint.Align.CENTER
        }
    }
    val yellow = colorResource(id = R.color.yellow)
    val red = colorResource(id = R.color.red)
    val blue = colorResource(id = R.color.blue)
    val textPaintRevenues = remember {
        Paint().apply {
            color = yellow.toArgb()
            textSize = textFontSize
            textAlign = Paint.Align.CENTER
        }
    }
    val textPaintExpenses = remember {
        Paint().apply {
            color = red.toArgb()
            textSize = textFontSize
            textAlign = Paint.Align.CENTER
        }
    }
    val textPaintDebt = remember {
        Paint().apply {
            color = blue.toArgb()
            textSize = textFontSize
            textAlign = Paint.Align.CENTER
        }
    }

    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.Center) {

        val canvasSize = min(constraints.maxWidth, constraints.maxHeight)
        val size = Size(canvasSize.toFloat(), canvasSize.toFloat())
        val canvasSizeDp = with(LocalDensity.current) { canvasSize.toDp() }

        Canvas(modifier = Modifier
            .size(canvasSizeDp)
            .pointerInput(inputValues) {

                detectTapGestures { offset ->
                    val clickedAngle = touchPointToAngle(
                        width = canvasSize.toFloat(),
                        height = canvasSize.toFloat(),
                        touchX = offset.x,
                        touchY = offset.y,
                        chartDegrees = chartDegrees
                    )
                    progressSize.forEachIndexed { index, item ->
                        if (clickedAngle <= item) {
                            clickedItemIndex = index
                            return@detectTapGestures
                        }
                    }
                }
            }  ) {

            angleProgress.forEachIndexed { index, angle ->
                if (!donut) {
                    drawArc(
                        color = colors[index],
                        startAngle = startAngle,
                        sweepAngle = angle.toFloat(),
                        useCenter = true,
                        size = size,
                        style = Fill
                    )
                } else {
                    drawArc(
                        color = colors[index],
                        startAngle = startAngle,
                        sweepAngle = angle.toFloat(),
                        useCenter = false,
                        size = size,
                        style = Stroke(width = sliceWidth)
                    )
                }

                startAngle += angle.toFloat()
            }


            drawIntoCanvas { canvas ->
                canvas.nativeCanvas.drawText(
                    "${proportions[clickedItemIndex].roundToInt()}%",
                    (canvasSize / 2) + textFontSize / 4,
                    (canvasSize / 2) + textFontSize / 4,
                    textPaint
                )

                if (clickedItemIndex == 0) {
                    canvas.nativeCanvas.drawText(
                        transactionTypes[clickedItemIndex],
                        canvasSize / 2 + textFontSize / 4,
                        canvasSize + canvasSize / 5 + textFontSize / 4,
                        textPaintRevenues
                    )
                }
                else if (clickedItemIndex == 1) {
                    canvas.nativeCanvas.drawText(
                        transactionTypes[clickedItemIndex],
                        canvasSize / 2 + textFontSize / 4,
                        canvasSize + canvasSize / 5 + textFontSize / 4,
                        textPaintExpenses
                    )
                }
                else if (clickedItemIndex == 2) {
                    canvas.nativeCanvas.drawText(
                        transactionTypes[clickedItemIndex],
                        canvasSize / 2 + textFontSize / 4,
                        canvasSize + canvasSize / 5 + textFontSize / 4,
                        textPaintDebt
                    )
                }
            }

        }
    }
}

private fun touchPointToAngle(
    width: Float,
    height: Float,
    touchX: Float,
    touchY: Float,
    chartDegrees: Float
): Double {
    val x = touchX - (width * 0.5f)
    val y = touchY - (height * 0.5f)
    var angle = Math.toDegrees(atan2(y.toDouble(), x.toDouble()) + Math.PI / 2)
    angle = if (angle < 0) angle + chartDegrees else angle
    return angle
}

////////////////////////////////////////////////////////////////////////////////////
@Composable
fun BarChartGraph(chartColors: List<androidx.compose.ui.graphics.Color>,
                  chartValues: List<Double>) {
    val barsData = listOf(
        BarData(point = Point(x = 0F, y = 0.toFloat()),
                color = colorResource(id = R.color.white),
                label = ""),
        BarData(point = Point(x = 1F, y = chartValues[0].toFloat()),
                color = chartColors[0],
                label = "Active"),
        BarData(point = Point(x = 2F, y = chartValues[1].toFloat()),
                color = chartColors[1],
                label = "Pasive"),
        BarData(point = Point(x = 3F, y = chartValues[2].toFloat()),
                color = chartColors[2],
                label = "Datorii"),
        BarData(point = Point(x = 0F, y = 0.toFloat()),
            color = colorResource(id = R.color.white),
            label = "")
    )

    val maxValue = max(chartValues)

    val stepSize = 5

    val xAxis = AxisData.Builder()
                        .axisStepSize(dimensionResource(id = R.dimen.hundred))
                        .steps(5)
                        .bottomPadding(dimensionResource(id = R.dimen.middle))
                        .axisLabelAngle(20f)
                        .labelData { index -> barsData[index].label }
                        .axisLineColor(MaterialTheme.colorScheme.tertiary)
                        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
                        .build()

    val yAxis = AxisData.Builder()
                        .steps(stepSize)
                        .labelAndAxisLinePadding(dimensionResource(id = R.dimen.middle))
                        .axisOffset(dimensionResource(id = R.dimen.margin_extra))
                        .labelData { index -> (index * (maxValue / stepSize)).toString() }
                        .axisLineColor(MaterialTheme.colorScheme.tertiary)
                        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
                        .build()

    val barChartData = BarChartData(
        chartData = barsData,
        xAxisData = xAxis,
        yAxisData = yAxis,
        backgroundColor = MaterialTheme.colorScheme.surface
    )

    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = dimensionResource(id = R.dimen.margin),
                end = dimensionResource(id = R.dimen.margin)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            BarChart(
                modifier = Modifier,
                barChartData = barChartData
            )
        }
    }
}