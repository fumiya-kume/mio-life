package nagoya.kuu.miolife.ui.main.sim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import nagoya.kuu.miolife.databinding.SimDetailDialogBinding
import nagoya.kuu.miolife.ui.main.sim.viewentity.UseVolumeLogViewEntity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class SimDetailDialog(
    private val hdoServiceCode: String
) : BottomSheetDialogFragment() {

    private val viewModel: SimDetailDialogViewModel by viewModel(parameters = {
        parametersOf(
            hdoServiceCode
        )
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SimDetailDialogBinding
            .inflate(
                layoutInflater,
                container,
                false
            )

        viewModel
            .useVolumeLogList
            .observeForever {
                when (it) {
                    UseVolumeLogStatus.Loading -> {
                        binding.volumeLineChartLoadingProgressBar.visibility = View.VISIBLE
                    }
                    is UseVolumeLogStatus.Success -> {
                        binding.volumeLineChartLoadingProgressBar.visibility = View.GONE
                        binding.useVolumeLineChart.setupLineChart(it.useVolumeLogList)
                    }
                    is UseVolumeLogStatus.Failed -> {
                        binding.volumeLineChartLoadingProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        return binding.root
    }

    private fun LineChart.setupLineChart(useVolumeLogViewEntityList: List<UseVolumeLogViewEntity>) {
        val lineDataSet =
            LineDataSet(
                useVolumeLogViewEntityList.mapIndexed { index, useVolumeLogViewEntity ->
                    Entry(
                        index.toFloat(),
                        useVolumeLogViewEntity.withCouponVolume.toFloat()
                    )
                },
                "使用量グラフ"
            ).apply {
                this.valueTextSize = 12F
            }
        this.data = LineData(lineDataSet)

        this.setDrawBorders(false)
        this.setDrawGridBackground(false)
        this.description = Description().apply { text = "" }
        this.setBorderWidth(0F)
        this.xAxis.isEnabled = false

        this.getXAxis().valueFormatter =
            IndexAxisValueFormatter(useVolumeLogViewEntityList.map { it.date })
        this.getXAxis().setDrawLabels(true)
        this.axisLeft.isEnabled = false
        this.axisRight.isEnabled = false
        this.isClickable = false
        this.legend.setEntries(useVolumeLogViewEntityList.map { it.date }.map {
            LegendEntry().apply {
                label = it
            }
        })

        this.notifyDataSetChanged()
        this.invalidate()
    }
}