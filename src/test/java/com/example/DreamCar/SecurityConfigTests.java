package com.example.DreamCar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SecurityConfigTests{

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void privatePathShouldRedirectToLogin() throws Exception {
        // Private Path should redirect to /login
        mvc.perform(get("/all_licitations/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void loginPathShouldAlwaysReturn200() throws Exception {
        // Make Sure u can access /login
        mvc.perform(get("/login").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void privatePathOnlyAdminCanAccess() throws Exception {
        // Make Sure u can access /login
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("ADMIN");
      mvc.perform(get("/delete_deal").with(user("doesntmatter").authorities(authority))).andExpect(status().isOk());
    }

    @Test
    public void privatePathUsersCantAccess() throws Exception {
        // Make Sure u can access /login
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("USER");
        mvc.perform(get("/delete_deal").with(user("doesntmatter").authorities(authority))).andExpect(status().isForbidden());
    }


}