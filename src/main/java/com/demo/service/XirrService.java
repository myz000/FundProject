package com.demo.service;

import com.demo.entity.Trend;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface XirrService {
    HSSFWorkbook exportTrends(List<Trend> trendList);
}
