package fontys.sem3.school.business.impl;

import fontys.sem3.school.business.Converter.TicketConverterBusiness;
import fontys.sem3.school.business.Request.PurchaseTicketRequest;
import fontys.sem3.school.business.Response.User.GetUserResponse;
import fontys.sem3.school.business.interfaces.IEmailService;
import fontys.sem3.school.business.interfaces.ITicketBusinessRepository;
import fontys.sem3.school.business.interfaces.User.IUserService;
import fontys.sem3.school.domain.Role;
import fontys.sem3.school.domain.Ticket;
import fontys.sem3.school.domain.User;
import fontys.sem3.school.persistence.JPAmappers.RolesEnum;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TicketServiceTest {
    @Test
    public void testPurchaseTicketSuccess() {
        // Arrange
        ITicketBusinessRepository ticketBusinessRepository= mock(ITicketBusinessRepository.class);
        IUserService userService= mock(IUserService.class);
        IEmailService emailService= mock(IEmailService.class);
        TicketService ticketService= new TicketService(ticketBusinessRepository, userService, emailService);
        User user = new User();
        Role role = new Role();
        role.setId(1L);
        role.setType(RolesEnum.ADMIN); // assuming RolesEnum.ADMIN is a valid value
        role.setUser(user);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User mockUser = new User(1L,"Alec","solaalec1","sola.alec@gmail.com","123456",roles);
        PurchaseTicketRequest request = new PurchaseTicketRequest();
        request.setDate(new Date());
        request.setTime("10:00");
        request.setPrice(100);
        request.setTheatreId(1L);
        request.setEventId(1L);
        request.setZoneId(1L);
        request.setUserId(1L);
        request.setAmountTicket(1);

        Ticket ticket = TicketConverterBusiness.ticketRequestConverter(request);
        when(ticketBusinessRepository.purchaseTicket(ticket)).thenReturn(1L);

        GetUserResponse userResponse = new GetUserResponse(mockUser);
        when(userService.getUser(1L)).thenReturn(userResponse);

        // Act
        long result = ticketService.purchaseTicket(request);

        // Assert
        verify(emailService, times(1)).sendBookingConfirmation(anyString(), anyString(), anyString());
        assertEquals(1L, result);


    }



}
