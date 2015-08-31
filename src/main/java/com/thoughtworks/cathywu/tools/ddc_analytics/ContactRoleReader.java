package com.thoughtworks.cathywu.tools.ddc_analytics;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzwu
 * @since 7/24/15
 */
public class ContactRoleReader {

    public static Map<String, List<ContactRole>> readFromFile(File contactRoleFile) throws IOException, ParseException {
        InputStreamReader ir = new InputStreamReader(new FileInputStream(contactRoleFile));
        BufferedReader br = new BufferedReader(ir);

        Map<String, List<ContactRole>> customers = new HashMap<String, List<ContactRole>>();

        String line;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split("\\|");
            ContactRole contactRole = new ContactRole(fields[1].trim(), fields[2].trim(), fields[3].trim(), fields[4].trim(), fields[5].trim());
            List<ContactRole> contactRoles = customers.get(contactRole.getCustomerCode());
            if (contactRoles == null) {
                contactRoles = new ArrayList<ContactRole>();
                customers.put(contactRole.getCustomerCode(), contactRoles);
            }
            contactRoles.add(contactRole);
        }

        br.close();
        return customers;
    }
}
