package com.example.testprovince;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.andy.myapplication.R;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testprovince.model.CityModel;
import com.example.testprovince.model.CountyModel;
import com.example.testprovince.model.ProvinceModel;

public class MainActivity extends Activity implements OnClickListener {
    private String AddressXML;                 //xml格式的中国省市区信息
    private Button btn_province;
    private Button btn_city;
    private Button btn_county;
    private List<ProvinceModel> provinceList; //地址列表
    private int pPosition;
    private int cPosition;
    private boolean isCity = true;
    private boolean isCounty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFindView();
        initData();
    }

    public void initFindView() {
        btn_province = findViewById(R.id.btn_province);
        btn_city = findViewById(R.id.btn_city);
        btn_county = findViewById(R.id.btn_county);

        btn_province.setOnClickListener(this);
        btn_city.setOnClickListener(this);
        btn_county.setOnClickListener(this);
    }

    public void initData() {
        AddressXML = getRawAddress().toString();//获取中国省市区信息
        try {
            analysisXML(AddressXML);
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //初始化button数据
        btn_province.setText(provinceList.get(0).getProvince());
        btn_city.setText(provinceList.get(0).getCity_list().get(0).getCity());
        btn_county.setText(provinceList.get(0).getCity_list().get(0).getCounty_list().get(0).getCounty());
        //初始化列表下标
        pPosition = 0;
        cPosition = 0;
    }

    /**
     * 获取地区raw里的地址xml内容
     */
    public StringBuffer getRawAddress() {
        InputStream in = getResources().openRawResource(R.raw.address);
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            br.close();
            isr.close();
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return sb;
    }

    /**
     * 解析省市区xml，
     * 采用的是pull解析，
     * 为什么选择pull解析：因为pull解析简单浅显易懂！
     */
    public void analysisXML(String data) throws XmlPullParserException {
        try {
            ProvinceModel provinceModel = null;
            CityModel cityModel = null;
            CountyModel countyModel = null;
            List<CityModel> cityList = null;
            List<CountyModel> countyList = null;

            InputStream xmlData = new ByteArrayInputStream(data.getBytes("UTF-8"));
            XmlPullParserFactory factory = null;
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser;
            parser = factory.newPullParser();
            parser.setInput(xmlData, "utf-8");
            String currentTag = null;

            String province;
            String city;
            String county;

            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                String typeName = parser.getName();

                if (type == XmlPullParser.START_TAG) {
                    if ("root".equals(typeName)) {
                        provinceList = new ArrayList<ProvinceModel>();

                    } else if ("province".equals(typeName)) {
                        province = parser.getAttributeValue(0);//获取标签里第一个属性,例如<city name="北京市" index="1">中的name属性
                        provinceModel = new ProvinceModel();
                        provinceModel.setProvince(province);
                        cityList = new ArrayList<CityModel>();

                    } else if ("city".equals(typeName)) {
                        city = parser.getAttributeValue(0);
                        cityModel = new CityModel();
                        cityModel.setCity(city);
                        countyList = new ArrayList<CountyModel>();

                    } else if ("area".equals(typeName)) {
                        county = parser.getAttributeValue(0);
                        countyModel = new CountyModel();
                        countyModel.setCounty(county);

                    }

                    currentTag = typeName;

                } else if (type == XmlPullParser.END_TAG) {
                    if ("root".equals(typeName)) {

                    } else if ("province".equals(typeName)) {
                        provinceModel.setCity_list(cityList);
                        provinceList.add(provinceModel);

                    } else if ("city".equals(typeName)) {
                        cityModel.setCounty_list(countyList);
                        cityList.add(cityModel);

                    } else if ("area".equals(typeName)) {
                        countyList.add(countyModel);
                    }


                } else if (type == XmlPullParser.TEXT) {

                    currentTag = null;
                }

                type = parser.next();
            }

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_province:
                createDialog(1);
                break;

            case R.id.btn_city:
                if (isCity == true) {
                    createDialog(2);
                }
                break;
            case R.id.btn_county:
                if (isCounty == true) {
                    createDialog(3);
                }
                break;

            default:
                break;
        }
    }

    /**
     * 根据调用类型显示相应的数据列表
     *
     * @param type 显示类型 1.省；2.市；3.县、区
     */
    public void createDialog(final int type) {
        ListView lv = new ListView(this);
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("列表选择框");

        if (type == 1) {
            ProvinceAdapter pAdapter = new ProvinceAdapter(provinceList);
            lv.setAdapter(pAdapter);

        } else if (type == 2) {
            CityAdapter cAdapter = new CityAdapter(provinceList.get(pPosition).getCity_list());
            lv.setAdapter(cAdapter);
        } else if (type == 3) {
            CountyAdapter coAdapter = new CountyAdapter(provinceList.get(pPosition).getCity_list().get(cPosition).getCounty_list());
            lv.setAdapter(coAdapter);
        }

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                if (type == 1) {
                    pPosition = position;
                    btn_province.setText(provinceList.get(position).getProvince());
                    //判断该省下是否有市级
                    if (provinceList.get(position).getCity_list().size() < 1) {
                        btn_city.setText("");
                        btn_county.setText("");
                        isCity = false;
                        isCounty = false;
                    } else {
                        isCity = true;
                        btn_city.setText(provinceList.get(position).getCity_list().get(0).getCity());
                        cPosition = 0;
                        //判断该市下是否有区级或县级
                        if (provinceList.get(position).getCity_list().get(0).getCounty_list().size() < 1) {
                            btn_county.setText("");
                            isCounty = false;

                        } else {
                            isCounty = true;
                            btn_county.setText(provinceList.get(position).getCity_list().get(0).getCounty_list().get(0).getCounty());
                        }

                    }

                } else if (type == 2) {
                    cPosition = position;
                    btn_city.setText(provinceList.get(pPosition).getCity_list().get(position).getCity());
                    if (provinceList.get(pPosition).getCity_list().get(position).getCounty_list().size() < 1) {
                        btn_county.setText("");
                        isCounty = false;
                    } else {
                        isCounty = true;
                        btn_county.setText(provinceList.get(pPosition).getCity_list().get(cPosition).getCounty_list().get(0).getCounty());
                    }

                } else if (type == 3) {
                    btn_county.setText(provinceList.get(pPosition).getCity_list().get(cPosition).getCounty_list().get(position).getCounty());

                }

                dialog.dismiss();
            }
        });

        dialog.setContentView(lv);
        dialog.show();
    }

    class ProvinceAdapter extends BaseAdapter {
        public List<ProvinceModel> adapter_list;

        public ProvinceAdapter(List<ProvinceModel> list) {
            adapter_list = list;
        }

        @Override
        public int getCount() {
            return adapter_list.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View arg1, ViewGroup arg2) {
            TextView tv = new TextView(MainActivity.this);
            tv.setPadding(20, 20, 20, 20);
            tv.setTextSize(18);
            tv.setText(adapter_list.get(position).getProvince());
            return tv;
        }

    }

    class CityAdapter extends BaseAdapter {
        public List<CityModel> adapter_list;

        public CityAdapter(List<CityModel> list) {
            adapter_list = list;
        }

        @Override
        public int getCount() {
            return adapter_list.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View arg1, ViewGroup arg2) {
            TextView tv = new TextView(MainActivity.this);
            tv.setPadding(20, 20, 20, 20);
            tv.setTextSize(18);
            tv.setText(adapter_list.get(position).getCity());
            return tv;
        }

    }

    class CountyAdapter extends BaseAdapter {
        public List<CountyModel> adapter_list;

        public CountyAdapter(List<CountyModel> list) {
            adapter_list = list;
        }

        @Override
        public int getCount() {
            return adapter_list.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View arg1, ViewGroup arg2) {
            TextView tv = new TextView(MainActivity.this);
            tv.setPadding(20, 20, 20, 20);
            tv.setTextSize(18);
            tv.setText(adapter_list.get(position).getCounty());
            return tv;
        }

    }


}
