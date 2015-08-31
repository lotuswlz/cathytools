package com.thoughtworks.cathywu.tools.calculation.datecalculation;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lzwu
 * @since 7/14/15
 */
public class Executor {

    private Integer[] excludeLineNos;
    private Integer[] afterLineNos;
    private Date dateFrom;
    private Date dateEnd;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    public Executor(Integer[] excludeLineNos, Integer[] afterLineNos, Date dateFrom, Date dateEnd) {
        this.excludeLineNos = excludeLineNos == null ? new Integer[]{} : excludeLineNos;
        this.afterLineNos = afterLineNos == null ? new Integer[]{} : afterLineNos;
        this.dateFrom = dateFrom;
        this.dateEnd = dateEnd;
    }

    public void readFromFile(File file) throws IOException, ParseException {
        List<SortDateObj> dateObjs = readOriginDateObjects(file);
        sortByOriginDate(dateObjs);

        processDates(dateObjs);

        for (SortDateObj dateObj : dateObjs) {
            System.out.println(/*dateObj.getOrderNo() + "," + */simpleDateFormat.format(dateObj.getConvertedDate()));
        }
    }

    private void processDates(List<SortDateObj> dateObjs) {
        // part 1, from the start date, to current date
        // part 2, from current date to the end date
        int pastDateCount = dateObjs.size() - this.excludeLineNos.length - this.afterLineNos.length;
        long futureTimeGap = this.afterLineNos.length == 0 ? 0 : (this.dateEnd.getTime() - new Date().getTime()) / this.afterLineNos.length;
        long pastTimeGap;

        if (futureTimeGap == 0) {
            pastTimeGap = (this.dateEnd.getTime() - this.dateFrom.getTime()) / pastDateCount;
        } else {
            pastTimeGap = (new Date().getTime() - this.dateFrom.getTime()) / pastDateCount;
            ;
        }
        List<Integer> excludeDateNoList = Arrays.asList(this.excludeLineNos);
        List<Integer> afterLineNoList = Arrays.asList(this.afterLineNos);

        for (int i = 0; i < dateObjs.size(); i++) {
            SortDateObj dateObj = dateObjs.get(i);
            if (excludeDateNoList.contains(dateObj.getOrderNo() + 1)) {
                dateObj.setConvertedDate(dateObj.getOriginDate());
                System.out.println(dateObj.getOrderNo() + "," + dateObj.getOriginDate() + "," + dateObj.getConvertedDate());
                continue;
            }
            if (afterLineNoList.contains(dateObj.getOrderNo() + 1)) {
                dateObj.setConvertedDate(new Date(new Date().getTime() + futureTimeGap * (i + 1)));
            } else {
                dateObj.setConvertedDate(new Date(this.dateFrom.getTime() + pastTimeGap * i));
            }
        }

        Collections.sort(dateObjs, new Comparator<SortDateObj>() {
            @Override
            public int compare(SortDateObj o1, SortDateObj o2) {
                if (o1.getOrderNo() < o2.getOrderNo()) {
                    return -1;
                } else if (o1.getOrderNo() > o2.getOrderNo()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    private void sortByOriginDate(List<SortDateObj> dateObjs) {
        Collections.sort(dateObjs, new Comparator<SortDateObj>() {
            @Override
            public int compare(SortDateObj o1, SortDateObj o2) {
                if (o1.getOriginDate().before(o2.getOriginDate())) {
                    return -1;
                } else if (o1.getOriginDate().after(o2.getOriginDate())) {
                    return 1;
                }
                return 0;
            }
        });
    }

    private List<SortDateObj> readOriginDateObjects(File file) throws IOException, ParseException {
        InputStream is = new FileInputStream(file);
        InputStreamReader ir = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(ir);

        List<SortDateObj> dateObjs = new ArrayList<SortDateObj>();
        String line;
        int idx = 0;
        while ((line = br.readLine()) != null) {
            Date originDate = simpleDateFormat.parse(line);
            dateObjs.add(new SortDateObj(idx++, originDate));
        }
        return dateObjs;
    }

    public static void main(String[] args) {
//        try {
//            Executor executor = new Executor(new Integer[]{22,24,27,28,29}, new Integer[]{}, simpleDateFormat.parse("05-MAY-2015 00:00:00"), simpleDateFormat.parse("5-Aug-2015 00:00:00"));
//            executor.readFromFile(new File("/Users/lzwu/Downloads/111.txt"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        List<String> set = new ArrayList<String>();
        set.add("1-CZXZLZ|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZLL|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZLS|");
        set.add("1-CZXZLS|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZLX|");
        set.add("1-CZXZLX|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZMC|");
        set.add("1-CZXZMC|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZMG|");
        set.add("1-CZXZMG|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMX|");
        set.add("1-CZXZMX|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZMS|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZLZ|");
        set.add("1-CZXZLL|");
        set.add("1-CZXZLL|");
        set.add("1-CZXZLL|");
        set.add("1-CZXZLZ|");

        for (String value : set) {
//            System.out.println(value.replace("4", "1").replace("3", "5").replace("0", "2"));
            System.out.println(value.replace("L", "A"));
        }
    }
}
