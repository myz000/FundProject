package com.demo.controller;

import com.demo.api.FundDataDemo;
import com.demo.configuration.WebSecurityConfig;
import com.demo.entity.*;
import com.demo.service.FundService;
import com.demo.service.TableService.Impl.InvestService;
import com.demo.service.TableService.Impl.TrendService;
import com.demo.service.TableService.Impl.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class FundController {
    @Autowired
    private UserService userService;
    @Autowired
    private InvestService investService;
    @Autowired
    private TrendService trendService;
    @Autowired
    private FundService fundService;


    @RequestMapping(value = "/GetFundList")
    public String getFundList(HttpSession session) {
        List<Fund> fundList = fundService.getFundList();
        session.setAttribute("FundList", fundList);
        return "Fund";
    }


    @GetMapping(value = "/GetPageFundList")
    public ResponseEntity<?> getFundList2(HttpSession session) {
        List<Fund> fundList = (List<Fund>) session.getAttribute("FundList");
        return ResponseEntity.ok(fundList);
    }

    @RequestMapping(value = "/GetFundDetails")
    public String getFundDetails(String FundCode, String FundName, HttpServletRequest request) {
        System.out.println(FundCode);
        System.out.println(FundName);
        if (FundCode == null) {
            FundCode = "";
        }
        if (FundName == null) {
            FundName = "";
        }
        FundDataDemo FD = new FundDataDemo();
        JSONObject fundInfor = FD.getFundDetails(FundCode, FundName);
        JSONObject details = JSONObject.fromObject(fundInfor.get("details"));
        JSONArray managerList = JSONArray.fromObject(details.get("managerList"));
        JSONObject manager = managerList.getJSONObject(0);
        JSONObject managerCom = JSONObject.fromObject(details.get("manageComDetails"));
        request.setAttribute("fundInfor", fundInfor);
        request.setAttribute("manager", manager);
        request.setAttribute("managerCom", managerCom);
        request.setAttribute("details", details);
        return "FundDetails";
    }

    @RequestMapping(value = "/GetDetail")
    public ResponseEntity getDetail(String code) {
        String url = "http://fund.eastmoney.com/pingzhongdata/" + code + ".js";
        return ResponseEntity.ok(url);
    }

    @RequestMapping(value = "/GetFundDetailsTwo")
    public String getDetailTwo(String fundCode, HttpServletRequest request) {
        FundDetailInfo fundDetailInfo = fundService.getFundDetail(fundCode);
        request.setAttribute("Fund", fundDetailInfo);
        return "FundDetailsTest";
    }

    @RequestMapping(value = "/WriteInvestInfor")
    public String writeInvestInfor(String fundname, String fundcode, HttpServletRequest request) {
        System.out.println("WriteInvestInfor");
        System.out.println(fundname);
        System.out.println(fundcode);
        request.setAttribute("fundCode", fundcode);
        request.setAttribute("fundName", fundname);
        return "BuyFund";
    }

    @PostMapping(value = "/BuyFund")
    public String buyFund(String fundCode, String fundName, String firstDate, String investMode,
                          String amountOfInvest, String alreadyIncome, String fee,
                          String receivedDays, String delayDays, String platform, HttpSession session, HttpServletRequest request) {
        System.out.println("---" + receivedDays);
        String userName = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        User user = userService.findUserByName(userName);
        System.out.println(user.getUsername());
        Invest invest = new Invest();
        invest.setFundcode(fundCode);
        invest.setFundname(fundName);
        invest.setFirstdate(firstDate);
        invest.setInvestmode(investMode);
        invest.setAmountofinvest(Double.parseDouble(amountOfInvest));
        invest.setAlreadyincome(Double.parseDouble(alreadyIncome));
        invest.setState(1);
        invest.setFee(Float.parseFloat(fee));
        invest.setUserid(user.getId());
        invest.setReceiveddays(Integer.parseInt(receivedDays));
        invest.setDelaydays(Integer.parseInt(delayDays));
        invest.setUnfinish(0);
        invest.setPlatform(platform);
        investService.saveInvest(invest);
        Trend trend = new Trend();
        trend.setFundcode(fundCode);
        trend.setCurrentdate(firstDate);
        trend.setProperty(0);
        trend.setZhangdiefu(0);
        trend.setChicangyingkui(0);
        trend.setInvestdays(0);
        trend.setShouyirate(0);
        trend.setShourinianhua(0);
        trend.setXirr(0);
        trend.setProperty(0);
        trend.setInvestcost(Double.parseDouble(amountOfInvest));
        trend.setUserid(user.getId());
        trend.setState(1);
        trendService.saveTrend(trend);
        return LookBoughtFund(session, request);
    }

    @GetMapping(value = "/LookBoughtFund")
    public String LookBoughtFund(HttpSession session, HttpServletRequest request) {
        System.out.println(WebSecurityConfig.SESSION_KEY);
        String userName = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        if (userName == null) {
            request.setAttribute("msg", "请先登录！");
            System.out.println("请先登录");
            return "Welcome";
        }
        User user = userService.findUserByName(userName);
        List investList = investService.findInvestByUserId(user.getId());
        List showTrendList = new ArrayList();
        DecimalFormat df = new java.text.DecimalFormat("#.0000");
        //df.format("12345.6789");
        for (int i = 0; i < investList.size(); i++) {
            Invest invest = (Invest) investList.get(i);
            System.out.println(invest.getFundcode());
            if (invest.getState() == 1) {
                String code = invest.getFundcode();
                Trend trend = trendService.findLatestByUserIdFundCode(user.getId(), code);
                if (trend != null) {
                    showTrend st = new showTrend(invest.getFundcode(), invest.getFundname(), trend.getInvestdays(),
                            Float.parseFloat(df.format(trend.getShouyirate())), Double.parseDouble(df.format(trend.getShourinianhua())), trend.getXirr(), Double.parseDouble(df.format(trend.getProfit())), Double.parseDouble(df.format(trend.getInvestcost())), invest.getPlatform());
                    showTrendList.add(st);
                }
            }
        }
        request.setAttribute("showTrendList", showTrendList);
        return "MyFund";
    }

    @GetMapping(value = "/toUpdateTrend")
    public String toUpdateTrend(HttpSession session, HttpServletRequest request) {
        String userName = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        String msg = (String) request.getAttribute("msg");
        if (msg != null) {
            request.setAttribute("msg", msg);
        }
        if (userName == null) {
            request.setAttribute("msg", "请先登录！");
            System.out.println("请先登录");
            return "Welcome";
        }
        List showList = getBoughtFund(userName);
        request.setAttribute("showTrendList", showList);
        return "UpdateMyFund";
    }


    public List getBoughtFund(String username) {
        User user = userService.findUserByName(username);
        List investList = investService.findInvestByUserId(user.getId());
        List showTrendList = new ArrayList();
        DecimalFormat df = new java.text.DecimalFormat("#.0000");
        for (int i = 0; i < investList.size(); i++) {
            Invest invest = (Invest) investList.get(i);
            System.out.println(invest.getFundcode());
            if (invest.getState() == 1) {
                String code = invest.getFundcode();
                Trend trend = trendService.findLatestByUserIdFundCode(user.getId(), code);
                if (trend != null) {
                    showTrend st = new showTrend(invest.getFundcode(), invest.getFundname(), trend.getInvestdays(),
                            Float.parseFloat(df.format(trend.getShouyirate())), Double.parseDouble(df.format(trend.getShourinianhua())), trend.getXirr(), Double.parseDouble(df.format(trend.getProfit())), Double.parseDouble(df.format(trend.getInvestcost())), invest.getPlatform());
                    showTrendList.add(st);
                }
            }
        }
        return showTrendList;
    }

    @PostMapping(value = "/UpdateTrendtable")
    public String UpdateTrend(String date, String[] fundcode, String[] property, String[] zdf, String[] ccyk, HttpSession session, HttpServletRequest request) {
        if (date.equals("")) {
            request.setAttribute("msg", "请选择日期！");
            return toUpdateTrend(session, request);
        }
        int len = fundcode.length;
        System.out.println("len:" + len);
        if (property.length != len || zdf.length != len || ccyk.length != len) {
            request.setAttribute("msg", "请将更新信息填写完整！");
            return toUpdateTrend(session, request);
        }

        String userName = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        User user = userService.findUserByName(userName);
        for (int i = 0; i < len; i++) {

            if (fundcode[i].equals("") || property[i].equals("") || zdf[i].equals("") || ccyk[i].equals("")) {
                continue;
            }
            Invest invest = investService.findInvestByUserIdFundcode(user.getId(), fundcode[i]);
            try {
                String code = fundcode[i];
                double p = Double.parseDouble(property[i]);
                double z = Double.parseDouble(zdf[i]);
                double c = Double.parseDouble(ccyk[i]);
                double investedcost = z - (c - invest.getAlreadyincome());
                int investdays = daysBetween(invest.getFirstdate(), date);
                double profit = (c - invest.getAlreadyincome()) * (1 - invest.getFee());
                double shourinianhua = (profit / investedcost) / investdays * 365;
                float shouyirate = (float) ((c - invest.getAlreadyincome() - z * 0.005) / investedcost);
                double xirr = 0;
                Trend T = new Trend();
                T.setFundcode(code);
                T.setCurrentdate(date);
                T.setProperty(p);
                T.setZhangdiefu(z);
                T.setChicangyingkui(c);
                T.setInvestdays(investdays);
                T.setShouyirate(shouyirate);
                T.setShourinianhua(shourinianhua);
                T.setXirr(xirr);
                T.setProfit(profit);
                T.setInvestcost(investedcost);
                T.setUserid(user.getId());
                T.setState(1);
                trendService.UpdateStateByUserIdFundCode(user.getId(), code, 0);
                trendService.saveTrend(T);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return LookBoughtFund(session, request);
    }


    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    @GetMapping(value = "/StopInvest")
    public String StopInvest(HttpSession session, HttpServletRequest request) {
        System.out.println("StopInvest");
        String fundcode = (String) request.getParameter("fundcode");
        System.out.println("停止定投：" + fundcode);
        String userName = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        User user = userService.findUserByName(userName);
        investService.StopInvest(user.getId(), fundcode);
        trendService.UpdateStateByUserIdFundCode(user.getId(), fundcode, 0);
        return LookBoughtFund(session, request);
    }
}
