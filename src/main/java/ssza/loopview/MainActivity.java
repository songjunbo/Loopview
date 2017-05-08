package ssza.loopview;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import java.nio.channels.NonReadableChannelException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn, btnTime, btnEndTime;
    TextView tvSingle, tvStartTime, tvEndTime, tvTimeCha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btnTime = (Button) findViewById(R.id.btn_time);
        btnEndTime = (Button) findViewById(R.id.btn_end_time);

        btn.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnEndTime.setOnClickListener(this);

        tvSingle = (TextView) findViewById(R.id.tv_single);
        tvStartTime = (TextView) findViewById(R.id.tv_start_time);
        tvEndTime = (TextView) findViewById(R.id.tv_end_time);
    }


    /**
     * 获取当前的年份
     *
     * @return
     */
    private int getCurrentYear() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR);
    }

    /**
     * 获取当前的月份
     *
     * @return
     */
    private int getCurrentMonth() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取今天的day
     *
     * @return
     */
    private int getCurrentDay() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                final List<String> list = new ArrayList<String>();
                for (int i = 0; i < 10; i++) {
                    list.add(i + "");
                }
                OptionsPickerView pickerView = new OptionsPickerView.Builder(MainActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        String single = list.get(options1);
                        tvSingle.setText(single);
                    }
                }).build();
                pickerView.setNPicker(list, null, null);
                pickerView.show();
                break;
            case R.id.btn_time:
                Calendar startDate2 = Calendar.getInstance();
                startDate2.set(getCurrentYear(), getCurrentMonth(), getCurrentDay() + 1);
                TimePickerView tpvTime2 = new TimePickerView.Builder(MainActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        Long time = date.getTime();
                        String str = String.valueOf(time);
                        String str2 = stampToDate(str);
                        tvStartTime.setText(str2);
                    }
                })
                        .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                        .setRange(getCurrentYear(), getCurrentYear() + 1)
                        .build();
//                tpvTime.setDate(startDate);

                tpvTime2.show();
                break;
            case R.id.btn_end_time:
                Calendar startDate = Calendar.getInstance();
                startDate.set(getCurrentYear(), getCurrentMonth(), getCurrentDay());
                TimePickerView tpvTime = new TimePickerView.Builder(MainActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        Long time = date.getTime();
                        String str = String.valueOf(time);
                        String str2 = stampToDate(str);
                        tvEndTime.setText(str2);
                    }
                })
                        .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                        .setRange(getCurrentYear(), getCurrentYear() + 1)
                        .build();
//                tpvTime.setDate(startDate);
                tpvTime.show();
                break;
        }
    }

    /*
   * 将时间戳转换为时间
   */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
