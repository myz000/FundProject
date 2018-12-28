package com.demo.controller;

import com.demo.api.ShowApiService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private ShowApiService showApiService;


    @RequestMapping(value = "/GetFundList")
    public String getFundList(HttpSession session) {
        //  List<Fund> fundList = fundService.getFundList();
        //  session.setAttribute("FundList", fundList);
        return "Fund_1";
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
        // NetValue value=showApiService.getNetValue()

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
        trend.setInvestcost(0);
        trend.setUserid(user.getId());
        trend.setInvestid(invest.getId());
        trend.setPayments(invest.getAmountofinvest());
        trend.setUnitval(0);
        trend.setUnitshare(0);
        trend.setTotalshare(0);
        trendService.saveTrend(trend);
        return ResponseEntity.ok("200");
    }

    @GetMapping(value = "/user/LookBoughtFund")
    public String LookBoughtFund(HttpSession session, HttpServletRequest request) {
        String userName = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        User user = userService.findUserByName(userName);
        List showTrendList = getBoughtFund(userName);
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
                List<Trend> trendList = trendService.findTrendsByInvestId(invest.getId());
                Trend trend = trendList.get(0);
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
                                      String[] updateDate,
                                      HttpSession session
    ) throws ParseException {
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

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date a;
            Date b;
            try {
                a = sdf.parse(date);
                b = sdf.parse(updateDate[i]);
            } catch (ParseException e) {
                e.printStackTrace();
                m.put("stateCode", "400");
                m.put("message", e.toString());
                return ResponseEntity.ok(m);
            }

            if (a.before(b) || a.equals(b)) {
                m.put("stateCode", "400");
                m.put("message", "第" + (i + 1) + "条基金更新日期晚于当前日期！");
                return ResponseEntity.ok(m);
            }
        }

        for (int i = 0; i < len; i++) {
            if (!checkList.contains(String.valueOf(i + 1))) {
                continue;
            }

            Invest invest = investService.findInvestById(investid[i]);
            List<Trend> trendList = trendService.findTrendsByInvestId(invest.getId());
            Trend lt = trendList.get(0);

            NetValue netValue = showApiService.getNetValue(invest.getFundcode(), invest.getFirstdate());
            List<NetValue.netData> netDataList = netValue.getData();
            List<String> netDate = netDataList.stream().map(NetValue.netData::getDate).distinct().collect(Collectors.toList());

            if (lt.getTotalshare() == 0) {
                for (NetValue.netData n : netDataList) {
                    if (n.getDate().equals(lt.getCurrentdate())) {
                        lt.setUnitval(n.getUnitVal());
                        lt.setUnitshare(invest.getAmountofinvest() / n.getUnitVal());
                        lt.setTotalshare(0);
                        break;
                    }
                }
                trendService.saveTrend(lt);
            }

            double totalShare = lt.getTotalshare() + lt.getUnitshare();
            double cost = lt.getInvestcost() + lt.getPayments();


            List<String> trendDate = trendList.stream().map(Trend::getCurrentdate).distinct().collect(Collectors.toList());

            Calendar cc = Calendar.getInstance();
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate = dateFormat1.parse(invest.getFirstdate());
            Date endDate = dateFormat1.parse(date);
            Date today = new Date();
            Date d = beginDate;
            int investmode;
            switch (invest.getInvestmode()) {
                case "日投":
                    investmode = 1;
                    break;
                case "周投":
                    investmode = 7;
                    break;
                default:
                    investmode = 30;
            }

            cc.setTime(d);
            cc.add(Calendar.DATE, 1); // 日期加1天

            while (!d.after(today)) {
             /*   if (d.after(endDate)) {
                    break;
                }*/
                System.out.println(d);
                String dd = dateFormat1.format(d);
                if ((!trendDate.contains(dd)) && netDate.contains(dd)) {
                    Trend T = new Trend();
                    T.setFundcode(fundcode[i]);
                    T.setCurrentdate(dateFormat1.format(d));

                    for (NetValue.netData n : netDataList) {
                        if (n.getDate().equals(dd)) {
                            T.setUnitval(n.getUnitVal());
                            T.setUnitshare(invest.getAmountofinvest() / n.getUnitVal());
                            T.setTotalshare(totalShare);
                            totalShare = totalShare + T.getUnitshare();
                            break;
                        }
                    }

                    T.setProperty(T.getTotalshare() * T.getUnitval());
                    T.setPayments(invest.getAmountofinvest());
                    T.setInvestcost(cost);
                    cost = cost + T.getPayments();
                    T.setZhangdiefu(0);
                    T.setChicangyingkui(T.getProperty() - T.getInvestcost());
                    T.setInvestdays(daysBetween(invest.getFirstdate(), dd));
                    double profit = (T.getChicangyingkui() - invest.getAlreadyincome()) * (1 - invest.getFee());
                    double shourinianhua = (profit / T.getInvestcost()) / T.getInvestdays() * 365;
                    float shouyirate = (float) ((T.getChicangyingkui() - invest.getAlreadyincome() - T.getProperty() * 0.005) / T.getInvestcost());
                    double xirr = 0;
                    T.setShouyirate(shouyirate);
                    T.setShourinianhua(shourinianhua);
                    T.setXirr(xirr);
                    T.setProfit(profit);
                    T.setUserid(user.getId());
                    T.setInvestid(invest.getId());
                    trendService.saveTrend(T);
                }
                cc.setTime(d);
                if (investmode <= 7) {
                    cc.add(Calendar.DATE, investmode); // 日期加n天
                } else {
                    cc.add(Calendar.MONTH, 1);
                }

                while (cc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    cc.add(Calendar.DATE, 1);
                }


                if (cc.getTime().compareTo(endDate) >= 0) {
                    String fds = netDate.get(0);
                    Date fd = dateFormat1.parse(fds);

                    if (d.before(fd) || d.equals(fd)) {
                        Trend T = new Trend();
                        T.setFundcode(fundcode[i]);
                        T.setCurrentdate(dateFormat1.format(fd));
                        T.setUnitval(netDataList.get(0).getUnitVal());
                        T.setTotalshare(totalShare);
                        T.setUnitshare(0);
                        T.setProperty(T.getTotalshare() * T.getUnitval());
                        T.setInvestcost(cost);
                        T.setZhangdiefu(0);
                        T.setChicangyingkui(T.getProperty() - T.getInvestcost());
                        T.setInvestdays(daysBetween(invest.getFirstdate(), dateFormat1.format(fd)));
                        double profit = (T.getChicangyingkui() - invest.getAlreadyincome()) * (1 - invest.getFee());
                        double shourinianhua = (profit / T.getInvestcost()) / T.getInvestdays() * 365;
                        float shouyirate = (float) ((T.getChicangyingkui() - invest.getAlreadyincome() - T.getProperty() * 0.005) / T.getInvestcost());
                        double xirr = 0;
                        T.setShouyirate(shouyirate);
                        T.setShourinianhua(shourinianhua);
                        T.setXirr(xirr);
                        T.setProfit(profit);
                        T.setUserid(user.getId());
                        T.setInvestid(invest.getId());
                        T.setPayments(0);
                        trendService.saveTrend(T);
                    }
                    break;
                }
                d = cc.getTime();
            }











           /* try {
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
                T.setPayments(invest.getAmountofinvest());
                trendService.saveTrend(T);
            } catch (ParseException e) {
                e.printStackTrace();
                m.put("stateCode", "400");
                m.put("message", "e");
                return ResponseEntity.ok(m);
            }*/
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

    @RequestMapping(value = "/user/FundDetail", method = RequestMethod.GET)
    public String getFundInfo(@Param(value = "investId") String investId,
                              HttpServletRequest request
    ) {
        Invest invest = investService.findInvestById(investId);
        List<Trend> trendList = trendService.findTrendsByInvestId(investId);
        // trendList.removeIf(r->r.getPayments()==0);
        request.setAttribute("invest", invest);
        request.setAttribute("trendList", trendList);
        return "MyFundDetail";
    }

    @RequestMapping(value = "/user/StopInvest")
    public String StopInvest(
            @Param("investId") String investId,
            HttpSession session, HttpServletRequest request) {
        System.out.println("StopInvest");
        investService.StopInvest(investId);
        return LookBoughtFund(session, request);
    }
}
