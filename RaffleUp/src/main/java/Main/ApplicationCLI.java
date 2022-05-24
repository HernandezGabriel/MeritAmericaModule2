package Main;

import DBModels.Raffle;
import JDBC.*;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.util.List;


public class ApplicationCLI {

    private static final String MAIN_MENU_OPTION_EMPLOYEES = "Employees";
    private static final String MAIN_MENU_OPTION_DEPARTMENTS = "Departments";
    private static final String MAIN_MENU_OPTION_PROJECTS = "Projects";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_DEPARTMENTS,
            MAIN_MENU_OPTION_EMPLOYEES,
            MAIN_MENU_OPTION_PROJECTS,
            MAIN_MENU_OPTION_EXIT };

    private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to main menu";

    private static final String DEPT_MENU_OPTION_ALL_DEPARTMENTS = "Show all departments";
    private static final String DEPT_MENU_OPTION_UPDATE_NAME = "Update department name";
    private static final String[] DEPARTMENT_MENU_OPTIONS = new String[] { DEPT_MENU_OPTION_ALL_DEPARTMENTS,
            DEPT_MENU_OPTION_UPDATE_NAME,
            MENU_OPTION_RETURN_TO_MAIN};

    private static final String EMPL_MENU_OPTION_ALL_EMPLOYEES = "Show all employees";
    private static final String EMPL_MENU_OPTION_SEARCH_BY_NAME = "Employee search by name";
    private static final String EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS = "Show employees without projects";
    private static final String[] EMPL_MENU_OPTIONS = new String[] { EMPL_MENU_OPTION_ALL_EMPLOYEES,
            EMPL_MENU_OPTION_SEARCH_BY_NAME,
            EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS,
            MENU_OPTION_RETURN_TO_MAIN};

    private static final String PROJ_MENU_OPTION_ACTIVE_PROJECTS = "Show all projects";
    private static final String PROJ_MENU_OPTION_ADD_PROJECT = "Add new project";
    private static final String PROJ_MENU_OPTION_PROJECT_EMPLOYEES = "Show project employees";
    private static final String PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT = "Assign an employee to a project";
    private static final String PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT = "Remove employee from project";
    private static final String PROJ_MENU_OPTION_DELETE_PROJECT = "Delete project";
    private static final String[] PROJ_MENU_OPTIONS = new String[] { PROJ_MENU_OPTION_ACTIVE_PROJECTS,
            PROJ_MENU_OPTION_ADD_PROJECT,
            PROJ_MENU_OPTION_PROJECT_EMPLOYEES,
            PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT,
            PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT,
            PROJ_MENU_OPTION_DELETE_PROJECT,
            MENU_OPTION_RETURN_TO_MAIN };

    private final Menu menu;
    private final RaffleDao raffleDao;
    private final TicketDao ticketDao;
    private final UserDao userDao;

    public ApplicationCLI(DataSource dataSource) {
        this.menu = new Menu(System.in,System.out);
        this.raffleDao = new JdbcRaffleDao(dataSource);
        this.ticketDao = new JdbcTicketDao(dataSource);
        this.userDao = new JdbcUsersDao(dataSource);

    }

    public static void main(String[] args) {
        ApplicationCLI applicationCLI = new ApplicationCLI(establishDataSource());


        List<Raffle> raffles = applicationCLI.raffleDao.listAllRafflesNotExpired();
        for (Raffle r: raffles) {
            System.out.println(r.toString());

        }
    }

    private static DataSource establishDataSource() {
        BasicDataSource dataSource= new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/RaffleUp");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        return dataSource;
    }

}
