package com.example.membership.adapter.in.web;

import com.example.membership.domain.Membership;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegisterMembershipControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("test")
    public void testRegisterMembership() throws Exception {

        RegisterMembershipRequest request = new RegisterMembershipRequest("name", "address", "email@com", false);


        Membership membership = Membership.generateMember(
                new Membership.MembershipId(1L)
                , new Membership.MembershipName("name")
                , new Membership.MembershipEmail("email@com")
                , new Membership.MembershipAddress("address")
                , new Membership.MembershipIsValid(true)
                , new Membership.MembershipIsCorp(false)
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/membership/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(request))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(membership)));

    }


}
