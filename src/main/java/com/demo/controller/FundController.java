package com.demo.controller;

import com.demo.configuration.WebSecurityConfig;
import com.demo.entity.*;
import com.demo.service.FundService;
import com.demo.service.TableService.Impl.InvestService;
import com.demo.service.TableService.Impl.TrendService;
import com.demo.service.TableService.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public ResponseEntity<?> getPageFundList(HttpSession session) {
        List<Fund> fundList = (List<Fund>) session.getAttribute("FundList");
        return ResponseEntity.ok(fundList);
    }


    @RequestMapping(value = "/GetFundDetails")
    public String getFundDetails(@Param("fundCode") String fundCode, HttpServletRequest request) {
        FundDetailInfo fundDetailInfo = fundService.getFundDetail(fundCode);
        request.setAttribute("Fund", fundDetailInfo);
        return "FundDetails";
    }

    @RequestMapping(value = "/QueryFund", method = RequestMethod.POST)
    public String queryFund(@Param("queryCode") String queryCode, HttpServletRequest request) {
        FundDetailInfo fundDetailInfo = fundService.getFundDetail(queryCode);
        request.setAttribute("Fund", fundDetailInfo);
        return "FundDetails";
    }

    @RequestMapping(value = "/WriteInvestInfor")
    public String writeInvestInfor(
            @Param("fundname") String fundname,
            @Param("fundcode") String fundcode,
            HttpServletRequest request) {
        request.setAttribute("fundCode", fundcode);
        request.setAttribute("fundName", fundname);
        return "BuyFund";
    }

    @PostMapping(value = "/user/BuyFund")
    public ResponseEntity buyFund(@Param("fundCode") String fundCode,
                                  @Param("fundName") String fundName,
                                  @Param("firstDate") String firstDate,
                                  @Param("investMode") String investMode,
                                  @Param("amountOfInvest") String amountOfInvest,
                                  @Param("alreadyIncome") String alreadyIncome,
                                  @Param("fee") String fee,
                                  @Param("receivedDays") String receivedDays,
                                  @Param("delayDays") String delayDays,
                                  @Param("platform") String platform,
                                  HttpSession session) {
        String userName = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        User user = userService.findUserByName(userName);
        Invest invest = new Invest();
        UUID uuid = UUID.randomUUID();
        invest.setId(uuid.toString());
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
        trend.setInvestid(invest.getId());
        trendService.saveTrend(trend);
        return ResponseEntity.ok("");
    }

    @GetMapping(value = "/user/LookBoughtFund")
    public String LookBoughtFund(HttpSession session, HttpServletRequest request) {
        String userName = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        User user = userService.findUserByName(userName);
        List investList = investService.findInvestByUserId(user.getId());
        List showTrendList = new ArrayList();
        DecimalFormat df = new java.text.DecimalFormat("#.0000");
        for (int i = 0; i < investList.size(); i++) {
            Invest invest = (Invest) investList.get(i);
            if (invest.getState() == 1) {
                Trend trend = trendService.findLatestByInvestId(invest.getId());
                if (trend != null) {
                    showTrend st = new showTrend(invest.getFundcode(), invest.getFundname(), trend.getInvestdays(),
                            Float.parseFloat(df.format(trend.getShouyirate())), Double.parseDouble(df.format(trend.getShourinianhua())),
                            trend.getXirr(), Double.parseDouble(df.format(trend.getProfit())), Double.parseDouble(df.format(trend.getInvestcost())),
                            invest.getPlatform(), trend.getCurrentdate(), invest.getId());
                    showTrendList.add(st);
                }
            }
        }
        request.setAttribute("showTrendList", showTrendList);
        return "MyFund";
    }

    @GetMapping(value = "/user/toUpdateTrend")
    public String toUpdateTrend(HttpSession session, HttpServletRequest request) {
        String userName = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
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
                Trend trend = trendService.findLatestByInvestId(invest.getId());
                showTrend st = new showTrend(invest.getFundcode(), invest.getFundname(),
                        trend.getInvestdays(), Float.parseFloat(df.format(trend.getShouyirate())),
                        Double.parseDouble(df.format(trend.getShourinianhua())), trend.getXirr(),
                        Double.parseDouble(df.format(trend.getProfit())), Double.parseDouble(df.format(trend.getInvestcost())),
                        invest.getPlatform(), trend.getCurrentdate(), invest.getId());
                showTrendList.add(st);
            }
        }
        return showTrendList;
    }

    @PostMapping(value = "/user/UpdateTrend")
    public ResponseEntity UpdateTrend(String[] check,
                                      String date,
                                      String[] fundcode,
                                      String[] property,
                                      String[] zdf,
                                      String[] ccyk,
                                      String[] investid,
                                      HttpSession session
    ) {

        int len = fundcode.length;
        List checkList = Arrays.asList(check);

        String userName = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        User user = userService.findUserByName(userName);
        Map m = new HashMap();

        for (int i = 0; i < len; i++) {
            if (!checkList.contains(String.valueOf(i + 1))) {
                continue;
            }

            if (fundcode[i].equals("") || property[i].equals("") || zdf[i].equals("") || ccyk[i].equals("")) {
                m.put("stateCode", "400");
                m.put("message", "更新数据不能为空！");
                return ResponseEntity.ok(m);
            }

            Invest invest = investService.findInvestById(investid[i]);
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
                T.setInvestid(invest.getId());
                trendService.saveTrend(T);
            } catch (ParseException e) {
                e.printStackTrace();
                m.put("stateCode", "400");
                m.put("message", "e");
                return ResponseEntity.ok(m);
            }
        }
        m.put("stateCode", "200");
        return ResponseEntity.ok(m);
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

    @GetMapping(value = "/user/StopInvest")
    public String StopInvest(
            @Param("investId") String investId,
            HttpSession session, HttpServletRequest request) {
        System.out.println("StopInvest");
        investService.StopInvest(investId);
        return LookBoughtFund(session, request);
    }
}
