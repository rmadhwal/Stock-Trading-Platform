package com.example.demo.Controllers;

import static org.junit.Assert.assertEquals;

import com.cs.trading.Repositories.TraderRepository;
import com.cs.trading.UsersDbApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cs.trading.Models.Role;
import com.cs.trading.Models.Trader;
import com.cs.trading.Repositories.AdminRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDbApplication.class)
public class TraderControllerTest {

    //	@Autowired
    TraderRepository tr;

    @Before
    public void init() {
        tr = new TraderRepository();
    }



}