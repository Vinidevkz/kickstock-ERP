package com.erp.kicksotck.responsedtos;

import jakarta.validation.constraints.NotBlank;

public record ContratoResponseDTO(
        @NotBlank(message = "O campo nome_")
) {}
