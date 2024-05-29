package com.barbuceanuconstantin.proiectlicenta.view.charts

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
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
import androidx.compose.ui.unit.Dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarData
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.barbuceanuconstantin.proiectlicenta.R
import java.time.LocalDate
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
        textColor = colorResource(id = R.color.gray),
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
                if (proportions[clickedItemIndex].isNaN()) {
                    canvas.nativeCanvas.drawText(
                        "0%",
                        (canvasSize / 2) + textFontSize / 4,
                        (canvasSize / 2) + textFontSize / 4,
                        textPaint
                    )
                } else {
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
fun BarChartOnMementosScreen(
    chartColors: List<androidx.compose.ui.graphics.Color>,
    chartValues: List<Double>
) {
    val barsData = listOf(
        BarData(point = Point(x = 0F, y = 0.toFloat()),
            color = colorResource(id = R.color.white),
            label = ""),
        BarData(point = Point(x = 1F, y = chartValues[0].toFloat()),
            color = chartColors[0],
            label = "Actual"),
        BarData(point = Point(x = 2F, y = chartValues[1].toFloat()),
            color = chartColors[1],
            label = "Prag"),
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

@Composable
fun BarChartOnGraphsScreen(chartColors: List<androidx.compose.ui.graphics.Color>,
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

@Composable
fun LineChartGraph(
    lRevenues: List<Double>,
    lExpenses: List<Double>,
    lDebt: List<Double>
) {
    val steps = 5
    val maximum = max(lRevenues + lExpenses + lDebt)
    val pointsDataRevenues: MutableList<Point> = mutableListOf()
    val pointsDataExpenses: MutableList<Point> = mutableListOf()
    val pointsDataDebt: MutableList<Point> = mutableListOf()

    for (i in lRevenues.indices) {
        pointsDataRevenues.add(Point((i).toFloat(), lRevenues[i].toFloat()))
    }
    pointsDataRevenues.add(Point(lRevenues.size.toFloat(), 0.0F))

    for (i in lExpenses.indices) {
        pointsDataExpenses.add(Point((i).toFloat(), lExpenses[i].toFloat()))
    }
    pointsDataExpenses.add(Point(lExpenses.size.toFloat(), 0.0F))

    for (i in lDebt.indices) {
        pointsDataDebt.add(Point((i).toFloat(), lDebt[i].toFloat()))
    }
    pointsDataDebt.add(Point(lDebt.size.toFloat(), 0.0F))

    val xAxisData = AxisData.Builder()
        .axisStepSize(dimensionResource(id = R.dimen.hundred))
        .backgroundColor(androidx.compose.ui.graphics.Color.Transparent)
        .steps(pointsDataRevenues.size)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(dimensionResource(id = R.dimen.margin_extra))
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(androidx.compose.ui.graphics.Color.Transparent)
        .labelAndAxisLinePadding(dimensionResource(id = R.dimen.margin_extra))
        .labelData { i ->
            val yScale = maximum / steps
            (i * yScale).toString()
        }
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val yellow = colorResource(id = R.color.yellow)
    val red = colorResource(id = R.color.red)
    val blue = colorResource(id = R.color.blue)
    val gray = colorResource(id = R.color.gray)

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsDataRevenues.toList(),
                    LineStyle(
                        color = yellow,
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = MaterialTheme.colorScheme.tertiary
                    ),
                    SelectionHighlightPoint(color = MaterialTheme.colorScheme.primary),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.inversePrimary,
                                androidx.compose.ui.graphics.Color.Transparent
                            )
                        )
                    )
                ),
                Line(
                    dataPoints = pointsDataExpenses.toList(),
                    LineStyle(
                        color = red,
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = MaterialTheme.colorScheme.tertiary
                    ),
                    SelectionHighlightPoint(color = MaterialTheme.colorScheme.primary),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.inversePrimary,
                                androidx.compose.ui.graphics.Color.Transparent
                            )
                        )
                    )
                ),
                Line(
                    dataPoints = pointsDataDebt.toList(),
                    LineStyle(
                        color = blue,
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = MaterialTheme.colorScheme.tertiary
                    ),
                    SelectionHighlightPoint(color = MaterialTheme.colorScheme.primary),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.inversePrimary,
                                androidx.compose.ui.graphics.Color.Transparent
                            )
                        )
                    )
                )
            )
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = gray)
    )

    LineChart(
        modifier = Modifier.fillMaxSize(),
        lineChartData = lineChartData
    )
}

@Composable
internal fun stackedBarChartInputs(
    valuesRevenues: List<Double>,
    valuesExpenses: List<Double>,
    valuesDebt: List<Double>
) = (0..<LocalDate.now().monthValue).map { key1 ->
                                                val inputs = (0..2).map { key2 ->
                                                    if (key2 == 0) {
                                                        valuesRevenues[key1]
                                                    } else if (key2 == 1) {
                                                        valuesExpenses[key1]
                                                    } else {
                                                        valuesDebt[key1]
                                                    }
                                                }.toPercent()
                                                StackedData(
                                                    inputs = inputs,
                                                    colors = listOf(
                                                        colorResource(id = R.color.light_cream_yellow),
                                                        colorResource(id = R.color.light_cream_red),
                                                        colorResource(id = R.color.light_cream_blue)
                                                    )
                                                )
}

private fun List<Double>.toPercent(): List<Double> {
    return this.map { item ->
        item / this.sum()
    }
}

@Composable
internal fun StackedBarChartGraph(
    valuesRevenues: List<Double>,
    valuesExpenses: List<Double>,
    valuesDebt: List<Double>,
    maxHeight: Dp = dimensionResource(id = R.dimen.stacked_bar_chart_height)
) {
    val borderColor = colorResource(id = R.color.gray)
    val density = LocalDensity.current
    val strokeWidth = with(density) { dimensionResource(id = R.dimen.very_thin_line).toPx() }

    val values: List<StackedData> = stackedBarChartInputs(
        valuesRevenues = valuesRevenues,
        valuesExpenses = valuesExpenses,
        valuesDebt = valuesDebt
    )

    Column {
        Text(
            text = "100%",
            modifier = Modifier.padding(
                                        start = dimensionResource(id = R.dimen.margin),
                                        end = dimensionResource(id = R.dimen.margin),
                                        top = dimensionResource(id = R.dimen.gap)
                                    )
        )
        Row(
            modifier = Modifier.then(
                Modifier
                    .fillMaxWidth()
                    .height(maxHeight)
                    .padding(
                        start = dimensionResource(id = R.dimen.margin),
                        end = dimensionResource(id = R.dimen.margin),
                        bottom = dimensionResource(id = R.dimen.gap)
                    )
                    .drawBehind {
                        // draw X-Axis
                        drawLine(
                            color = borderColor,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = strokeWidth
                        )
                        // draw Y-Axis
                        drawLine(
                            color = borderColor,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = strokeWidth
                        )
                        // draw second Y-Axis
                        drawLine(
                            color = borderColor,
                            start = Offset(size.width, size.height),
                            end = Offset(size.width, 0f),
                            strokeWidth = strokeWidth
                        )
                        // draw second X-Axis
                        drawLine(
                            color = borderColor,
                            start = Offset(size.width, 0f),
                            end = Offset(0f, 0f),
                            strokeWidth = strokeWidth
                        )
                    }
            ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            values.forEachIndexed { index, item ->
                Column(modifier = Modifier.weight(1f)) {
                    if (item.inputs[0].toFloat() > 0)
                        Spacer(
                            modifier = Modifier
                                .padding(
                                    start = dimensionResource(id = R.dimen.thin_line),
                                    end = dimensionResource(id = R.dimen.thin_line),
                                    top = dimensionResource(id = R.dimen.thin_line)
                                )
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .background(item.colors[0])
                                .weight(item.inputs[0].toFloat())
                        )
                    if (item.inputs[1].toFloat() > 0)
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = dimensionResource(id = R.dimen.thin_line))
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .background(item.colors[1])
                                .weight(item.inputs[1].toFloat())
                        )
                    if (item.inputs[2].toFloat() > 0)
                        Spacer(
                            modifier = Modifier
                                .padding(horizontal = dimensionResource(id = R.dimen.thin_line))
                                .fillMaxHeight()
                                .fillMaxWidth()
                                .background(item.colors[2])
                                .weight(item.inputs[2].toFloat())
                        )
                    HorizontalDivider(
                        thickness = dimensionResource(id = R.dimen.very_thin_line),
                        color = colorResource(id = R.color.gray)
                    )
                    Text(
                        text = (index + 1).toString(),
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.thin_line))
                    )
                }
            }
        }
    }
}