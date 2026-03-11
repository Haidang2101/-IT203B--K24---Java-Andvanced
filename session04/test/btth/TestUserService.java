package btth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestUserService {
    UserService userService ;
    @BeforeEach
    public void initData(){
         userService = new UserService();
         userService.addUser(new User(2,"Huy","huy@gmail.com"));
    }
    @Test
    @DisplayName("Kiểm tra thêm mới")
    public void testUserService(){
        userService.addUser(new User(1,"huy","huy@gmail.com"));
        Assertions.assertEquals(2,userService.users.size());
    }
    @Test
    @DisplayName("Kiểm tra có ngaọi lệ hay không")
    public void testUserService2(){
        Assertions.assertThrows(IllegalArgumentException.class,()->{
           userService.addUser(
                   new User(1, "", "null")
           );
        });
    }
    @Test
    @DisplayName("Kiểm tra tìm kiếm")
    public void testUserService3(){
        User userFind=userService.findById(5);
        Assertions.assertNull(userFind);
    }
    @Test
    @DisplayName("kiểm tra định dạng email")
    public void testUserService4(){
        boolean result=userService.isValidEmail("huy@gmail.com");
        Assertions.assertTrue(result);
    }

}
