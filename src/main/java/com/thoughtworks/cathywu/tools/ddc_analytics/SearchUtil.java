package com.thoughtworks.cathywu.tools.ddc_analytics;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @author lzwu
 * @since 7/24/15
 */
public class SearchUtil {

    private File contactRoleFile;
    public SearchUtil(String filePath) {
        contactRoleFile = new File(filePath);
    }

    // return contact role which contains only one contactType
    public List<String> searchOnly(String contactType) throws IOException, ParseException {
        Map<String, List<ContactRole>> customers = ContactRoleReader.readFromFile(contactRoleFile);
        List<String> customerCodes = new ArrayList<String>();
        for (String customerCode : customers.keySet()) {
            List<ContactRole> roles = customers.get(customerCode);
            boolean isSame = false;
            for (ContactRole role : roles) {
                if (!role.getContactType().equals(contactType)) {
                    isSame = false;
                    break;
                }
                isSame = true;
            }
            if (isSame) {
                customerCodes.add(customerCode);
            }
        }
        return customerCodes;
    }

    public Map<String, List<String>> analysis(String contactType) {
        try {
            Map<String, List<ContactRole>> customers = ContactRoleReader.readFromFile(contactRoleFile);
            Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
            for (String customerCode : customers.keySet()) {
                List<ContactRole> contactRoles = customers.get(customerCode);
                Collections.sort(contactRoles);
                if (!isContains(contactRoles, contactType)) {
                    continue;
                }
                StringBuilder roleStr = new StringBuilder();
                for (ContactRole role : contactRoles) {
                    if (!roleStr.toString().contains(role.getContactType())) {
                        roleStr.append(role.getContactType()).append("|");
                    }
                }
                List<String> cacs = resultMap.get(roleStr.toString());
                if (cacs == null) {
                    cacs = new ArrayList<String>();
                }
                cacs.add(customerCode);
                resultMap.put(roleStr.toString(), cacs);
            }
            return resultMap;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isContains(List<ContactRole> roles, String contactType) {
        for (ContactRole role : roles) {
            if (role.getContactType().equals(contactType)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SearchUtil searchUtil = new SearchUtil("/Users/lzwu/Downloads/rcd_contact_role.csv");
//        try {
//            List<String> customerCodes = searchUtil.searchOnly("Legal Lessee");
//            for (String customerCode : customerCodes) {
//                System.out.println(customerCode);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        System.out.println("====================================");
        Map<String, List<String>> contactRolesStatistics = searchUtil.analysis("Legal Lessee");
        for (String key : contactRolesStatistics.keySet()) {
            if (key.contains("Legal Lessee") && key.contains("Full") && key.contains("Limited")) {
                System.out.println(key + ":\t" + mergeCacs(contactRolesStatistics.get(key)));
            }
        }

    }

    private static String mergeCacs(List<String> cacs) {
        StringBuilder sb = new StringBuilder();
        for (String cac : cacs) {
            sb.append(cac).append(",");
        }
        return sb.toString();
    }
}
