package com.demo.service.Impl;

import com.demo.entity.Trend;
import com.demo.service.XirrService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Component
public class XirrServiceImpl implements XirrService {
    private static final List<String> TREND_HEAD = Arrays.asList(
            "日期", "资产", "持仓盈亏", "定投天数", "收益率", "首日年化",
            "本轮收益", "投入成本", "单位净值", "购买份额", "总份额"
    );
    private static final List<String> TREND_HEAD_ENG = Arrays.asList(
            "currentdate", "property", "chicangyingkui", "investdays", "shouyirate", "shourinianhua",
            "profit", "investcost", "unitval", "unitshare", "totalshare"
    );

    @Override
    public HSSFWorkbook exportTrends(List<Trend> trendList) {
        HSSFWorkbook wb = null;
        try {
            wb = exportExcel(trendList);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return wb;
    }

    public HSSFWorkbook exportExcel(List<Trend> trendList) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {   //fileName 为生成文件的文件名（包含地址）
        //第一步创建workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        //第二步创建sheet
        HSSFSheet sheet = wb.createSheet();

        //第三步创建行row:添加表头0行
        HSSFRow row = sheet.createRow(0);

        //设置单元格格式
        HSSFCellStyle cellStyle = wb.createCellStyle();    //style可设置单元格样式
        cellStyle.setWrapText(true);                      //自动换行
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中

        //第四步创建表头单元格
        for (int i = 0; i < TREND_HEAD.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(TREND_HEAD.get(i));
            cell.setCellStyle(cellStyle);
        }

        //第五步插入数据
        for (int i = 0; i < trendList.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);   //创建第i行
            Trend t = trendList.get(i);
            for (int j = 0; j < TREND_HEAD_ENG.size(); j++) {
                Method m = null;
                String name = TREND_HEAD_ENG.get(j).substring(0, 1).toUpperCase() + TREND_HEAD_ENG.get(j).substring(1);
                m = t.getClass().getMethod("get" + name);
                Object value = m.invoke(t); // 调用getter方法获取属性值
                HSSFCell cell = row1.createCell(j); //第i行第j个单元格
                cell.setCellValue(value.toString());
                cell.setCellStyle(cellStyle);
            }
        }

        return wb;

    }
}
