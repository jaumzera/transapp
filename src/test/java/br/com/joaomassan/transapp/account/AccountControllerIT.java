package br.com.joaomassan.transapp.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerIT {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void getAccountById_shouldGetAnAccountByItsId() throws Exception {
    this.mockMvc
        .perform(get("/accounts/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userId").value(1L))
        .andExpect(jsonPath("$.documentNumber").value("22233333311"))
        .andExpect(jsonPath("$.name").value("Déx Mi"))
        .andExpect(jsonPath("$.availableCreditLimit").value("1000.0"));
  }

  @Test
  void getAccountById_shouldReturn404ForUserNotFound() throws Exception {
    this.mockMvc.perform(get("/accounts/0")).andExpect(status().isNotFound());
  }

  @Test
  void postAccount_shouldCreateAnAccount() throws Exception {
    val accountCreationRequest =
        new AccountCreationRequest("00011144499", "Mike Tyson", new BigDecimal("1000.00"));
    this.mockMvc
        .perform(
            post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsBytes(accountCreationRequest)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.userId").isNotEmpty())
        .andExpect(jsonPath("$.documentNumber").value(accountCreationRequest.getDocumentNumber()))
        .andExpect(jsonPath("$.name").value(accountCreationRequest.getName()));
  }

  @Test
  void postAccount_shouldNotCreateIfAccountAlreadyExists() throws Exception {
    val accountCreationRequest =
        new AccountCreationRequest(
            "12345678910", "Ronnie James Padovana", new BigDecimal("1000.00"));
    this.mockMvc
        .perform(
            post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsBytes(accountCreationRequest)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.userId").isNotEmpty())
        .andExpect(jsonPath("$.documentNumber").value(accountCreationRequest.getDocumentNumber()))
        .andExpect(jsonPath("$.name").value(accountCreationRequest.getName()));

    val mvcResult =
        this.mockMvc
            .perform(
                post("/accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsBytes(accountCreationRequest)))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andReturn();

    assertEquals(
        "Account already exists for document number: 12345678910",
        Objects.requireNonNull(mvcResult.getResolvedException()).getMessage());
  }
}
