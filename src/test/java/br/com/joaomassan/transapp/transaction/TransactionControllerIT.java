package br.com.joaomassan.transapp.transaction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerIT {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void postTransaction_shouldCreateANewTransaction() throws Exception {
    val transactionCreationRequest =
        new TransactionCreationRequest(1L, 1L, new BigDecimal("201.77"));
    this.mockMvc
        .perform(
            post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(transactionCreationRequest)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.account.userId").value(1L))
        .andExpect(jsonPath("$.operationType.id").value(1L))
        .andExpect(jsonPath("$.amount").value(new BigDecimal("201.77")))
        .andExpect(jsonPath("$.eventDate").isNotEmpty());
  }

  @Test
  void postTransaction_shouldNotCreateANewTransactionWithInvalidOperationType() throws Exception {
    val transactionCreationRequest =
        new TransactionCreationRequest(1L, 129L, new BigDecimal("201.77"));
    this.mockMvc
        .perform(
            post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(transactionCreationRequest)))
        .andExpect(status().isBadRequest());
  }
}
