package cz.muni.fi.pa165.sportsactivitymanager.service.facade.test;

import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserCreateDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Dto.UserDTO;
import cz.muni.fi.pa165.sportsactivitymanager.Entity.User;
import cz.muni.fi.pa165.sportsactivitymanager.Enums.Sex;
import cz.muni.fi.pa165.sportsactivitymanager.Facade.UserFacade;
import cz.muni.fi.pa165.sportsactivitymanager.service.BeanMappingService;
import cz.muni.fi.pa165.sportsactivitymanager.service.UserService;
import cz.muni.fi.pa165.sportsactivitymanager.service.facade.UserFacadeImpl;
import org.hibernate.service.spi.ServiceException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Petra Gasparikova, Michal Stefanik
 */

@RunWith(MockitoJUnitRunner.class)
public class UserFacadeTest {

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserFacade userFacade = new UserFacadeImpl();
    private User user;
    private User user1;
    private UserDTO userDTO;
    private UserDTO userDTO1;
    private List<User> userList;
    private List<UserDTO> userDTOList;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        user = new User(1l);
        user.setName("Peter");
        user.setAge(23);
        user.setEmail("peter@java.fi");
        user.setHeight(180.5);
        user.setSex(Sex.Male);
        user.setWeight(96.3);

        user1 = new User(2l);
        user1.setName("Maria");
        user1.setAge(38);
        user1.setEmail("maria@java.fi");
        user1.setHeight(158.4);
        user1.setSex(Sex.Female);
        user1.setWeight(65.7);

        userDTO = new UserDTO();
        userDTO.setId(1l);
        userDTO.setName("Peter");
        userDTO.setAge(23);
        userDTO.setEmail("peter@java.fi");
        userDTO.setHeight(180.5);
        userDTO.setWeight(96.3);

        userDTO1 = new UserDTO();
        userDTO1.setId(2l);
        userDTO1.setName("Maria");
        userDTO1.setAge(38);
        userDTO1.setEmail("maria@java.fi");
        userDTO1.setHeight(158.4);
        userDTO1.setWeight(65.7);

        userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

        userDTOList = new ArrayList<>();
        userDTOList.add(userDTO);
        userDTOList.add(userDTO1);
    }

    @Test
    public void testCreateUser() {
        UserCreateDTO uDTO = new UserCreateDTO();
        uDTO.setName("Charlie");
        uDTO.setEmail("charlie@java.fi");
        uDTO.setAge(81);
        uDTO.setHeight(169.9);
        uDTO.setWeight(65.0);
        when(beanMappingService.mapTo(uDTO, User.class)).thenReturn(user);
        when(userService.createUser(user)).thenReturn(user);
        userFacade.createUser(uDTO);
    }

    @Test
    public void testDeleteExcursion() {
        userFacade.deleteUser(1l);
        verify(userService).deleteUser(new User(1l));
    }

    @Test
    public void testUpdateExcursion() {
        when(beanMappingService.mapTo(userDTO, User.class)).thenReturn(user);
        userFacade.updateUser(userDTO);
        verify(userService).updateUser(user);
    }

    @Test
    public void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(userList);
        when(beanMappingService.mapTo(Matchers.anyCollection(), Matchers.eq(UserDTO.class))).thenReturn(userDTOList);
        assertEquals(userFacade.getAllUsers().size(), 2);
    }

    @Test
    public void testGetUserById() {
        when(userService.getUserById(1l)).thenReturn(user);
        when(beanMappingService.mapTo(user, UserDTO.class)).thenReturn(userDTO);
        assertEquals(userFacade.getUserById(1l), userDTO);
    }

    @Test
    public void testGetUserByEmail() {
        when(userService.getUserByEmail("maria@java.fi")).thenReturn(user1);
        when(beanMappingService.mapTo(user1, UserDTO.class)).thenReturn(userDTO1);
        assertEquals(userFacade.getUserByEmail("maria@java.fi"), userDTO1);
    }

    @Test
    public void testGetUsersByName() {
        String name = "Maria";
        userDTOList.remove(userDTO);

        when(userService.getUserByName(name)).thenReturn(Collections.singletonList(user1));
        when(beanMappingService.mapTo(Collections.singletonList(user1), UserDTO.class)).thenReturn(userDTOList);
        assertEquals(userFacade.getUsersByName("Maria").size(), 1);

        User user2 = new User(2l);
        user2.setName("Maria");
        user2.setAge(41);
        user2.setEmail("maria1@java.fi");
        user2.setHeight(171.9);
        user2.setSex(Sex.Female);
        user2.setWeight(59.4);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(1l);
        userDTO2.setName("Maria");
        userDTO2.setAge(41);
        userDTO2.setEmail("maria1@java.fi");
        userDTO2.setHeight(171.9);
        userDTO2.setWeight(59.4);

        userDTOList.add(userDTO2);

        when(userService.getUserByName(name)).thenReturn(Arrays.asList(user1, user2));
        when(beanMappingService.mapTo(Arrays.asList(user1, user2), UserDTO.class)).thenReturn(userDTOList);
        assertEquals(2, userFacade.getUsersByName("Maria").size());
    }


}
