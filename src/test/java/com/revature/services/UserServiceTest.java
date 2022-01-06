package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.revature.exceptions.NewUserHasNonZeroIdException;
import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.repositories.UserDAO;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.models.Role;
import com.revature.models.User;

public class UserServiceTest {

    private static UserService userService;
    private static UserDAO userDAO;

    private User GENERIC_EMPLOYEE_1;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        userService = new UserService();
        userDAO = mock(UserDAO.class);
    }

    @Before
    public void setUp() throws Exception {
        GENERIC_EMPLOYEE_1 = new User(0, "genericEmployee1", "genericPassword", Role.EMPLOYEE);
    }

    @Test
    public void testGetByUsernamePassesWhenUsernameExists() {
    	//System.out.println(" callin test method");
    	//UserService us=new UserService();
    	//Optional<User> u=us.getByUsername("hassanm");
    	//Optional<User> optJob =	Optional.ofNullable(us.getByUsername("hassanm"));
    	//assertEquals(u.get(). getUsername(), "hassanm");
       when(userDAO.getByUsername(anyString())).thenReturn(GENERIC_EMPLOYEE_1 );
          User u=userService.getByUsername(GENERIC_EMPLOYEE_1.getUsername());
          System.out.println(" print user " +u);
        assertEquals(GENERIC_EMPLOYEE_1,
        	userService.getByUsername(GENERIC_EMPLOYEE_1.getUsername()));

        verify(userDAO).getByUsername(GENERIC_EMPLOYEE_1.getUsername());
    }
    
    @Test
    public void testGetByIdPassesWhenUsernameExists() {
    	
    	/*UserService us=new UserService();
    	User u=us.getUserById(2);
    	assertEquals(u.getUsername(), "hassanm");*/
        when(userDAO.getByUsername(anyString())).thenReturn(GENERIC_EMPLOYEE_1);

       assertEquals(Optional.of(GENERIC_EMPLOYEE_1), userService.getByUsername(GENERIC_EMPLOYEE_1.getUsername()));

        verify(userDAO).getByUsername(GENERIC_EMPLOYEE_1.getUsername());
    }
}
