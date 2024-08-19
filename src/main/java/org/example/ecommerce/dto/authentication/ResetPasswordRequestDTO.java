package org.example.ecommerce.dto.authentication;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResetPasswordRequestDTO(
    @NonNull
    @NotBlank
    String token,

    @NonNull
    @NotBlank
    @Size(min = 8, max = 32)
    String password
) {}
