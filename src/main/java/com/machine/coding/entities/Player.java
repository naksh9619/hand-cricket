package com.machine.coding.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Player {

    private String playerId = UUID.randomUUID().toString();

    private String playerName;
}
