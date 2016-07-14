package com.bonia.BParser.parsers.impl.xml;

import com.bonia.BParser.io.impl.FileInput;
import com.bonia.BParser.models.Address;
import com.bonia.BParser.models.Company;
import com.bonia.BParser.models.Department;
import com.bonia.BParser.models.Employee;
import com.bonia.BParser.parsers.IParser;
import org.apache.log4j.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JaxBParser<T> implements IParser<T> {

    private JAXBContext context;
    private Unmarshaller unmarshaller;
    private T t;

    private static final Logger LOG = Logger.getLogger(JaxBParser.class);

    public JaxBParser() {
    }

    @Override
    public T parse(String fileName) {
        FileInput input = new FileInput(fileName);
        LOG.info("JAXB PARSER IS STARTING.");

        try {
            context = JAXBContext.newInstance(Company.class);
            unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new File(input.toURI()));
        } catch (JAXBException e) {
            LOG.error("JAXBException while context up.", e);
        }
        LOG.info("JAXB PARSER IS FINISHING.");
        return t;
    }

    @Deprecated
    public static void defaultMarshalling() {
        Address a1 = new Address();
        a1.setCountryName("Coun1");
        a1.setCityName("c1");
        a1.setStreetName("s1");
        a1.setHouseNumber(1);
        Address a2 = new Address();
        a2.setCountryName("Coun2");
        a2.setCityName("c2");
        a2.setStreetName("s2");
        a2.setHouseNumber(2);
        Address a3 = new Address();
        a3.setCountryName("Coun3");
        a3.setCityName("c3");
        a3.setStreetName("s3");
        a3.setHouseNumber(3);
        Address a4 = new Address();
        a4.setCountryName("Coun4");
        a4.setCityName("c4");
        a4.setStreetName("s4");
        a4.setHouseNumber(4);

        List<Employee> employeeList1 = new ArrayList<>();
        List<Employee> employeeList2 = new ArrayList<>();
        Employee e1 = new Employee();
        Employee e2 = new Employee();
        Employee e3 = new Employee();
        Employee e4 = new Employee();
        e1.setFirstName("f1");
        e1.setLastName("l1");
        e1.setAge(22);
        e1.setPosition("p1");
        e1.setAddress(a1);
        e2.setFirstName("f2");
        e2.setLastName("l2");
        e2.setAge(23);
        e2.setPosition("p2");
        e2.setAddress(a2);
        e3.setFirstName("f3");
        e3.setLastName("l3");
        e3.setAge(24);
        e3.setPosition("p3");
        e3.setAddress(a3);
        e4.setFirstName("f4");
        e4.setLastName("l4");
        e4.setAge(25);
        e4.setPosition("p4");
        e4.setAddress(a4);
        employeeList1.add(e1);
        employeeList1.add(e2);
        employeeList2.add(e3);
        employeeList2.add(e4);

        List<Department> departmentList = new ArrayList<>();
        Department d1 = new Department();
        Department d2 = new Department();
        d1.setDepartmentName("dep1");
        d2.setDepartmentName("dep2");
        d1.setAddress(a1);
        d1.setEmployeeList(employeeList1);
        d2.setAddress(a2);
        d2.setEmployeeList(employeeList2);
        departmentList.add(d1);
        departmentList.add(d2);
        Company c1 = new Company();
        c1.setCompanyName("comp1");
        c1.setDepartmentList(departmentList);

        try {
            JAXBContext jc = JAXBContext.newInstance(Company.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(c1, System.out);
            marshaller.marshal(c1, new File("new.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}