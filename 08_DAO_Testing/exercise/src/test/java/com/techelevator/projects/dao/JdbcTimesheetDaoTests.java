package com.techelevator.projects.dao;

import com.techelevator.projects.model.Timesheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class JdbcTimesheetDaoTests extends BaseDaoTests {

    private static final Timesheet TIMESHEET_1 = new Timesheet(1L, 1L, 1L, 
            LocalDate.parse("2021-01-01"), 1.0, true, "Timesheet 1");
    private static final Timesheet TIMESHEET_2 = new Timesheet(2L, 1L, 1L,
            LocalDate.parse("2021-01-02"), 1.5, true, "Timesheet 2");
    private static final Timesheet TIMESHEET_3 = new Timesheet(3L, 2L, 1L,
            LocalDate.parse("2021-01-01"), 0.25, true, "Timesheet 3");
    private static final Timesheet TIMESHEET_4 = new Timesheet(4L, 2L, 2L,
            LocalDate.parse("2021-02-01"), 2.0, false, "Timesheet 4");

    //added for testing
    private static final Timesheet TIMESHEET_5 = new Timesheet(5L, 2L, 2L,
            LocalDate.parse("2021-02-01"), 2.0, false, "Timesheet 5");
    //added for update
    private static final Timesheet TIMESHEET_5_UPDATED = new Timesheet(5L, 1L, 1L,
            LocalDate.parse("2021-02-02"), 4.0, true, "Timesheet 5 updated");

    private JdbcTimesheetDao sut;


    @Before
    public void setup() {
        sut = new JdbcTimesheetDao(dataSource);
    }

    @Test
    public void getTimesheet_returns_correct_timesheet_for_id() {
        assertTimesheetsMatch(sut.getTimesheet(1L),TIMESHEET_1);
        assertTimesheetsMatch(sut.getTimesheet(2L),TIMESHEET_2);
        assertTimesheetsMatch(sut.getTimesheet(3L),TIMESHEET_3);
        assertTimesheetsMatch(sut.getTimesheet(4L),TIMESHEET_4);
    }

    @Test
    public void getTimesheet_returns_null_when_id_not_found() {
        Assert.assertNull(sut.getTimesheet(5L));
    }

    @Test
    public void getTimesheetsByEmployeeId_returns_list_of_all_timesheets_for_employee() {
        List<Timesheet> list = sut.getTimesheetsByEmployeeId(2L);
        assertTimesheetsMatch(list.get(0),TIMESHEET_3);
        assertTimesheetsMatch(list.get(1),TIMESHEET_4);

        list = sut.getTimesheetsByEmployeeId(1L);
        assertTimesheetsMatch(list.get(0),TIMESHEET_1);
        assertTimesheetsMatch(list.get(1),TIMESHEET_2);
    }

    @Test
    public void getTimesheetsByProjectId_returns_list_of_all_timesheets_for_project() {
        List<Timesheet> list = sut.getTimesheetsByProjectId(1L);
        assertTimesheetsMatch(list.get(0),TIMESHEET_1);
        assertTimesheetsMatch(list.get(1),TIMESHEET_2);
        assertTimesheetsMatch(list.get(2),TIMESHEET_3);


        list = sut.getTimesheetsByProjectId(2L);
        assertTimesheetsMatch(list.get(0),TIMESHEET_4);

    }

    @Test
    public void createTimesheet_returns_timesheet_with_id_and_expected_values() {
        assertTimesheetsMatch(sut.createTimesheet(TIMESHEET_5),TIMESHEET_5);

    }

    @Test
    public void created_timesheet_has_expected_values_when_retrieved() {
        sut.createTimesheet(TIMESHEET_5);
        assertTimesheetsMatch(sut.getTimesheet(5L),TIMESHEET_5);
    }

    @Test
    public void updated_timesheet_has_expected_values_when_retrieved() {
        sut.createTimesheet(TIMESHEET_5);
        sut.updateTimesheet(TIMESHEET_5_UPDATED);
        assertTimesheetsMatch(sut.getTimesheet(5L),TIMESHEET_5_UPDATED);
    }

    @Test
    public void deleted_timesheet_cant_be_retrieved() {
        sut.deleteTimesheet(4l);
        Assert.assertNull(sut.getTimesheet(4l));
        sut.deleteTimesheet(3l);
        Assert.assertNull(sut.getTimesheet(3l));
        sut.deleteTimesheet(2l);
        Assert.assertNull(sut.getTimesheet(2l));
        sut.deleteTimesheet(1l);
        Assert.assertNull(sut.getTimesheet(1l));

            }

    @Test
    public void getBillableHours_returns_correct_total() {

        Assert.assertEquals(2.5,sut.getBillableHours(1l,1l),Double.MIN_NORMAL); //emp1,proj1,billable=true
        Assert.assertEquals(.25,sut.getBillableHours(2l,1l),Double.MIN_NORMAL); //emp2, proj1, billable = true
        Assert.assertEquals(0,sut.getBillableHours(2l,2l),Double.MIN_NORMAL); //emp2, proj2, billable = false
    }

    private void assertTimesheetsMatch(Timesheet expected, Timesheet actual) {
        Assert.assertEquals(expected.getTimesheetId(), actual.getTimesheetId());
        Assert.assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        Assert.assertEquals(expected.getProjectId(), actual.getProjectId());
        Assert.assertEquals(expected.getDateWorked(), actual.getDateWorked());
        Assert.assertEquals(expected.getHoursWorked(), actual.getHoursWorked(), 0.001);
        Assert.assertEquals(expected.isBillable(), actual.isBillable());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

}
