package com.thoughtworks.cathywu.tools.calculation.datecalculation;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lzwu
 * @since 7/14/15
 */
public class ExecutorOld {

    private Integer[] excludeLineNumbers;
    private int addMonths = 0;
    private int addDays = 0;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    public ExecutorOld(Integer[] excludeLineNumbers, int addMonths, int addDays) {
        this.excludeLineNumbers = excludeLineNumbers;
        this.addMonths = addMonths;
        this.addDays = addDays;
    }

    public void execute(String filePath) {
        try {
            List<Date> dateList = readFromFile(new File(filePath));
            List<Date> convertedDates = changeDate(dateList);
            output(dateList, convertedDates);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void output(List<Date> dateList, List<Date> convertedDates) {
        for (int i = 0; i < dateList.size(); i++) {
            System.out.println(simpleDateFormat.format(dateList.get(i)) + "\t" + simpleDateFormat.format(convertedDates.get(i)));
        }
    }

    private List<Date> changeDate(List<Date> dateList) {
        List<Date> changedDates = new ArrayList<Date>();
        List<Integer> excludeNos = Arrays.asList(excludeLineNumbers);
        for (int i = 0; i < dateList.size(); i++) {
            if (excludeNos.contains(i + 1)) {
                changedDates.add(dateList.get(i));
                continue;
            }
            Date date = dateList.get(i);
            Calendar cdate = Calendar.getInstance();
            cdate.setTime(date);
            cdate.add(Calendar.MONTH, addMonths);
            cdate.add(Calendar.DATE, addDays);

            changedDates.add(cdate.getTime());
        }
        return changedDates;
    }

    private List<Date> readFromFile(File file) throws IOException, ParseException {
        if (file == null || !file.exists()) {
            return null;
        }
        List<Date> dates = new ArrayList<Date>();
        List<String> lines = readContent(file);
        for (String line : lines) {
            dates.add(simpleDateFormat.parse(line));
        }
        return dates;
    }

    private List<String> readContent(File file) throws IOException {
        List<String> content = new ArrayList<String>();
        InputStream is = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            content.add(line);
        }
        return content;
    }

    public static void main(String[] args) {
        ExecutorOld executor = new ExecutorOld(new Integer[]{15, 250, 251, 252, 253, 254, 255, 256, 257, 258}, 0, 2);
        executor.execute("/Users/lzwu/Downloads/test.txt");
    }
}
