package nagoya.kuu.miolife.ui.main.sim

import android.graphics.Color
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
import nagoya.kuu.miolife.ui.main.sim.viewentity.SimDetailDialogViewEntity
import nagoya.kuu.miolife.ui.main.sim.viewentity.UseVolumeLogViewEntity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class SimDetailDialog(
    private val serviceCode: String,
    private val phoneNumber: String
) : BottomSheetDialogFragment() {

    private val viewModel: SimDetailDialogViewModel by viewModel(parameters = {
        parametersOf(
            serviceCode
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


        binding.viewentity = SimDetailDialogViewEntity(
            id = 0,
            phoneNumber = phoneNumber,
            currentCouponStatus = false
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

        viewModel
            .couponSwitch
            .observeForever {
                when (it) {
                    CouponSwitchStatus.Loading -> {
                        binding.couponSwitchLoadingProgressBar.visibility = View.VISIBLE
                        binding.couponSwitch.isEnabled = false
                    }
                    is CouponSwitchStatus.Success -> {
                        binding.couponSwitchLoadingProgressBar.visibility = View.GONE
                        binding.couponSwitch.isEnabled = true

                        binding.viewentity =
                            binding.viewentity?.copy(currentCouponStatus = it.checked)
                    }
                    is CouponSwitchStatus.Failed -> {
                        binding.couponSwitchLoadingProgressBar.visibility = View.VISIBLE
                        binding.couponSwitch.isEnabled = false

                        Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        binding.couponSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateCouponSwitch(isChecked, serviceCode)
        }

        viewModel.refreshSwitchData()

        return binding.root
    }

    private fun LineChart.setupLineChart(useVolumeLogViewEntityList: List<UseVolumeLogViewEntity>) {


        fun setUpLineDataSet(data: List<Float>, label: String, color: Int): LineDataSet {
            return LineDataSet(
                data.mapIndexed { index, item ->
                    Entry(
                        index.toFloat(),
                        item
                    )
                },
                label
            ).apply {
                this.valueTextSize = 12F
                setDrawHorizontalHighlightIndicator(false)
                setDrawVerticalHighlightIndicator(false)
                mode = LineDataSet.Mode.CUBIC_BEZIER
                setDrawFilled(true)
                fillColor = color
            }
        }

        this.data =
            LineData(
                setUpLineDataSet(
                    useVolumeLogViewEntityList.map { it.withCouponVolume.toFloat() },
                    "クーポン使用量",
                    Color.rgb(140, 234, 255)
                ),
                setUpLineDataSet(
                    useVolumeLogViewEntityList.map { it.withoutCouponVolume.toFloat() },
                    "クーポン外使用量",
                    Color.rgb(140, 234, 140)
                )
            )

        this.setDrawBorders(false)
        this.setDrawGridBackground(false)
        this.description = Description().apply { text = "MB" }
        this.setBorderWidth(0F)
        this.xAxis.isEnabled = false

        this.setVisibleXRange(0F, 10F)

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