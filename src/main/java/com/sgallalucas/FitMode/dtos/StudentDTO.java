package com.sgallalucas.FitMode.dtos;

import java.util.UUID;

public record StudentDTO(

        UUID id,
        String name,
        String email
) {
}
